package com.mindtree.parallelStream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamBuilders5 {
	public static void main(String[] args) {
		// Convert Stream to List using stream.collect(Collectors.toList())
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i < 10; i++) {
			list.add(i);
		}
		Stream<Integer> stream = list.stream();
		List<Integer> evenNumbersList = stream.filter(i -> i % 2 == 0).collect(
				Collectors.toList());
		System.out.print(evenNumbersList);
		// Convert Stream to array using stream.toArray(EntryType[]::new)
		List<Integer> list1 = new ArrayList<Integer>();
		for (int i = 1; i < 10; i++) {
			list1.add(i);
		}
		Stream<Integer> stream1 = list.stream();
		Integer[] evenNumbersArr = stream1.filter(i -> i % 2 == 0).toArray(Integer[]::new);
		System.out.print(evenNumbersArr);

	}
}