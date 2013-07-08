package com.jasel.classes.cs795dm.homework4;

import java.util.ArrayList;


public class Cluster {
	private ArrayList<Instance> instances = new ArrayList<Instance>();
	private String name = null;
	
	public Cluster(String name, Instance instance) {
		this.name = name;
		instances.add(instance);
	}
	
	
	public void add(Instance instance) {
		instances.add(instance);
	}
	
	
	public void remove(Instance instance) {
		instances.remove(instance);
	}
	
	
	public int size() {
		return instances.size();
	}
	
	
	public Instance get(int i) {
		return instances.get(i);
	}
	
	
	public String getName() {
		return name;
	}
	
	
	public String getInstancesNameSet() {
		StringBuilder sb = new StringBuilder("{");
		int size = instances.size();
		
		for (int i = 0; i < size; i++) {
			sb.append(instances.get(i));
			
			if (i < (size - 1)) {
				sb.append(",");
			}
		}
		
		sb.append("}");
		
		return sb.toString();
	}
	
	
	public Instance getCentroid() throws InvalidEducationValueException {
		Instance instance = null;
		
		int size = instances.size();
		String name = "ClusterCentroid";
		double age = 0.0;
		double education = 0.0;
		double gpa = 0.0;
		double experience = 0.0;
		double salary = 0.0;
		
		if (size == 1) {
			age = instances.get(0).getAge();
			education = instances.get(0).getEducation();
			gpa = instances.get(0).getGPA();
			experience = instances.get(0).getExperience();
			salary = instances.get(0).getSalary();
		} else {
			for (int i = 0; i < size; i++) {
				instance = instances.get(i);
				
				age += instance.getAge();
				education += instance.getEducation();
				gpa += instance.getGPA();
				experience += instance.getExperience();
				salary += instance.getSalary();
			}
			
			age = age / size;
			education = education / size;
			gpa = gpa / size;
			experience = experience / size;
			salary = salary / size;
		}
		
		return new Instance(name, age, convertEducation(education), gpa, experience, salary);
	}
	
	
	private String convertEducation(double education) {
		if (education < 1.5) {
			return "BS";
		} else {
			return "MS";
		}
	}
	
	
	public void merge(Cluster cluster) {
		for (int i = 0; i < cluster.size(); i++) {
			this.add(cluster.get(i));
		}
	}
}