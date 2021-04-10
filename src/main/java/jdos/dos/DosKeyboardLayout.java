package jdos.dos;

import jdos.misc.setup.Module_base;
import jdos.misc.setup.Section;

public class DosKeyboardLayout extends Module_base {
    public static Section.SectionFunction DOS_KeyboardLayout_Init = new Section.SectionFunction() {
        public void call(Section section) {
        }
    };

    public DosKeyboardLayout(Section configuration) {
        super(configuration);
    }
}
