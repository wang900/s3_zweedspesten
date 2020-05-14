package messages.client;

public class DrawFromDeckMessage {
    private int amount;

    public DrawFromDeckMessage(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
