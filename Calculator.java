import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
* This class defines and implements the calculator's GUI.
* It contains the main which creates an instance of the calculator.
*
* @author Darragh King (ID 113372871)
*/

public class Calculator extends JFrame{
	private Button addButton, subtractButton, unaryPlusButton, unaryMinusButton,
			 multiplyButton, divideButton, squareRootButton, exponentiateButton, reciprocalButton, clearAllButton;
	private JTextField operandOne, operandTwo, resultField;
	private JPanel operatorPanel,operandPanel,clearAllAndResultPanel;
	private final UnaryOperations squareRoot= new SquareRoot(), reciprocal = new Reciprocal(), unaryPlus = new UnaryPlus(), unaryMinus = new UnaryMinus();
	private final BinaryOperations adder = new Adder(), subtractor = new Subtractor(), multiplier = new Multiplier(),
								divider = new Divider(), exponentiate = new Exponentiate();
	private final String ERROR = "Error";
	private final String CLEAR_VALUE = "";

	/**
	*	Adds an operator button to the calculator.
	*
	*	@param buttonName The name of the button
	*	@param name The string passed to the actionlistener to determine the requested computation
	*	@param buttonType The type of computation ie Unary or Binary Operation
	*/
	public void addOperatorButton(Button buttonName, String name, String buttonType){
		buttonName = new Button(name, buttonType);
		operatorPanel.add(buttonName);
	}

	/**
	*	A method for clearing all of the input and output fields.
	*
	*/
	public void clearAllFields(){
		operandOne.setText(CLEAR_VALUE);
		operandTwo.setText(CLEAR_VALUE);
		resultField.setText(CLEAR_VALUE);
	}
	/**
	*	A method for outputing an error messaged in the specified 
	*	text field.
	*
	*	@param name The name of the text field in which the error message is to be displayed
	*/
	public void error(JTextField name){
		name.setText(ERROR);
		name.setForeground(Color.RED);
	}

	/**
	*	The constructor for the calculator. Once an instance of the calculator is created
	*	this constructor will build the calculator for displaying on screen.
	*	It adds 3 panels to a JFrame. The first of which contains the two input
	*	text fields, the second contans the operator buttons and the thrd contains
	*	the clear all fields button and the output/result feld.
	*/
	public Calculator(){
		final JFrame frame = new JFrame("Calculator");
		frame.setSize(400,400);
		frame.setMinimumSize(new Dimension(400,400));
		frame.setLocationRelativeTo(null);
		frame.setTitle("Calculator");
		frame.setLayout(new GridLayout(3,1));
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		operandPanel = new JPanel();
		operatorPanel = new JPanel();
		clearAllAndResultPanel = new JPanel();

		frame.add(operandPanel);
		frame.add(operatorPanel);
		frame.add(clearAllAndResultPanel);

		//text fields for the input operands 
		operandPanel.setLayout(new BoxLayout(operandPanel,BoxLayout.X_AXIS));
		operandOne = new JTextField();
		operandOne.setHorizontalAlignment(JTextField.CENTER);
		operandPanel.add(operandOne);
		operandTwo = new JTextField();
		operandTwo.setHorizontalAlignment(JTextField.CENTER);
		operandPanel.add(operandTwo);

		//buttons for the operations
		operatorPanel.setLayout(new GridLayout(3,3));
		addOperatorButton(addButton,"add","binary");
		addOperatorButton(subtractButton, "subtract","binary");
		addOperatorButton(unaryPlusButton, "unaryPlus","unary");
		addOperatorButton(unaryMinusButton, "unaryMinus","unary");
		addOperatorButton(multiplyButton, "multiply","binary");
		addOperatorButton(divideButton, "divide","binary");
		addOperatorButton(squareRootButton, "squareRoot","unary");
		addOperatorButton(exponentiateButton, "exponentiate","binary");
		addOperatorButton(reciprocalButton, "reciprocal","unary");

		//clear text fields button and output field
		clearAllAndResultPanel.setLayout(new GridLayout(1,1));
		clearAllButton = new Button("Clear","Tool");
		clearAllAndResultPanel.add(clearAllButton);
		resultField = new JTextField();
		resultField.setHorizontalAlignment(JTextField.CENTER);
		clearAllAndResultPanel.add(resultField);

		frame.setVisible(true);

	}


	/**
	*	This class listens for information from the users inputs and processes them accordingly
	*	using the information associated with any of the users inputs. IE pushing add passes
	*	the add buttons information as well as all associated information such as 
	* 	operands from the texts fields to the actionlistener which performs the action of adding 
	*	the two operands together assuming the inouts were correct.
	*
	*	It also extends the JButton class and implements the ActionListener interface.
	*
	*/
	private class Button extends JButton implements ActionListener{
		private final String operator;
		private final String operationType;

		/**
		*	Constructor for button takes the operator and operation type 
		*	given by the button for use in actionPerformed
		*
		*	@param operator A string representing the requested operator
		*	@param operationType A string representing the operation type IE unary or binary
		*/
		private Button(final String operator,final String operationType){
			super(operator);
			addActionListener(this);
			this.operator = operator;
			this.operationType = operationType;
		}


		/**
		*	Carrys out the requested computation made by the user.
		*
		*	@param even The event that occured IE the button that was pressed.
		*/
		@Override
		public void actionPerformed(final ActionEvent event){
			double firstOperand, secondOperand;
			double result = 0;
			String resultAsString;
			//If the user selects clear then set all text fields to an empty string
			if(operator.equals("Clear")){
				clearAllFields();
			}
			else{
				//ensure the first operand is a valid double.
				try{
					firstOperand = Double.parseDouble(operandOne.getText() );
					operandOne.setForeground(Color.BLACK);
				}
				catch(NumberFormatException incorrectInput){
					error(operandOne);
					return;
				}
				//if the operation is a unary operation enter this brach of if statements
				if(operationType.equals("unary")){
					//carry out the requested computation on the first operand only
					if(operator.equals("unaryPlus")){
						result = unaryPlus.apply(firstOperand);
					}
					else if(operator.equals("unaryMinus")){
						result = unaryMinus.apply(firstOperand);
					}
					else if(operator.equals("squareRoot") && firstOperand >= 0){
						result = squareRoot.apply(firstOperand);
					}
					else if(operator.equals("reciprocal") && firstOperand != 0){
						result = reciprocal.apply(firstOperand);
					}
					else{
						error(operandOne);
					}
					//clear second operand field to show the operation is being performed on
					//the first operand only
					operandTwo.setText("");
				}
				//else the operation is a binary operation
				else if(operationType.equals("binary")){
					//ensure the second operand is a valid double
					try{
						secondOperand = Double.parseDouble(operandTwo.getText() );
						operandTwo.setForeground(Color.BLACK);
					}
					catch(NumberFormatException incorrectInput){
						error(operandTwo);
						return;
					}
					//carry out the requested computation
					if(operator.equals("add")){
						result = adder.apply(firstOperand,secondOperand);
					}
					else if(operator.equals("subtract")){
						result = subtractor.apply(firstOperand,secondOperand);
					}
					else if(operator.equals("multiply")){
						result = multiplier.apply(firstOperand,secondOperand);
					}
					else if(operator.equals("exponentiate")){
						result = exponentiate.apply(firstOperand,secondOperand);
					}
					else if(operator.equals("divide")){
						if(secondOperand != 0){
							result = divider.apply(firstOperand,secondOperand);
						}else{
							error(operandTwo);
						}
					}
				}
				//convert the result back into string format and place it into the result/output field
				resultAsString = Double.toString(result);
				resultField.setText(resultAsString);
			}
		}
	}



	/**
	*	Creates an instance of the Calculator class.
	*
	*	@param args The given command line arguments 
	*/
	public static void main(String[] args){
		Calculator calculator = new Calculator();

	}


}
