/**
* This class implements the UnaryOperations interface and defines the apply method for Reciprocal.
*
* @author Darragh King (ID 113372871)
*/

public class Reciprocal implements UnaryOperations{
	/**
	*	Overrides apply to be used for Reciprocal.
	*
	*	@param firstOperand The first operand entered into the calculator by 
	*			the user.
	*	@return A double representing the result of the requested computation.
	*/

	@Override
	public double apply(final double firstOperand){
		double result = 1 / firstOperand;
		return result;
	}
}
