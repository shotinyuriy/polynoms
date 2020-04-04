package com.shotin.rest.webservices.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class PolynomParser {
	
	Pattern polynomPattern = Pattern.compile("()|([-+]?((\\d+)|(\\w+\\d*)|(\\d+\\\\w+\\\\d*)))+");
	
	public Map<String, Integer> parsePolynom(String polynomStr) {
		
		Matcher matcher = polynomPattern.matcher(polynomStr);
		if(!matcher.matches()) {
			throw new RuntimeException("The polynom does not match the format "+polynomStr);
		}
		
		Map<String, Integer> polynom = new HashMap<>();
			
		StringBuilder sbD = new StringBuilder();
		StringBuilder sbVar = new StringBuilder();
		for (int i = 0; i < polynomStr.length(); i++) {
			char c = polynomStr.charAt(i);
			
			if (Character.isAlphabetic(c)) {
				sbVar.append(c);
				continue;
			}
			if (c == '-' || c == '+') {
				if (sbD.length() > 0 || sbVar.length() > 0) {
					addPartToPolynom(sbD.toString(), sbVar.toString(), polynom);
					sbD = new StringBuilder();
					sbVar = new StringBuilder();
				}
			}
			if (c == '-' || Character.isDigit(c)) {
				if(sbVar.length() == 0) {
					sbD.append(c);
				} else {
					sbVar.append(c);
				}
			}
			
		}
		addPartToPolynom(sbD.toString(), sbVar.toString(), polynom);
		
		return polynom;
	}
	
	private void addPartToPolynom(String value, String var, Map<String, Integer> polynom) {
		final int currentVal;
		if (value.length() > 0) {
			if ("-".equals(value)) {
				currentVal = -1;
			} else {
				currentVal = Integer.parseInt(value);
			}
		} else {
			if(var != null && !var.isEmpty()) {
				currentVal = 1;
			} else {
				currentVal = 0;
			}
		}
		polynom.compute(var, (key, existing) -> existing == null ? currentVal : existing + currentVal);

	}
	
	public String polynomToString(Map<String, Integer> polynom) {
		final StringBuilder sb = new StringBuilder();
		SortedMap<String, Integer> sortedPolynom = new TreeMap<>(new Comparator<String> () {

			@Override
			public int compare(String o1, String o2) {
				if (o2 == null) {
					if (o1 == null) return 0;
					else return -1;
				}
				
				return o2.compareTo(o1);
			}
			
		});
		sortedPolynom.putAll(polynom);
		sortedPolynom.entrySet().stream()
			.forEach(entry -> {
				if(entry.getValue() != 0) {
					if(sb.length() > 0 && entry.getValue() > 0) {
						sb.append('+');
					}
					if (!entry.getKey().isEmpty() && Integer.valueOf(-1).equals(entry.getValue())) {
						sb.append('-');
					}
					if (entry.getKey().isEmpty() || (entry.getValue() != 1 && entry.getValue() != -1)) {
						sb.append(entry.getValue());
					}
					sb.append(entry.getKey());
				}
			});
		if (sb.length() == 0) {
			sb.append("0");
		}
		return sb.toString();
	}

}
