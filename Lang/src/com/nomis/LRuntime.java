package com.nomis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LRuntime {
	
	private Map<String, Module> modules = new HashMap<String, Module>();
	
	public LRuntime() {
		loadModule("main");
	}
	
	public void run() {
		modules.get("main").getFunction("main").run();
	}
	
	public void call(String module, String name) {
		
	}
	
	public void loadModule(String name) {
		if(modules.containsKey(name)) {
			System.out.println("Already loaded module '" + name + "'. Not loading again.");
			return;
		}
		try {
			System.out.println("Loading module: " + name);
			List<String> lines = Files.readAllLines(Paths.get(name + ".prg"));
			modules.put(name, new Module(lines, this));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void printModules() {
		for(String name : modules.keySet()) {
			Module mod = modules.get(name);
			System.out.println("'" + name + "': ");
			mod.printFunctions();
		}
	}
}
