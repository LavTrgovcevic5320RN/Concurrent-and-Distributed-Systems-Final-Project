package servent.message;

import app.MyFile;

public class AddFileMessage extends BasicMessage {
    private static final long serialVersionUID = -8558031124520315033L;
    private final String requesterIpAddress;
    private final int requesterPort;
    private final MyFile myFile;

    public AddFileMessage(int senderPort, int receiverPort, String text, int requesterPort, String requesterIpAddress, MyFile myFile) {
        super(MessageType.ADD_FILE, senderPort, receiverPort, text);
        this.requesterIpAddress = requesterIpAddress;
        this.requesterPort = requesterPort;
        this.myFile = myFile;
    }

    public String getRequesterIpAddress() {
        return requesterIpAddress;
    }

    public int getRequesterPort() {
        return requesterPort;
    }

    public MyFile getMyFile() {
        return myFile;
    }
}
