package com.boot.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.domain.Journal;
import com.boot.repository.JournalRepository;

@Service
public class JournalService
{
	private static final Logger	log	= LoggerFactory.getLogger(JournalService.class);

	@Autowired
	JournalRepository			repo;

	@PersistenceContext
	private EntityManager		manager;

	public void insertData() throws ParseException
	{
		log.info("Proxy :" + repo.getClass()
		                         .getName());
		log.info("> Inserting data...");
		repo.save(new Journal("Get to know Spring Boot", "Today I will learn Spring Boot", "01-01-2016"));
		repo.save(new Journal("Simple Spring Boot Project", "I will do my first Spring Boot Project", "01-02-2016"));
		repo.save(new Journal("Spring Boot Reading", "Read more about Spring Boot", "02-01-2016"));
		repo.save(new Journal("Spring Boot in the Cloud", "Spring Boot using Cloud Foundry", "03-01-2016"));
		log.info("> Done.");

	}

	public List<Journal> findAll()
	{
		return repo.findAll();
	}

	public List<Journal> findByCustomTitleQuery(String word)
	{
		return repo.findByCustomTitleQuery(word);
	}

	public List<Journal> findAllByEntityManager()
	{
		return manager.createQuery("select j  from Journal j")
		              .getResultList();
	}

	public Journal findByCreated(Date created)
	{
		return repo.findByCreated(created);
	}

	public List<Journal> findByCreatedAfter(Date date)
	{
		return repo.findByCreatedAfter(date);
	}
}