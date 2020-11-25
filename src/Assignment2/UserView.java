package Assignment2;

/*
 * CS3560 Assignment 2
 * Patriz Elaine Daroy
 * 
 * UserView class that serves as the
 * UserView GUI for each Twitter user.
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.Border;

public class UserView extends JFrame
{
	private JPanel userPanel;
	private JTextArea followIdField, tweetField;
	private JLabel followLabel, newsLabel, followerLabel, timeCreationLabel;
	private JButton followIdButton, tweetButton;
	private JList followingsList, newsFeedList, followersList;
	private DefaultListModel followingsModel, newsFeedModel, followersModel;
	private String newFollowing, newTweet, lowercaseTweet;
	private ArrayList<String> userFollowingsList, userFeedList, userFollowersList;
	private User currentUser;
	private HashMap<String,User> twitterUsers = AdminControlPanel.getUsers();
	
	public UserView(User user)
	{	
		currentUser = user.getUser();
		
		// Colors
		Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
		Border border2 = BorderFactory.createLineBorder(Color.GRAY, 3);
		
		// Admin control panel GUI
		userPanel = new JPanel();
		setTitle(user.getId() + "'s Twitter Page");
		setBounds(50, 50, 440, 500);
		setContentPane(userPanel);
		userPanel.setBackground(currentUser.getColor());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		userPanel.setLayout(null);	
		
		String color = userPanel.getBackground().toString();
		
		// If the user is being opened for the first time, it sets the background color randomly
		if(color.equals("java.awt.Color[r=0,g=0,b=0]"))
		{
			int rand = (int) ( Math.random() * 2 + 1);
			// Sets to orange
			if(rand==1)
			{
				currentUser.setColor(new Color(255,192,203));
				userPanel.setBackground(new Color(255,192,203));
			}
			// Sets to pink
			else
			{
				currentUser.setColor(new Color(255,255,177));
				userPanel.setBackground(new Color(255,255,177));
			}
		}
		
		// Field to enter a user to follow
		followIdField = new JTextArea();
		followIdField.setBounds(20, 15, 230, 25);
		followIdField.setBorder(border2);
		userPanel.add(followIdField);
				
		// Follow button
		followIdButton = new JButton("Follow User");
		followIdButton.setBounds(250, 15, 160, 25);
		followIdButton.addActionListener(new FollowIdListener());
		userPanel.add(followIdButton);
		
		// Followings label
		followLabel = new JLabel("Following:");
		followLabel.setBounds(20, 55, 110, 25);
		userPanel.add(followLabel);
		
		// Showing list of the user's followings
		followingsModel = new DefaultListModel();
		followingsList = new JList(followingsModel);
		followingsList.setBounds(20,80,190,150);
		followingsList.setBorder(border);
		userFollowingsList = (ArrayList<String>)currentUser.getFollowings().clone();
		userPanel.add(followingsList);
		
		// Loops through the list of followings
		for(int i=0; i<userFollowingsList.size(); i++)
		{
			followingsModel.addElement(userFollowingsList.get(i));
		}
	
		// Followers label
		followerLabel = new JLabel("Followers:");
		followerLabel.setBounds(220, 55, 110, 25);
		userPanel.add(followerLabel);
				
		// Showing list of the user's followings
		followersModel = new DefaultListModel();
		followersList = new JList(followersModel);
		followersList.setBounds(220,80,190,150);
		followersList.setBorder(border);
		userFollowersList = (ArrayList<String>)currentUser.getFollowers().clone();
		userPanel.add(followersList);
		
		// Loops through the list of followers
		for(int i=0; i<userFollowersList.size(); i++)
		{
			followersModel.addElement(userFollowersList.get(i));
		}
			
		// Field to enter a tweet
		tweetField = new JTextArea();
		tweetField.setBounds(20, 240, 230, 25);
		tweetField.setBorder(border2);
		userPanel.add(tweetField);
		
		// Tweet button
		tweetButton = new JButton("Post Tweet");
		tweetButton.setBounds(250, 240, 160, 25);
		tweetButton.addActionListener(new TweetListener());
		userPanel.add(tweetButton);
		
		// Followings label
		newsLabel = new JLabel("News Feed:");
		newsLabel.setBounds(20, 275, 230, 25);
		userPanel.add(newsLabel);
		
		// Followings label
		newsLabel = new JLabel("Last updated: " + currentUser.getUpdate());
		newsLabel.setBounds(220, 275, 230, 25);
		userPanel.add(newsLabel);
				
		// Showing the user's newsfeed
		newsFeedModel = new DefaultListModel();
		newsFeedList = new JList(newsFeedModel);
		newsFeedList.setBounds(20,300,400,150);
		newsFeedList.setBorder(border);
		userFeedList = (ArrayList<String>)currentUser.getFeed().clone();
		userPanel.add(newsFeedList);
		
		// Loops through the list of tweets to go on the newsfeed
		for(int i=0; i<userFeedList.size(); i++)
		{
			newsFeedModel.addElement(userFeedList.get(i));
		}
		
		// Time creation label
		timeCreationLabel = new JLabel("User created at " + currentUser.getCreationTime());
		timeCreationLabel.setBounds(20, 455, 230, 25);
		userPanel.add(timeCreationLabel);
	}
	
	private class FollowIdListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			// Checks conditions to follow a user
			newFollowing = followIdField.getText();
			if(newFollowing.equals(currentUser.getId()))
			{
				JOptionPane.showMessageDialog(null, "You can't follow yourself.");
			}
			else if(!twitterUsers.containsKey(newFollowing))
			{
				JOptionPane.showMessageDialog(null, "That user doesn't exist.");
			}
			else if(newFollowing.isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Enter a user to follow.");
			}
			else
			{
				currentUser.attach(newFollowing);
				JOptionPane.showMessageDialog(null, "You are following " + newFollowing + " now!");
				followingsModel.addElement(newFollowing);
			}
		}
	}
	
	private class TweetListener implements ActionListener
	{
		// List of positive words
		String[] positiveWords = {"yay", "happy", "congratulations", "great", "wow", "amazing", "thanks"};
		
		@Override
		public void actionPerformed(ActionEvent e) {
			newTweet = currentUser.getId() + ": " + tweetField.getText();
			lowercaseTweet = tweetField.getText().toLowerCase();
			if(newTweet.isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Enter something to tweet.");
			}
			else
			{
				// Sets the update time of the newsfeed
				Long timestamp = System.currentTimeMillis();
				
				currentUser.notifyObservers(newTweet);
				currentUser.update(newTweet, timestamp);
				newsFeedModel.addElement(newTweet);
				
				LastUser lastUser = new LastUser(currentUser.getId());
				TotalVisitor updateLastUser = new IncrementingVisitor();
				lastUser.accept(updateLastUser);
				
				Tweet tweet = new Tweet();
				TotalVisitor incrementTweet = new IncrementingVisitor();
				tweet.accept(incrementTweet);
			}
			
			// Visitor pattern to check positive words in a tweet
			PositivePercent posPercent = new PositivePercent();
			for(int i=0; i<positiveWords.length; i++)
			{
				if(lowercaseTweet.contains(positiveWords[i]))
				{
					TotalVisitor incrementPositive = new IncrementingVisitor();
					posPercent.accept(incrementPositive);;
				}
			}
			
		}
	}
	
	

}
