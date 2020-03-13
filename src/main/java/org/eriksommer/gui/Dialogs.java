package org.eriksommer.gui;

import javafx.scene.control.Alert;

public class Dialogs {

    public static void showErrorDialog(String feilHeader, String feilContent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Feil!");
        alert.setHeaderText(feilHeader);
        alert.setContentText(feilContent);

        alert.showAndWait();
    }

    public static void showSuccessDialog(String rettTittel, String rettHeader, String rettContent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(rettTittel);
        alert.setHeaderText(rettHeader);
        alert.setContentText(rettContent);

        alert.showAndWait();
    }

}
