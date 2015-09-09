/**
* This class implements the BinaryOperations interface and defines the apply method for Divide.
*
* @author Darragh King (ID 113372871)
*/

public class Divider implements BinaryOperations{
	/**
	*	Overrides apply to be used for Divide.
	*
	*	@param firstOperand The first operand entered into the calculator by 
	*			the user.
	*	@param secondOperand The second operand entered into the calculator 
	*			by the user.
	*	@return A double representing the result of the requested computation.
	*/


	@Override
	public double apply(final double firstOperand,final double secondOperand){
		double result = firstOperand / secondOperand;
		
		return result;
	}
}
