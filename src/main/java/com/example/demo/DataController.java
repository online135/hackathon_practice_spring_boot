package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(origins = "*")
public class DataController {
	
    private List<Map<String, Object>> data = new ArrayList<>();

    public DataController() {
        // Sample data
        Map<String, Object> item1 = new HashMap<>();
        item1.put("category", Category.A);
        item1.put("title", "TestTitle1");
        item1.put("date", "2024-08-22");
        item1.put("description", "This is the 1 item.");
        item1.put("status", Status.A);
        item1.put("id", 1);

        data.add(item1);

        Map<String, Object> item2 = new HashMap<>();
        item2.put("category", Category.B);
        item2.put("title", "TestTitle2");
        item2.put("date", "2024-08-24");
        item2.put("description", "This is the 2 item.");
        item2.put("status", Status.B);
        item2.put("id", 2);

        data.add(item2);

        Map<String, Object> item3 = new HashMap<>();
        item3.put("category", Category.C);
        item3.put("title", "TestTitle3");
        item3.put("date", "2024-08-24");
        item3.put("description", "This is the third item.");
        item3.put("status", Status.C);
        item3.put("id", 3);

        data.add(item3);
    }

    @GetMapping
    @RequestMapping("/api/issues")
    public ResponseEntity<List<Map<String, Object>>> getData() {
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/api/issue/{id}")
    public ResponseEntity<Map<String, Object>> getDataById(@PathVariable("id") int id) {
        for (Map<String, Object> item : data) {
            if (item.get("id").equals(id)) {
                return new ResponseEntity<>(item, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/api/issue")
    public ResponseEntity<String> postData(@RequestBody Map<String, Object> payload) {
        payload.put("id", data.size() + 1);
        data.add(payload);
        return new ResponseEntity<>("Data received successfully", HttpStatus.CREATED);

        // // Process the incoming data
        // String category = (String) payload.get("category");
        // String title = (String) payload.get("title");
        // String selectedDate = (String) payload.get("selectedDate");
        // String description = (String) payload.get("description");

        // System.out.println("Received category: " + category);
        // System.out.println("Received title: " + title);
        // System.out.println("Received selectedDate: " + selectedDate);
        // System.out.println("Received description: " + description);

        // return new ResponseEntity<>("Data received successfully", HttpStatus.CREATED);
    }

    @PutMapping("/api/issue/{id}")
    public ResponseEntity<String> updateData(@PathVariable("id") int id, @RequestBody Map<String, Object> payload) {
        for (Map<String, Object> item : data) {
            if (item.get("id").equals(id)) {
                item.put("category", payload.get("category"));
                item.put("title", payload.get("title"));
                item.put("date", payload.get("date"));
                item.put("description", payload.get("description"));
                item.put("status", payload.get("status"));
                return new ResponseEntity<>("Data updated successfully", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Data not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/api/issue/{id}")
    public ResponseEntity<String> deleteData(@PathVariable("id") int id) {
        for (Map<String, Object> item : data) {
            if (item.get("id").equals(id)) {
                data.remove(item);
                return new ResponseEntity<>("Data deleted successfully", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Data not found", HttpStatus.NOT_FOUND);
    }
}