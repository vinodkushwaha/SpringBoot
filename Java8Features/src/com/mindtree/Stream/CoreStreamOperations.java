package com.mindtree.Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CoreStreamOperations {

	public static void main(String[] args) {
		List<String> memberNames = new ArrayList<>();
		memberNames.add("Amitabh");
		memberNames.add("Shekhar");
		memberNames.add("Aman");
		memberNames.add("Rahul");
		memberNames.add("Shahrukh");
		memberNames.add("Salman");
		memberNames.add("Yana");
		memberNames.add("Lokesh");

		// filter()
		memberNames.stream().filter((s) -> s.startsWith("A"))
				.forEach(System.out::println);
		// map()
		memberNames.stream().filter((s) -> s.startsWith("A"))
				.map(String::toUpperCase).forEach(System.out::println);
		System.out.println("before sorting");
		// sorted()
		memberNames.stream().sorted().filter((s) -> s.startsWith("A")).map(String::toUpperCase)
				.forEach(System.out::println);
		
		//or
		/*memberNames.stream().filter((s) -> s.startsWith("A")).sorted().map(String::toUpperCase)
		.forEach(System.out::println);*/

		// Terminal operations
		// A) forEach()
		memberNames.forEach(System.out::println);

		// B) collect()
		List<String> memNamesInUppercase = memberNames.stream().sorted()
				.map(String::toUpperCase).collect(Collectors.toList());

		System.out.print(memNamesInUppercase);

		// C) match()
		boolean matchedResult = memberNames.stream().anyMatch(
				(s) -> s.startsWith("A"));

		System.out.println(matchedResult);

		matchedResult = memberNames.stream().allMatch((s) -> s.startsWith("A"));

		System.out.println(matchedResult);

		matchedResult = memberNames.stream()
				.noneMatch((s) -> s.startsWith("A"));

		System.out.println(matchedResult);

		// D) count()
		// Count is a terminal operation returning the number of elements in the
		// stream as a long.

		long totalMatched = memberNames.stream()
				.filter((s) -> s.startsWith("A")).count();

		System.out.println(totalMatched);
		/*
		 * E) reduce() This terminal operation performs a reduction on the
		 * elements of the stream with the given function. The result is an
		 * Optional holding the reduced value.
		 */
		Optional<String> reduced = memberNames.stream().reduce(
				(s1, s2) -> s1 + "#" + s2);

		reduced.ifPresent(System.out::println);
		/*
		 * A) anyMatch() This will return true once a condition passed as
		 * predicate satisfy. It will not process any more elements.
		 */
		boolean matched = memberNames.stream().anyMatch(
				(s) -> s.startsWith("A"));

		System.out.println(matched);
		/*
		 * B) findFirst() It will return first element from stream and then will
		 * not process any more element.
		 */

		String firstMatchedName = memberNames.stream()
				.filter((s) -> s.startsWith("L")).findFirst().get();

		System.out.println(firstMatchedName);
	}

}
