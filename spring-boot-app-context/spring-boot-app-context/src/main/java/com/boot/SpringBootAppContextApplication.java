package com.boot;

import org.springframework.boot.builder.SpringApplicationBuilder;

import com.boot.child.ChildCtxConfig;
import com.boot.parent.ParentCtxConfig;
import com.boot.sibling.SiblingCtxConfig;

public class SpringBootAppContextApplication
{

	public static void main(String[] args)
	{
		new SpringApplicationBuilder().sources(ParentCtxConfig.class) // type AnnotationConfigApplicationContext 
		                              .child(ChildCtxConfig.class)    // type AnnotationConfigEmbeddedWebApplicationContext
		                              .sibling(SiblingCtxConfig.class)
		                              .logStartupInfo(true)
		                              .run(args);
		
		/*new SpringApplicationBuilder().sources(ParentCtxConfig.class)
        .run(args);*/
	}
}
