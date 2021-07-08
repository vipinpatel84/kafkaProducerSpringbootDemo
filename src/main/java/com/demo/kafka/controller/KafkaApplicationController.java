package com.demo.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaApplicationController {

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	@Value("${topicName}")
	private String topicName;
	
	@GetMapping(path ="/{name}")
	public String getHelloWorld(@PathVariable String name) {
		System.out.println(name);
		kafkaTemplate.send(topicName, "Hello "+name);
		return "Hello " + name;
	}
}
