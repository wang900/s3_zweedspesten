package messages.server;

public class RoundResultMessage {

    private String winner;

    public String getWinner() {
        return winner;
    }


    public RoundResultMessage(String winner)
    {
        this.winner = winner;
    }


}
