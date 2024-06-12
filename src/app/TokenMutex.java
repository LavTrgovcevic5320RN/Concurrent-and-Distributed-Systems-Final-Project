package app;

import servent.message.Message;
import servent.message.TokenMessage;
import servent.message.util.MessageUtil;

public class TokenMutex{

    private static volatile boolean haveToken = false;
    private static volatile boolean wantLock = false;

    public static void lock() {
        wantLock = true;

        while (!haveToken) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void initialiseToken() {
        haveToken = true;
    }

    public static void unlock() {
        haveToken = false;
        wantLock = false;
        sendTokenForward();
    }

    public static void receiveToken() {
        if (wantLock) {
            haveToken = true;
        } else {
            sendTokenForward();
        }
    }

    public static void sendTokenForward() {
        int nextNodePort = AppConfig.chordState.getNextNodePort();

        Message tokenMessage = new TokenMessage(AppConfig.myServentInfo.getListenerPort(), nextNodePort);
        MessageUtil.sendMessage(tokenMessage);
    }

}
