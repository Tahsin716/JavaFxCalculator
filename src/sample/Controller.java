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

        //So that the display is set to blank, as the new number will get appended with the previos one
        start = true;

        if(!tempValue.equals("=")) {

            //Clear everything
            if(tempValue.equals("C")){
                tempNum = 0L;
                operator = "";
                tempValue = "";
                display.setText("0");
                return;
            }

            //For trailing calculations like (5 +3 * 11 - 2 / 6)
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
            //In case the user presses "=", without inputting any numbers to calculate
            if(operator.isEmpty())
                return;

            display.setText(String.valueOf(model.calculateLong(tempNum, Long.parseLong(display.getText()), operator)));
            operator = "";
            start = true;
        }
    }

}

//TODO: i) negative input, ii) double input, iii) fractional part in division, iv) bigint and bigdouble
