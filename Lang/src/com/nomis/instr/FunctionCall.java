package com.nomis.instr;

import com.nomis.LRuntime;

public class FunctionCall extends Instruction {

	private String module;
	private String name;
	
	public FunctionCall(String module, String name) {
		this.module = module;
		this.name = name;
	}

	public void run(LRuntime runtime) {
		runtime.call(module, name);
	}
}
