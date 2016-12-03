package sample;

public class Model {

    public long calculateLong(long number1, long number2, String operator) {

        if (operator.equals("+")) {
            return number1 + number2;
        }
        else if (operator.equals("-")) {
            return number1 - number2;
        }
        else if (operator.equals("*")) {
            return number1 * number2;
        }
        else if (operator.equals("/")) {
            if (number2 == 0)
                return 0;

            return number1 / number2;
        }

        System.out.println("Unknown operator - " + operator);
        return 0;
    }
}
