package Assignment2;

/*
 * CS3560 Assignment 2
 * Patriz Elaine Daroy
 * 
 * IncrementingVisitor class that implements
 * the TotalVisitor interface to help
 * implement the Visitor pattern to the Group,
 * User, Tweet, and PositivePercent classes.
 */

public class IncrementingVisitor implements TotalVisitor 
{
	
	private static int userTot;
	private static int groupTot;
	private static int tweetTot;
	private static int posTot;

	@Override
	public void visitUser(User user) {
		userTot++;
	}
	
	public int getUserTot()
	{
		return userTot;
	}

	@Override
	public void visitGroup(Group group) {
		groupTot++;
		
	}
	
	public int getGroupTot()
	{
		return groupTot;
	}

	@Override
	public void visitTweet(Tweet tweet) {
		tweetTot++;
		
	}
	
	public int getTweetTot()
	{
		return tweetTot;
	}

	@Override
	public void visitPos(PositivePercent posPercent) {
		posTot++;
		
	}
	
	public int getPosTot()
	{
		return posTot;
	}

}
