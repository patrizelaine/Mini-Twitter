package Assignment2;

import java.util.ArrayList;

/*
 * CS3560 Assignment 2
 * Patriz Elaine Daroy
 * 
 * Last User class that implements the
 * Visitable interface that helps to implement
 * the Visitor pattern.
 */

public class LastUser implements Visitable {
	
	private String userId;


	@Override
	public void accept(TotalVisitor visitor) {
		visitor.visitLastUser(this);
	}
	
	public LastUser(String id)
	{
		userId = id;
	}
	
	public String getUserId()
	{
		return userId;
	}


}
