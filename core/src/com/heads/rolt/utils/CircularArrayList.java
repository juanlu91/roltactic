package com.heads.rolt.utils;

import java.util.ArrayList;

public class CircularArrayList<E> extends ArrayList<E> {
	private static final long serialVersionUID = 1L;

	@Override
	public E get(int index) {
		
		if (index < 0) {
			index = size() - index;
		}

		else if (index == size()) {
			index = 0;
		}

		return super.get(index);
	}
}
