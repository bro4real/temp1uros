import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class CalculatorEngine implements ActionListener {
 
 Calculator parent; // a reference to the Calculator
 Double firstNumber = 0.0;
 Double secondNumber=0.0;
 Double result=0.0;
 String doWhat = "";
 
	
 // Constructor stores the reference to the 
 // Calculator window in  the member variable parent
 CalculatorEngine(Calculator parent){
   this.parent = parent;
 }

 public void actionPerformed(ActionEvent e){
   // Get the source of this action
	 Object src = e.getSource();
	 
	 if (src == parent.buttonEqual) {
		 
		 secondNumber = Double.parseDouble(parent.getDisplayValue());
		 
		 switch (doWhat) {
		case " + ":
			result = firstNumber + secondNumber;
			break;
		case " - ":
			result = firstNumber - secondNumber;
			break;
		case " * ":
			result = firstNumber * secondNumber;
			break;
		case " / ":
			result = firstNumber / secondNumber;
			break;
		default:
			break;
		}
		 parent.setDisplayValue(String.valueOf(result));
	 } else if (src == parent.buttonPlus) {
		 doWhat = " + ";
		 firstNumber = Double.parseDouble(parent.getDisplayValue());
		 parent.setDisplayValue("");
	 } else if (src == parent.buttonMinus) {
		 doWhat = " - ";
		 firstNumber = Double.parseDouble(parent.getDisplayValue());
		 parent.setDisplayValue("");
	 } else if (src == parent.buttonTimes) {
		 doWhat = " * ";
		 firstNumber = Double.parseDouble(parent.getDisplayValue());
		 parent.setDisplayValue("");
	 } else if (src == parent.buttonDivide) {
		 doWhat = " / ";
		 firstNumber = Double.parseDouble(parent.getDisplayValue());
		 parent.setDisplayValue("");
	 } else if (src == parent.buttonClear) {
		 parent.setDisplayValue("");
	 } else {
	 
   JButton clickedButton =  (JButton) e.getSource();
    	
   // Get the existing text from the Calculator's
   // display field. Reaching inside another object is bad.
   String dispFieldText = parent.getDisplayValue();

   // Get the button's label 
   String clickedButtonLabel = clickedButton.getText();
   	
   parent.setDisplayValue(dispFieldText + 
                                   clickedButtonLabel);
	 }
 }
}
