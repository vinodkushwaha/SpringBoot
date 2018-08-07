package com.boot.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.boot.domain.Journal;

public interface JournalRepository extends JpaRepository<Journal, Long>
{
	// select * from JOURNAL where created > ?1 .
	List<Journal> findByCreatedAfter(Date date);

	// select * from JOURNAL where title like %?1% .
	public List<Journal> findByTitleContaining(String word);

	// The @Query annotation accepts the JPQL syntax.
	@Query("select j from Journal j where j.title like %?1%")
	List<Journal> findByCustomTitleQuery(String word);

	Journal findByCreated(Date date);

}
