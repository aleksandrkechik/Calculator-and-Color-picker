package Options;

import Calculator.MyCalculator;
import ColorPicker.ColorPicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;


// Стартовое окно, позволяющее выбрать нужную программу

class OptionFrame extends JFrame {

    private OptionFrame(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(400,200));
        setLocationByPlatform(true);
        setTitle("FileChooser");
        setResizable(false);
        setVisible(true);
    }

    static OptionFrame getOptionFrame() {
        OptionFrame frame = new OptionFrame();
        createUI(frame);
        return frame;
    }

    private static void createUI (OptionFrame frame) {
        JPanel panel = new JPanel();
        GridBagLayout optionLayout = new GridBagLayout();
        panel.setLayout(optionLayout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 3;
        c.gridx = c.gridy = 0;
        c.weightx = c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        int inset = 5;
        c.insets = new Insets(inset,inset,inset,inset);

        JLabel text = new JLabel("Choose option");
        text.setFont(new Font(null,Font.BOLD,50));
        text.setHorizontalAlignment(JLabel.CENTER);
        panel.add(text, c);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        JButton calculator = new JButton("Open Calculator");
        calculator.addActionListener(e -> {
            MyCalculator.createCalculator();
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        });
        panel.add(calculator, c);

        c.gridx = 1;
        JButton colorChooser = new JButton("Open Color Picker");
        colorChooser.addActionListener(e -> {
            ColorPicker.createColorPicker();
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        });
        panel.add(colorChooser ,c);

        c.gridx = 2;
        JButton exit = new JButton("Exit");
        exit.addActionListener(e -> frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)));
        panel.add(exit, c);
        frame.add(panel);

    }

}
