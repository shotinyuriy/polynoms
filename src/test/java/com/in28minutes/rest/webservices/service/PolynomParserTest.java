package com.in28minutes.rest.webservices.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.shotin.rest.webservices.service.PolynomParser;

import static org.junit.Assert.*;

public class PolynomParserTest {

	private PolynomParser parser = new PolynomParser();
	
	
	
	@Test
	public void testEmptyString() {
		Map<String, Integer> polynom = parser.parsePolynom("");
		
		assertEquals((Integer)0, (Integer)polynom.get(""));
	}
	
	@Test(expected = RuntimeException.class)
	public void testMinusMinus() {
		Map<String, Integer> polynom = parser.parsePolynom("--");
	}
	
	@Test(expected = RuntimeException.class)
	public void testPluPlus() {
		Map<String, Integer> polynom = parser.parsePolynom("++");
	}
	
	@Test(expected = RuntimeException.class)
	public void testPlu1Plus() {
		Map<String, Integer> polynom = parser.parsePolynom("+1+");
	}
	
	@Test
	public void test1X() {
		Map<String, Integer> polynom = parser.parsePolynom("x");
		
		assertEquals((Integer)1, (Integer)polynom.get("x"));
	}
	
	@Test
	public void testMinus2X() {
		Map<String, Integer> polynom = parser.parsePolynom("-2x");
		
		assertEquals((Integer)(-2), (Integer)polynom.get("x"));
	}
	
	@Test
	public void test3XPow2Minus2X() {
		Map<String, Integer> polynom = parser.parsePolynom("3x2-2x");
		
		assertEquals((Integer)(3), (Integer)polynom.get("x2"));
		assertEquals((Integer)(-2), (Integer)polynom.get("x"));
	}
	
	@Test
	public void testXMinusY() {
		Map<String, Integer> polynom = parser.parsePolynom("x-y");
		
		assertEquals((Integer)(1), (Integer)polynom.get("x"));
		assertEquals((Integer)(-1), (Integer)polynom.get("y"));
	}
	
	@Test
	public void testXPlusY() {
		Map<String, Integer> polynom = parser.parsePolynom("x+y");
		
		assertEquals((Integer)(1), (Integer)polynom.get("x"));
		assertEquals((Integer)(1), (Integer)polynom.get("y"));
	}
	
	@Test
	public void testMinusXMinusY() {
		Map<String, Integer> polynom = parser.parsePolynom("-x-y");
		
		assertEquals((Integer)(-1), (Integer)polynom.get("x"));
		assertEquals((Integer)(-1), (Integer)polynom.get("y"));
	}
	
	@Test
	public void testPolynomToString3x2Plus1xMinus1() {
		Map<String, Integer> polynom = new HashMap<>();
		polynom.put("x2", 3);
		polynom.put("x", 1);
		polynom.put("", -1);
		
		String result = parser.polynomToString(polynom);
		
		assertEquals("3x2+x-1", result);
	}
	
	@Test
	public void testPolynomToStringMinus3x2Minus2xPlus0() {
		Map<String, Integer> polynom = new HashMap<>();
		polynom.put("x2", -3);
		polynom.put("x", -2);
		polynom.put("", 0);
		
		String result = parser.polynomToString(polynom);
		
		assertEquals("-3x2-2x", result);
	}
}
