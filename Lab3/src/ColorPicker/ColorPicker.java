package ColorPicker;

public class ColorPicker {
    public static void createColorPicker(){
        ColorPickerMainFrame mainFrame = ColorPickerMainFrame.getColorPickerMainFrame();
        ColorPickerMainPanel mainPanel = ColorPickerMainPanel.getMainPanel();
        mainFrame.add(mainPanel);
        mainFrame.pack();
    }
}
