package servent.message;

import app.ServentInfo;

public class TokenMessage extends BasicMessage {

    private static final long serialVersionUID = 2084490973699262440L;

    public TokenMessage(int senderPort, int receiverPort) {
        super(MessageType.TOKEN, senderPort, receiverPort);
    }
}
