package com.mindtree.defaultInterface;

//http://www.javainterviewpoint.com/multiple-inheritance-java-8/
public class MyClass implements Interface1, Interface2 {

	@Override
	public void method2() {
	}

	@Override
	public void method1(String str) {
	}

	@Override
	public void log(String str){
		System.out.println("MyClass logging::"+str);
		Interface1.super.log("abc");  //resolve multiple inheritance
	}
public static void main(String [] str){
	MyClass c = new MyClass();
	c.log("VINOD");
	
}
}
