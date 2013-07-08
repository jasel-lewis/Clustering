package com.jasel.classes.cs795dm.homework4;

import java.util.ArrayList;


public class CentroidLinkageClustering {
	/**
	 * @param args
	 * @throws InvalidEducationValueException 
	 */
	public static void main(String[] args) throws InvalidEducationValueException {
		final double HIGH_MIN = 100000000.0;
		final ClusterNumberFormat cnf = new ClusterNumberFormat();
		
		Cluster tempClusterI = null;
		Cluster tempClusterJ = null;
		Cluster minA = null;
		Cluster minB = null;
		Instance tempClusterICentroid = null;
		Instance tempClusterJCentroid = null;
		
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
		
		while (clusters.size() > 1) {
			for (int i = 0; i < clusters.size(); i++) {
				for (int j = i + 1; j < clusters.size(); j++) {
					tempClusterI = clusters.get(i);
					tempClusterICentroid = tempClusterI.getCentroid();
					tempClusterJ = clusters.get(j);
					tempClusterJCentroid = tempClusterJ.getCentroid();
					
					dist = distance(tempClusterICentroid, tempClusterJCentroid);
					
					if ((minA == null) || (minB == null) || (dist < minDist)) {
						minDist = dist;
						minA = tempClusterI;
						minB = tempClusterJ;
					}
					
					System.out.print("Cluster " + tempClusterI.getName() + " contains instance(s) " + tempClusterI.getInstancesNameSet() +
							" with centroid " + tempClusterICentroid.toString());
					System.out.print("Cluster " + tempClusterJ.getName() + " contains instance(s) " + tempClusterJ.getInstancesNameSet() +
							" with centroid " + tempClusterJCentroid.toString());
					
					System.out.println("DIST(" + tempClusterI.getName() + "," + tempClusterJ.getName() + ") = " + cnf.format(dist));
				}
			}
				
			System.out.println("Minimum exists between the centroids of clusters " + minA.getName() + " and " + minB.getName() +
					" with a value of " + cnf.format(minDist));
			System.out.println("Merging cluster " + minB.getName() + " into cluster " + minA.getName() + ".");
			
			// minA and minB are the two clusters with the closest centroids. Merge B into A and remove B from the list of Clusters.
			minA.merge(minB);
			clusters.remove(minB);
			
			System.out.println("Cluster " + minA.getName() + " now contains instance(s) " + minA.getInstancesNameSet());
		}
	}
	
	
	private static double distance(Instance a, Instance b) throws InvalidEducationValueException {
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