import javax.swing.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class Calculator {

    // Declare all calculator's components.
	//					these are new comments! (5 tabs to the right)
	//                  we won't need this JPanel
	//					JPanel windowContent;
	
	private JTextField displayField;
	JButton button0;
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;
	JButton button6;
	JButton button7;
	JButton button8;
	JButton button9;
	JButton buttonPoint;
	JButton buttonEqual;
	JButton buttonPlus, buttonMinus, 
	buttonTimes, buttonDivide, buttonClear;
	JPanel p1;

    public void setDisplayValue(String val){
        displayField.setText(val);
    }

    public String getDisplayValue() {
        return displayField.getText();
    } 

      // Constructor  creates the components
      // and adds the to the frame using combination of 
      // Borderlayout and Gridlayout

  Calculator(){

	  p1 = new JPanel();
	  // Let's try with box layout
	  BoxLayout b1 = new BoxLayout(p1, BoxLayout.Y_AXIS);
	  // Set the layout manager for this panel
	  p1.setLayout(b1);

	 

     // Create the display field    
	 displayField = new JTextField(15);
	 // and place it to be first from the top - downwards
	 p1.add(displayField);
     // Create buttons using constructor of the  
     // class JButton that takes the label of the 
     // button as a parameter 

	  button0=new JButton("0");
	  button1=new JButton("1");
	  button2=new JButton("2");
	  button3=new JButton("3");
	  button4=new JButton("4");
	  button5=new JButton("5");
	  button6=new JButton("6");
      button7=new JButton("7");
      button8=new JButton("8");
	  button9=new JButton("9");
	  buttonPoint = new JButton(".");
	  buttonPlus = new JButton(" + ");
	  buttonMinus = new JButton(" - ");
	  buttonTimes = new JButton(" * ");
	  buttonDivide = new JButton(" / ");
	  buttonEqual = new JButton(" = ");
	  buttonClear = new JButton(" C ");
	 

	 //  Add window controls to the panel p1
		  p1.add(button1);
		  p1.add(button2);
		  p1.add(button3);
		  p1.add(button4);
		  p1.add(button5);
		  p1.add(button6);
		  p1.add(button7);
		  p1.add(button8);
		  p1.add(button9);
		  p1.add(button0);
		  p1.add(buttonPoint);
		  p1.add(buttonPlus);
		  p1.add(buttonMinus);
		  p1.add(buttonTimes);
		  p1.add(buttonDivide);
		  p1.add(buttonEqual);
		  p1.add(buttonClear);
  
		  CalculatorEngine calcEngine = new CalculatorEngine(this);
		  button0.addActionListener(calcEngine);
		  button1.addActionListener(calcEngine);
		  button2.addActionListener(calcEngine);
		  button3.addActionListener(calcEngine);
		  button4.addActionListener(calcEngine);
		  button5.addActionListener(calcEngine);
		  button6.addActionListener(calcEngine);
		  button7.addActionListener(calcEngine);
		  button8.addActionListener(calcEngine);
		  button9.addActionListener(calcEngine);
		  buttonPoint.addActionListener(calcEngine);
		  buttonPlus.addActionListener(calcEngine);
		  buttonMinus.addActionListener(calcEngine);
		  buttonTimes.addActionListener(calcEngine);
		  buttonDivide.addActionListener(calcEngine);
		  buttonEqual.addActionListener(calcEngine);
		  buttonClear.addActionListener(calcEngine);

	//Create the frame and set its content pane	               
		JFrame frame = new JFrame("Calculator");
		//					old ContentPane:	
	    //  				frame.setContentPane(windowContent);
		frame.setContentPane(p1);
		
	 // Set the size of the window to be big enough to accommodate all controls 		
	       frame.pack();
	      

		 // Display the window
		  frame.setVisible(true);
     }


     public static void main(String[] args) {

	  new Calculator();
     
     }
}
