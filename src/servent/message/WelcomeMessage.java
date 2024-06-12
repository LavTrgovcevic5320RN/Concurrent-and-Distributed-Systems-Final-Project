package servent.message;

import app.ServentInfo;

import java.util.List;
import java.util.Map;

public class WelcomeMessage extends BasicMessage {

    private static final long serialVersionUID = -8981406250652693908L;

    private List<ServentInfo> friends;

    public WelcomeMessage(int senderPort, int receiverPort, List<ServentInfo> friends) {
        super(MessageType.WELCOME, senderPort, receiverPort);

        this.friends = friends;
    }

    public List<ServentInfo> getFriends() {
        return friends;
    }

//        private Map<Integer, Integer> values;
//
//
//    public WelcomeMessage(int senderPort, int receiverPort, Map<Integer, Integer> values) {
//        super(MessageType.WELCOME, senderPort, receiverPort);
//
//        this.values = values;
//    }
//
//    public Map<Integer, Integer> getValues() {
//        return values;
//    }
}
