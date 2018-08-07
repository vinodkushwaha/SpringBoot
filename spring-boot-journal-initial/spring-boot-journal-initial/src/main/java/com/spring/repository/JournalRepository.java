package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.domain.Journal;

public interface JournalRepository extends JpaRepository<Journal, Long>
{

}
