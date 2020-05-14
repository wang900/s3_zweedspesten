package frontend.controllers;

import frontend.AlertBox;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import logging.Logger;
import websocketclient.interfaces.IRegisterClientGUI;
import websocketclient.interfaces.IGameClient;

import java.io.IOException;

public class RegisterController extends BaseController implements IRegisterClientGUI {

    @FXML
    Button btnRegister;

    @FXML
    Button btnBack;

    @FXML
    TextField tfUsername;

    @FXML
    PasswordField pfPassword;

    @FXML
    PasswordField pfPasswordConfirmation;

    public RegisterController(IGameClient gameClient) {
        super(gameClient);
        getGameClient().registerRegisterClientGUI(this);
    }

    @FXML
    private void processBtnRegisterClicked(ActionEvent event) throws IOException {
        String userName = tfUsername.getText();
        String password = pfPassword.getText();
        String confirmation = pfPasswordConfirmation.getText();
        if(("").equals(userName) || ("").equals(password) || ("").equals(confirmation))
        {
            Platform.runLater(() -> AlertBox.display("Invalide gegegevens", "Vul alle gegevens in!"));
        }
        else if(password.equals(confirmation)) {
            getGameClient().registerPlayer(userName, password);
        }
        else {
            Platform.runLater(() -> AlertBox.display("Invalide gegevens", "De gegeven wachtwoorden komen niet overeen!"));
        }
    }

    @FXML
    private void processBtnBackClicked(ActionEvent event) throws  IOException {
        ControllerHelper.getInstance().loadFxml("Zweeds Pesten", "/login.fxml");
    }

    @Override
    public void processRegistrationResponse(boolean resp) {
        if(resp) {
            Platform.runLater(() -> AlertBox.display("Succes", "Je bent nu geregistreerd!"));
            Platform.runLater(() -> {
                try {
                    ControllerHelper.getInstance().loadFxml("Login", "/login.fxml");
                } catch (IOException e) {
                    Logger.getInstance().log(e);
                }
            });
        }
        else {
            Platform.runLater(() -> AlertBox.display("Gefaald", "Kon niet registreren, probeer een ander gebruikersnaam"));
        }
    }

    @Override
    public void processPlayerRegistered(String playerName) {
    }
}
