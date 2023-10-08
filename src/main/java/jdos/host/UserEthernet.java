package jdos.host;

import java.util.LinkedList;

import jdos.host.router.EtherUtil;
import jdos.misc.setup.Section_prop;
import jdos.util.Ptr;

public class UserEthernet extends EtherUtil implements Ethernet {

    @Override
    public void send(byte[] buffer, int offset, int len) {
        //dump(buffer, offset, len);
        ether.handle(buffer, offset, len);
    }

    @Override
    public void receive(RxFrame frame) {
        if (frames.size() > 0) {
            byte[] data = (byte[]) frames.removeFirst();
            //dump(data, 0, data.length);
            frame.rx_frame(new Ptr(data, 0), data.length);
        }
    }

    @Override
    public boolean open(Section_prop section, byte[] mac) {
        frames = new LinkedList();
        return true;
    }

    @Override
    public void close() {
    }
}
