package com.nomis.instr;

import com.nomis.LRuntime;

public class Output extends Instruction {

	private int port;
	
	public Output(int port) {
		this.port = port;
	}

	public void run(LRuntime runtime) {
		runtime.output(port);
	}
}
