package com.example.BookNetworkServer.Book;

import org.springframework.stereotype.Service;

import com.example.BookNetworkServer.History.BookTransactionHistory;

@Service
public class BookMapper {
    public Book toBook(BookRequest request) {
        return Book.builder()
                .id(request.id())
                .title(request.title())
                .isbn(request.isbn())
                .authorName(request.authorName())
                .synopsis(request.synopsis())
                .archived(false)
                .shareable(request.shareable())
                .build();
    }

    public BookResponse toBookResponse(Book book){
        return BookResponse.builder()
                .id(book.getId())
                .authorName(book.getAuthorName())
                .title(book.getTitle())
                .synopsis(book.getSynopsis())
                .shareable(book.getShareable())
                .isbn(book.getIsbn())
                .owner(book.getOwner().fullName())
                .rate(book.getRate()).build() ;
    }
     public BorrowedBookResponse toBorrowedBookResponse(BookTransactionHistory history) {
        return BorrowedBookResponse.builder()
                .id(history.getBook().getId())
                .title(history.getBook().getTitle())
                .authorName(history.getBook().getAuthorName())
                .isbn(history.getBook().getIsbn())
                .rate(history.getBook().getRate())
                .returned(history.getReturned())
                .returnApproved(history.getReturnedApproved())
                .build();
    }
}
