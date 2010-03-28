import java.awt.BorderLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;


public class ScrollBar extends JPanel {
    
    JLabel label;
    
    public ScrollBar() {
        super(true);
        label=new JLabel();
        setLayout(new BorderLayout());
        
        JScrollBar hbar = new JScrollBar(
                JScrollBar.HORIZONTAL, 30, 20, 0, 300);
        JScrollBar vbar = new JScrollBar(
                JScrollBar.VERTICAL, 30, 40, 0, 300);
        
        hbar.setUnitIncrement(2);
        hbar.setBlockIncrement(1);
        
        hbar.addAdjustmentListener(new MyListener());
        vbar.addAdjustmentListener(new MyListener());
        
        add(hbar, BorderLayout.SOUTH);
        add(vbar, BorderLayout.EAST);
        add(label, BorderLayout.CENTER);
    }
    
    class MyListener implements AdjustmentListener {
        public void adjustmentValueChanged(AdjustmentEvent e) {
        	// bla bla bla
        }
    }
}