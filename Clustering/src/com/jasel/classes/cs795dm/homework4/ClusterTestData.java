package com.jasel.classes.cs795dm.homework4;

import java.util.ArrayList;

public final class ClusterTestData {
	static ArrayList<Cluster> clusters = new ArrayList<Cluster>();
	
	public ClusterTestData (ArrayList<Cluster> clusters) {
		clusters.add(new Cluster("1", new Instance("a", 0.1167, "BS", 0.1321, 0.0538, 0.0787)));
		clusters.add(new Cluster("2", new Instance("b", 0.0833, "MS", 0.1132, 0.0000, 0.0562)));
		clusters.add(new Cluster("3", new Instance("c", 0.1000, "BS", 0.0943, 0.0645, 0.1124)));
		clusters.add(new Cluster("4", new Instance("d", 0.1833, "BS", 0.1321, 0.3326, 0.1685)));
		clusters.add(new Cluster("5", new Instance("e", 0.1500, "MS", 0.1321, 0.1613, 0.1124)));
		clusters.add(new Cluster("6", new Instance("f", 0.1000, "MS", 0.1509, 0.0215, 0.0787)));
		clusters.add(new Cluster("7", new Instance("g", 0.1333, "BS", 0.0943, 0.2151, 0.2247)));
		clusters.add(new Cluster("8", new Instance("h", 0.1333, "MS", 0.1509, 0.1613, 0.1685)));
	}
	
	
	public static ArrayList<Cluster> getClusters() {
		return clusters;
	}
}