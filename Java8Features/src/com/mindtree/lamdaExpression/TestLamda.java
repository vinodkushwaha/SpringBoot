package com.mindtree.lamdaExpression;

public class TestLamda {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StateOwner stateOwner = new StateOwner();
System.out.println("comming");
		stateOwner.addStateListener(
		     (oldState, newState) -> System.out.println("State changed")
		);


	}

}
