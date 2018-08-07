package com.mindtree.parallelStream;


import java.util.ArrayList;
import java.util.List;

/**
 * @author only2dhir
 *
 */
public class ParallelStreamPerformanceCheck {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> numList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
        	numList.add(i);
        }
 
        // Processing sequentially
        long startTime = System.currentTimeMillis();
        numList.stream().forEach(i -> processData(i));
        long endTime = System.currentTimeMillis();
        double sequentialStreamTimetaken = (endTime - startTime) / 1000;
        System.out.println("Time required with stream() : " + sequentialStreamTimetaken);
 
        // Parallel processing 
        startTime = System.currentTimeMillis();
        numList.parallelStream().forEach(i -> processData(i));
        endTime = System.currentTimeMillis();
        long parallelStreamTimetaken = (endTime - startTime) / 1000;
        System.out.println("Time required with parallelStream() : " + parallelStreamTimetaken);
        System.out.println("Differential time : " + (sequentialStreamTimetaken - parallelStreamTimetaken));
    }
 
    private static void processData(int i) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}


}
