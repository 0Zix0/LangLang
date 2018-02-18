package com.nomis;

import java.util.ArrayList;
import java.util.List;

import com.nomis.instr.FunctionCall;
import com.nomis.instr.Instruction;
import com.nomis.instr.Map;
import com.nomis.instr.Multiply;
import com.nomis.instr.Output;
import com.nomis.instr.Put;

public class Function {

	private List<Instruction> instructions = new ArrayList<Instruction>();
	private Module module;	
	
	public Function(List<Instruction> instructions, Module module) {
		this.instructions = instructions;
		this.module = module;
	}
	
	public List<Instruction> getInstructions() {
		return instructions;
	}
	
	public static Function construct(List<String> lines, Module module) {
		
		List<Instruction> instructions = new ArrayList<Instruction>();
		
		for(String line : lines) {
			if(line.charAt(0) == ':') {
				// Function call
				String[] parts = line.split(":");
				String moduleName = parts[1];
				String funcName = parts[2];
				
				if(moduleName.equalsIgnoreCase("this")) {
					moduleName = module.getName();
				}
				
				instructions.add(new FunctionCall(moduleName, funcName));		
			} else if(line.charAt(0) == 'p') {
				if(line.length() != 3) {
					System.out.println("ERROR PLEASE HANDLE ME");
				} else {
					int port = Integer.parseInt(line.charAt(1) + "");
					int value = Integer.parseInt(line.charAt(2) + "");
					instructions.add(new Put(port, value));
				}
			} else if(line.charAt(0) == 's') {
				if(line.length() != 3) {
					System.out.println("ERROR PLEASE HANDLE ME");
				} else {
					int register = Integer.parseInt(line.charAt(1) + "");
					int port = Integer.parseInt(line.charAt(2) + "");
					instructions.add(new Map(register, port));
				}
			} else if(line.charAt(0) == 'm') {
				instructions.add(new Multiply());
			} else if(line.charAt(0) == 'o') {
				if(line.length() != 2) {
					System.out.println("ERROR PLEASE HANDLE ME");
				} else {
					int port = Integer.parseInt(line.charAt(1) + "");
					instructions.add(new Output(port));
				}
			} else {
				System.out.println(line);
			}
		}
		
		return new Function(instructions, module);
	}
	
	public void run() {
		for(Instruction instr : instructions) {
			instr.run(module.getRuntime());
		}
	}
}
