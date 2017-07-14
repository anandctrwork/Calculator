package com.aa.tools;

import static org.junit.Assert.assertTrue;

import junit.framework.TestCase;

public class CalculatorTest extends TestCase {
	
	public void testAdd()
	{
		Calculator cal = new Calculator();
		assertTrue(cal.add(3,5)==8);
	}

	public void testMultipy()
	{
		Calculator cal = new Calculator();
		assertTrue(cal.multiply(3,5)==15);
	}

}
