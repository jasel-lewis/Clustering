package com.jasel.classes.cs795dm.homework4;

import java.util.Comparator;

public class NameComparator implements Comparator<Instance> {
	@Override
	public int compare(Instance a, Instance b) {
		return a.getName().compareTo(b.getName());
	}
}