package servent.message;

import java.io.Serial;

public class NewNodeJoinMessage extends BasicMessage {

    @Serial
    private static final long serialVersionUID = -4381135751918243813L;

    public NewNodeJoinMessage(int senderPort, int receiverPort) {
        super(MessageType.NEW_NODE_JOIN, senderPort, receiverPort);
    }

}
