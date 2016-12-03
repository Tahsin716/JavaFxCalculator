package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class Controller {

    @FXML
    private Text display;

    @FXML
    private void onNumberClick(ActionEvent event) {

        System.out.println("onNumberClick worked.");

    }


    @FXML
    private void onOperatorClick(ActionEvent event) {

        System.out.println("onOperatorClick worked.");
    }



}
