package Assignment2;

/*
 * CS3560 Assignment 2
 * Patriz Elaine Daroy
 * 
 * Group class that helps implement the 
 * Visitor and Composite pattern.
 */

import java.util.List;

public class Group implements Visitable, TwitterEntry {
	
	private String groupId;
	private Long creationTime;
	
	// A group can hold a list of Users and Groups
	private List<TwitterEntry> entries;
	
	public Group(String id)
	{
		groupId = id;
	}

	@Override
	public void accept(TotalVisitor visitor) {
		visitor.visitGroup(this);
	}
	
	public List<TwitterEntry> getEntries()
	{
		return entries;
	}

	@Override
	public String getId() {
		return groupId;
	}

	@Override
	public void setCreationTime(Long time) {
		creationTime = time;
	}

	@Override
	public Long getCreationTime() {
		return creationTime;
	}
}
