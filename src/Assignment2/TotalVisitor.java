package Assignment2;

/*
 * CS3560 Assignment 2
 * Patriz Elaine Daroy
 * 
 * TotalVisitor interface that is implemented by
 * the IncrementingVisitor interface used for the
 * Visitor pattern.
 */

public interface TotalVisitor {
	
	public void visitUser(User user);
	public void visitGroup(Group group);
	public void visitTweet(Tweet tweet);
	public void visitPos(PositivePercent posPercent);

}
