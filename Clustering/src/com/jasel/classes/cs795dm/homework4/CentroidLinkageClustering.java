package com.jasel.classes.cs795dm.homework4;

import java.util.ArrayList;


public class CentroidLinkageClustering {
	private final static double HIGH_MIN = 100000000.0;

	/**
	 * @param args
	 * @throws InvalidEducationException 
	 */
	public static void main(String[] args) throws InvalidEducationException {
		Cluster minA = null;
		Cluster minB = null;
		Instance centroid = null;
		
		double dist = 0.0;
		double minDist = HIGH_MIN;
		
		ArrayList<Cluster> clusters = new ArrayList<Cluster>();
		
		clusters.add(new Cluster("1", new Instance("a", 0.1167, "BS", 0.1321, 0.0538, 0.0787)));
		clusters.add(new Cluster("2", new Instance("b", 0.0833, "MS", 0.1132, 0.0000, 0.0562)));
		clusters.add(new Cluster("3", new Instance("c", 0.1000, "BS", 0.0943, 0.0645, 0.1124)));
		clusters.add(new Cluster("4", new Instance("d", 0.1833, "BS", 0.1321, 0.3326, 0.1685)));
		clusters.add(new Cluster("5", new Instance("e", 0.1500, "MS", 0.1321, 0.1613, 0.1124)));
		clusters.add(new Cluster("6", new Instance("f", 0.1000, "MS", 0.1509, 0.0215, 0.0787)));
		clusters.add(new Cluster("7", new Instance("g", 0.1333, "BS", 0.0943, 0.2151, 0.2247)));
		clusters.add(new Cluster("8", new Instance("h", 0.1333, "MS", 0.1509, 0.1613, 0.1685)));
		
		for (int i = 0; i < clusters.size(); i++) {
			for (int j = i + 1; j < clusters.size(); j++) {
				dist = distance(clusters.get(i).getCentroid(), clusters.get(j).getCentroid());
				
				if ((minA == null) || (minB == null) || (dist < min)) {
					minDist = dist;
					minA = clusters.get(i);
					minB = clusters.get(j);
				}
				
				System.out.print("DIST(" + instances.get(i).getName() + "," + instances.get(j).getName() + ") = ");
				System.out.println(dist);
			}
		}
			
		System.out.println("Minimum is between " + minA.getName() + " and " + minB.getName() + " with value " + min);
		System.out.println("Merging cluster " + minB.getName() + " into cluster " + minA.getName() + ".");
		
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
			
			centroid = getCentroid(cluster);
			
			for (int i = 0; i < instances.size(); i++) {
				dist = distance(centroid, instances.get(i));
				
				if (dist < min) {
					min = dist;
					minA = instances.get(i);
				}
				
				System.out.print("DIST(" + centroid.getName() + "," + instances.get(i).getName() + ") = ");
				System.out.println(dist);
			}
			
			System.out.println("Instance with minimum distance to cluster's centroid is " + minA.getName() +
					" with distance " + min);
			System.out.println("Bringing " + minA.getName() + " into the cluster.");
			
			// Add to the cluster the closest instance (having the minimum distance to the centroid of the cluster)
			cluster.add(minA);
			
			System.out.print("Instances in cluster: {");
			for (int c = 0; c < cluster.size(); c++) {
				System.out.print(cluster.get(c).getName());
				
				if (c < (cluster.size() - 1)) {
					System.out.print(",");
				}
			}
			
			System.out.print("}\n");
			
			// Remove the identified instance from the available (not-yet-clustered) instances
			instances.remove(minA);
			
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
	
	
	private static double distance(Instance a, Instance b) throws InvalidEducationException {
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