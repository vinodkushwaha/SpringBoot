package com.test.lamdaExp;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class InterviewPrintDistinctStr {

	public static void main(String[] args) {
		List<String> st = new ArrayList<>();
		st.add("vinod");
		st.add("Neelam");
		st.add("Jatin");
		st.add("Duggu");
		st.add("vinod");
		
		// Get collection without duplicate i.e. distinct only
	     List<String> distinctElements = st.stream().distinct().collect(Collectors.toList());
		//List<String> distinctElements = st.stream().filter(e->( e.contains("A"))).collect(Collectors.toList());;
		
		//Let's verify distinct elements
		System.out.println(distinctElements);
	}

}
