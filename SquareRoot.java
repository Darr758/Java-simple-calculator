/**
* This class implements the BinaryOperations interface and defines the apply method for Square root.
*
* @author Darragh King (ID 113372871)
*/

public class SquareRoot implements UnaryOperations{
	/**
	*	Overrides apply to be used for Square Root.
	*
	*	@param firstOperand The first operand entered into the calculator by 
	*			the user.
	*	@return A double representing the result of the requested computation.
	*/


	@Override
	public double apply(final double firstOperand){
		double result = Math.sqrt(firstOperand);
		return result;
	}
}
