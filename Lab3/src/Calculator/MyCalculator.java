package Calculator;

public class MyCalculator {

    public static void createCalculator() {
        //Создание окна
        MainFrameCalculator frame = MainFrameCalculator.getCalculatorFrame();
        //Создание основной панели
        MainPanelCalculator mainPanel = MainPanelCalculator.getMainPanel();
        frame.add(mainPanel);
        frame.pack();

    }



}
