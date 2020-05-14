package messages.client;

public class ReadyMessage {
    private boolean ready;

    public boolean isReady() {
        return ready;
    }

    public ReadyMessage(boolean ready) {
        this.ready = ready;
    }
}
