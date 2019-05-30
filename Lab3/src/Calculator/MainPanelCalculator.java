package Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

//Основная панель калькулятора
class MainPanelCalculator extends JPanel {



     static MainPanelCalculator getMainPanel(){
         MainPanelCalculator mainPanel = new MainPanelCalculator();
         addButtons(mainPanel);
         return  mainPanel;
    }
    //Определение внешнего вида клавиш
    private static void addButtons(MainPanelCalculator mainPanel) {
        GridBagLayout baseLayout = new GridBagLayout();
        mainPanel.setLayout(baseLayout);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        int inset = 2;
        c.insets = new Insets(inset,inset,inset,inset);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        c.weightx = c.weighty = 1;

        final CalculatorInfo[] info = {CalculatorInfo.getInfo()};

        JLabel[] result = {new JLabel(info[0].getWhatToShow())};
        result[0].setHorizontalAlignment(JLabel.RIGHT);
        result[0].setFont(new Font(null,Font.PLAIN,20));
        mainPanel.add(result[0], c);

        c.gridy = 1;
        c.gridwidth = 1;
        String[] buttons = {"7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "CE", "0", ".", "/", "="};
        for (String button:
                buttons) {
            if (c.gridx == 4 && !button.contains("=")) {
                c.gridx = 0;
                c.gridy++;
            } else if (button.contains("=")) {
                c.gridx = 0;
                c.gridy = 6;
                c.gridwidth = 4;
            }
            JButton newButton = new JButton(button);
            //Добавление слушателя нажатий на клавиши калькулятора
            newButton.addActionListener(e -> upload(newButton.getText(), info[0], result[0]));

            mainPanel.add(newButton, c);
            c.gridx++;
        }
        //Добавление слушателя клавиатуры
        mainPanel.setFocusable(true);
        mainPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                Arrays.stream(buttons).filter(string -> e.getKeyChar() == string.charAt(0)).forEach(string -> upload(string, info[0], result[0]));
            }
        });

    }

    private static void upload (String newButton, CalculatorInfo info, JLabel result) {
        info.setCurrentButton(newButton);
        CalculatorInfo.updateData(info);
        result.setText(info.getWhatToShow());
    }



}
