package jdos.win.builtin.winmm;

import java.util.Vector;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

import jdos.hardware.Memory;
import jdos.win.Win;
import jdos.win.builtin.WinAPI;
import jdos.win.system.WinObject;
import jdos.win.utils.Ptr;

public class Waveform extends WinAPI {
    public static final int WAVECAPS_PITCH = 0x0001; /* supports pitch control */
    public static final int WAVECAPS_PLAYBACKRATE = 0x0002; /* supports playback rate control */
    public static final int WAVECAPS_VOLUME = 0x0004; /* supports volume control */
    public static final int WAVECAPS_LRVOLUME = 0x0008; /* separate left-right volume control */
    public static final int WAVECAPS_SYNC = 0x0010; /* driver is synchronous and playing is blocking */
    public static final int WAVECAPS_SAMPLEACCURATE = 0x0020; /* position is sample accurate */
    public static final int WAVECAPS_DIRECTSOUND = 0x0040; /* ? */

    //MMRESULT waveOutClose(HWAVEOUT hwo)
    public static int waveOutClose(int hwo) {
        WaveObject obj = WaveObject.get(hwo);
        if (obj == null)
            return WinAPI.MMSYSERR_INVALHANDLE;
        obj.thread.exit = true;
        synchronized (obj.thread.buffers) {
            obj.thread.buffers.notify();
        }
        try {
            obj.thread.join();
        } catch (Exception e) {
        }
        obj.close();
        return WinAPI.MMSYSERR_NOERROR;
    }

    // MMRESULT waveOutGetDevCaps(UINT_PTR uDeviceID, LPWAVEOUTCAPS pwoc, UINT cbwoc)
    public static int waveOutGetDevCapsA(int uDeviceID, int pwoc, int cbwoc) {
        if (pwoc == 0)
            return WinAPI.MMSYSERR_INVALPARAM;

        WAVEOUTCAPS mapper_caps = new WAVEOUTCAPS();
        mapper_caps.wMid = 0xFF;
        mapper_caps.wPid = 0xFF;
        mapper_caps.vDriverVersion = 0x00010001;
        mapper_caps.dwFormats = 0xFFFFFFFF;
        mapper_caps.wReserved1 = 0;
        mapper_caps.dwSupport = WAVECAPS_LRVOLUME | WAVECAPS_VOLUME | WAVECAPS_SAMPLEACCURATE;
        mapper_caps.wChannels = 2;
        mapper_caps.szPname = "Wine Sound Mapper";
        mapper_caps.write(pwoc);
        return WinAPI.MMSYSERR_NOERROR;
    }

    //MMRESULT waveOutOpen(LPHWAVEOUT phwo, UINT_PTR uDeviceID, LPWAVEFORMATEX pwfx, DWORD_PTR dwCallback, DWORD_PTR dwCallbackInstance, DWORD fdwOpen)
    public static int waveOutOpen(int lphWaveOut, int uDeviceID, int pwfx, int dwCallback, int dwCallbackInstance,
        int fdwOpen) {
//        WINMM_OpenInfo info;
//        WINMM_CBInfo cb_info;
//
//        if(!WINMM_StartDevicesThread())
//            return MMSYSERR_ERROR;

        if (lphWaveOut == 0 && (fdwOpen & WinAPI.WAVE_FORMAT_QUERY) == 0)
            return WinAPI.MMSYSERR_INVALPARAM;

        int res = WinMM.WINMM_CheckCallback(dwCallback, fdwOpen, false);
        if (res != WinAPI.MMSYSERR_NOERROR)
            return res;

        if (fdwOpen != 0)
            Win.panic("WinMM.waveOutOpen fdwOpen=" + Ptr.toString(fdwOpen) + " not supported yet");
        if (uDeviceID != WinAPI.WAVE_MAPPER)
            Win.panic("WinMM.waveOutOpen uDeviceID=" + uDeviceID + " not supported yet");

        writed(lphWaveOut, WaveObject.create(new WAVEFORMATEX(pwfx)).handle);

//        info.format = (WAVEFORMATEX*)lpFormat;
//        info.callback = dwCallback;
//        info.cb_user = dwInstance;
//        info.req_device = uDeviceID;
//        info.flags = dwFlags;
//
//        res = SendMessageW(g_devices_hwnd, WinMM.WODM_OPEN, (DWORD_PTR)&info, 0);
//        if(res != WinMM.MMSYSERR_NOERROR)
//            return res;
//
//        if(lphWaveOut)
//            *lphWaveOut = (HWAVEOUT)info.handle;
//
//        cb_info.flags = HIWORD(fdwOpen & WinMM.CALLBACK_TYPEMASK);
//        cb_info.callback = dwCallback;
//        cb_info.user = dwInstance;
//        cb_info.hwave = info.handle;
//
//        WINMM_NotifyClient(&cb_info, WOM_OPEN, 0, 0);

        return res;
    }

    // MMRESULT waveOutPrepareHeader(HWAVEOUT hwo, LPWAVEHDR pwh, UINT cbwh)
    public static int waveOutPrepareHeader(int hwo, int pwh, int cbwh) {
        if (pwh == 0 || cbwh < WAVEHDR.SIZE)
            return WinAPI.MMSYSERR_INVALPARAM;

        WAVEHDR hdr = new WAVEHDR(pwh);
        if ((hdr.dwFlags & WAVEHDR.WHDR_INQUEUE) != 0)
            return WinAPI.WAVERR_STILLPLAYING;

        WaveObject obj = WaveObject.get(hwo);
        if (obj == null)
            return WinAPI.MMSYSERR_INVALHANDLE;

        hdr.dwFlags |= WAVEHDR.WHDR_PREPARED;
        hdr.dwFlags &= ~WAVEHDR.WHDR_DONE;
        hdr.reserved = pwh;
        hdr.writeFlags();
        return WinAPI.MMSYSERR_NOERROR;
    }

    // MMRESULT waveOutReset(HWAVEOUT hwo)
    public static int waveOutReset(int hwo) {
        WaveObject obj = WaveObject.get(hwo);
        if (obj == null)
            return WinAPI.MMSYSERR_INVALHANDLE;
        obj.thread.reset();
        return WinAPI.MMSYSERR_NOERROR;
    }

    // MMRESULT waveOutUnprepareHeader(HWAVEOUT hwo, LPWAVEHDR pwh, UINT cbwh)
    public static int waveOutUnprepareHeader(int hwo, int pwh, int cbwh) {
        if (pwh == 0 || cbwh < WAVEHDR.SIZE)
            return WinAPI.MMSYSERR_INVALPARAM;

        WaveObject obj = WaveObject.get(hwo);
        if (obj == null)
            return WinAPI.MMSYSERR_INVALHANDLE;

        WAVEHDR hdr = new WAVEHDR(pwh);
        if ((hdr.dwFlags & WAVEHDR.WHDR_INQUEUE) != 0)
            return WinAPI.WAVERR_STILLPLAYING;
        hdr.dwFlags &= ~WAVEHDR.WHDR_PREPARED;
        hdr.dwFlags |= WAVEHDR.WHDR_DONE;
        hdr.writeFlags();
        return WinAPI.MMSYSERR_NOERROR;
    }

    // MMRESULT waveOutWrite(HWAVEOUT hwo, LPWAVEHDR pwh, UINT cbwh)
    public static int waveOutWrite(int hwo, int pwh, int cbwh) {
        if (pwh == 0 || cbwh < WAVEHDR.SIZE)
            return WinAPI.MMSYSERR_INVALPARAM;

        WaveObject obj = WaveObject.get(hwo);
        if (obj == null)
            return WinAPI.MMSYSERR_INVALHANDLE;

        WAVEHDR hdr = new WAVEHDR(pwh);
        if (hdr.lpData == 0 || (hdr.dwFlags & WAVEHDR.WHDR_PREPARED) == 0)
            return WinAPI.WAVERR_UNPREPARED;

        if ((hdr.dwFlags & WAVEHDR.WHDR_INQUEUE) != 0)
            return WinAPI.WAVERR_STILLPLAYING;

        hdr.dwFlags |= WAVEHDR.WHDR_INQUEUE;
        hdr.dwFlags &= ~WAVEHDR.WHDR_DONE;
        hdr.reserved = pwh;
        hdr.writeFlags();

        hdr.data = new byte[hdr.dwBufferLength];
        Memory.mem_memcpy(hdr.data, 0, hdr.lpData, hdr.dwBufferLength);

        if (pwh == 0xb0004afc) {
            int ii = 0;
        }
        synchronized (obj.thread.buffers) {
            obj.thread.buffers.add(hdr);
            obj.thread.buffers.notify();
        }
        return WinAPI.MMSYSERR_NOERROR;
    }

    private static class WaveObject extends WinObject {
        public WaveOutThread thread;

        public WaveObject(int id, WAVEFORMATEX format) {
            super(id);
            thread = new WaveOutThread(format);
            thread.start();
        }

        public static WaveObject create(WAVEFORMATEX format) {
            return new WaveObject(nextObjectId(), format);
        }

        public static WaveObject get(int handle) {
            WinObject object = getObject(handle);
            if (object == null || !(object instanceof WaveObject))
                return null;
            return (WaveObject) object;
        }
    }

    private static class WaveOutThread extends Thread {
        final Vector<WAVEHDR> buffers = new Vector<>();
        WAVEFORMATEX format;
        boolean exit = false;
        SourceDataLine line;

        public WaveOutThread(WAVEFORMATEX format) {
            this.format = format;
            open();
        }

        public boolean open() {
            try {
                AudioFormat af = new AudioFormat(format.nSamplesPerSec, format.wBitsPerSample, format.nChannels, true,
                    false);
                DataLine.Info info = new DataLine.Info(SourceDataLine.class, af);
                line = (SourceDataLine) AudioSystem.getLine(info);
                line.open(af, 8192);
                line.start();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        public void reset() {
            buffers.clear();
        }

        @Override
        public void run() {
            while (!exit) {
                while (buffers.size() > 0) {
                    WAVEHDR hdr = buffers.remove(0);
                    line.write(hdr.data, 0, hdr.data.length);
                    hdr.dwFlags &= ~WAVEHDR.WHDR_INQUEUE;
                    hdr.dwFlags |= WAVEHDR.WHDR_DONE;
                    hdr.writeFlags();
                }
                synchronized (buffers) {
                    if (buffers.size() == 0 && !exit)
                        try {
                            buffers.wait();
                        } catch (Exception e) {
                        }
                }
            }
            line.stop();
            line.close();
            line = null;
        }
    }
}
