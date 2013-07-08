package com.jasel.classes.cs795dm.homework4;

import java.util.ArrayList;


public class SingleLinkageClustering {
	/**
	 * @param args
	 * @throws InvalidEducationValueException 
	 */
	public static void main(String[] args) throws InvalidEducationValueException {
		final ClusterNumberFormat cnf = new ClusterNumberFormat();
		
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
					tempClusterJ = clusters.get(j);
					
					System.out.println("Cluster " + tempClusterI.getName() + " has Instance(s) " + tempClusterI.getInstancesNameSet());
					System.out.println("Cluster " + tempClusterJ.getName() + " has Instance(s) " + tempClusterJ.getInstancesNameSet());
					
					for (int k = 0; k < tempClusterI.size(); k++) {
						for (int l = 0; l < tempClusterJ.size(); l++) {
							tempClusterIInstance = tempClusterI.get(k);
							tempClusterJInstance = tempClusterJ.get(l);
							
							dist = ClusterCalculation.distance(tempClusterI.get(k), tempClusterJ.get(l));
							
							System.out.println("DIST(" + tempClusterIInstance.getName() + "," + tempClusterJ.getName() + ") = " +
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
				
			System.out.println("Minimum exists between Instance " + minInstanceA.getName() + " of Cluster " + minClusterA.getName() +
					" and Instance " + minInstanceB.getName() + " of Cluster " + minClusterB.getName() +
					" with a value of " + cnf.format(minDist));
			System.out.println("Merging cluster " + minClusterB.getName() + " into cluster " + minClusterA.getName());
			
			// minA and minB are the two clusters with the closest member Instance(s).
			// Merge B into A and remove B from the list of Clusters.
			minClusterA.merge(minClusterB);
			clusters.remove(minClusterB);
			
			System.out.println("Cluster " + minClusterA.getName() + " now contains instance(s) " + minClusterA.getInstancesNameSet());
		}
	}
}