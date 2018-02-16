package com.nomis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Module {
	
	private List<String> program;
	private Map<String, Function> functions = new HashMap<String, Function>();
	private LRuntime runtime;
	
	public Module(List<String> program, LRuntime runtime) {
		this.program = program;
		this.runtime = runtime;
		process();
	}
	
	public void process() {
		boolean inFunction = false;
		String functionName = "";
		List<String> currentFunction = new ArrayList<String>();
		
		for(String line : program) {
			if(line.isEmpty()) continue;
			if(line.charAt(0) == ';') continue;
			line = line.trim();
			
			if(!inFunction) {
				if(line.charAt(0) == '|') {
					String[] parts = line.split("\\|");
					String moduleName = parts[1];
					runtime.loadModule(moduleName);
				} else if(line.charAt(0) == '>') {
					inFunction = true;
					functionName = line.replace(">", "");
					System.out.println("Start function: " + functionName);
				}	
			} else {
				if(line.charAt(0) == '>') {
					functions.put(functionName, new Function(currentFunction));
					currentFunction = new ArrayList<String>();
					inFunction = false;
					System.out.println("Ending function: " + functionName);
				} else {
					currentFunction.add(line);
				}
			}
		}
	}
	
	public Function getFunction(String name) {
		return functions.get(name);
	}
	
	public void printFunctions() {
		for(String name : functions.keySet()) {
			System.out.println("  func: " + name + ", " + functions.get(name).getLines().size() + " lines.");
		}
	}
}
