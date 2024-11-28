package com.example.BookNetworkServer.Book.Feedback;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;

public interface FeedBackRepositary extends JpaRepository<Feedback, Integer> {

    @Query("""
            SELECT feedback
            FROM Feedback feedback
            WHERE feedback.book.id =:bookId
            """)
    Page<Feedback> findAllbyBookId(@Param("bookId") Integer bookId, Pageable pageable);
}
