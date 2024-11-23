# Social Media API

A simple backend API for social media platform built using Spring Boot. The API supports user registration, post creation, commenting, liking posts, and user following.

## Features
- User Registration
- Create, Update, Delete Posts
- Like/Dislike Posts and Comments
- Comment on Posts
- Follow/Unfollow Users

## Technologies Used
- Java 17
- Spring Boot
- Spring Data JPA
- Maven (Build Tool)

## Setup
Clone the repository:
git clone https://github.com/yourusername/social-media-api.git
cd social-media-api

Installation Steps:
Configure application.properties: 
    - Update the database connection settings in src/main/resources/application.properties (for MySQL/PostgreSQL).
Install Dependencies:
    - Use Maven to install dependencies.
        -  mvn clean install
Run the Application:
    - To run the API locally, use: mvn spring-boot:run

## API Endpoints
User Endpoints:
 - Register User: POST /users/register
 - Get User Profile By Username: GET /users/name/{username}
 - Get User Profile By Email: GET /users/email/{email}
 - Update User Profile: PUT /users/update/{username}
 - Get All the followers: GET /users/{userId}/followers
 - Get All the Following Users: GET /users/{userId}/following
Post Endpoints:
 - Create Post: POST /posts/create
 - Get All Posts: GET /posts?id={userId}
 - Get Post by ID: GET /api/posts/{postId}?uid={userId}
 - Update Post: PUT /posts/update/{postId}?uid={userId}
 - Delete Post: DELETE /posts/{postId}?uid={userId}
Follow/Unfollow Endpoints:
 - Follow a User: POST /users/{userId}/follow/{followUserId}
 - Unfollow a User: POST /users/{userId}/unfollow/{followUserId}
Like/Dislike Endpoints:
 - Like a Post: POST /posts/like/{postId}?uid={userId}
 - Dislike a Post: POST /posts/dislike/{postId}?uid={userId}
Comment Endpoints:
 - Add Comment to Post: POST /api/posts/{postId}/comments
 - Get Comments for Post: GET /api/posts/{postId}/comments

## Testing the API
Postman: Use Postman to test the endpoints by sending requests.
