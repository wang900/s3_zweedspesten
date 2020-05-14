package messages.server;

public class LoginResultMessage {

    private String token;
    private String playerName;

    public LoginResultMessage(String token, String playerName) {
        this.token = token;
        this.playerName = playerName;
    }

    public String getToken() {
        return token;
    }

    public String getPlayerName() {
        return playerName;
    }
}
