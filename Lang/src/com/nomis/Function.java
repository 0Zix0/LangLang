package com.nomis;

import java.util.List;

public class Function {

	private List<String> lines;
	
	public Function(List<String> lines) {
		this.lines = lines;
	}
	
	public List<String> getLines() {
		return lines;
	}
	
	public void run() {
		for(String line : lines) {
			if(line.charAt(0) == ':') {
				// Function call
				String[] parts = line.split(":");
				System.out.println("Calling " + parts[1] + "." + parts[2]);
			}
		}
	}
}
