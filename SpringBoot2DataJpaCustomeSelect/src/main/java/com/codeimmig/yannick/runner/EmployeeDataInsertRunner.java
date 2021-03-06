package com.codeimmig.yannick.runner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.codeimmig.yannick.model.Employee;
import com.codeimmig.yannick.repository.EmployeRepository;
import com.codeimmig.yannick.repository.EmployeRepository.MyView;
import com.codeimmig.yannick.repository.EmployeRepository.MyViewTwo;

@Component
public class EmployeeDataInsertRunner implements CommandLineRunner {
	@Autowired
	private EmployeRepository empRepo;

	@Override
	public void run(String... args) throws Exception {
		empRepo.save(new Employee(101, "SAM", 5000.0, "DEV", "ORCL"));
		empRepo.save(new Employee(102, "SYED", 6000.0, "DEV", "NIT"));
		empRepo.save(new Employee(103, "AJAY", 8000.0, "QA", "ORCL"));
		empRepo.save(new Employee(104, "SUN", 5400.0, "QA", "NIT"));
		empRepo.save(new Employee(105, "ABM", 5200.0, "DEV", "NIT"));
		empRepo.save(new Employee(106, "XYZ", 1800.0, "BA", "ORCL"));
		empRepo.save(new Employee(107, "KUM", 1200.0, "BA", "NIT"));

		System.out.println(" select employee with a specila id ");
		empRepo.findByEmpIdEquals(102).forEach(System.out::println);

//		System.out.println(" Employee les than  id ");
//		empRepo.findByEmpIdLessThan(102).forEach(System.out::println);

		System.out.println("------------------------employe between to id --------------------------------------");
//		
		// empRepo.findByEmpIdBetween(101,104).forEach(System.out::println);

		List<Employee> list = empRepo.findByEmpIdNot(105);
		list.stream().sorted((e1, e2) -> e1.getEmpName().compareTo(e2.getEmpName()))
				.map(ob -> ob.getEmpName() + "," + ob.getEmpProj() + "," + ob.getEmpDept())
				.forEach(System.out::println);

		// projection example
		System.out.println(
				"------------------------------------------ Projection example ------------------------------------------");
		List<MyView> listr = empRepo.findByEmpDept("DEV");
		for (MyView m : listr) {
			System.out.println(m.getEmpName() + "-" + m.getEmpSal());
		}

		List<MyViewTwo> liste = empRepo.findByEmpProj("NIT");
		for (MyViewTwo m : liste) {
			System.out.println(m.getEmpName() + "-" + m.getEmpId() + "-" + m.getEmpDept());

			/*List<Employee> list = repo.findByEmpIdNot(105);
			list.stream()
			.sorted((e1,e2)->e1.getEmpName().compareTo(e2.getEmpName()))
			.map(ob->ob.getEmpName()+","+ob.getEmpProj()+","+ob.getEmpDept())
			.forEach(System.out::println);
			*/
			//repo.findByEmpNameIsNull().forEach(System.out::println);
			//repo.findByEmpNameIsNotNull().forEach(System.out::println);
			
			//ename --->Starts with A
			//repo.findByEmpNameLike("A%").forEach(System.out::println);
			//repo.findByEmpNameStartingWith("A").forEach(System.out::println);
			//repo.findByEmpNameEndingWith("M").forEach(System.out::println);
			//repo.findByEmpNameContaining("A").forEach(System.out::println);
			
			
			//ename --->2nd char is A
			//repo.findByEmpNameLike("_A%").forEach(System.out::println);
			
			//repo.findByEmpNameNotLike("_A%").forEach(System.out::println);
			
			//repo.findByEmpIdIn(Arrays.asList(101,103,106,108,109)).forEach(System.out::println);
			//repo.findByEmpIdInAndEmpNameIsNotNull(Arrays.asList(101,103,106,108,109)).forEach(System.out::println);
			//repo.findByEmpIdGreaterThanEqualOrEmpSalLessThanEqualOrderByEmpName(101,5000.0).forEach(System.out::println);
			//repo.findByEmpIdGreaterThanEqualOrEmpSalLessThanEqualOrderByEmpNameDesc(101,5000.0).forEach(System.out::println);
		}

	}

}
