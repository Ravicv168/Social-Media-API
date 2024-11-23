package com.socailmedia.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String content;
	
	private int likes=0;
	private int dislikes=0;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public Comment() {
		this.createdAt = createdAt.now();
		this.updatedAt = createdAt.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Comment(Long id, String content, int likes, int dislikes, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.content = content;
		this.likes = likes;
		this.dislikes = dislikes;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
}
