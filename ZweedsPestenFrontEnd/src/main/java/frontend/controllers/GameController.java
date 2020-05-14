package frontend.controllers;

import frontend.AlertBox;
import frontend.CardImageProcessor;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.enums.GameAction;
import model.interfaces.ICard;
import websocketclient.interfaces.IGameClient;
import websocketclient.interfaces.IGameClientGUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameController extends BaseController implements IGameClientGUI {

    @FXML
    private CheckBox cbPlayer1Ready;
    @FXML
    private CheckBox cbPlayer4Ready;
    @FXML
    private CheckBox cbPlayer3Ready;
    @FXML
    private CheckBox cbPlayer2Ready;
    @FXML
    private ImageView ivDeck;
    @FXML
    private ImageView ivPlayedCards;
    @FXML
    private Label lbPlayer4;
    @FXML
    private Label lbPlayer3;
    @FXML
    private Label lbPlayer2;
    @FXML
    private Label lbPlayer1;
    @FXML
    private Button btnSurrender;
    @FXML
    private Button btnEndTurn;
    @FXML
    private Button btnReady;
    @FXML
    private Button btnLogOut;
    @FXML
    private ImageView image;

    private String playerName;
    private int playerAmount;
    private boolean ready = false;
    private int playerNumber;
    private CardImageProcessor cardImageProcessor;
    private List<ICard> selectedCards = new ArrayList<>();
    private List<ImageView> handPlayer1 = new ArrayList<>();
    private List<ImageView> handPlayer2 = new ArrayList<>();
    private List<ImageView> handPlayer3 = new ArrayList<>();
    private List<ImageView> handPlayer4 = new ArrayList<>();
    private List<ImageView> extraHandPlayer1 = new ArrayList<>();
    private List<ImageView> extraHandPlayer2 = new ArrayList<>();
    private List<ImageView> extraHandPlayer3 = new ArrayList<>();
    private List<ImageView> extraHandPlayer4 = new ArrayList<>();
    private List<ImageView> deck = new ArrayList<>();
    private List<ImageView> playedCards = new ArrayList<>();

    public GameController(IGameClient gameClient) {
        super(gameClient);
        getGameClient().registerGameClientGUI(this);
        ControllerHelper.getInstance().setGameGUI(this);
    }

    @Override
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
        Platform.runLater(() -> lbPlayer1.setText(playerName));
    }

    @FXML
    private void processBtnLogOutClicked(ActionEvent event) throws IOException {
        ControllerHelper.getInstance().loadFxml("Login", "/login.fxml");
    }

    @FXML
    private void processBtnSurrender(ActionEvent event) throws IOException {
        ControllerHelper.getInstance().loadFxml("Login", "/login.fxml");
    }

    @Override
    public void processHandClicked(MouseEvent mouseEvent, String id, ICard card) {
        handAction(mouseEvent, card);
    }

    @Override
    public void processExtraHandClicked(MouseEvent mouseEvent, String id, ICard card) {
        handAction(mouseEvent, card);
    }

    private void handAction(MouseEvent mouseEvent, ICard card) {
        if(mouseEvent.isPrimaryButtonDown()) {
            if(!selectedCards.contains(card)){
                selectedCards.add(card);
            }
           getGameClient().handlePlaceCardsFromHand(selectedCards);
        }
        else if(mouseEvent.isSecondaryButtonDown()) {
            if(selectedCards.contains(card)) {
                selectedCards.remove(card);
            }
            else {
                selectedCards.add(card);
            }
        }
    }

    @Override
    public void processPlayedCardsClicked(MouseEvent mouseEvent, String id, ICard card) {

    }

    @Override
    public void processPlaceCardFromHand(ICard card) {

    }

    @Override
    public void processPlaceCardsFromHand(String playerName, List<ICard> cards) {

    }

    @Override
    public void processPlaceCardsFromExtraHand(String playerName, List<ICard> cards) {

    }

    @Override
    public void handlePlaceCardsFromHand(List<ICard> cards) {

    }

    @Override
    public void handlePlaceCardFromHand(ICard card) {

    }

    @Override
    public void processDrawCardsFromDeck(String playerName, List<ICard> cards) {
        int drawnAmount = createImageViews(playerName, cards);
        if(drawnAmount < 0) {
            drawnAmount = drawnAmount * -1;
        }
        for(int i = 0; i <= drawnAmount; i++) {
            deck.remove(deck.size() - 1);
        }
        update();
    }

    @FXML
    private void update() {
        Platform.runLater(() -> {
            for(ImageView view : handPlayer1) {
                ControllerHelper.getInstance().getPrimaryStage().getScene().getRoot().getChildrenUnmodifiable().add(view);
            }
            for(ImageView view : handPlayer2) {
                ControllerHelper.getInstance().getPrimaryStage().getScene().getRoot().getChildrenUnmodifiable().add(view);
            }
            for(ImageView view : handPlayer3) {
                ControllerHelper.getInstance().getPrimaryStage().getScene().getRoot().getChildrenUnmodifiable().add(view);
            }
            for(ImageView view : handPlayer4) {
                ControllerHelper.getInstance().getPrimaryStage().getScene().getRoot().getChildrenUnmodifiable().add(view);
            }
            for(ImageView view : extraHandPlayer1) {
                ControllerHelper.getInstance().getPrimaryStage().getScene().getRoot().getChildrenUnmodifiable().add(view);
            }
            for(ImageView view : extraHandPlayer2) {
                ControllerHelper.getInstance().getPrimaryStage().getScene().getRoot().getChildrenUnmodifiable().add(view);
            }
            for(ImageView view : extraHandPlayer3) {
                ControllerHelper.getInstance().getPrimaryStage().getScene().getRoot().getChildrenUnmodifiable().add(view);
            }
            for(ImageView view : extraHandPlayer4) {
                ControllerHelper.getInstance().getPrimaryStage().getScene().getRoot().getChildrenUnmodifiable().add(view);
            }
            for(ImageView view : deck) {
                ControllerHelper.getInstance().getPrimaryStage().getScene().getRoot().getChildrenUnmodifiable().add(view);
            }
            for(ImageView view : playedCards) {
                ControllerHelper.getInstance().getPrimaryStage().getScene().getRoot().getChildrenUnmodifiable().add(view);
            }
        });
    }

    private int createImageViews(String playerName, List<ICard> cards) {
        int beforeSize = 0;
        int afterSize = 0;
        if (playerName.equals(lbPlayer1.getText())) {
            double[] position = new double[2];
            int lastCard = handPlayer1.size() - 1;
            position[0] = handPlayer1.get(lastCard).getLayoutX();
            position[1] = handPlayer1.get(lastCard).getLayoutY();
            beforeSize = handPlayer1.size();
            handPlayer1.addAll(cardImageProcessor.getImageViews(getType(playerName, "Hand"), cards, position));
            afterSize = handPlayer1.size();
        } else if (playerName.equals(lbPlayer2.getText())) {
            double[] position = new double[2];
            int lastCard = handPlayer2.size() - 1;
            position[0] = handPlayer2.get(lastCard).getLayoutX();
            position[1] = handPlayer2.get(lastCard).getLayoutY();
            beforeSize = handPlayer2.size();
            handPlayer2.addAll(cardImageProcessor.getImageViews(getType(playerName, "Hand"), cards, position));
            afterSize = handPlayer2.size();
        } else if (playerName.equals(lbPlayer3.getText())) {
            double[] position = new double[2];
            int lastCard = handPlayer3.size() - 1;
            position[0] = handPlayer3.get(lastCard).getLayoutX();
            position[1] = handPlayer3.get(lastCard).getLayoutY();
            beforeSize = handPlayer3.size();
            handPlayer3.addAll(cardImageProcessor.getImageViews(getType(playerName, "Hand"), cards, position));
            afterSize = handPlayer3.size();
        } else if (playerName.equals(lbPlayer4.getText())) {
            double[] position = new double[2];
            int lastCard = handPlayer4.size() - 1;
            position[0] = handPlayer4.get(lastCard).getLayoutX();
            position[1] = handPlayer4.get(lastCard).getLayoutY();
            beforeSize = handPlayer4.size();
            handPlayer4.addAll(cardImageProcessor.getImageViews(getType(playerName, "Hand"), cards, position));
            afterSize = handPlayer4.size();
        }
        return (afterSize - beforeSize);
    }

    private String getType(String playerName, String cardLocation) {
        String type = "";
        type += cardLocation;
        if(playerName.equals(lbPlayer1.getText())) {
            type += "Player1";
        } else if(playerName.equals(lbPlayer2.getText())) {
            type += "Player2";
        } else if(playerName.equals(lbPlayer3.getText())) {
            type += "Player3";
        } else if(playerName.equals(lbPlayer4.getText())) {
            type += "Player4";
        }
        return type;
    }

    @Override
    public void processDrawCardsFromPlayedCards(String playerName, List<ICard> cards) {

    }

    @Override
    public void processInvalidMove(GameAction gameAction) {
        AlertBox.display("Niet geldige zet", gameAction.name().toLowerCase());
    }

    @Override
    public void processDeckCreated(List<ICard> cards) {
        deck = cardImageProcessor.getImageViews("Deck", cards, null);
        update();
    }

    @Override
    public void processExtraHandCreated(String playerName, List<ICard> cards) {
        if(playerName.equals(lbPlayer1.getText())) {
            extraHandPlayer1 = cardImageProcessor.getImageViews("ExtraHandPlayer1", cards, null);
        }
        else if(playerName.equals(lbPlayer2.getText())) {
            extraHandPlayer2 = cardImageProcessor.getImageViews("ExtraHandPlayer2", cards, null);
        }
        else if(playerName.equals(lbPlayer3.getText())) {
            extraHandPlayer3 = cardImageProcessor.getImageViews("ExtraHandPlayer3", cards, null);
        }
        else if(playerName.equals(lbPlayer4.getText())) {
            extraHandPlayer4 = cardImageProcessor.getImageViews("ExtraHandPlayer4", cards, null);
        }
    }

    @Override
    public void processDeckClicked(MouseEvent mouseEvent, String id, ICard card) {

    }

    @FXML
    private void processBtnReadyClicked(ActionEvent event) {
        ready = !ready;
        getGameClient().readyPlayer(ready);
    }


    @Override
    public void processGameSessionStarted() {
        Platform.runLater(() -> {
            AlertBox.display("Start", "Game is gestart!");
            cbPlayer1Ready.setVisible(false);
            cbPlayer2Ready.setVisible(false);
            cbPlayer3Ready.setVisible(false);
            cbPlayer4Ready.setVisible(false);
                }
        );
        update();
    }

    @Override
    public void processGameSessionResult(String winner) {

    }


    @Override
    public void processReadyResult(Map<String, Boolean> map) {
        for(int i = 0; i < map.keySet().size(); i++) {
            if(map.keySet().toArray()[i].equals(lbPlayer1.getText())) {
                int finalI = i;
                Platform.runLater(() -> cbPlayer1Ready.setSelected((Boolean) map.values().toArray()[finalI]));
            }
            else if(map.keySet().toArray()[i].equals(lbPlayer2.getText())) {
                int finalI1 = i;
                Platform.runLater(() -> cbPlayer2Ready.setSelected((Boolean) map.values().toArray()[finalI1]));
            }
            else if(map.keySet().toArray()[i].equals(lbPlayer3.getText())) {
                int finalI2 = i;
                Platform.runLater(() -> cbPlayer3Ready.setSelected((Boolean) map.values().toArray()[finalI2]));
            }
            else if(map.keySet().toArray()[i].equals(lbPlayer4.getText())) {
                int finalI3 = i;
                Platform.runLater(() -> cbPlayer3Ready.setSelected((Boolean) map.values().toArray()[finalI3]));
            }
        }
    }

    @Override
    public void processGetPlayerNames(List<String> playerNames) {
        for(int i = 0; i < playerNames.size(); i++) {
            if(playerNames.get(i).equals(playerName)){
                playerNumber = i;
            }
        }
        setOtherPlayerNames(playerNumber, playerNames);
    }

    private void setOtherPlayerNames(int playerNumber, List<String> playerNames) {
        for(int i = 0; i < playerNames.size(); i++) {
            if(i != playerNumber) {
                int finalI = i;
                switch(playerAmount) {
                    case 0: Platform.runLater(() -> {
                        lbPlayer2.setText(playerNames.get(finalI));
                        cbPlayer2Ready.setVisible(true); });
                    break;
                    case 1: Platform.runLater(() -> {
                        lbPlayer3.setText(playerNames.get(finalI));
                        cbPlayer3Ready.setVisible(true); });
                    break;
                    case 2: Platform.runLater(() -> {
                        lbPlayer4.setText(playerNames.get(finalI));
                        cbPlayer4Ready.setVisible(true); });
                    break;
                    default:
                    break;
                }
                playerAmount++;
            }
        }
    }
}

