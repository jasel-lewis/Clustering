package com.jasel.classes.cs795dm.homework4;

import java.util.ArrayList;


public class AverageLinkageClustering {
	/**
	 * @param args
	 * @throws InvalidEducationValueException 
	 */
	public static void main(String[] args) throws InvalidEducationValueException {
		final ClusterNumberFormat cnf = new ClusterNumberFormat();
		final ClusterTestData ctd = new ClusterTestData();
		
		Cluster tempClusterI = null;
		Cluster tempClusterJ = null;
		Cluster minClusterA = null;
		Cluster minClusterB = null;
		Instance tempClusterIInstance = null;
		Instance tempClusterJInstance = null;
		
		double dist = 0.0;
		double distSum = 0.0;
		double minDist = Double.POSITIVE_INFINITY;
		double avgDist = Double.POSITIVE_INFINITY;
		int count = 0;
		
		ArrayList<Cluster> clusters = ctd.getClusters();
		
		while (clusters.size() > 1) {
			for (int i = 0; i < clusters.size(); i++) {
				for (int j = i + 1; j < clusters.size(); j++) {
					tempClusterI = clusters.get(i);
					tempClusterJ = clusters.get(j);
					
					System.out.println("Cluster " + tempClusterI.getName() + " has Instance(s) " + tempClusterI.getInstancesNameSet());
					System.out.println("Cluster " + tempClusterJ.getName() + " has Instance(s) " + tempClusterJ.getInstancesNameSet());
					
					for (int k = 0; k < tempClusterI.size(); k++) {
						for (int l = 0; l < tempClusterJ.size(); l++) {
							tempClusterIInstance = tempClusterI.get(k);
							tempClusterJInstance = tempClusterJ.get(l);
							
							// Find distance between the two instances and add it to running total for
							// the current, two, Clusters
							dist = ClusterCalculation.distance(tempClusterI.get(k), tempClusterJ.get(l));
							distSum += dist;
							++count;
							
							System.out.println("   DIST(" + tempClusterIInstance.getName() + "," + tempClusterJInstance.getName() +
									") = " + cnf.format(dist));
						}
					}
					
					// Find average of the distances
					avgDist = distSum / count;
					
					System.out.println("   ** Average distance found to be " + cnf.format(avgDist));
					
					if ((minClusterA == null) || (minClusterB == null) || (avgDist < minDist)) {
						minDist = avgDist;
						minClusterA = tempClusterI;
						minClusterB = tempClusterJ;
					}
					
					dist = 0.0;
					distSum = 0.0;
					avgDist = Double.POSITIVE_INFINITY;
					count = 0;
				}
			}
				
			System.out.println("*** Minimum average distance exists between Cluster " + minClusterA.getName() +
					" and Cluster " + minClusterB.getName() + " with a value of " + cnf.format(minDist));
			System.out.print("  * Merging cluster " + minClusterB.getName() + " into cluster " + minClusterA.getName() + ": ");
			
			// minA and minB are the two clusters with the closest average distance.
			// Merge B into A and remove B from the list of Clusters.
			minClusterA.merge(minClusterB);
			clusters.remove(minClusterB);
			
			System.out.println("Cluster " + minClusterA.getName() + " now contains instance(s) " + minClusterA.getInstancesNameSet());
			
			minDist = Double.POSITIVE_INFINITY;
		}
	}
}