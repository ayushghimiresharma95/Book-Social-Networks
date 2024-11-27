package com.example.BookNetworkServer.History;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookTransactionHostoryRepositaory extends JpaRepository<BookTransactionHistory,Integer> {
    @Query("""
            SELECT history
            FROM BookTransactionHistory history
            WHERE history.userId= :userId
            """)
    Page<BookTransactionHistory> findAllBorrowedBooks(Pageable page, Integer userId);

    @Query("""
            SELECT HSITORY
            FROM BookTransactionHistory history
            WHERE history.createdBy = :userId
            """)
    Page<BookTransactionHistory> findAllReturnedBooks(Pageable page,Integer userId);
}
