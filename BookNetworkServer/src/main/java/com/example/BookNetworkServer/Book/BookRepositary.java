package com.example.BookNetworkServer.Book;

import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface BookRepositary extends JpaRepository<Book,Integer> ,JpaSpecificationExecutor<Book>{
    @Query("""
            SELECT book 
            FROM Book book
            WHERE book.archived = false
            AND book.shareable = true
            AND book.createdBy!= :userID
            """)
    Page<Book> findAlldisplayableBooks(org.springframework.data.domain.Pageable pageable,Integer userID) ;
}
