package Assignment2;

/*
 * CS3560 Assignment 2
 * Patriz Elaine Daroy
 * 
 * Positive Percent class that implements the
 * Visitable interface that helps to implement
 * the Visitor pattern.
 */

public class PositivePercent implements Visitable {

	@Override
	public void accept(TotalVisitor visitor) {
		visitor.visitPos(this);
		
	}


}
