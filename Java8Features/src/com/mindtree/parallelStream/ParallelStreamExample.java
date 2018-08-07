package com.mindtree.parallelStream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParallelStreamExample {
	public static void main(String[] args) {

		/* If we use serial stream the order is guaranted as below: */
		Stream.of("John", "Mike", "Ryan", "Donald", "Matthew").forEach(
				System.out::println);

		/* Whereas, the order is not guaranted while using parallel stream. */

		Stream.of("John", "Mike", "Ryan", "Donald", "Matthew").parallel()
				.forEach(System.out::println);
		
		count();
	}

	public static void count() {
		final long count = IntStream.range(1, 50).parallel()
				.filter(number -> isPrime(number)).count();
		System.out.println("Count - " + count);
	}

	public static boolean isPrime(final int number) {
		return number > 1
				&& IntStream.rangeClosed(2, (int) Math.sqrt(number)).noneMatch(
						divisor -> number % divisor == 0);
	}
}
