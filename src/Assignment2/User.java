package Assignment2;

/*
 * CS3560 Assignment 2
 * Patriz Elaine Daroy
 * 
 * User class that implements the Visitable interface
 * for the Visitor pattern, TwitterEntry interface for 
 * the Composite pattern, and Observer & Subject
 * interfaces for the Observer pattern.
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

public class User implements Visitable, TwitterEntry, Observer, Subject {
	
	private String userId;
	private Color color;
	private ArrayList<String> followers;
	private ArrayList<String> followings;
	private ArrayList<String> feed;
	private HashMap<String,User> twitterUsers = AdminControlPanel.getUsers();
	
	public User getUser()
	{
		return User.this;
	}
	
	public void setColor(Color str)
	{
		color = str;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public User(String id)
	{
		userId = id;
		followings = new ArrayList<String>();
		followers = new ArrayList<String>();
		feed = new ArrayList<String>();
	}

	public ArrayList<String> getFeed() {
		return feed;
	}
	
	public void addFollower(String follower)
	{
		followers.add(follower);
	}

	public ArrayList<String> getFollowers() {
		return followers;
	}
	
	public void addFollowing(String following)
	{
		followings.add(following);
	}

	public ArrayList<String> getFollowings() {
		return followings;
	}

	@Override
	public void accept(TotalVisitor visitor) {
		visitor.visitUser(this);
	}

	@Override
	public String getId() {
		return userId;
	}


	@Override
	public void update(String tweet) {
		feed.add(tweet);
	}


	// Attaches the corresponding following and follower to the users at hand
	@Override
	public void attach(String userId) {
		getUser().addFollowing(userId);  
		twitterUsers.get(userId).addFollower(getUser().getId());
	}


	// Notifies followers of a new follower and also updates their newsfeed 
	@Override
	public void notifyObservers(String tweet) {
		int numFollowers = getUser().getFollowers().size();
		for (int i=0; i<numFollowers; i++)
		{
			// Gets the user ID of all of the followers
			String userFollower = getUser().getFollowers().get(i);
			
			User currentUser = twitterUsers.get(userFollower);
			currentUser.update(tweet);
		}
	}

}
