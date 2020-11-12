package Assignment2;

/*
 * CS3560 Assignment 2
 * Patriz Elaine Daroy
 * 
 * Visitable is the interface for the Tweet,
 * User, Group, and PositivePercent.
 */

public interface Visitable {
	
	public void accept(TotalVisitor visitor);

}
