package servent.handler;

import app.AppConfig;
import app.ChordState;
import app.MyFile;
import app.ServentInfo;
import servent.message.AddFileMessage;
import servent.message.Message;
import servent.message.MessageType;
import servent.message.RemoveFileMessage;
import servent.message.util.MessageUtil;

public class RemoveFileHandler implements MessageHandler {

    private Message clientMessage;

    public RemoveFileHandler(Message clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        if (clientMessage.getMessageType() == MessageType.REMOVE_FILE) {
            RemoveFileMessage removeFileMessage = (RemoveFileMessage) clientMessage;
            AppConfig.chordState.removeFile(removeFileMessage.getMyFile());

        } else {
            AppConfig.timestampedErrorPrint("Remove file handler got a message that is not REMOVE_FILE");
        }
    }
}
