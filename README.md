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
git clone https://github.com/Ravicv168/Social-Media-API.git
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
###User Endpoints:
 - Register User: POST /users/register
 - Get User Profile By Username: GET /users/name/{username}
 - Get User Profile By Email: GET /users/email/{email}
 - Update User Profile: PUT /users/update/{username}
 - Get All the followers: GET /users/{userId}/followers
 - Get All the Following Users: GET /users/{userId}/following
###Post Endpoints:
 - Create Post: POST /posts/create
 - Get All Posts: GET /posts?id={userId}
 - Get Post by ID: GET /posts/{postId}?uid={userId}
 - Update Post: PUT /posts/update/{postId}?uid={userId}
 - Delete Post: DELETE /posts/{postId}?uid={userId}
###Follow/Unfollow Endpoints:
 - Follow a User: PUT /users/{userId}/follow/{followUserId}
 - Unfollow a User: PUT /users/{userId}/unfollow/{followUserId}
###Like/Dislike Endpoints:
 - Like a Post: PUT /posts/like/{postId}?uid={userId}
 - Dislike a Post: PUT /posts/dislike/{postId}?uid={userId}
 - Like a Comment: PUT /comments/like/{id}
 - Dislike a Comment: PUT /comments/dislike/{id}
###Comment Endpoints:
 - Add Comment to Post: POST /comments/create
 - Get Comments for Post: GET /comments?pid={postId}
 - Edit a Comment: PUT /comments/update/{id}
 - Delete a Comment: DELETE /comments/{id}?pid={postId}

## Testing the API
Postman: Use Postman to test the endpoints by sending requests.
