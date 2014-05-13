package com.example.stickyheadergridview;

import java.util.Comparator;

public class YMComparator implements Comparator<GridItem> {

	@Override
	public int compare(GridItem o1, GridItem o2) {
		return o1.getTime().compareTo(o2.getTime());
	}

}
