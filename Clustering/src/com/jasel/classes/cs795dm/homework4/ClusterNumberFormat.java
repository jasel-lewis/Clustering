package com.jasel.classes.cs795dm.homework4;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ClusterNumberFormat {
	private final static NumberFormat numberFormat = NumberFormat.getInstance();
	private final static int DECIMAL_PLACES = 4;
	
	public ClusterNumberFormat() {
		// Set us up to round in the normal fashion to the identified number of decimal digits
		if (numberFormat instanceof DecimalFormat) {
			((DecimalFormat) numberFormat).setRoundingMode(RoundingMode.HALF_UP);
			((DecimalFormat) numberFormat).setMaximumFractionDigits(DECIMAL_PLACES);
			((DecimalFormat) numberFormat).applyPattern("###0.0000");
		}
	}
	
	
	public String format(double number) {
		return numberFormat.format(number);
	}
}