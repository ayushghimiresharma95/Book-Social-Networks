package com.example.BookNetworkServer.Book;


import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Objects;

import javax.naming.OperationNotSupportedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page ;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.BookNetworkServer.Common.PageResponse;
import com.example.BookNetworkServer.History.BookTransactionHistory;
import com.example.BookNetworkServer.History.BookTransactionHostoryRepositaory;
import com.example.BookNetworkServer.User.User;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    private final BookRepositary bookRepositary ;
    private final BookMapper bookMapper ;
    private final BookTransactionHostoryRepositaory history ; 

    public Integer save(BookRequest request,Authentication connectedUser){
        Logger log = LoggerFactory.getLogger(this.getClass());
        User user = ((User) connectedUser.getPrincipal()) ;

        log.info("New request has been done", user);
        Book book = bookMapper.toBook(request) ;
        book.setOwner(user);
        bookRepositary.save(book) ;
        return book.getId() ;

    }

    public BookResponse findBookID(Integer bookID){
        return bookRepositary.findById(bookID).
                map(bookMapper::toBookResponse).orElseThrow(() -> new EntityNotFoundException("No Book with this ID Found.")) ;
    }

/*************  ✨ Codeium Command ⭐  *************/
/**
 * Retrieves a paginated list of displayable books for the authenticated user.
 *
 * @param page the page number to retrieve, zero-based
 * @param size the number of books per page
 * @param connectedUser the authentication object representing the current user
 * @return a PageResponse containing a list of BookResponse objects and pagination details
 * @throws EntityNotFoundException if no books are found for the specified user
 */
/******  3084e6f4-cc31-4256-9b93-dce6df269d58  *******/
    public PageResponse<BookResponse> findAllByBooks(int page,int size, Authentication connectedUser){
        User user = ((User) connectedUser.getPrincipal()) ;

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending()) ;
        Page<Book> books = bookRepositary.findAlldisplayableBooks(pageable,user.getId()) ;
        List<BookResponse> bookResponse = books.stream().map(bookMapper::toBookResponse).toList() ;

        return new  PageResponse<> (
            bookResponse,
            books.getNumber(),
            books.getSize(),
            books.getTotalElements(),
            books.getTotalPages(),
            books.isFirst(),
            books.isLast()
        ) ;


       

    }

    public PageResponse<BookResponse> findAllBooksByOwner(int page,int size, Authentication connectedUser){
        User user = ((User) connectedUser.getPrincipal()) ;
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Book> books = bookRepositary.findAll(pageable) ;
        List<BookResponse> booksResponse = books.stream()
                .map(bookMapper::toBookResponse)
                .toList();
        return new PageResponse<>(
                booksResponse,
                books.getNumber(),
                books.getSize(),
                books.getTotalElements(),
                books.getTotalPages(),
                books.isFirst(),
                books.isLast()
        );
    }

    public PageResponse<BorrowedBookResponse> findAllBorrowedBooks(int page,int size, Authentication connectedUser){
        User user = ((User) connectedUser.getPrincipal()) ;
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<BookTransactionHistory> books = history.findAllBorrowedBooks(pageable,user.getId()) ;
        List<BorrowedBookResponse> booksResponse = books.stream().map(bookMapper::toBorrowedBookResponse).toList() ;

        return new PageResponse<>(
            booksResponse,
            books.getNumber(),
            books.getSize(),
            books.getTotalElements(),
            books.getTotalPages(),
            books.isFirst(),
            books.isLast()
        ) ; 

        
    }

    public PageResponse<BorrowedBookResponse> findAllReturnedBooks(int page, int size, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<BookTransactionHistory> allBorrowedBooks = history.findAllReturnedBooks(pageable, user.getId());
        List<BorrowedBookResponse> booksResponse = allBorrowedBooks.stream()
                .map(bookMapper::toBorrowedBookResponse)
                .toList();
        return new PageResponse<>(
                booksResponse,
                allBorrowedBooks.getNumber(),
                allBorrowedBooks.getSize(),
                allBorrowedBooks.getTotalElements(),
                allBorrowedBooks.getTotalPages(),
                allBorrowedBooks.isFirst(),
                allBorrowedBooks.isLast()
        );
    }

    public Integer updateShareblestatus(Integer bookId,Authentication connectedUser){
        Book book = bookRepositary.findById(bookId).orElseThrow(() -> new EntityNotFoundException("No Book with this ID Found.")) ;
        User user = ((User) connectedUser.getPrincipal()) ;

        if(!Objects.equals(book.getCreatedBy(), connectedUser.getName())) {
            throw new SecurityException("You are not allowed to update this book.") ;
           
        }

        book.setShareable(!book.getShareable());
        bookRepositary.save(book) ;

        return book.getId();

    }
}
