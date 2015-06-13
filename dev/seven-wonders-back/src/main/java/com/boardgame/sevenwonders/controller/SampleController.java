package com.boardgame.sevenwonders.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/rest/sample")
public class SampleController {

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ResponseEntity<String> hello(@RequestParam String name) {
		try {
			return new ResponseEntity<String>("Hello :" + name, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
