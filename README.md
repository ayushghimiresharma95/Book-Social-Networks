# Book-Social-Networks

### Book Mangement 
-- user can create, update , adn delete
-- each Book has the attributes like Title, author ,genre, descriptions
-- Implement CRUD(create,read , update and delete) operation for both frontend and backend.
-- Users can archieve books they no longer want to share but want to keep records on.

## Docker compose
-- we have create a docker-compose file for the maildev and for the postgressql
  docker-compose up -d 
And for the maildev we go to the 127.0.0.1:1025 




### URL

#### GET REQUESTS

-- to get all the Books -  #### /api/v1/books

-- to get all the owner books or the user books - #### /api/v1/books/owner

-- to checout a particular book - #### /api/v1/Books/{book-id}

-- to get all the books that the user have borrowed - #### /api/v1/books/borrowed 

-- to get all the books that user have returned - #### /api/v1/book/returned


#### POST REQUESTS

-- to save the book -  - /api/v1/books

#### PATCH REQUESTS

-- to borrow the book - /api/v1//books/borrow/{book-id}

-- to archived a book - /api/v1/books/archived/{book-id}

-- to change the books that a user want shared to unshareable and vice-versa - (patch) - /api/v1/books/shareable/{book-id}

-- to return a book that a user have borrowed - /api/v1/books/borrow/return/{book-id}

-- to approved a book that another user have borrowed and want to return - /api/v1/books/borrow/approve/{book-id}


### Book sharing 
-- Users can mark their book as book for available.

-- Others can view the list of available books.

-- Implement a functionality to share a book with another user. 

### Book Borrowing 
-- users can borrow the books 

-- Track the status of each books(borrowed, archived, and available)

-- prevent a mechanism so that multiple users can not borrowing the same book 

### Feedback 
-- a book that is shareable and is not archived , but  user can give the feedback to that book , but the book is not owned by the user, only then a user can give the feedback  - /api/v1/feedbacks (post)

-- to get the feedback of a book - (get) - /api/v1/feedbacks/book/{book-id}

### Users authenticate 
-- Implement a user registration and login functionality

-- use spring security for backend authentications 

-- Integrate JWT for secure token authentication between spring and react.



## Class Diagram

![screenshot](class-diagram.png)


