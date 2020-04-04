package com.shotin.rest.webservices.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class PolynomCalculator {

	public Map<String, Integer> add(Map<String, Integer> polynom1, Map<String, Integer> polynom2) {
		Map<String, Integer> result = new HashMap<>();
		HashSet<String> keys = new HashSet<>();
		keys.addAll(polynom1.keySet());
		keys.addAll(polynom2.keySet());
		for(String key : keys) {
			Integer var1 = polynom1.getOrDefault(key, 0);
			Integer var2 = polynom2.getOrDefault(key, 0);
			result.put(key, var1 + var2);
		}
		return result;
	}
}
