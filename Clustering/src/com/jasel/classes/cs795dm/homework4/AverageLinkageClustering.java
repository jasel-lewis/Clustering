package com.jasel.classes.cs795dm.homework4;

import java.util.ArrayList;
import java.util.zip.DataFormatException;


public class AverageLinkageClustering {
	private final static double HIGH_MIN = 100000000.0;

	/**
	 * @param args
	 * @throws DataFormatException 
	 */
	public static void main(String[] args) throws DataFormatException {
		Instance minA = null;
		Instance minB = null;
		
		double dist = 0.0;
		double min = HIGH_MIN;
		
		ArrayList<Instance> instances = new ArrayList<Instance>();
		ArrayList<Instance> cluster = new ArrayList<Instance>();
		
		instances.add(new Instance("a", 0.1167, "BS", 0.1321, 0.0538, 0.0787));
		instances.add(new Instance("b", 0.0833, "MS", 0.1132, 0.0000, 0.0562));
		instances.add(new Instance("c", 0.1000, "BS", 0.0943, 0.0645, 0.1124));
		instances.add(new Instance("d", 0.1833, "BS", 0.1321, 0.3326, 0.1685));
		instances.add(new Instance("e", 0.1500, "MS", 0.1321, 0.1613, 0.1124));
		instances.add(new Instance("f", 0.1000, "MS", 0.1509, 0.0215, 0.0787));
		instances.add(new Instance("g", 0.1333, "BS", 0.0943, 0.2151, 0.2247));
		instances.add(new Instance("h", 0.1333, "MS", 0.1509, 0.1613, 0.1685));
		
		for (int i = 0; i < instances.size(); i++) {
			for (int j = i + 1; j < instances.size(); j++) {
				dist = distance(instances.get(i), instances.get(j));
				
				if (dist < min) {
					min = dist;
					minA = instances.get(i);
					minB = instances.get(j);
				}
				
				System.out.print("DIST(" + instances.get(i).getName() + "," + instances.get(j).getName() + ") = ");
				System.out.println(dist);
			}
		}
			
		System.out.println("Minimum is between " + minA.getName() + " and " + minB.getName() + " with value " + min);
		System.out.println("Clustering these two instances.");
		
		// Add the two instances with the minimum distance to the cluster
		cluster.add(minA);
		cluster.add(minB);
		
		System.out.print("Instances in cluster: {");
		for (int c = 0; c < cluster.size(); c++) {
			System.out.print(cluster.get(c).getName());
			
			if (c < (cluster.size() - 1)) {
				System.out.print(",");
			}
		}
		
		System.out.print("}\n");
		
		// Remove the two instances from the available (not-yet-clustered) instances
		instances.remove(minA);
		instances.remove(minB);
		
		System.out.print("Instances not yet clustered: {");
		for (int i = 0; i < instances.size(); i++) {
			System.out.print(instances.get(i).getName());
			
			if (i < (instances.size() - 1)) {
				System.out.print(",");
			}
		}
		
		System.out.print("}\n");
		
		while (instances.size() > 0) {
			min = HIGH_MIN;
			
			for (int c = 0; c < cluster.size(); c++) {
				for (int i = 0; i < instances.size(); i++) {
					dist = ClusterCalculation.distance(cluster.get(c), instances.get(i));
					
					if (dist < min) {
						min = dist;
						minA = cluster.get(c);
						minB = instances.get(i);
					}
					
					System.out.print("DIST(" + cluster.get(c).getName() + "," + instances.get(i).getName() + ") = ");
					System.out.println(dist);
				}
			}
			
			System.out.println("Minimum is between " + minA.getName() + " (already clustered) and " +
					minB.getName() + " with value " + min);
			System.out.println("Bringing " + minB.getName() + " into the cluster.");
			
			// Add to the cluster the closest instance (having the minimum distance to any instance within the cluster)
			cluster.add(minB);
			
			System.out.print("Instances in cluster: {");
			for (int c = 0; c < cluster.size(); c++) {
				System.out.print(cluster.get(c).getName());
				
				if (c < (cluster.size() - 1)) {
					System.out.print(",");
				}
			}
			
			System.out.print("}\n");
			
			// Remove the two instances from the available (not-yet-clustered) instances
			instances.remove(minB);
			
			System.out.print("Instances not yet clustered: {");
			for (int i = 0; i < instances.size(); i++) {
				System.out.print(instances.get(i).getName());
				
				if (i < (instances.size() - 1)) {
					System.out.print(",");
				}
			}
			
			System.out.print("}\n");
		}
	}
	
	
	private static Instance average(Instance a, Instance b) throws DataFormatException {
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