package messages.client;

public class LoginMessage {
    private String playerName;
    private String password;

    public LoginMessage(String name, String password)
    {
        this.playerName = name;
        this.password = password;
    }

    public String getPassword() { return password; }

    public String getPlayerName(){
        return playerName;
    }
}
