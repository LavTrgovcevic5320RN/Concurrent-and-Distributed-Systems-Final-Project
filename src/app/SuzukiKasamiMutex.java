package app;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class SuzukiKasamiMutex {
    private int siteId;  // ID trenutnog sajta
    private int numSites; // Ukupan broj sajtova u sistemu
    private int[] RN; // Niz za čuvanje brojeva sekvenci za sve sajtove
    private Token token; // Token koji koristi sistem
    private boolean hasToken; // Oznaka da li trenutni sajt poseduje token
    private Queue<Integer> requestQueue; // Red čekanja za zahteve za token

    public SuzukiKasamiMutex(int siteId, int numSites) {
        this.siteId = siteId;
        this.numSites = numSites;
        this.RN = new int[numSites];
        this.token = null;
        this.hasToken = false;
        this.requestQueue = new LinkedList<>();
        Arrays.fill(RN, 0); // Inicijalizacija brojeva sekvenci sa 0
    }

    // Klasa Token koja sadrži red čekanja i niz sekvenci
    private static class Token {
        private Queue<Integer> Q;
        private int[] LN;

        public Token(int numSites) {
            this.Q = new LinkedList<>();
            this.LN = new int[numSites];
            Arrays.fill(LN, 0); // Inicijalizacija LN sa 0
        }
    }

    // Metoda za slanje zahteva za token (REQUEST poruke)
    public void requestCriticalSection() {
        RN[siteId]++; // Inkrementiraj broj sekvence za trenutni sajt
        if (hasToken) {
            // Ako već poseduje token, može odmah da uđe u kritičnu sekciju
            enterCriticalSection();
        } else {
            // Šalje REQUEST poruke svim ostalim sajtovima
            broadcastRequest(siteId, RN[siteId]);
        }
    }

    // Metoda za primanje REQUEST poruka od drugih sajtova
    public void receiveRequest(int requestingSite, int sequenceNumber) {
        RN[requestingSite] = Math.max(RN[requestingSite], sequenceNumber);
        if (hasToken && RN[requestingSite] == token.LN[requestingSite] + 1) {
            // Ako poseduje token i zahtevi se podudaraju, šalje token
            sendToken(requestingSite);
        }
    }

    // Metoda za ulazak u kritičnu sekciju
    private void enterCriticalSection() {
        System.out.println("Site " + siteId + " is entering critical section.");
        // Izvršavanje kritične sekcije
        // Simulacija rada u kritičnoj sekciji
        try {
            Thread.sleep(1000); // Simulacija vremena provedenog u kritičnoj sekciji
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        exitCriticalSection();
    }

    // Metoda za izlazak iz kritične sekcije
    private void exitCriticalSection() {
        System.out.println("Site " + siteId + " is exiting critical section.");
        token.LN[siteId] = RN[siteId]; // Ažuriranje LN niza u tokenu
        for (int i = 0; i < numSites; i++) {
            if (RN[i] == token.LN[i] + 1 && !token.Q.contains(i)) {
                token.Q.add(i);
            }
        }
        if (!token.Q.isEmpty()) {
            int nextSite = token.Q.poll();
            sendToken(nextSite);
        }
    }

    // Metoda za slanje tokena drugom sajtu
    private void sendToken(int site) {
        System.out.println("Sending token from site " + siteId + " to site " + site);
        hasToken = false;
        // Simulacija slanja tokena drugom sajtu
        // U stvarnosti, ovo bi uključivalo mrežnu komunikaciju
        // Ova simulacija će jednostavno direktno pozvati receiveToken metodu na drugom sajtu
        sites[site].receiveToken(token);
    }

    // Metoda za primanje tokena
    public void receiveToken(Token token) {
        System.out.println("Site " + siteId + " received the token.");
        this.token = token;
        this.hasToken = true;
        enterCriticalSection();
    }

    // Metoda za brodkastovanje zahteva za tokenom
    private void broadcastRequest(int siteId, int sequenceNumber) {
        System.out.println("Broadcasting REQUEST(" + siteId + ", " + sequenceNumber + ")");
        // Simulacija brodkastovanja poruke svim sajtovima
        // U stvarnosti, ovo bi uključivalo mrežnu komunikaciju
        for (SuzukiKasamiMutex site : sites) {
            if (site.siteId != this.siteId) {
                site.receiveRequest(siteId, sequenceNumber);
            }
        }
    }

    // Staticko polje za držanje svih sajtova u sistemu
    private static SuzukiKasamiMutex[] sites;

    public static void main(String[] args) {
        int numSites = 5; // Broj sajtova u sistemu
        sites = new SuzukiKasamiMutex[numSites];
        for (int i = 0; i < numSites; i++) {
            sites[i] = new SuzukiKasamiMutex(i, numSites);
        }

        // Inicijalno, sajt 0 poseduje token
        sites[0].hasToken = true;
        sites[0].token = new Token(numSites);

        // Simulacija zahteva za kritičnu sekciju od sajtova
        sites[1].requestCriticalSection();
        sites[2].requestCriticalSection();
        sites[3].requestCriticalSection();
        sites[4].requestCriticalSection();
    }
}

