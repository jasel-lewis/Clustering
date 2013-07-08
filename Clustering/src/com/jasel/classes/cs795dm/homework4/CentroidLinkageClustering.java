package com.jasel.classes.cs795dm.homework4;

import java.util.ArrayList;


public class CentroidLinkageClustering {
	/**
	 * @param args
	 * @throws InvalidEducationValueException 
	 */
	public static void main(String[] args) throws InvalidEducationValueException {
		final ClusterNumberFormat cnf = new ClusterNumberFormat();
		
		Cluster tempClusterI = null;
		Cluster tempClusterJ = null;
		Cluster minA = null;
		Cluster minB = null;
		Instance tempClusterICentroid = null;
		Instance tempClusterJCentroid = null;
		
		double dist = 0.0;
		double minDist = Double.POSITIVE_INFINITY;
		
		ArrayList<Cluster> clusters = ClusterTestData.getClusters();
		
		while (clusters.size() > 1) {
			for (int i = 0; i < clusters.size(); i++) {
				for (int j = i + 1; j < clusters.size(); j++) {
					tempClusterI = clusters.get(i);
					tempClusterICentroid = tempClusterI.getCentroid();
					tempClusterJ = clusters.get(j);
					tempClusterJCentroid = tempClusterJ.getCentroid();
					
					dist = ClusterCalculation.distance(tempClusterICentroid, tempClusterJCentroid);
					
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
			System.out.println("Merging cluster " + minB.getName() + " into cluster " + minA.getName());
			
			// minA and minB are the two clusters with the closest centroids. Merge B into A and remove B from the list of Clusters.
			minA.merge(minB);
			clusters.remove(minB);
			
			System.out.println("Cluster " + minA.getName() + " now contains instance(s) " + minA.getInstancesNameSet());
		}
	}
}