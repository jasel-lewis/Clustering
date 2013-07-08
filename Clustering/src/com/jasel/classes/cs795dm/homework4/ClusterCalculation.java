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
	
	
	public static Instance average(Instance a, Instance b) throws InvalidEducationValueException {
		return new Instance("x",
				average(a.getAge(), b.getAge()),
				averageEducation(a.getEducation(), b.getEducation()),
				average(a.getGPA(), b.getGPA()),
				average(a.getExperience(), b.getExperience()),
				average(a.getSalary(), b.getSalary()));
	}
	
	
	private static double average(double a, double b) {
		return ((a + b) / 2);
	}
	
	
	private static String averageEducation(int a, int b) {
		double average = ((a + b) / 2);
		
		if (average < 1.5) {
			return "BS";
		} else {
			return "MS";
		}
	}
}