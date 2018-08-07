package com.boot.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.boot.repository.PersonRepository;

/*
 * This is the main interface that you need to implement in order to create your
 * custom health monitor. You need to implement the health method that returns a
 * health instance.
 */
@Component
public class QuotaHealthIndicator implements HealthIndicator
{

	private static final Long	QUOTA_MAX_SIZE	= 3L;

	@Autowired
	PersonRepository			repo;

	@Override
	public Health health()
	{
		long size = repo.count();
		if (size <= QUOTA_MAX_SIZE) return Health.up()
		                                         .withDetail("quota.entries", size)
		                                         .build();
		else
			return Health.down()
			             .withDetail("quota.entries", size)
			             .withException(new QuotaException("Quota Exceeded. Max allow: " + QUOTA_MAX_SIZE + ". See your Administrator for Quota policies."))
			             .build();
	}

}
