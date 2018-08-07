package com.boot.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.boot.domain.Journal;

public interface JournalRepository extends MongoRepository<Journal, String>
{
	// db.journal.find({"title": /.*?1*/})
	public List<Journal> findByTitleLike(String word);
}
