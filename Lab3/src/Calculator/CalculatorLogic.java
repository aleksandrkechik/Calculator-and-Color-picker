package Calculator;

class CalculatorLogic {
    // метод определяющий, какие поля заполняются при вводе данных
   static CalculatorInfo decideWhatToDO(CalculatorInfo info) {
        String firstValue = info.getFirstValue();
        String secondValue = info.getSecondValue();
        String operation = info.getOperation();
        String currentButton = info.getCurrentButton();
        String whatToShow = info.getWhatToShow();
        boolean thisIsTheAnswer = info.isThisIsTheAnswer();

        //Описание возможных сценариев, необходимых для переключения между полями (для удобочитаемости кода)
        boolean dotIsPressed = currentButton.contains(".");
        boolean buttonPressedCE = currentButton.contains("CE");
        boolean operationIsPressed = currentButton.contains("*") |
                                     currentButton.contains("-") |
                                     currentButton.contains("+") |
                                     currentButton.contains("/");
        boolean equalsIsPressed = currentButton.contains("=");
        boolean digitIsPressed = !(buttonPressedCE || operationIsPressed || dotIsPressed || equalsIsPressed);
        boolean addingToFirst = (operation.length() == 0) && (secondValue.length() == 0) && (digitIsPressed || dotIsPressed);
        boolean addingToSecond = !(operation.length() == 0) && (digitIsPressed || dotIsPressed);
        boolean needToCount = !(secondValue.length() == 0) && (operationIsPressed || equalsIsPressed);
        boolean replacingFirstNumber = thisIsTheAnswer && digitIsPressed && (operation.length() == 0);

        // Логика метода
        if (buttonPressedCE) {
            firstValue = secondValue = operation = "";
            whatToShow = "0";
        } else if (replacingFirstNumber) {
            firstValue = currentButton;
            thisIsTheAnswer = false;
            whatToShow = firstValue;
        } else if (addingToFirst) {
            firstValue = addToNumber(firstValue, currentButton);
            whatToShow = firstValue;
        } else if (addingToSecond) {
            secondValue = addToNumber(secondValue,currentButton);
            whatToShow = secondValue;
        } else if (operationIsPressed && !needToCount) {
            operation = currentButton;
            whatToShow = firstValue + " " + operation;
        } else if (needToCount) {
            firstValue = count(firstValue, secondValue, operation);
            whatToShow = firstValue;
            secondValue = "";
            if (equalsIsPressed) {
                operation = "";
                thisIsTheAnswer = true;
            } else {
                operation = currentButton;
            }
        }
        //Обновление текущего состояния
        info.setFirstValue(firstValue);
        info.setSecondValue(secondValue);
        info.setOperation(operation);
        info.setWhatToShow(whatToShow);
        info.setThisIsTheAnswer(thisIsTheAnswer);
        if (whatToShow.contains("Error: division by zero")){
            info.setFirstValue("");
            info.setSecondValue("");
            info.setOperation("");
            info.setThisIsTheAnswer(false);
        }
        return info;
    }

    // Добавление знака к числу, данный метод обрабатывает пограничные ситуации
    private static String addToNumber(String value, String currentButton) {

        boolean zeroAndZero = (value.length() == 1 && value.contains("0")) && currentButton.contains("0");
        boolean nothingAndMinus = (value.length() == 0) && currentButton.contains("-");
        boolean HasDot = value.contains(".");


        if  (nothingAndMinus) {
            value = simplyAdd(value, currentButton);
            return value;
        } else if (!HasDot && currentButton.contains(".")) {
            value = simplyAdd(value, currentButton);
            return value;
        } else if (!currentButton.contains(".") && !zeroAndZero){
            value = simplyAdd(value, currentButton);
            return value;
        }
        return value;
    }

    // Простое добавление знака
    private static String simplyAdd(String x, String y) {
       x = x + y;
       return  x;
    }

    //Операции подсчета
    private static String count(String firstNumber, String secondNumber, String operation) {
        Double first = Double.parseDouble(firstNumber);
        Double second = Double.parseDouble(secondNumber);
        double answer = 0.0;
        String finalAnswer;
        switch (operation) {
            case "-":
                answer = first - second;
                break;
            case "+":
                answer = first + second;
                break;
            case "*":
                answer = first * second;
                break;
            case "/":
                if (second == 0) {
                    finalAnswer = "Error: division by zero";
                    return finalAnswer;
                } else {
                    answer = first / second;
                }
                break;
        }
        finalAnswer = String.valueOf(answer);
        return finalAnswer;
    }

}
