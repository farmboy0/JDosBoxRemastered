package jdos.win.system;

import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

import jdos.win.utils.FilePath;

public class WinMidi extends WinMCI {
    private FilePath file;
    private Sequence sequence;
    private Sequencer sequencer;

    public WinMidi(int id) {
        super(id);
    }

    public static WinMidi create() {
        return new WinMidi(nextObjectId());
    }

    @Override
    public void play(int from, int to, int hWndCallback, boolean wait) {
        hWnd = hWndCallback;
        sequencer.start();
    }

    @Override
    public void stop(int hWndCallback, boolean wait) {
        if (sequencer != null)
            sequencer.stop();
        hWnd = hWndCallback;
        if (hWnd != 0)
            sendNotification(MCI_NOTIFY_SUCCESSFUL);
    }

    @Override
    public void close(int hWndCallback, boolean wait) {
        if (sequencer != null)
            sequencer.close();
        hWnd = hWndCallback;
        if (hWnd != 0)
            sendNotification(MCI_NOTIFY_SUCCESSFUL);
    }

    public boolean setFile(FilePath file) {
        this.file = file;
        try {
            sequence = MidiSystem.getSequence(file.getInputStream());
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.setSequence(sequence);
            sequencer.addMetaEventListener(meta -> {
                if (meta.getType() == 47) {
                    if (hWnd != 0)
                        sendNotification(MCI_NOTIFY_SUCCESSFUL);
                }
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
