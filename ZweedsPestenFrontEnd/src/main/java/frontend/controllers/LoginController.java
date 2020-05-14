package frontend.controllers;

import frontend.AlertBox;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import websocketclient.interfaces.IGameClient;
import websocketclient.interfaces.ILoginClientGUI;

public class LoginController extends BaseController implements ILoginClientGUI {

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private TextField tfUsername;

    @FXML
    private Button btnRegister;

    public LoginController(IGameClient gameClient) {
        super(gameClient);
        getGameClient().registerLoginClientGUI(this);
    }

    @FXML
    private void processBtnRegisterClicked(ActionEvent event) {
        loadFxml("Registreren", "/register.fxml");
    }

    @FXML
    private void processBtnLoginClicked(ActionEvent event) {
        if(("").equals(pfPassword.getText()) || ("").equals(tfUsername.getText())) {
            Platform.runLater(() -> AlertBox.display("Invalide gegegevens", "Vul alle gegevens in!"));
        }
        else {
            getGameClient().loginPlayer(tfUsername.getText(), pfPassword.getText());
        }

    }

    @Override
    public void processLoginResponse(String token, String playerName) {
        Platform.runLater(() -> {
            if(token == null || "".equals(token))
                AlertBox.display("Gefaald", "Incorrect gebruikersnaam en/of wachtwoord.");
            else {
                loadFxml("Zweeds Pesten", "/game.fxml");
                getGameClient().getAllPlayerNames();
                setPlayerName(playerName);
            }
        });

    }
}
