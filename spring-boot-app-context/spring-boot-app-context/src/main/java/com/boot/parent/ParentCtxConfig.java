package com.boot.parent;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.boot.child.ChildCtxConfig;
import com.boot.sibling.SiblingCtxConfig;

@SpringBootApplication
public class ParentCtxConfig
{
	@Bean(name = "parent_ctx_bean")
	public String getParentBean()
	{
		return "parent_ctx_bean";
	}

	/*@Bean
	public ServletRegistrationBean createChildCtx()
	{
		DispatcherServlet dispatcherServlet = new DispatcherServlet();

		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(ChildCtxConfig.class);
		applicationContext.setId("child");

		dispatcherServlet.setApplicationContext(applicationContext);
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(dispatcherServlet,
		                                                                              "/child/*");
		servletRegistrationBean.setName("child");
		servletRegistrationBean.setLoadOnStartup(1);

		return servletRegistrationBean;
	}
	
	@Bean
	public ServletRegistrationBean createSiblingCtx()
	{
		DispatcherServlet dispatcherServlet = new DispatcherServlet();

		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(SiblingCtxConfig.class);
		applicationContext.setId("sibling");

		dispatcherServlet.setApplicationContext(applicationContext);
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(dispatcherServlet,
		                                                                              "/sibling/*");
		servletRegistrationBean.setName("sibling");
		servletRegistrationBean.setLoadOnStartup(2);

		return servletRegistrationBean;
	}
*/
}
