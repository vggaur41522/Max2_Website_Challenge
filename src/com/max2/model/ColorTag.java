package com.max2.model;
import java.util.List;

// This class is used for Question 5 API with structure :
// "color": "<>",
// "count": <>,
// "names": ["<>", "<>"]
public class ColorTag {
	private int count;
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	private List<String> names; 

	public ColorTag(int count, List<String> names) {
		this.count = count;
		this.names = names;
	}
	
	public String toString()
	{
		return this.count + " | " +this.names;
	}
}
