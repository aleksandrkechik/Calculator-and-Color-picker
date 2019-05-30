package Calculator;

class CalculatorInfo {
    //Описание первоначального состояния полей
    private String firstValue = "";
    private String secondValue = "";
    private String operation = "";
    private String currentButton = "";
    private String whatToShow = "0";
    private boolean thisIsTheAnswer = false;


    static CalculatorInfo getInfo() {
        return new CalculatorInfo();
    }


    // Обновление информации при добавлении знака
    static CalculatorInfo updateData(CalculatorInfo info) {
        return CalculatorLogic.decideWhatToDO(info);
    }

    String getFirstValue() {
        return firstValue;
    }

    void setFirstValue(String firstValue) {
        this.firstValue = firstValue;
    }

    String getSecondValue() {
        return secondValue;
    }

    void setSecondValue(String secondValue) {
        this.secondValue = secondValue;
    }

    String getOperation() {
        return operation;
    }

    void setOperation(String operation) {
        this.operation = operation;
    }

    String getCurrentButton() {
        return currentButton;
    }

    void setCurrentButton(String currentButton) {
        this.currentButton = currentButton;
    }

    String getWhatToShow() {
        return whatToShow;
    }

    void setWhatToShow(String whatToShow) {
        this.whatToShow = whatToShow;
    }

    boolean isThisIsTheAnswer() {
        return thisIsTheAnswer;
    }

    void setThisIsTheAnswer(boolean thisIsTheAnswer) {
        this.thisIsTheAnswer = thisIsTheAnswer;
    }
}


