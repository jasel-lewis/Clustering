package com.jasel.classes.cs795dm.homework4;


public class Instance implements Comparable<Instance> {
	private final ClusterNumberFormat cnf = new ClusterNumberFormat();
	
	private String name;
	private double age = 0.0;
	private String education = "BS";
	private double gpa = 0.0;
	private double experience = 0.0;
	private double salary = 0.0;
	
	public Instance(String name, double age, String education, double gpa, double experience, double salary) {
		this.name = name;
		this.age = age;
		this.education = education;
		this.gpa = gpa;
		this.experience = experience;
		this.salary = salary;
	}
	
	
	public String getName() {
		return name;
	}
	
	
	public double getAge() {
		return age;
	}
	
	
	public int getEducation() throws InvalidEducationValueException {
		if (education.compareTo("BS") == 0) {
			return 1;
		} else if (education.compareTo("MS") == 0) {
			return 2;
		} else {
			throw new InvalidEducationValueException("Education attribute is not \"BS\" or \"MS\".");
		}
	}
	
	
	public double getGPA() {
		return gpa;
	}
	
	
	public double getExperience() {
		return experience;
	}
	
	
	public double getSalary() {
		return salary;
	}
	
	
	@Override
	public int compareTo(Instance instance) {
		return this.name.compareTo(instance.name);
	}
	
	
	@Override
	public String toString() {
		return "Name:" + name + ";Age:" + cnf.format(age) + ";Educ:" + education + ";GPA:" + cnf.format(gpa) +
				";Exper:" + cnf.format(experience) + ";Sal:" + cnf.format(salary);
	}
}