package com.socailmedia.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private String content;
	
	private int likes=0;
	private int dislikes=0;
	
	private LocalDateTime createdAt;
	
	@ManyToOne
	@JsonBackReference
    private User user;
	
	public Post() {
		// TODO Auto-generated constructor stub
		this.createdAt=createdAt.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post(Long id, String title, String content, int likes, int dislikes) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.likes = likes;
		this.dislikes = dislikes;
	}
	
}
