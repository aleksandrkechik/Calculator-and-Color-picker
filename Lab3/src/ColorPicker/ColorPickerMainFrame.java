package ColorPicker;

import javax.swing.*;
import java.awt.*;

//Окно определителя цвета
class ColorPickerMainFrame extends JFrame {
    private ColorPickerMainFrame (){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(800,400));
        setLocationByPlatform(true);
        setTitle("Color Picker");
        setMinimumSize(new Dimension(300,450));
        setVisible(true);
    }

    static ColorPickerMainFrame getColorPickerMainFrame(){
        return new ColorPickerMainFrame();
    }

}
