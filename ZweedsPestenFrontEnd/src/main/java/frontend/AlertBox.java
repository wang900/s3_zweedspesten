package frontend;

import javafx.scene.control.Alert;

public class AlertBox {

    public static void display(String header, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
