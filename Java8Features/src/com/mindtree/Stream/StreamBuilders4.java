package com.mindtree.Stream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamBuilders4 {
	   public static void main(String[] args){
	        IntStream stream = "12345_abcdefg".chars();
	        stream.forEach(p -> System.out.println(p));
	         System.out.println("             ");
	        //OR
	         
	        Stream<String> stream1 = Stream.of("A$B$C".split("\\$"));
	        stream1.forEach(p -> System.out.println(p));
	     }
	}