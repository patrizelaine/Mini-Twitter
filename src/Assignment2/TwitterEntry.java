package Assignment2;

/*
 * CS3560 Assignment 2
 * Patriz Elaine Daroy
 * 
 * Interface to implement the Composite pattern.
 * This is implemented by User and Group.
 */

public interface TwitterEntry {
	
	public String getId();
	public void setCreationTime(Long time);
	public Long getCreationTime();

}
