
# **Book Social Network**  

A platform for managing and sharing books, where users can lend, borrow, review, and track books easily. This project integrates a comprehensive book management system with user authentication and secure borrowing processes.

---

## **Features**  

### **Book Management**  
- Users can create, update, delete, and manage books.  
- Each book has attributes like:  
  - **Title**  
  - **Author**  
  - **Genre**  
  - **Description**  
- Implemented **CRUD (Create, Read, Update, Delete)** operations for both the frontend and backend.  
- Users can archive books they no longer wish to share but want to keep a record of.

### **Book Sharing**  
- Users can mark books as available for sharing.  
- View a list of all available books.  
- Share a book with another user using the sharing functionality.  

### **Book Borrowing**  
- Borrow and return books with a robust tracking system.  
- Track the status of books: **borrowed**, **archived**, or **available**.  
- Prevent multiple users from borrowing the same book simultaneously.  

### **Feedback System**  
- Users can leave feedback on books if:  
  - The book is **shareable** and **not archived**.  
  - The user does **not own** the book.  
- Retrieve feedback for specific books.  

### **User Authentication**  
- User registration and login functionalities implemented.  
- Backend authentication secured with **Spring Security**.  
- JWT (JSON Web Token) integration for secure communication between the backend and frontend.

---

## **API Endpoints**  

### **GET Requests**  
- **Get all books**: `/api/v1/books`  
- **Get books owned by a user**: `/api/v1/books/owner`  
- **Get details of a specific book**: `/api/v1/books/{book-id}`  
- **Get books borrowed by the user**: `/api/v1/books/borrowed`  
- **Get books returned by the user**: `/api/v1/books/returned`  

### **POST Requests**  
- **Save a book**: `/api/v1/books`  

### **PATCH Requests**  
- **Borrow a book**: `/api/v1/books/borrow/{book-id}`  
- **Archive a book**: `/api/v1/books/archived/{book-id}`  
- **Toggle book shareability**: `/api/v1/books/shareable/{book-id}`  
- **Return a borrowed book**: `/api/v1/books/borrow/return/{book-id}`  
- **Approve a returned book**: `/api/v1/books/borrow/approve/{book-id}`  

### **Feedback API**  
- **Add feedback for a book**: `/api/v1/feedbacks` (POST)  
- **Get feedback for a specific book**: `/api/v1/feedbacks/book/{book-id}` (GET)  

---

## **System Architecture**  

### **Backend**  
- Built with **Spring Boot**.  
- Implements **Spring Security** for secure authentication.  
- Uses **JWT** for session and token management.  

### **Frontend**  
- Developed with **React** for dynamic and user-friendly UI interactions.  

---

## **Database and Deployment**  

### **Database**  
- Uses **PostgreSQL** as the database for storing books, users, and transactions.  

### **Docker Compose**  
- A `docker-compose.yml` file is included to run the application components.  
- Commands:  
  - Start the application:  
    ```bash  
    docker-compose up -d  
    ```  
  - Stop the application:  
    ```bash  
    docker-compose down  
    ```  
- **Note**: The `maildev` service was initially planned but has not been implemented in this project.  

---

## **Class Diagram**  

Below is the class diagram of the application:  

![Class Diagram](class-diagram.png)  

---

## **How to Run the Project**  

1. **Clone the Repository**:  
   ```bash  
   git clone <repository-url>  
   cd <repository-folder>  
   ```  

2. **Backend Setup**:  
   - Navigate to the backend directory:  
     ```bash  
     cd backend  
     ```  
   - Build and run the Spring Boot application:  
     ```bash  
     mvn spring-boot:run  
     ```  

3. **Frontend Setup**:  
   - Navigate to the frontend directory:  
     ```bash  
     cd frontend  
     ```  
   - Install dependencies and start the React application:  
     ```bash  
     npm install  
     npm start  
     ```  

4. **Run with Docker** (Optional):  
   - Start all components using Docker Compose:  
     ```bash  
     docker-compose up -d  
     ```  

---



---


