package com.jasel.classes.cs795dm.homework4;

public final class ClusterCalculation {
	public static double distance(Instance a, Instance b) throws InvalidEducationValueException {
		double distAge = distance(a.getAge(), b.getAge());
		int distEducation = distance(a.getEducation(), b.getEducation());
		double distGPA = distance(a.getGPA(), b.getGPA());
		double distExperience = distance(a.getExperience(), b.getExperience());
		double distSalary = distance(a.getSalary(), b.getSalary());
		
		return Math.sqrt((distAge * distAge) + (distEducation * distEducation) + (distGPA * distGPA) +
				(distExperience * distExperience) + (distSalary * distSalary));
	}
	
	
	private static double distance(double a, double b) {
		return (b - a);
	}
	
	
	private static int distance(int a, int b) {
		return (b - a);
	}
}