package frontend;

import frontend.controllers.ControllerHelper;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.interfaces.ICard;

import java.util.ArrayList;
import java.util.List;

public class CardImageProcessor {

    private static int player1HandCount = 0;
    private static int player2HandCount = 0;
    private static int player3HandCount = 0;
    private static int player4HandCount = 0;
    private static int player1ExtraHandCount = 0;
    private static int player2ExtraHandCount = 0;
    private static int player3ExtraHandCount = 0;
    private static int player4ExtraHandCount = 0;
    private static int deckCount = 0;
    private static int playedCardsCount = 0;

    public List<ImageView> getImageViews(String type ,List<ICard> cards, double[] lastCardPosition) {
        List<ImageView> imageViews = new ArrayList<>();
        for(ICard card : cards) {
            imageViews.add(getImageView(type, card, lastCardPosition));
        }
        return imageViews;
    }


    private ImageView getImageView(String type , ICard card, double[] lastCardPosition) {
        ImageView imageView = new ImageView();
        imageView.setId(getId(type));
        if(card.isWatchable()) {
            imageView.setImage(getImage(card));
        }
        else {
            imageView.setImage(new Image("/pictures/back.png"));
        }
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        imageView.setViewport(getViewPort(type, lastCardPosition));
        if(type.contains("Extra")){
        imageView.setOnMouseClicked(e -> ControllerHelper.getInstance().getGameGUI().processExtraHandClicked(e, imageView.getId(), card));
        }
        else if(type.contains("Deck")) {
        imageView.setCursor(Cursor.HAND);
        imageView.setOnMouseClicked(e -> ControllerHelper.getInstance().getGameGUI().processDeckClicked(e, imageView.getId(), card));
        }
        else if(type.contains("Hand")) {
        imageView.setCursor(Cursor.HAND);
        imageView.setOnMouseClicked(e -> ControllerHelper.getInstance().getGameGUI().processHandClicked(e, imageView.getId(), card));
        }
        else if(type.contains("Played")) {
        imageView.setCursor(Cursor.HAND);
        imageView.setOnMouseClicked(e -> ControllerHelper.getInstance().getGameGUI().processPlayedCardsClicked(e, imageView.getId(), card));
        }
        if(type.contains("Player4"))
        imageView.setRotate(90);
        else if(type.contains("Player3")) {
            imageView.setRotate(-90);
        }
        return imageView;
    }

    private String getId(String type) {
        String id = "";
        switch (type) {
            case "HandPlayer1":
                id = type + player1HandCount;
                player1HandCount++;
                break;
            case "HandPlayer2":
                id = type + player2HandCount;
                player2HandCount++;
                break;
            case "HandPlayer3":
                id = type + player3HandCount;
                player3HandCount++;
                break;
            case "HandPlayer4":
                id = type + player4HandCount;
                player4HandCount++;
                break;
            case "ExtraHandPlayer1":
                id = type + player1ExtraHandCount;
                player1ExtraHandCount++;
                break;
            case "ExtraHandPlayer2":
                id = type + player2ExtraHandCount;
                player2ExtraHandCount++;
                break;
            case "ExtraHandPlayer3":
                id = type + player3ExtraHandCount;
                player3ExtraHandCount++;
                break;
            case "ExtraHandPlayer4":
                id = type + player4ExtraHandCount;
                player4ExtraHandCount++;
                break;
            case "Deck":
                id = type + deckCount;
                deckCount++;
                break;
            case "PlayedCards":
                id = type + playedCardsCount;
                playedCardsCount++;
                break;
        }
        return id;
    }

    private Rectangle2D getViewPort(String type, double[] lastCardPosition) {
        Rectangle2D viewportRect;
        switch (type) {
            case "HandPlayer1":
                if( lastCardPosition == null || lastCardPosition.length == 0) {
                viewportRect = new Rectangle2D(550.0, 480.0,120.0, 185.0);
                }
                else {
                viewportRect = new Rectangle2D(lastCardPosition[0] + 35, lastCardPosition[1], 120.0, 185.0);
                }
                break;
            case "HandPlayer2":
                if( lastCardPosition == null || lastCardPosition.length == 0) {
                viewportRect = new Rectangle2D(550.0, 77.0,90.0, 120.0);
                }
                else {
                    viewportRect = new Rectangle2D(lastCardPosition[0] + 35, lastCardPosition[1], 90.0, 120.0);
                }
                break;
            case "HandPlayer3":
                if( lastCardPosition == null || lastCardPosition.length == 0) {
                    viewportRect = new Rectangle2D(1125.0, 370.0,90.0, 120.0);
                }
                else {
                    viewportRect = new Rectangle2D(lastCardPosition[0], lastCardPosition[1] + 35, 90.0, 120.0);
                }
                break;
            case "HandPlayer4":
                if( lastCardPosition == null || lastCardPosition.length == 0) {
                    viewportRect = new Rectangle2D(45.0, 370.0,90.0, 120.0);
                }
                else {
                    viewportRect = new Rectangle2D(lastCardPosition[0], lastCardPosition[1] + 35, 90.0, 120.0);
                }
                break;
            case "ExtraHandPlayer1":
                if( lastCardPosition == null || lastCardPosition.length == 0) {
                    viewportRect = new Rectangle2D(230.0, 510.0,90.0, 120.0);
                }
                else {
                    viewportRect = new Rectangle2D(lastCardPosition[0] + 90, lastCardPosition[1], 90.0, 120.0);
                }
                break;
            case "ExtraHandPlayer2":
                if( lastCardPosition == null || lastCardPosition.length == 0) {
                    viewportRect = new Rectangle2D(230.0, 80.0,90.0, 120.0);
                }
                else {
                    viewportRect = new Rectangle2D(lastCardPosition[0] + 90, lastCardPosition[1],90.0, 120.0);
                }
                break;
            case "ExtraHandPlayer3":
                if( lastCardPosition == null || lastCardPosition.length == 0) {
                    viewportRect = new Rectangle2D(1125.0, 90.0,90.0, 120.0);
                }
                else {
                    viewportRect = new Rectangle2D(lastCardPosition[0], lastCardPosition[1] + 90, 90.0, 120.0);
                }
                break;
            case "ExtraHandPlayer4":
                if( lastCardPosition == null || lastCardPosition.length == 0) {
                    viewportRect = new Rectangle2D(45.0, 90.0,90.0, 120.0);
                }
                else {
                    viewportRect = new Rectangle2D(lastCardPosition[0], lastCardPosition[1] + 90, 90.0, 120.0);
                }
                break;
            case "Deck":
                if( lastCardPosition == null || lastCardPosition.length == 0) {
                    viewportRect = new Rectangle2D(400.0, 240.0,90.0, 120.0);
                }
                else {
                    viewportRect = new Rectangle2D(lastCardPosition[0] + 2, lastCardPosition[1] - 2, 120.0, 170.0);
                }
                break;
            case "PlayedCards":
                if( lastCardPosition == null || lastCardPosition.length == 0) {
                    viewportRect = new Rectangle2D(650.0, 240.0,90.0, 120.0);
                }
                else {
                    viewportRect = new Rectangle2D(lastCardPosition[0] + 2, lastCardPosition[1] - 2, 120.0, 170.0);
                }
                break;
            default: viewportRect = null;
            break;
        }
        return viewportRect;
    }


    private Image getImage(ICard card) {
        Image image;
        String path = "/pictures/";
        switch (card.getSuitType()) {
            case CLUBS:
                path += "c_";
                break;
            case HEARTS:
                path += "h_";
                break;
            case SPADES:
                path += "s_";
                break;
            case DIAMONDS:
                path += "d_";
                break;
        }
        path += card.getCardValue().getValue() + ".png";
        image = new Image(path);
        return image;
    }
}
