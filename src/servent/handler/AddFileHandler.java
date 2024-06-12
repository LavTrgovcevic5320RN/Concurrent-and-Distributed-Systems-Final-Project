package servent.handler;

import app.AppConfig;
import app.ChordState;
import app.MyFile;
import app.ServentInfo;
import servent.message.AddFileMessage;
import servent.message.Message;
import servent.message.MessageType;
import servent.message.util.MessageUtil;

public class AddFileHandler implements MessageHandler {

    private Message clientMessage;

    public AddFileHandler(Message clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        if (clientMessage.getMessageType() == MessageType.ADD_FILE) {
            AddFileMessage addFileMessage = (AddFileMessage) clientMessage;

            int key = ChordState.chordHash(addFileMessage.getReceiverIpAddress() + ":" + addFileMessage.getReceiverPort());

            if (key == AppConfig.myServentInfo.getChordId()) {
                MyFile myFile = addFileMessage.getMyFile();
                AppConfig.chordState.addFile(myFile, "localhost", addFileMessage.getSenderPort());
            }
            else {
                ServentInfo nextNode = AppConfig.chordState.getNextNodeForKey(key);
                Message newAddFileMessage = new AddFileMessage(
                        addFileMessage.getSenderPort(),
                        nextNode.getListenerPort(),
                        "",
                        addFileMessage.getRequesterPort(),
                        addFileMessage.getRequesterIpAddress(),
                        addFileMessage.getMyFile());
                MessageUtil.sendMessage(newAddFileMessage);
            }
        } else {
            AppConfig.timestampedErrorPrint("Add file handler got a message that is not ADD_FILE");
        }
    }
}
