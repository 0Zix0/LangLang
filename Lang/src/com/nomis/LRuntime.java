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
	
	private int[] registers = new int[10];
	private int[] ports = new int[10];
	
	public LRuntime() {
		loadModule("main");
	}
	
	public void run() {
		modules.get("main").getFunction("main").run();
	}
	
	public void map(int register, int port) {
		ports[port] = register;
	}
	
	public void store(int port, int value) {
		registers[ports[port]] = value;
	}
	
	public void multiply() {
		registers[ports[2]] = registers[ports[0]] * registers[ports[1]];
	}
	
	public void call(String module, String name) {
		modules.get(module).getFunction(name).run();
	}
	
	public void loadModule(String name) {
		if(modules.containsKey(name)) {
			System.out.println("Already loaded module '" + name + "'. Not loading again.");
			return;
		}
		try {
			System.out.println("Loading module: " + name);
			List<String> lines = Files.readAllLines(Paths.get(name + ".prg"));
			modules.put(name, new Module(lines, this, name));
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
	
	public void printRegisters() {
		for(int i = 0; i < registers.length; i++) {
			System.out.println("R" + i + " = " + registers[i] + "  " + "P" + i + " = " + ports[i]);
		}
	}

	public void output(int port) {
		System.out.println(registers[ports[port]]);
	}
}
