package ColorPicker;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicReference;

class ColorPickerMainPanel extends JPanel {

    static ColorPickerMainPanel getMainPanel() {
        ColorPickerMainPanel mainPanel = new ColorPickerMainPanel();
        createUI(mainPanel);
        return mainPanel;
    }

    private static void createUI (ColorPickerMainPanel mainPanel) {
        GridBagLayout colorPickerLayout = new GridBagLayout();
        mainPanel.setLayout(colorPickerLayout);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        int inset = 5;
        c.insets = new Insets(inset,inset,inset,inset);
        c.gridx = c.gridy = 0;
        c.weightx = c.weighty = 1;
        c.gridheight = 3;
        c.gridwidth = 5;
        Integer[] colorCode = {125,125,125};
        JButton colorArea = new JButton();
        AtomicReference<Color> currentColor = new AtomicReference<>(new Color(colorCode[0], colorCode[1], colorCode[2]));
        colorArea.setBackground(currentColor.get());
        String hexValue = convertRGBtoHEX(colorCode);
        colorArea.setToolTipText(hexValue);

        mainPanel.add(colorArea,c);
        c.gridheight = c.gridwidth = 1;
        c.gridx = 6;
        c.gridy = 0;
        JLabel redLabel = new JLabel("Red:");
        mainPanel.add(redLabel, c);
        c.gridy = GridBagConstraints.RELATIVE;
        JLabel greenLabel = new JLabel("Green:");
        mainPanel.add(greenLabel, c);
        JLabel blueLabel = new JLabel("Blue:");
        mainPanel.add(blueLabel, c);
        c.weightx = c.weighty = 1;
        c.gridy = 0;
        c.gridx = 5;
        c.gridheight = 1;
        c.gridwidth = 4;


        Dictionary <Integer, JLabel> labels = new Hashtable<>();
        labels.put(0, new JLabel("0"));
        labels.put(125, new JLabel("125"));
        labels.put(255, new JLabel("255"));

        JSlider redSlider = new JSlider(0,255,125);
        JSlider greenSlider = new JSlider(0,255,125);
        JSlider blueSlider = new JSlider(0,255,125);

        JSlider[] sliders = new JSlider[3];
        sliders[0] = redSlider;
        sliders[1] = greenSlider;
        sliders[2] = blueSlider;

        for (int i = 0; i < 3; i++) {
            sliders[i].setMajorTickSpacing(15);
            sliders[i].setPaintTicks(true);
            sliders[i].setLabelTable(labels);
            sliders[i].setPaintLabels(true);
            int finalI = i;
            // Создание слушателя передвижения ползунков
            sliders[i].addChangeListener(e -> {
                colorCode[finalI] = sliders[finalI].getValue();
                currentColor.set(new Color(colorCode[0], colorCode[1], colorCode[2]));
                colorArea.setBackground(currentColor.get());
                String colorText = convertRGBtoHEX(colorCode);
                colorArea.setToolTipText(colorText);
                //Создание ограничителя, сохраняющего в буфер обмена
                //только результат, получаемый после остановки ползунка
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()){
                    copyToBuffer(colorText);
                }

            });
            mainPanel.add(sliders[i], c);


            c.gridy++;
        }


    }
    //Перевод цветового формата в шестнадцатиричную систему
    private static String convertRGBtoHEX (Integer[] colors){
        StringBuilder hex = new StringBuilder();
        hex.append("#");
        for (Integer color:
             colors) {
            hex.append(Integer.toHexString(color).toUpperCase());
        }
        return hex.toString();
    }

    //Копирование результата в буфер обмена
    private static void copyToBuffer (String text) {
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
}
