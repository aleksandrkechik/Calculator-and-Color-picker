package Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

//Окно калькулятора
class MainFrameCalculator extends JFrame {
    private MainFrameCalculator() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(400,600));
        setLocationByPlatform(true);
        setTitle("Calculator");
        setMaximumSize(new Dimension(600,900));
        setMinimumSize(new Dimension(300,450));
        setVisible(true);
    }

    static MainFrameCalculator getCalculatorFrame() {
        MainFrameCalculator frame = new MainFrameCalculator();
        frame.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                double w = frame.getSize().getWidth();
                double h = frame.getSize().getHeight();
                if(w > 600.0 && h > 900){
                    frame.setSize(new Dimension(600, 900));
                    frame.repaint();
                    frame.revalidate();
                }

                super.componentResized(e);
            }

        });
        return frame;
    }


}
