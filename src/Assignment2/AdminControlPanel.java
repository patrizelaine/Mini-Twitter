package Assignment2;

/*
 * CS3560 Assignment 2
 * Patriz Elaine Daroy
 * 
 * AdminControlPanel that uses the Singleton 
 * pattern and creates the components for the
 * panel's GUI.
 */

import java.awt.Color;
import java.awt.event.*;
import java.util.HashMap;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.tree.*;

// @SuppressWarnings("serial") -- is this needed?
public class AdminControlPanel extends JFrame {
	
	// GUI components
	private JPanel panel;
	private JTree tree;
	private String userId, groupId;
	private JTextArea userIdField, groupIdField;
	private JButton lastUserButton, verifyIdButton, addUserButton, addGroupButton, openUserViewButton, userTotButton, groupTotButton, messageTotButton, positiveButton;
	private static HashMap<String, User> totalUsers = new HashMap<String,User>();
	private HashMap<String, Group> totalGroups = new HashMap<String,Group>();
	private DefaultMutableTreeNode currentNode, root;
	
	// Singleton pattern
	private static AdminControlPanel instance = null;
	
	// AdminControlPanel instance through Singleton pattern
	public static AdminControlPanel getInstance(){
		if (instance == null)
		{
			synchronized(AdminControlPanel.class)
			{
				if(instance == null)
				{
					instance = new AdminControlPanel();
				}
			}
		}
		return instance;
	}
	
	private AdminControlPanel()
	{
		// Colors
		Border border = BorderFactory.createLineBorder(Color.GRAY, 3);
		
		// Admin control panel GUI
		panel = new JPanel();
		setTitle("Mini Twitter");
		panel.setBackground(new Color(176,224,230));
		setBackground(Color.pink);
		setBounds(50, 50, 650, 350);
		setContentPane(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		panel.setLayout(null);	
		
		// Tree of users and groups
		root = new DefaultMutableTreeNode("Root");
		tree = new JTree(root);
		// tree.setModel(new DefaultTreeModel(root));
		tree.setBounds(15,10,200,300);
		panel.add(tree);
		
		// User ID field
		userIdField = new JTextArea();
		userIdField.setBounds(230, 10, 230, 25);
		userIdField.setBorder(border);
		panel.add(userIdField);
		
		// User ID button
		addUserButton = new JButton("Add User");
		addUserButton.setBounds(470, 10, 160, 25);
		addUserButton.addActionListener(new addUserListener());
		panel.add(addUserButton);
		
		// Group field
		groupIdField = new JTextArea();
		groupIdField.setBounds(230, 50, 230, 25);
		groupIdField.setBorder(border);
		panel.add(groupIdField);

		// Group button
		addGroupButton = new JButton("Add Group");
		addGroupButton.setBounds(470, 50, 160, 25);
		addGroupButton.addActionListener(new addGroupListener());
		panel.add(addGroupButton);
		
		// Open user view button
		openUserViewButton = new JButton("Open User View");
		openUserViewButton.setBounds(230,90,400,25);
		openUserViewButton.addActionListener(new openUserViewListener());
		panel.add(openUserViewButton);
		
		// Last updated user button
		lastUserButton = new JButton("Last Updated User");
		lastUserButton.setBounds(230, 220, 200, 25);
		lastUserButton.addActionListener(new lastUserListener());
		panel.add(lastUserButton);
		
		// User and group ID verification button
		verifyIdButton = new JButton("Verify IDs");
		verifyIdButton.setBounds(430, 220, 200, 25);
		verifyIdButton.addActionListener(new verifyIdListener());
		panel.add(verifyIdButton);
		
		// Show user total button
		userTotButton = new JButton("Show User Total");
		userTotButton.setBounds(230, 250, 200, 25);
		userTotButton.addActionListener(new userTotListener());
		panel.add(userTotButton);
		
		// Show group total button
		groupTotButton = new JButton("Show Group Total");
		groupTotButton.setBounds(430, 250, 200, 25);
		groupTotButton.addActionListener(new groupTotListener());
		panel.add(groupTotButton);
		
		// Show messages total button
		messageTotButton = new JButton("Show Messages Total");
		messageTotButton.setBounds(230, 280, 200, 25);
		messageTotButton.addActionListener(new tweetTotListener());
		panel.add(messageTotButton);
		
		// Show positive percentage button
		positiveButton = new JButton("Show Positive Percentage");
		positiveButton.setBounds(430, 280, 200, 25);
		positiveButton.addActionListener(new positiveListener());
		panel.add(positiveButton);
	}
	
	public static HashMap<String,User> getUsers()
	{
		return totalUsers;
	}
	
	private class lastUserListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			IncrementingVisitor lastUser = new IncrementingVisitor();
			String last = lastUser.getLastUser();
			if(last==null)
			{
	        	JOptionPane.showMessageDialog(null, "None of the users have an update.");

			}
			else
			{
				JOptionPane.showMessageDialog(null, "Last updated user: " + last);
			}
		}
	}
	
	private class verifyIdListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(totalGroups.isEmpty() && totalUsers.isEmpty())
			{
				JOptionPane.showMessageDialog(null, "There are no IDs to validate.");
			}
			
			// Checks if group IDs contain a space
			for (Map.Entry groupElement : totalGroups.entrySet())
			{
				String groupKey = (String)groupElement.getKey();
				if(groupKey.contains(" "))
				{
					JOptionPane.showMessageDialog(null, "Not all IDs are valid.");
	            	break;
				}
			}
			for (Map.Entry userElement : totalUsers.entrySet()) { 
	            String userKey = (String)userElement.getKey(); 
	            
	            // Checks if IDs are unique among users and groups
	            if(totalGroups.containsKey(userKey))
	            {
	            	JOptionPane.showMessageDialog(null, "Not all IDs are valid.");
	            	break;
	            }
	            // Checks if IDs contain a space
	            if(userKey.contains(" "))
	            {
	            	JOptionPane.showMessageDialog(null, "Not all IDs are valid.");
	            	break;
	            }
	            // Else all IDs are valid
	            else
	            {
	            	JOptionPane.showMessageDialog(null, "All IDs are valid.");
	            }
	        }
		}
	}
	
	private class addUserListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			userId = userIdField.getText();
			currentNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
			
			if(userId.isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Enter a username to add.");
			}
			else if(totalUsers.containsKey(userId))
			{
				JOptionPane.showMessageDialog(null, "User already exists.");
			}
			else if(currentNode==null)
			{
				JOptionPane.showMessageDialog(null, "You can only add a user to a group or root.");
			}
			else if(totalUsers.containsKey(currentNode.toString()))
			{
				JOptionPane.showMessageDialog(null, "You can only add a user to a group or root.");
			}
			else
			{
				Long timestamp = System.currentTimeMillis();
				User user = new User(userId);
				user.setCreationTime(timestamp);
				user.setColor(Color.black);
				totalUsers.put(userId, user);
				TotalVisitor newUser = new IncrementingVisitor();
				user.accept(newUser);
				
				if(currentNode==null)	// Adding FIRST user
				{
					root.add(new DefaultMutableTreeNode(userId));
				}
				else	// Adding second and more users
				{
					currentNode.add(new DefaultMutableTreeNode(userId));
				}
				((DefaultTreeModel)tree.getModel()).reload();
				JOptionPane.showMessageDialog(null, "User successfully added!");
			}
		}
	}
	
	private class addGroupListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			groupId = groupIdField.getText();
			currentNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
			
			if(groupId.isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Enter a group name to add.");
			}
			else if(totalGroups.containsKey(groupId))
			{
				JOptionPane.showMessageDialog(null, "Group already exists.");
			}
			else if(currentNode==null)
			{
				JOptionPane.showMessageDialog(null, "You can only add a group in an existing group or to root.");
			}
			else if(totalUsers.containsKey(currentNode.toString()))
			{
				JOptionPane.showMessageDialog(null, "You can only add a group in an existing group or to root.");
			}
			else
			{
				// Creating a new group
				Long timestamp = System.currentTimeMillis();
				Group group = new Group(groupId);
				group.setCreationTime(timestamp);
				
				totalGroups.put(groupId, group);
				TotalVisitor newUser = new IncrementingVisitor();
				group.accept(newUser);
				
				if(currentNode==null)
				{
					root.add(new DefaultMutableTreeNode(groupId));
				}
				else
				{
					currentNode.add(new DefaultMutableTreeNode(groupId));
				}
				((DefaultTreeModel)tree.getModel()).reload();
				JOptionPane.showMessageDialog(null, "Group successfully added!");
			}
		}
	}
	
	private class openUserViewListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			currentNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
			if(currentNode==null)
			{
				JOptionPane.showMessageDialog(null, "You must select a user to view.");
			}
			else if(totalGroups.containsKey(currentNode.toString()) || currentNode.toString().equals("Root"))
			{
				JOptionPane.showMessageDialog(null, "You cannot view a group. Please select a user to view.");
			}
			else
			{
				User user = totalUsers.get(currentNode.toString());
				UserView uv = new UserView(user);
				uv.setVisible(true);
			}
		}
	}
	
	private class userTotListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			IncrementingVisitor userTot = new IncrementingVisitor();
			JOptionPane.showMessageDialog(null, "Total Users: " + userTot.getUserTot());
		}
	}
	
	private class groupTotListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			IncrementingVisitor groupTot = new IncrementingVisitor();
			JOptionPane.showMessageDialog(null, "Total Groups: " + groupTot.getGroupTot());
		}
	}
	
	private class tweetTotListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			IncrementingVisitor tweetTot = new IncrementingVisitor();
			JOptionPane.showMessageDialog(null, "Total Tweets: " + tweetTot.getTweetTot());
		}
	}
	
	private class positiveListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			IncrementingVisitor posTot = new IncrementingVisitor();
			IncrementingVisitor tweetVisitor = new IncrementingVisitor();
			double numTot = tweetVisitor.getTweetTot();
			double numPos = posTot.getPosTot();
			JOptionPane.showMessageDialog(null, "Positive Tweets: " + (numPos/numTot)*100 + "%");
		}
	}
}
