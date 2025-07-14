package com.aa.tools;

import org.springframework.stereotype.Service;

@Service
public class Calculator {

	public int add(int a, int b) {
		return a + b;
	}
	
	public int subtract(int a, int b) {
		return a - b;
	}
	
	public long multiply(int a, int b) {
		return (long) a * b;
	}
}
