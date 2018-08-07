package com.mindtree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
//https://www.journaldev.com/2389/java-8-features-with-examples
//When I try to copy the code into eclipse(newest version, jdk 1.8, java compliance 1.7)
//it says that a can't be resolved to variable. 
public class StreamExample {

	public static void main(String[] args) {
		
		List<Integer> myList = new ArrayList<>();
		for(int i=0; i<100; i++) 
			myList.add(i);
		
		//sequential stream
		Stream<Integer> sequentialStream = myList.stream();
		
		//parallel stream
		Stream<Integer> parallelStream = myList.parallelStream();
	
		//using lambda with Stream API, filter example
		Stream<Integer> highNums = parallelStream.filter(p -> p > 90);
		//using lambda in forEach
		highNums.forEach(p -> System.out.println("High Nums parallel="+p));
		
		Stream<Integer> highNumsSeq = sequentialStream.filter(p -> p > 90);
		highNumsSeq.forEach(p -> System.out.println("High Nums sequential="+p));

	}

}
/*op:
	If you will run above example code, you will get output like this:

		High Nums parallel=91
		High Nums parallel=96
		High Nums parallel=93
		High Nums parallel=98
		High Nums parallel=94
		High Nums parallel=95
		High Nums parallel=97
		High Nums parallel=92
		High Nums parallel=99
		High Nums sequential=91
		High Nums sequential=92
		High Nums sequential=93
		High Nums sequential=94
		High Nums sequential=95
		High Nums sequential=96
		High Nums sequential=97
		High Nums sequential=98
		High Nums sequential=99
		Notice that parallel processing values are not in order, so parallel processing will be very helpful while working with huge collections.*/
