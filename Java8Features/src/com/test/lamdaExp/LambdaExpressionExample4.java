package com.test.lamdaExp;

public class LambdaExpressionExample4{  
    public static void main(String[] args) {  
    	Person p = new Person();
    	p.setName("vinod");
    	p.setAge(29);
    	
    	PersonInterface pr = (Person) -> { return "hi" + p.getAge() + p.getName();};
    	
    	//print return type
    	 System.out.println(pr.getdetails(null));
    	//System.out.println(pr.getdetails(p).toString());
      
        // Lambda expression with single parameter.  
        Sayable s1=(name)->{  
            return "Hello, "+name;  
        };  
        System.out.println(s1.say(""));  
        System.out.println(s1.say("Sonoo"));  
          
        // You can omit function parentheses    
        Sayable s2= name ->{  
            return "Hello, "+name;  
        };  
        System.out.println(s2.say("Sonoo"));  
    }  
}
