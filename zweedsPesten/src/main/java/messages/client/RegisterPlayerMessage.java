package messages.client;

public class RegisterPlayerMessage {
    private String playerName;
    private String password;

    public RegisterPlayerMessage(String name, String password)
    {
        this.playerName = name;
        this.password = password;
    }

    public String getPassword() { return password; }

    public String getPlayerName(){
        return playerName;
    }
}
