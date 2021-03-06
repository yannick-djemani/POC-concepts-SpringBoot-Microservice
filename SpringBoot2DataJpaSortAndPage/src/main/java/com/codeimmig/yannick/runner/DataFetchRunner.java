package com.codeimmig.yannick.runner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;

import com.codeimmig.yannick.model.Student;
import com.codeimmig.yannick.repository.StudentRepository;

@Component
public class DataFetchRunner implements CommandLineRunner {
	@Autowired
	private StudentRepository repo;

	@Override
	public void run(String... args) throws Exception {
		//SQL: select * from stdtab order by sfee asc;
		//Sort s1 = Sort.by("stdFee"); //default
		
		//SQL: select * from stdtab order by sname asc;
		//Sort s1 = Sort.by(Direction.ASC, "stdName");
		
		//SQL: select * from stdtab order by sname desc;
		//Sort s1 = Sort.by(Direction.DESC, "stdName");

		//SQL: select * from stdtab order by sfee desc,sname desc;
		//Sort s1 = Sort.by(Direction.DESC,"stdFee","stdName");

		//SQL: select * from stdtab order by sfee desc,sname asc;
		//Sort s1 = Sort.by(Order.desc("stdFee"),Order.asc("stdName"));
		
		Sort s1 = Sort.by(Order.desc("stdFee"), Order.asc("stdName"));
		
		List<Student> list = repo.findAll(s1);
		for (Student stu : list) {
			System.out.println(stu);
			
		}

	}

}
