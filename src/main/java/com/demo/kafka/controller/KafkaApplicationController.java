package com.demo.kafka.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
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
	
	@GetMapping("/")
	public String getHelloWorld() {
		List<String> teamName = new ArrayList<>();
		teamName.add("Darwin");
		teamName.add("B2C");
		teamName.add("Darwin Legacy");
		
		for (String name: teamName) {
			kafkaTemplate.send(topicName, "Hello "+name);	
		}
		
		return "Hello Kafka" ;
	}
}
