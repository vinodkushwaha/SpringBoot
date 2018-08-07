package com.boot;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.boot.dao.AdminDAO;

@SpringBootApplication
public class SpringBootFailureAnalyzerApplication
{
	private static final Logger	log	= LoggerFactory.getLogger(SpringBootFailureAnalyzerApplication.class);

	/*@Resource(name = "adminDAO")
	private AdminDAO			adminDAO;
*/
	public static void main(String[] args)
	{
		new SpringApplicationBuilder(SpringBootFailureAnalyzerApplication.class).bannerMode(Banner.Mode.LOG)
		                                                                        .logStartupInfo(true)
		                                                                        .profiles("prod")
		                                                                        .listeners(new ApplicationListener<ApplicationEvent>()
		                                                                        {
			                                                                        @Override
			                                                                        public void onApplicationEvent(ApplicationEvent event)
			                                                                        {
				                                                                        log.info("#### > "
				                                                                                + event.getClass()
				                                                                                       .getCanonicalName());
			                                                                        }
		                                                                        })
		                                                                        .run(args);
	}

}
