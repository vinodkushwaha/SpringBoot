package com.boot.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.boot.util.JsonDateDeserializer;
import com.boot.util.JsonDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "entry")
public class JournalEntry
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long					id;
	private String					title;
	private String					summary;
	private Date					created;

	@Transient
	private final SimpleDateFormat	format	= new SimpleDateFormat("dd-MM-yyyy");

	public JournalEntry(String title, String summary, String created) throws ParseException
	{
		this.title = title;
		this.created = format.parse(created);
		this.summary = summary;

	}

	public JournalEntry()
	{
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getCreated()
	{
		return created;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setCreated(Date created) throws ParseException
	{
		this.created = created;
	}

	@JsonIgnore
	public String getCreatedAsShort()
	{
		return format.format(created);
	}

	public String getSummary()
	{
		return summary;
	}

	public void setSummary(String summary)
	{
		this.summary = summary;
	}

	public String toString()
	{
		StringBuilder value = new StringBuilder("* JournalEntry(");
		value.append(",Title: ");
		value.append(title);
		value.append(",Summary: ");
		value.append(summary);
		value.append(",Created: ");
		value.append(format.format(created));
		value.append(")");
		return value.toString();
	}

}
