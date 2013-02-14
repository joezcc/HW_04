import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;

public class ScribbleApplet extends JFrame {

    JSlider red = new JSlider(JSlider.VERTICAL, 0, 255, 0);
    JSlider green = new JSlider(JSlider.VERTICAL, 0, 255, 0);
    JSlider blue = new JSlider(JSlider.VERTICAL, 0, 255, 0);
    DrawingPad drawPad;
    
    static JTextField txtField;
    static JPanel palette;
    
   	public static void main(String[] args){
		
		ScribbleApplet scribble= new ScribbleApplet();
		scribble.setSize(900,700);
		scribble.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		scribble.setVisible(true);
	}
    
	public ScribbleApplet(){

		//making interfaces for the canvas
		Container canvas =this.getContentPane();
	    drawPad = new DrawingPad();
	    drawPad.setBounds(0, 0, 900, 700);	    
	    canvas.add(drawPad, BorderLayout.CENTER);
	    
	    //making panel for adjustment of size and color
		JPanel adjust = new JPanel(new GridLayout(2,1,0,0));
	    TitledBorder border = new TitledBorder("Adjustment Panel");
	    adjust.setBorder(border);
	    
	    JPanel colorPanel = new JPanel(new GridLayout(1,4,0,0));
	    JPanel sizeAndClear = new JPanel(new GridLayout(3,1,5,0));
	    
	    JButton clearButton = new JButton("Clear Canvas");
	    
	    red.setBackground(Color.RED);
	    green.setBackground(Color.GREEN);
	    blue.setBackground(Color.BLUE);
	    
	    palette = new JPanel();
	    palette.setBackground(Color.BLACK);
	    
	    JSlider size = new JSlider(JSlider.HORIZONTAL,0,100,10);
	    
		size.setMajorTickSpacing(20);
		size.setMinorTickSpacing(1);
		size.setPaintTicks(true);
		size.setPaintLabels(true);
		
		//textbox
		txtField = new JTextField();
		txtField.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtField.setText("Color(" + "0" + ", " + "0" + ", " + "0" +
		")" );
		
		
		txtField.setEditable(false);
		

		red.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				if (!source.getValueIsAdjusting()) {
					drawPad.rVal = (int) source.getValue();
					txtField.setText("Color(" + drawPad.rVal + ", " + drawPad.gVal + ", " + drawPad.bVal +
							")");
					updatePalette(drawPad.rVal,drawPad.gVal,drawPad.bVal);
				}
			}
		});
		
		green.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				if (!source.getValueIsAdjusting()) {
					drawPad.gVal = (int) source.getValue();
					txtField.setText("Color(" + drawPad.rVal + ", " + drawPad.gVal + ", " + drawPad.bVal +
							")");
					updatePalette(drawPad.rVal,drawPad.gVal,drawPad.bVal);
				}
			}
		});
		
		blue.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				if (!source.getValueIsAdjusting()) {
					drawPad.bVal = (int) source.getValue();
					txtField.setText("Color(" + drawPad.rVal + ", " + drawPad.gVal + ", " + drawPad.bVal +
							")");
					updatePalette(drawPad.rVal,drawPad.gVal,drawPad.bVal);
				}
			}
		});
	    
		size.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				if (!source.getValueIsAdjusting()) {
					drawPad.brushSize = (int) source.getValue();
				}
			}
		});
	    
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawPad.clear();
			}
		});

		//adding left and right component to adjustment panel
		canvas.add(adjust,BorderLayout.WEST);
		
		adjust.add(colorPanel);
		adjust.add(sizeAndClear);
		
		colorPanel.add(red);	colorPanel.add(green);	colorPanel.add(blue);
		colorPanel.add(palette);
		
		sizeAndClear.add(txtField);
		sizeAndClear.add(size,BorderLayout.NORTH);
		sizeAndClear.add(clearButton);
		
	}
	
	public void updatePalette(int r,int g,int b){
		palette.setBackground(new Color(r,g,b));
		palette.repaint();
	}
	
	public void updateRGB(int r,int g,int b){
		drawPad.rVal = r;
		drawPad.gVal = g;
		drawPad.bVal = b;

		txtField.setText("Color(" + r + ", " + g + ", " + b +
				")");

		updatePalette(r,g,b);
		
	}
}

class DrawingPad extends JComponent {
    Image image;
    Graphics2D graphics2D;
    int currentX, currentY, oldX, oldY;
    int rVal,gVal,bVal;
    static int brushSize = 10;
    
    public DrawingPad() {
    	setDoubleBuffered(false);
            
    	addMouseListener(new MouseAdapter() {
           	public void mousePressed(MouseEvent e) {
           		oldX = e.getX();
           		oldY = e.getY();
           	}
        });
            
        addMouseMotionListener(new MouseMotionAdapter() {
           	public void mouseDragged(MouseEvent e) {
           		currentX = e.getX();
           		currentY = e.getY();
           		graphics2D.setColor(new Color(rVal,gVal,bVal));
           		if (graphics2D != null)
           			graphics2D.setStroke(new BasicStroke(brushSize));
           		
           		graphics2D.drawLine(oldX, oldY, currentX, currentY);
           		repaint();
           		oldX = currentX;
           		oldY = currentY;
           	}
        });
    }
   
    public void paintComponent(Graphics g) {
    	if (image == null) {
    		image = createImage(getSize().width, getSize().height);
    		graphics2D = (Graphics2D) image.getGraphics();
    		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
    				RenderingHints.VALUE_ANTIALIAS_ON);
    		clear();
        }
        g.drawImage(image, 0, 0, null);
    }

    public void clear() {
        graphics2D.setPaint(Color.white);
        graphics2D.fillRect(0, 0, getSize().width, getSize().height);
        repaint();
    }
}
