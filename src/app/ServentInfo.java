package app;

import java.io.Serializable;
import java.util.Objects;

/**
 * This is an immutable class that holds all the information for a servent.
 *
 * @author bmilojkovic
 */
public class ServentInfo implements Serializable {

    private static final long serialVersionUID = 5304170042791281555L;
    private final String ipAddress;
    private final int listenerPort;
    private final int chordId;

    public ServentInfo(String ipAddress, int listenerPort) {
        this.ipAddress = ipAddress;
        this.listenerPort = listenerPort;
        this.chordId = ChordState.chordHash(ipAddress + ":" + listenerPort);
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public int getListenerPort() {
        return listenerPort;
    }

    public int getChordId() {
        return chordId;
    }

    @Override
    public String toString() {
        return "[" + chordId + "|" + ipAddress + "|" + listenerPort + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServentInfo that)) return false;
        return listenerPort == that.listenerPort && Objects.equals(ipAddress, that.ipAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ipAddress, listenerPort);
    }
}
