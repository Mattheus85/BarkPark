   # Team_Bark_Park Design Document

## *Bark Park App* Design

## 1. Problem Statement

When a dog owner wants to find or learn more about a dog park, their best option right now is to do a basic web search for nearby dog parks
on the search engine of their choice. The results of this search are often limited, cluttered with non-relevant findings and 
lacking in valuable detail and information regarding the parks that are found.

This design document details Bark Park, a new service that will provide users access to a geographically organized database
of dog parks along with detailed information and reviews for each park. It is designed to interact with a
client application which will allow customers to view all dog parks known to the service or search for dog parks in a specific location. Customers will
also be able to create user accounts and review parks as well as filter parks by average review rating.


## 2. Top Questions to Resolve in Review

1. If we're not trying abstract details about the underlying db table implementation, should we still utilize models 
   in the results?
2. A review in the reviews table is identified by the unique composite key of partition key: parkId and sort key: userId.
   1. Is it conventional to provide a GET method for this sort of thing?
3. Is our implementation of GetReviews reasonable?
4. If we can think of a front end use case, should we build this functionality into the API? See locations
5. Can ModelConverter methods be static?
6. If we have text that will potentially be long form and include formatting, how should we store this?
7. How do we want to store location data? 
   1. Possibilities include geographical coordinates, city names, etc.
8. When a User deletes their account, should we also delete all reviews they have written and/or all parks they may have added?
   1. If we leave the reviews, how do we handle the associated UserId (if this is indeed how we implement the Review model)

## 3. Use Cases

U1. *As a customer, I want to view a list of all stored parks*

U2. *As a customer, I want to view a list of all nearby parks when I specify a location*

U3. *As a customer, I want to view details and reviews for a park when I select it*

U4. *As a customer, I want to view a list of all locations with parks*
___
U5. *As a new customer, I want to create a user account with a username, email, and password*

U6. *As a customer, I want to view my user account*

U7. *As a customer, I want to edit my user account*

U8. *As a customer, I want to delete my user account*
___
U8. *As a customer, I want to create a review of a park*

U9. *As a customer, I want to view my review of a park*

U10. *As a customer, I want to edit my review of a park*

U11. *As a customer, I want to delete my review of a park*

U11. *As a customer, I want to view a sorted and filtered list of parks that each have an average rating equal to or greater than
a review rating value I provide*



## 4. Project Scope

### 4.1. In Scope

1. Retrieve a list of all parks known to the service
2. Retrieve a list of parks filtered and sorted by location and/or average rating 
3. Retrieve a list of locations with available parks
4. Create, edit, and delete a user account
5. Create, edit, and delete a review for a park
6. Retrieve a list of reviews filtered by parkId and/or userId

### 4.2. Out of Scope

1. Allow users to add, remove, or update existing parks
   1. This could be done directly with permissions or through a request system handled by admins
2. User log in/out functionality.
   1. Will require frontend to pass user id or name without authentication for now
3. Require user to log in and be authenticated to edit or delete their user account
   1. Can be added later to applicable API Gateways with AWS Cognito
4. Require customer to log in and be authenticated to create, edit, or delete a review
   1. Can be added later to applicable API Gateways with AWS Cognito
5. User ability to *favorite* a park and persist this information in the database
6. User ability to upload photos for a park
   1. This can be integrated later with Cloudinary or a similar service
7. Integration with an api to generate driving directions or show the park on a map
   1. This can be integrated later with Mapbox or a similar service
8. Donation option to support local government maintenance of the park
9. Ranking system for *most popular* dogs in each park


# 5. Proposed Architecture Overview

This initial iteration will provide the minimum viable product (MVP) including retrieving a list of parks, 
retrieving a park and its specific reviews, and creating, retrieving, updating, and deleting user accounts and user reviews.

We will use API Gateway and Lambda to create 12 endpoints (see API section of this document for more details) to handle the
functionality necessary to satisfy our requirements.

We will store parks, users, reviews, and locations in tables in DynamoDB. For simpler review list retrieval, 
we will store the list of reviews for a given park directly in the parks table.

We will provide a basic web interface for customers to use the service. A main page will list all parks and provide links
to search for parks by location and/or average rating and register a new user account. Any page listing parks will link off
to pages per-park to view details and reviews and add a review. Review and user pages will also be provided and will provide
links to update or delete.


# 6. API

## 6.1. Public Models

```
// ParkModel

String id;
String name;
String location;
Double avgRating;
Set<String> tags;
```


```
// LocationModel

Set<String> locationSet;
```

```
// ReviewModel

String parkId;
String userId;
String date;
String reviewTitle;
String reviewBody;
Integer rating;
```

```
// UserModel

String id;
String username;
```

## Endpoints

## 6.2. *GetParksActivity*
* Accepts `GET` requests to `/parks`
* Retrieves a list of parks
    * By default, returns the corresponding ParksModel containing a list all parks in an arbitrary order
    * If the optional `location` parameter is provided, this API will filter
      the list to include only parks in the specified location
        * If `location` is invalid or doesn't exist, will throw a `LocationNotFoundException`
        * If there are no parks at the specified location, will throw a `ParksNotFoundException`
    * If the optional `rating` parameter is provided, this API will filter
      the list to include only parks with an average rating equal to or greater than
      the specified rating
        * If `rating` is invalid (e.g. less than 1 or greater than 5), will throw a `InvalidRatingException`
        * If there are no parks that meet the specified rating criteria, will throw a `ParksNotFoundException`

## 6.3 *GetParkActivity*
* Accepts `GET` requests to `/parks/:parkId`
* Accepts a park ID and returns the corresponding ParkModel
  * If the given park ID is not found, will throw a `ParkNotFoundException`

## 6.4 *GetLocationsActivity*
* Accepts `GET` requests to `/locations`
* Retrieves a list of locations
  * By default, returns the corresponding LocationsModel containing a list of all locations

## 6.5 *CreateUserActivity*
* Accepts `POST` requests to `/users`
* Accepts data to create a new user with a provided name, a provided email, a provided password. 
Returns the new user, including a unique user ID assigned by User Service Class
* We will have a utility class with a string validation method (validate email has a @, validate user name is within proper 
parameters) and a method to generate a new, unique user ID
* For security concerns, we will validate that the provided username and email do not contain any invalid characters: `“ ‘ \ `
* If the username or email contains any of the invalid characters, will throw an `InvalidAttributeValueException`

## 6.6 *GetUserActivity*
* Accepts `GET` requests to `/users/:userId`
* Accepts a user ID and returns the corresponding UserModel
* If the given user ID is not found, will throw an `UserNotFoundException`

## 6.7 *UpdateUserActivity*
* Accepts `PUT` requests to `/users/:userId`
* Accepts data to update user including user name and email. Returns the updated user.
* If the user ID is not found, will throw a `UserNotFoundException`
* For security concerns, we will validate that the provided user ID does not contain any invalid characters: `“ ‘ \ `
* If the user ID contains any of the invalid characters, will throw an `InvalidAttributeValueException`

## 6.8 *DeleteUserActivity*
* Accepts `DELETE` requests to `/users/:userId`
* Accepts data to delete a user ID. Returns confirmation of deleted user ID.
* If the user ID is not found, will throw a `UserNotFoundException`

## 6.9 *CreateReviewActivity*
* Accepts `POST` requests to `/reviews`.
* Accepts data to create a new review including a required parkId, a required user ID, a required review rating, and an optional review title and review body.
* Returns the corresponding ReviewModel.
* For usability, we will limit the available ratings to only the numbers `1-5`.
  * If the user ID is not found, will throw a `UserNotFoundException`.
  * If the park ID is not found, will throw a `ParkNotFoundException`.

![CreateReviewActivityImage](../bark_park_app/resources/images/create-review-activity.png)

## 6.10 *GetReviewsActivity*
* Accepts `GET` requests to `/reviews`.
* Retrieves a list of reviews
  * Requires at least one query parameter of type `parkId` or `userId` 
    * If only the `parkId` parameter is provided, this API will filter
      the list to include only reviews for the specified park
      * If `parkId` is invalid or doesn't exist, will throw a `ParkNotFoundException`
      * If there are no reviews for the specified park, will return an empty list
    * If only the `userId` parameter is provided, this API will filter
      the list to include only reviews created by the specified user
        * If `userId` is invalid or doesn't exist, will throw a `UserNotFoundException`
        * If there are no reviews for the specified user, will return an empty list
    * If both the `parkId` and `userId` parameters are provided, this API will return
      a singleton list including the unique review specified 
        * If `parkId` is invalid or doesn't exist, will throw a `ParkNotFoundException`
        * If `userId` is invalid or doesn't exist, will throw a `UserNotFoundException`
        * If there is no review that matches the specified user and park, will return an empty list
  

![GetParkReviewsActivityImage](../bark_park_app/resources/images/get-park-reviews-activity.png)

## 6.11 *UpdateReviewActivity*
* Accepts `PUT` requests to `/reviews/:parkId/:userId`.
* Accepts data to update a review including the optional parameters review rating, 
   review title, and review body. If any of these optional parameters are not included the updated review will use the
   existing values. Returns the updated review.
* If the user ID is not found, will throw a `UserNotFoundException`.
* If the park ID is not found, will throw a `ParkNotFoundException`.
* For usability, we will limit the available ratings to only the numbers `1-5`.
* For security concerns, we will validate all provided parameters to ensure they do not contain any invalid characters: `“ ‘ \ `
* If any of provided parameters contains any of the invalid characters, will throw an `InvalidAttributeValueException`

![UpdateReviewActivityImage](../bark_park_app/resources/images/update-review-activity.png)

## 6.12 *DeleteReviewActivity*
* Accepts `DELETE` requests to `/reviews/:parkId/:userId`
* Accepts data to delete a review including a user ID and a park ID.
  Returns the deleted ReviewModel.
   * If the user ID is not found, will throw a `UserNotFoundException`.
   * If the park ID is not found, will throw a `ParkNotFoundException`.

![DeleteReviewActivityImage](../bark_park_app/resources/images/delete-review-activity.png)


# 7. Tables

### 7.1 `parks`
```
id // partition key, string
name // string
location // string 
avgRating // number
tags // list
```

### 7.2 `users`
```
id // partition key, string
username // string
```

### 7.3 `reviews`
```
parkId // partition key, string
userId // sort key, string
date // string
reviewTitle // string
reviewBody // string
rating // number
```

# 8. Pages

## Main Parks Page with no filtering:
### `/parks`
![ParksPageImage](../bark_park_app/resources/images/ParksPage.jpg)

## Park Page
### `/parks/:parkId`
![ParkPageImage](../bark_park_app/resources/images/park_page.jpg)

## Login/Create User Page
### `users/login`
![ParkPageImage](../bark_park_app/resources/images/login_or_create_user_page.jpg)

## User Page
### `users/:userId`
![ParkPageImage](../bark_park_app/resources/images/user_page.jpg)

## Edit/Delete User Page
### `users/:userId/edit`
![ParkPageImage](../bark_park_app/resources/images/edit_or_delete_user_page.jpg)

## Create Review Page
### `/parks/:parkId/reviews/create`
![ParkPageImage](../bark_park_app/resources/images/edit_or_delete_user_page.jpg)

## Login/Create User Page
### `users/login`
![ParkPageImage](../bark_park_app/resources/images/login_or_create_user_page.jpg)

## Review Page
### `/parks/:parkId/reviews/:reviewId`
![ParkPageImage](../bark_park_app/resources/images/review_page.jpg)


