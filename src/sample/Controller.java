package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField display;

    private String tempValue = "";
    private Long tempNum = 0L;
    private Boolean start = true;
    private String operator = "";

    Model model = new Model();

    @FXML
    private void onNumberClick(ActionEvent event) {

        //System.out.println("onNumberClick worked.");
        if(start) {
            display.setText("");
            start = false;
        }

        tempValue = ((Button)event.getSource()).getText();
        display.setText(display.getText() + tempValue);

    }


    @FXML
    private void onOperatorClick(ActionEvent event) {

        //System.out.println("onOperatorClick worked.");
        tempValue = ((Button)event.getSource()).getText();
        start = true;

        if(!tempValue.equals("=")) {
            if(!operator.isEmpty()) {

                display.setText(String.valueOf(model.calculateLong(tempNum, Long.parseLong(display.getText()), operator)));
                tempNum = Long.parseLong(display.getText());
                operator = tempValue;
                return;
            }

            operator = tempValue;
            tempNum = Long.parseLong(display.getText());
        }
        else {
            if(operator.isEmpty())
                return;

            display.setText(String.valueOf(model.calculateLong(tempNum, Long.parseLong(display.getText()), operator)));
            operator = "";
            start = true;
        }
    }


}
