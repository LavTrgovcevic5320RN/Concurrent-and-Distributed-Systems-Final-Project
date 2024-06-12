package servent.message;

import app.MyFile;

public class RemoveFileMessage extends BasicMessage {
    private static final long serialVersionUID = -8558031734527395033L;
    private final MyFile myFile;

    public RemoveFileMessage(int senderPort, int receiverPort, String text, MyFile myFile) {
        super(MessageType.REMOVE_FILE, senderPort, receiverPort, text);
        this.myFile = myFile;
    }

    public MyFile getMyFile() {
        return myFile;
    }
//    private final String requesterIpAddress;
//    private final int requesterPort;
//    private final MyFile myFile;
//
//    public RemoveFileMessage(int senderPort, int receiverPort, String text, int requesterPort, String requesterIpAddress, MyFile myFile) {
//        super(MessageType.ADD_FILE, senderPort, receiverPort, text);
//        this.requesterIpAddress = requesterIpAddress;
//        this.requesterPort = requesterPort;
//        this.myFile = myFile;
//    }
//
//    public String getRequesterIpAddress() {
//        return requesterIpAddress;
//    }
//
//    public int getRequesterPort() {
//        return requesterPort;
//    }
//
//    public MyFile getMyFile() {
//        return myFile;
//    }
}
