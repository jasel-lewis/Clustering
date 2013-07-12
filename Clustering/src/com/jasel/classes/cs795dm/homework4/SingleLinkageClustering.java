package com.jasel.classes.cs795dm.homework4;

import java.util.ArrayList;


public class SingleLinkageClustering {
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
		Instance minInstanceA = null;
		Instance minInstanceB = null;
		
		double dist = 0.0;
		double minDist = Double.POSITIVE_INFINITY;
		
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
							
							dist = ClusterCalculation.distance(tempClusterIInstance, tempClusterJInstance);
							
							System.out.println("   DIST(" + tempClusterIInstance.getName() + "," + tempClusterJInstance.getName() + ") = " +
									cnf.format(dist));
							
							if (dist < minDist) {
								minDist = dist;
								minClusterA = tempClusterI;
								minInstanceA = tempClusterIInstance;
								minClusterB = tempClusterJ;
								minInstanceB = tempClusterJInstance;
							}
						}
					}
				}
			}
				
			System.out.println("*** Minimum exists between Instance " + minInstanceA.getName() + " of Cluster " + minClusterA.getName() +
					" and Instance " + minInstanceB.getName() + " of Cluster " + minClusterB.getName() +
					" with a distance of " + cnf.format(minDist));
			System.out.print("  * Merging cluster " + minClusterB.getName() + " into cluster " + minClusterA.getName() + ": ");
			
			// minClusterA and minClusterB are the two clusters with the closest member Instance(s).
			// Merge B into A and remove B from the list of Clusters.
			minClusterA.merge(minClusterB);
			clusters.remove(minClusterB);
			
			System.out.println("Cluster " + minClusterA.getName() + " now contains instance(s) " + minClusterA.getInstancesNameSet());
			
			minDist = Double.POSITIVE_INFINITY;
		}
	}
}