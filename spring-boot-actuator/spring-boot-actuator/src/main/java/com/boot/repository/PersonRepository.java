package com.boot.repository;

import org.springframework.data.repository.CrudRepository;

import com.boot.model.Person;

public interface PersonRepository extends CrudRepository<Person, Long>
{
}