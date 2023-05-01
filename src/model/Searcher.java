package model;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Searcher<T, K extends Comparable<K>> {
	public T binarySearch(ArrayList<T> list, String searchVariable, K attributeValue) {
		return binarySearch(list, searchVariable, attributeValue, 0, list.size() - 1);
	}

	private T binarySearch(ArrayList<T> list, String searchVariable, K attributeValue, int left, int right) {
		if (left > right) {
			return null;
		}
		int mid = (right + left) / 2;
		T element = list.get(mid);
		if (compareValues(element, searchVariable, attributeValue) == 0)
			return element;
		if (compareValues(element, searchVariable, attributeValue) < 0) {
			left = mid + 1;
		} else {
			right = mid - 1;
		}
		return binarySearch(list, searchVariable, attributeValue, left, right);
	}

	private int compareValues(T element, String searchVariable, K attributeValue) {
		K value = getAttributeValue(element, searchVariable);
		return value.compareTo(attributeValue);
	}

	private K getAttributeValue(T element, String searchVariable) {
		try {
			Field field = element.getClass().getDeclaredField(searchVariable);
			field.setAccessible(true);
			Object value = field.get(element);
			return (K) value;
		} catch (NoSuchFieldException | IllegalAccessException | ClassCastException e) {
			throw new IllegalArgumentException("Invalid attribute name or incompatible types", e);
		}
	}

	public ArrayList<T> filterRange(ArrayList<T> list, String searchVariable, K min, K max) {
		ArrayList<T> elementsInRange = new ArrayList<>();
		for (T element : list) {
			K value = getAttributeValue(element, searchVariable);
			if (value.compareTo(min) >= 0 && value.compareTo(max) <= 0) {
				elementsInRange.add(element);
			}
		}
		return elementsInRange;
	}

}
