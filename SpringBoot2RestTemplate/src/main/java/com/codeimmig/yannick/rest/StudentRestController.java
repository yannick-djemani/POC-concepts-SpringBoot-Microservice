package com.codeimmig.yannick.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeimmig.yannick.model.Student;

@RestController
@RequestMapping("/rest/student")
public class StudentRestController {
	@GetMapping("/showa")
	public ResponseEntity<String> showMsgA(){
		System.out.println("FROM SHOWA");
		return ResponseEntity.ok("WELCOME TO APPLICATION");
	}

	@GetMapping("/showb/{id}/{name}")
	public ResponseEntity<String> showMsgB(@PathVariable Integer id,@PathVariable String name) {
		System.out.println("FROM SHOWB");
		return ResponseEntity.ok("WELCOME TO APPLICATION! " + id +"," + name);
	}

	@GetMapping("/showc")
	public ResponseEntity<Student> showMsgC() {
		System.out.println("FROM SHOWC");
		return ResponseEntity.ok(new Student(10,"BBCC",200.0));

	}

	@GetMapping("/showd")
	public ResponseEntity<List<Student>> showMsgD() {
		System.out.println("FROM SHOWD");
		return ResponseEntity.ok(
				Arrays.asList(
						new Student(10,"A",200.0),
						new Student(11,"B",300.0),
						new Student(12,"C",400.0)
						)
				);
	}

	@PostMapping("/savea")
	public ResponseEntity<String> saveDataA(@RequestBody Student student) 
	{
		System.out.println("FROM SAVE#A" + student);
		return ResponseEntity.ok(student.toString());
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> removeData(@PathVariable Integer id) {
		System.out.println("FROM REMOVE ");
		return ResponseEntity.ok("DELETE SUCCESS " + id);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateData(@PathVariable Integer id,@RequestBody Student student) 
	{
		System.out.println("FROM UPDATE ");
		return ResponseEntity.ok("UPDATE SUCCESS " + id + "-" + student);
	}
}
