package com.aa.tools;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculatorTest {
	
	@Test
	public void testAdd()
	{
		Calculator cal = new Calculator();
		assertTrue(cal.add(3,5)==8, "3 + 5 should equal 8");
	}

	@Test
	public void testSubstract()
	{
		Calculator cal = new Calculator();
		assertTrue(cal.substract(5,3)==2, "5 - 3 should equal 2");
	}

	@Test
	public void testMultiply() // Corrected method name from testMultipy to testMultiply
	{
		Calculator cal = new Calculator();
		assertTrue(cal.multiply(3,5)==15, "3 * 5 should equal 15");
	}

}
