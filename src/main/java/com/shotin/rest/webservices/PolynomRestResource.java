package com.shotin.rest.webservices;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shotin.rest.webservices.model.PolynomAddition;
import com.shotin.rest.webservices.model.PolynomResponse;
import com.shotin.rest.webservices.service.PolynomCalculator;
import com.shotin.rest.webservices.service.PolynomParser;

@RestController
@RequestMapping("/polynoms")
public class PolynomRestResource {

	private PolynomParser parser;

	private PolynomCalculator calculator;

	public PolynomRestResource(@Autowired PolynomParser parser, @Autowired PolynomCalculator calculator) {
		this.parser = parser;
		this.calculator = calculator;
	}

	@PostMapping("/addition")
	public ResponseEntity<Object> postPolynomAddition(@RequestBody PolynomAddition polynomAddition) {

		if (polynomAddition.getPolynom1() == null) {
			return ResponseEntity.badRequest().body(new PolynomResponse("polynom1 should not be null"));
		}
		if (polynomAddition.getPolynom2() == null) {
			return ResponseEntity.badRequest().body(new PolynomResponse("polynom2 should not be null"));
		}
		try {
			Map<String, Integer> polynom1 = parser.parsePolynom(polynomAddition.getPolynom1());
			Map<String, Integer> polynom2 = parser.parsePolynom(polynomAddition.getPolynom2());
	
			Map<String, Integer> polynomResult = calculator.add(polynom1, polynom2);
	
			String result = parser.polynomToString(polynomResult);
			PolynomResponse polynomResponse = new PolynomResponse(result);
			return ResponseEntity.ok(polynomResponse);
		} catch (Exception e) {
			PolynomResponse response = new PolynomResponse(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
}
