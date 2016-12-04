package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField display;

    private String tempValue = "";
    private String tempNum = "";
    private Boolean start = true;
    private String operator = "";

    Model model = new Model();

    @FXML
    private void onNumberClick(ActionEvent event) {

        //System.out.println("onNumberClick worked.");

        //So that the display is set to blank, as the new number will get appended with the previos one
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

        if(!tempValue.equals("=")) {

            //Clear everything
            if(tempValue.equals("C")){
                tempNum = "";
                operator = "";
                tempValue = "";
                display.setText("0");
                start = true;
                return;
            }

            //For negative number
            if(tempValue.equals("-") && display.getText().equals("0")) {
                display.setText("-");
                start = false;
                return;
            }

            //Incase minus sign for negative number is input multiple times
            if(tempValue.equals("-") && display.getText().equals("-")) {
                start = false;
                return;
            }


            //For trailing calculations like (5 +3 * 11 - 2 / 6)
            if(!operator.isEmpty()) {

                display.setText(model.dataProcess(tempNum, display.getText(), operator));
                tempNum = display.getText();
                operator = tempValue;
                start = true;
                return;
            }

            //For input of other operators
            if(!display.getText().equals("-")) {
                operator = tempValue;
                tempNum = display.getText();
                start = true;
            }
        }
        else {
            //In case the user presses "=", without inputting any numbers to calculate
            if(operator.isEmpty())
                return;

            display.setText(model.dataProcess(tempNum, display.getText(), operator));
            operator = "";
            start = true;
        }
    }

}

//TODO: i) negative input, ii) double input, iii) fractional part in division, iv) bigint and bigdouble
