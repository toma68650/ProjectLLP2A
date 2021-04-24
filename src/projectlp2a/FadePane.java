package projectlp2a;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @class FadePane.java
 * @brief JPanel which can simply fade out.
 * @author alexandrev - thomasl 
 * @author Thanks to MadProgrammer (here : stackoverflow.com/questions/13203415/how-to-add-fade-fade-out-effects-to-a-jlabel
 * @author Thanks to Tym (here : blog.tym-project.fr )
 * @version 1.0
 * @date 2021
 */
@SuppressWarnings("serial")
public class FadePane extends JPanel {

    private float direction = -0.03f; //!< the little transparence delta change performed each time the timer run.
    private FadeLabel label; //!< the label with the text
    
    public FadePane(String text) {
        setLayout(new BorderLayout());
        JLabel background = new JLabel();
        background.setLayout(new GridBagLayout());
        setOpaque(false);
        setBounds(0,0,735,400);
        add(background);

        label = new FadeLabel(text);
        background.add(label);

        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	float alpha = label.getAlpha();
            	if(alpha > Math.abs(direction)) {
            		alpha += direction;
                    label.setAlpha(alpha);
                } else {
                	alpha = 0f;
                }
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();
    }
    
    public void changeAnnounce(String text, Color color) {
    	label.setText(text);
    	label.setForeground(color);
    	resetAlpha();
    }
    
    private void resetAlpha() {
    	label.setAlpha(1);
    }
    
    public FadeLabel getFadeLabel() {
    	return label;
    }

    /**
     * @class FadeLabel
     * @brief inner class of FadePane. It inherits from a JLabel. It is where the text is displayed
     * @author alexandrev - thomasl
     * @version 1.0
     * @date 2021
     */
    public class FadeLabel extends JLabel {

        private float alpha; //!< the opacity of the FadeLabel.

        public FadeLabel(String text) {
            setText(text);
            setBounds(0,0,735,735);
            setFont(new Font("Arial",Font.BOLD, 40));
            setAlpha(1f);
        }

        public void setAlpha(float value) {
            if (alpha != value) {
            	float old = alpha;
                alpha = value;
                firePropertyChange("alpha", old, alpha);
                repaint();
            }
        }

        public float getAlpha() {
            return alpha;
        }

        @Override
        public void paint(Graphics g) {
            // This is one of the few times I would directly override paint
            // This makes sure that the entire paint chain is now using
            // the alpha composite, including borders and child components
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getAlpha()));
            super.paint(g2d);
            g2d.dispose();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
        }
    }
}
