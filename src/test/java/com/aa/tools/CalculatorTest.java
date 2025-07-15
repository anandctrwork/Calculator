package com.aa.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
	
	@Test
	public void testAdd() {
		Calculator cal = new Calculator();
		assertEquals(8, cal.add(3, 5));
	}

	@Test
	public void testSubtract() {
		Calculator cal = new Calculator();
		assertEquals(2, cal.subtract(5, 3));
	}

	@Test
	public void testMultiply() {
		Calculator cal = new Calculator();
		assertEquals(15, cal.multiply(3, 5));
	}
}
