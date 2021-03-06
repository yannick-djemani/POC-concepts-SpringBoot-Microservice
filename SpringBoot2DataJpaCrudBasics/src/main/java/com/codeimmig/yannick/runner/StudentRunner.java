package com.codeimmig.yannick.runner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.codeimmig.yannick.model.Student;
import com.codeimmig.yannick.repo.StudentRepository;

@Component
public class StudentRunner implements CommandLineRunner{
	@Autowired
	private StudentRepository studentRepo;

	@Override
	public void run(String... args) throws Exception {
		studentRepo.save(new Student(11010, "Yannick", 500.25));
		studentRepo.save(new Student(12584, "Paul", 698.25));
		studentRepo.save(new Student(1000, "Audrey", 500.25));
		studentRepo.save(new Student(874, "Bemi", 500.25));
		studentRepo.save(new Student(69200, "Djambou", 500.55));
		
		studentRepo.saveAll(
				Arrays.asList(
						new Student(13,"E",5.5),
						new Student(14,"F",6.5),
						new Student(15,"F",8.5)
						)
				);
		
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("--------------------------Find all Student --------------------------------");
		List<Student> students=studentRepo.findAll();
		students.forEach(s->System.out.println(s));
		
		System.out.println("************");
		Set<String> names=students.stream()
								   .map(stud->stud.getStdName())
								   .sorted()
								   .collect(Collectors.toSet());
		names.forEach(System.out::println);
		System.out.println("----------------------findeOne student---------------------------------------");
		
	Student student=null;
	Optional<Student> optional=studentRepo.findById(698888);
	
	if (optional.isPresent()) {
		student=optional.get();
		System.out.println(student);
	}
	else {
		System.out.println("Student doesn't exist");
	}
	System.out.println("-----------------student exist ---------------");
	
	Boolean exist=studentRepo.existsById(8784);
	System.out.println("is student exist ? "+exist);
	
	System.out.println("-----------------delete student  ---------------");
	studentRepo.deleteById(874);
	
	}

}
