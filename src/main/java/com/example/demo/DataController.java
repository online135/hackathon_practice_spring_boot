package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/data")
public class DataController {
	
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getData() {
        List<Map<String, Object>> data = new ArrayList<>();

        Map<String, Object> item1 = new HashMap<>();
        item1.put("title", "Item 1");
        item1.put("description", "This is the first item.");
        data.add(item1);

        Map<String, Object> item2 = new HashMap<>();
        item2.put("title", "Item 2");
        item2.put("description", "This is the second item.");
        data.add(item2);

        Map<String, Object> item3 = new HashMap<>();
        item3.put("title", "Item 3");
        item3.put("description", "This is the third item.");
        data.add(item3);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}