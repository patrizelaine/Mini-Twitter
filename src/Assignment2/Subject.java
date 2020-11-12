package Assignment2;

/*
 * CS3560 Assignment 2
 * Patriz Elaine Daroy
 * 
 * Subject interface that is implemented
 * by the User class to help implement
 * the Observer pattern.
 */

public interface Subject {
	
	public void attach(String userId);
	
	public void notifyObservers(String tweet);


}
