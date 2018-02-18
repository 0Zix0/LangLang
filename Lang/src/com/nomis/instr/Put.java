package com.nomis.instr;

import com.nomis.LRuntime;

public class Put extends Instruction {

	private int port;
	private int value;
	
	public Put(int port, int value) {
		this.port = port;
		this.value = value;
	}

	public void run(LRuntime runtime) {
		runtime.store(port, value);
	}
}
