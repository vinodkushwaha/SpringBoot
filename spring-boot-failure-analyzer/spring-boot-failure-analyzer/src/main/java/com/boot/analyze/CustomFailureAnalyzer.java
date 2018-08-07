package com.boot.analyze;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

public class CustomFailureAnalyzer extends AbstractFailureAnalyzer<BeanNotOfRequiredTypeException>
{

	@Override
	protected FailureAnalysis analyze(Throwable rootFailure, BeanNotOfRequiredTypeException cause)
	{
		String message = "####################### This is a custom fail Message ################. "
		        + getDescription(cause);
		return new FailureAnalysis(message,
		                           (String) null,
		                           cause);
	}

	private String getDescription(BeanNotOfRequiredTypeException ex)
	{

		StringWriter description = new StringWriter();
		PrintWriter printer = new PrintWriter(description);
		printer.printf("The bean %s could not be injected: "
		        + "expected type %s", ex.getBeanName(), ex.getRequiredType()
		                                           .getName());
		return description.toString();
	}

}
