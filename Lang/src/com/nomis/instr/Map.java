package com.nomis.instr;

import com.nomis.LRuntime;

public class Map extends Instruction {

	private int register;
	private int port;
	
	public Map(int register, int port) {
		this.register = register;
		this.port = port;
	}

	public void run(LRuntime runtime) {
		runtime.map(register, port);
	}
}
