package com.mindtree.parallelStream;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class MyParallelStream {

	public static void main(String[] args) {
		List<Employee> empList = new ArrayList<Employee>();
		empList.add(new Employee(100, 10000, "vinod"));
		empList.add(new Employee(500, 50000, "Rahul"));
		empList.add(new Employee(300, 30000, "Neelam"));
		empList.add(new Employee(200, 80000, "Gullu"));
		
		Stream<Employee> em = empList.stream().filter( e->e.getSalary() > 40000 && e.getSalary() < 90000);
		Predicate<Employee> employeePredicate = employee -> (employee.getSalary() > 40000 && employee.getSalary() < 90000);
		System.out.println(employeePredicate);
		OptionalDouble average = empList.parallelStream().filter(employeePredicate).mapToDouble(emp->emp.getSalary()).average();
		System.out.println("Average Salary::" + average.getAsDouble());
	}

}
