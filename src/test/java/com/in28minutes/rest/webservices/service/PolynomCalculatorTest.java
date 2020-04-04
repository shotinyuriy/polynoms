package com.in28minutes.rest.webservices.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.shotin.rest.webservices.service.PolynomCalculator;

public class PolynomCalculatorTest {
	
	PolynomCalculator polynomCalculator = new PolynomCalculator();
	
	@Test
	public void testMinus2Plus3() {
		Map<String, Integer> polynom1 = new HashMap<>();
		polynom1.put("", -2);
		
		Map<String, Integer> polynom2 = new HashMap<>();
		polynom2.put("", 3);
		
		Map<String, Integer> result = polynomCalculator.add(polynom1, polynom2);
		Assert.assertEquals((Integer)1, result.get(""));
	}

	@Test
	public void test1XPlu1X() {
		Map<String, Integer> polynom1 = new HashMap<>();
		polynom1.put("x", 1);
		
		Map<String, Integer> polynom2 = new HashMap<>();
		polynom2.put("x", 1);
		
		Map<String, Integer> result = polynomCalculator.add(polynom1, polynom2);
		
		Assert.assertEquals((Integer)2, result.get("x"));
	}
	
	@Test
	public void test1XMinus1X() {
		Map<String, Integer> polynom1 = new HashMap<>();
		polynom1.put("x", 1);
		
		Map<String, Integer> polynom2 = new HashMap<>();
		polynom2.put("x", -1);
		
		Map<String, Integer> result = polynomCalculator.add(polynom1, polynom2);
		
		Assert.assertEquals((Integer)0, result.get("x"));
	}
	
	@Test
	public void test1XMinus0() {
		Map<String, Integer> polynom1 = new HashMap<>();
		polynom1.put("x", 1);
		
		Map<String, Integer> polynom2 = new HashMap<>();
		polynom2.put("", 0);
		
		Map<String, Integer> result = polynomCalculator.add(polynom1, polynom2);
		
		Assert.assertEquals((Integer)1, result.get("x"));
		Assert.assertEquals((Integer)0, result.get(""));
	}
}
