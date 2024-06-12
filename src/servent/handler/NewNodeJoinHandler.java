package servent.handler;

import app.AppConfig;
import app.TokenMutex;
import servent.message.Message;
import servent.message.MessageType;

public class NewNodeJoinHandler implements MessageHandler {

    private final Message clientMessage;

    public NewNodeJoinHandler(Message clientMessage) { this.clientMessage = clientMessage; }

    @Override
    public void run() {

        if (clientMessage.getMessageType() == MessageType.NEW_NODE_JOIN) {
            TokenMutex.unlock();
        } else {
            AppConfig.timestampedErrorPrint("New Node Join handler got message that is not NEW_NODE_JOIN.");
        }

    }

}
