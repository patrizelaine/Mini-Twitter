package Assignment2;

/*
 * CS3560 Assignment 2
 * Patriz Elaine Daroy
 * 
 * Tweet class that implements the Visitable
 * interface to help implement the Visitor 
 * pattern.
 */

public class Tweet implements Visitable {

	@Override
	public void accept(TotalVisitor visitor) {
		visitor.visitTweet(this);
		
	}

}
