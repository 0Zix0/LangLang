package com.nomis;

public class Main {

	public static void main(String[] args) throws Exception {
		LRuntime runtime = new LRuntime();
		runtime.printModules();
		runtime.run();
	}
}
