package Assignment2;

/*
 * CS3560 Assignment 2
 * Patriz Elaine Daroy
 * 
 * Driver class that begins the Mini Twitter
 * program with Singleton
 */

public class Driver 
{
	public static void main(String[] args)
	{
		AdminControlPanel.getInstance().setVisible(true);
	}
}
