package model;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Searcher<T, K extends Comparable<K>> {
	public T search(ArrayList<T> list, String searchVariable, K attributeValue) {
		int index = binarySearch(list, searchVariable, attributeValue, 0, list.size() - 1);
		T element = index < list.size() && index != -1 ? list.get(index) : null;
		return element != null && compareValues(element, searchVariable, attributeValue) == 0 ? element : null;
	}

	private int binarySearch(ArrayList<T> list, String searchVariable, K attributeValue, int left, int right) {
		if (list.isEmpty()) return -1;
		if (left > right) return right + 1;

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

	private K getAttributeValue(T element, String searchVariable) throws IllegalArgumentException {
		K value;
		try {
			Field field = element.getClass().getDeclaredField(searchVariable);
			field.setAccessible(true);
			value = (K) field.get(element);
		} catch (NoSuchFieldException | IllegalAccessException | ClassCastException e) {
			throw new IllegalArgumentException("Invalid comparison variable.");
		}
		return value;
	}

	public ArrayList<T> filterList(ArrayList<T> list, String searchVariable, K min, K max) {
		if (list == null) throw new RuntimeException("Error. The list must be initialized to search for matches.");
		if (min.compareTo(max) > 0)
			throw new RuntimeException("Error. The minimum search criteria cannot be greater than the maximum.");
		int indexMin = binarySearch(list, searchVariable, min, 0, list.size() - 1);
		int indexMax = binarySearch(list, searchVariable, max, 0, list.size() - 1);
		ArrayList<T> listCropped = new ArrayList<>();
		if (indexMin != -1 && indexMax != -1) {
			for (int i = indexMin; i < list.size() && i < indexMax; i++)
				listCropped.add(list.get(i));
			if (indexMax < list.size() && compareValues(list.get(indexMax), searchVariable, max) == 0)
				listCropped.add(list.get(indexMax));
		}
		return listCropped;
	}

}
