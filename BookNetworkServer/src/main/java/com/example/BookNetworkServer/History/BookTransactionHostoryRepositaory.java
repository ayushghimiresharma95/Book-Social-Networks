package com.example.BookNetworkServer.History;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookTransactionHostoryRepositaory extends JpaRepository<BookTransactionHistory,Integer> {
    @Query("""
            SELECT history
            FROM BookTransactionHistory history
            WHERE history.user.id= :userId
            """)
    Page<BookTransactionHistory> findAllBorrowedBooks(Pageable page, Integer userId);

    @Query("""
            SELECT history
            FROM BookTransactionHistory history
            WHERE history.createdBy = :userId
            """)
    Page<BookTransactionHistory> findAllReturnedBooks(Pageable page,Integer userId);
    @Query("""
            SELECT (COUNT(*)>0) AS isBorrowed
            FROM BookTransactionHistory bookTransactionHistory
            WHERE bookTransactionHistory.book.id = :bookId
            And bookTransactionHistory.returned = false
            """)
    boolean isAlreadyBorrowed(Integer bookId);

    @Query("""
                    SELECT transaction 
                    FROM BookTransactionHistory transaction
                    WHERE transaction.book.id = :bookId
                    AND transaction.user.id = :userId
                    AND transaction.returned = false
                    And transaction.returnedApproved = false
                    """)
    Optional<BookTransactionHistory> findByBookIdAndUserId(Integer bookId,Integer userId);
    @Query("""
        SELECT transaction 
        FROM BookTransactionHistory transaction
        WHERE transaction.book.id = :bookId
        AND transaction.book.owner.id = :userId
        AND transaction.returned = true
        And transaction.returnedApproved = false
        """)
    Optional<BookTransactionHistory> findByBookIdAndOwnerId(Integer bookId,Integer userId);

}
