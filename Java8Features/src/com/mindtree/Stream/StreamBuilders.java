package com.mindtree.Stream;

import java.util.stream.Stream;

public class StreamBuilders {
    public static void main(String[] args){
        Stream<Integer> stream = Stream.of(1,2,1,3,4,5,6,7,8,9);
        stream.distinct().forEach(p -> System.out.println(p));
       //or
        stream.forEach(System.out::println);
    }
}