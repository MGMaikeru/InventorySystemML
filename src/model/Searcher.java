package model;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Searcher<T, K extends Comparable<K>> {
	public T search(ArrayList<T> list, String searchVariable, K attributeValue) {
		int index = binarySearch(list, searchVariable, attributeValue, 0, list.size() - 1);
		T element = index == -1 ? null : list.get(index);
		return element != null && compareValues(element, searchVariable, attributeValue) == 0 ? element : null;
	}

	private int binarySearch(ArrayList<T> list, String searchVariable, K attributeValue, int left, int right) {
		if (list.isEmpty()) return -1;
		if (left > right) return right;

		int mid = (right + left) / 2;
		T element = list.get(mid);
		if (compareValues(element, searchVariable, attributeValue) == 0)
			return mid;
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

	public ArrayList<T> filterList(ArrayList<T> list, String searchVariable, K min, K max) {
		int indexMin = binarySearch(list, searchVariable, min, 0, list.size() - 1);
		int indexMax = binarySearch(list, searchVariable, max, 0, list.size() - 1);
		return indexMin != -1 && indexMax != -1 ? (ArrayList<T>) list.subList(indexMin, indexMax) : new ArrayList<>();
	}

}
