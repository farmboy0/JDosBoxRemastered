package jdos.gui;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.DataBufferUShort;
import java.awt.image.IndexColorModel;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

import jdos.ints.Mouse;

public class Main extends MainBase {
    public static KeyboardHandler defaultKeyboardHandler;
    public static MouseHandler defaultMouseHandler;
    static byte[][] byte_rawImageData2 = new byte[2][];
    static short[][] short_rawImageData2 = new short[2][];
    static int[][] int_rawImageData2 = new int[2][];
    static int pitch;
    static int screen_width;
    static int screen_height;
    static int buffer_width;
    static int buffer_height;
    static IndexColorModel colorModel;
    static int[] cmap = new int[256];
    static BufferedImage[] buffer2 = new BufferedImage[2];
    static int front = 0;
    static int back = 1;
    private static Point lastMouse = new Point();
    private static int skipCount = 0;

    static {
        int i = 0;
        for (int r = 0; r < 256; r += 51) {
            for (int g = 0; g < 256; g += 51) {
                for (int b = 0; b < 256; b += 51) {
                    cmap[i++] = r << 16 | g << 8 | b;
                }
            }
        }
        // And populate the rest of the cmap with gray values
        int grayIncr = 256 / (256 - i);

        // The gray ramp will be between 18 and 252
        int gray = grayIncr * 3;
        for (; i < 256; i++) {
            cmap[i] = gray << 16 | gray << 8 | gray;
            gray += grayIncr;
        }
    }

    public static void GFX_SetCursor(Cursor cursor) {

    }

    public static void GFX_Events() {
        while (events.size() > 0) {
            Object event = events.elementAt(0);
            events.removeElementAt(0);
            if (event == null)
                throw new ShutdownException();
            if (event instanceof NativeKeyEvent keyEvent)
                handle(keyEvent);
            else if (event instanceof FocusChangeEvent focusEvent)
                handle(focusEvent);
            else if (event instanceof MouseEvent mouseEvent)
                handle(mouseEvent);
        }
    }

    private static void handle(MouseEvent event) {
        if (event.getID() == MouseEvent.MOUSE_MOVED || event.getID() == MouseEvent.MOUSE_DRAGGED) {
            // :TODO: test relative mouse with lucas arts games (indy3, indy4)
            if (mouse_locked || !mouse_autoenable) {
                if (event instanceof MouseEvent2) {
                    MouseEvent2 event2 = (MouseEvent2) event;
                    Mouse.Mouse_CursorMoved(event2.rel_x * mouse_sensitivity / 100.0f,
                        event2.rel_y * mouse_sensitivity / 100.0f, event2.abs_x, event2.abs_y, mouse_locked);
                } else {
                    Mouse.Mouse_CursorMoved((event.getPoint().x - lastMouse.x) * mouse_sensitivity / 100.0f,
                        (event.getPoint().y - lastMouse.y) * mouse_sensitivity / 100.0f,
                        (float) event.getPoint().x / (screen_width - 1) * mouse_sensitivity / 100.0f,
                        (float) event.getPoint().y / (screen_height - 1) * mouse_sensitivity / 100.0f, mouse_locked);
                    lastMouse = event.getPoint();
                }
            }
        } else if (event.getID() == MouseEvent.MOUSE_PRESSED) {
            if (mouse_requestlock && !mouse_locked) {
                GFX_CaptureMouse();
                // Dont pass klick to mouse handler
                return;
            }
            if (!mouse_autoenable && mouse_autolock && event.getButton() == MouseEvent.BUTTON3) {
                GFX_CaptureMouse();
                return;
            }
            if (event.getButton() == MouseEvent.BUTTON1) {
                Mouse.Mouse_ButtonPressed(0);
            } else if (event.getButton() == MouseEvent.BUTTON2) {
                Mouse.Mouse_ButtonPressed(2);
            } else if (event.getButton() == MouseEvent.BUTTON3) {
                Mouse.Mouse_ButtonPressed(1);
            }
        } else if (event.getID() == MouseEvent.MOUSE_RELEASED) {
            if (event.getButton() == MouseEvent.BUTTON1) {
                Mouse.Mouse_ButtonReleased(0);
            } else if (event.getButton() == MouseEvent.BUTTON2) {
                Mouse.Mouse_ButtonReleased(2);
            } else if (event.getButton() == MouseEvent.BUTTON3) {
                Mouse.Mouse_ButtonReleased(1);
            }
        }
    }

    public static void addKeyEvent(NativeKeyEvent key) {
        if (defaultKeyboardHandler != null)
            defaultKeyboardHandler.handle(key);
        else {
            if (paused) {
                if (key.getKeyCode() == NativeKeyEvent.VC_PAUSE && key.getID() == NativeKeyEvent.NATIVE_KEY_PRESSED) {
                    synchronized (pauseMutex) {
                        try {
                            pauseMutex.notify();
                        } catch (Exception e) {
                        }
                    }
                }
            } else {
                // what about allowing the key release for the keys that where down when we paused
                events.add(key);
            }
        }
    }

    public static void addMouseEvent(MouseEvent event) {
        if (defaultMouseHandler != null)
            defaultMouseHandler.handle(event);
        else
            events.add(event);
    }

    public static void handle(NativeKeyEvent key) {
        KeyboardKey.CheckEvent(key);
    }

    public static void GFX_EndUpdate() {
        if (pitch != 0) {
            if (startupTime != 0) {
                System.out.println("Startup time: " + (System.currentTimeMillis() - startupTime) + "ms");
                startupTime = 0;
            }
            long startTime = 0;
            if (Render.render.frameskip.auto)
                startTime = System.currentTimeMillis();
            synchronized (paintMutex) {
                if (Render.render.frameskip.auto) {
                    long diff = System.currentTimeMillis() - startTime;
                    if (diff > 5) {
                        skipCount = 0;
                        Render.render.frameskip.max++;
                        MainBase.GFX_SetTitle(-1, Render.render.frameskip.max, false);
                    } else if (diff < 2 && Render.render.frameskip.max > 0) {
                        skipCount++;
                        if (skipCount > 100) {
                            skipCount = 0;
                            Render.render.frameskip.max--;
                            MainBase.GFX_SetTitle(-1, Render.render.frameskip.max, false);
                        }
                    }
                }
            }
            if (front == 0) {
                front = 1;
                back = 0;
            } else {
                front = 0;
                back = 1;
            }
            gui.dopaint();
        }
    }

    public static boolean GFX_StartUpdate(Render.Render_t.SRC src) {
        src.outPitch = Main.pitch;
        src.outWrite8 = byte_rawImageData2[Main.back];
        src.outWrite16 = short_rawImageData2[Main.back];
        src.outWrite32 = int_rawImageData2[Main.back];
        return true;
    }

    public static void drawImage(Image image) {
        Graphics graphics = buffer2[front].getGraphics();
        graphics.drawImage(image, 0, 0, null);
        gui.dopaint();
    }

    public static /*Bitu*/void GFX_SetSize(int screenWidth, int screenHeight, int width, int height, boolean aspect,
        int bpp) {
        buffer_width = width;
        buffer_height = height;
        screen_height = screenHeight;
        screen_width = screenWidth;
        if (Render.render.aspect) {
            screen_height = screen_width * 3 / 4;
        }
        gui.setSize(screen_width, screen_height);
        updateBuffers(bpp);
    }

    private static void updateBuffers(int bpp) {
        switch (bpp) {
            case 8: {
                colorModel = new IndexColorModel(8, 256, cmap, 0, false, -1, DataBuffer.TYPE_BYTE);
                buffer2[0] = new BufferedImage(buffer_width, buffer_height, BufferedImage.TYPE_BYTE_INDEXED,
                    colorModel);
                buffer2[1] = new BufferedImage(buffer_width, buffer_height, BufferedImage.TYPE_BYTE_INDEXED,
                    colorModel);
                DataBufferByte buf = (DataBufferByte) buffer2[0].getRaster().getDataBuffer();
                byte_rawImageData2[0] = buf.getData();
                buf = (DataBufferByte) buffer2[1].getRaster().getDataBuffer();
                byte_rawImageData2[1] = buf.getData();
                pitch = buffer_width;
                short_rawImageData2[0] = null;
                short_rawImageData2[1] = null;
                int_rawImageData2[0] = null;
                int_rawImageData2[1] = null;
            }
                break;
            case 15: {
                buffer2[0] = new BufferedImage(buffer_width, buffer_height, BufferedImage.TYPE_USHORT_555_RGB);
                buffer2[1] = new BufferedImage(buffer_width, buffer_height, BufferedImage.TYPE_USHORT_555_RGB);
                DataBufferUShort buf = (DataBufferUShort) buffer2[0].getRaster().getDataBuffer();
                short_rawImageData2[0] = buf.getData();
                buf = (DataBufferUShort) buffer2[1].getRaster().getDataBuffer();
                short_rawImageData2[1] = buf.getData();
                pitch = buffer_width * 2;
                byte_rawImageData2[0] = null;
                byte_rawImageData2[1] = null;
                int_rawImageData2[0] = null;
                int_rawImageData2[1] = null;
            }
                break;
            case 16: {
                buffer2[0] = new BufferedImage(buffer_width, buffer_height, BufferedImage.TYPE_USHORT_565_RGB);
                buffer2[1] = new BufferedImage(buffer_width, buffer_height, BufferedImage.TYPE_USHORT_565_RGB);
                DataBufferUShort buf = (DataBufferUShort) buffer2[0].getRaster().getDataBuffer();
                short_rawImageData2[0] = buf.getData();
                buf = (DataBufferUShort) buffer2[1].getRaster().getDataBuffer();
                short_rawImageData2[1] = buf.getData();
                pitch = buffer_width * 2;
                byte_rawImageData2[0] = null;
                byte_rawImageData2[1] = null;
                int_rawImageData2[0] = null;
                int_rawImageData2[1] = null;
            }
                break;
            case 32: {
                buffer2[0] = new BufferedImage(buffer_width, buffer_height, BufferedImage.TYPE_INT_RGB);
                buffer2[1] = new BufferedImage(buffer_width, buffer_height, BufferedImage.TYPE_INT_RGB);
                DataBufferInt buf = (DataBufferInt) buffer2[0].getRaster().getDataBuffer();
                int_rawImageData2[0] = buf.getData();
                buf = (DataBufferInt) buffer2[1].getRaster().getDataBuffer();
                int_rawImageData2[1] = buf.getData();
                pitch = buffer_width * 4;
                byte_rawImageData2[0] = null;
                byte_rawImageData2[1] = null;
                short_rawImageData2[0] = null;
                short_rawImageData2[1] = null;
            }
                break;
        }
    }

    public static void GFX_SetPalette(/*Bitu*/int start, /*Bitu*/int count, Render.RenderPal_t.RGB[] entries,
        int entriesOffset, int bpp) {
        for (int i = start; i < start + count; i++) {
            cmap[i] = GFX_GetRGB(entries[i].red, entries[i].green, entries[i].blue);
        }
        updateBuffers(bpp);
    }

    public static void GFX_SetPalette(int[] entries, int count) {
        System.arraycopy(entries, 0, cmap, 0, count);
        if (Render.render.src.bpp == 8)
            updateBuffers(Render.render.src.bpp);
    }

    public interface KeyboardHandler {
        void handle(NativeKeyEvent key);
    }

    public interface MouseHandler {
        void handle(MouseEvent event);
    }

    public static class MouseEvent2 extends MouseEvent {
        public int rel_x;
        public int rel_y;
        public float abs_x;
        public float abs_y;

        public MouseEvent2(MouseEvent event, int rel_x, int rel_y, float abs_x, float abs_y, int offX, int offY) {
            super(event.getComponent(), event.getID(), event.getWhen(), event.getModifiers(), event.getX() - offX,
                event.getY() - offY, event.getClickCount(), event.isPopupTrigger());
            this.rel_x = rel_x;
            this.rel_y = rel_y;
            this.abs_x = abs_x;
            this.abs_y = abs_y;
        }
    }

    public static class MouseEvent1 extends MouseEvent {
        public MouseEvent1(MouseEvent event, int offX, int offY) {
            super(event.getComponent(), event.getID(), event.getWhen(), event.getModifiers(), event.getX() - offX,
                event.getY() - offY, event.getClickCount(), event.isPopupTrigger());
        }
    }
}
