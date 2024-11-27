package com.example.BookNetworkServer.Book;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BookNetworkServer.Common.PageResponse;
import com.example.BookNetworkServer.User.User;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
@RequestMapping("books")
@RequiredArgsConstructor
@Tag(name = "Book")
@Slf4j
public class BookController {


    private final BookService service ;

     @PostMapping
    public ResponseEntity<Integer> saveBook(
            @Valid @RequestBody BookRequest request,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(service.save(request, connectedUser));
    }
    @GetMapping("/{book-id}")
    public ResponseEntity<BookResponse> findBook(@Valid @PathVariable("book-id") Integer bookId ){
        return ResponseEntity.ok(service.findBookID(bookId)) ;
    }
    

    @GetMapping
    public ResponseEntity<PageResponse<BookResponse>> getAllbooks(
        @RequestParam(name = "page",defaultValue = "0",required = false) int page,
        @RequestParam(name = "size",defaultValue = "10",required = false) int size,
        Authentication connectedUser
    ){
        

        return ResponseEntity.ok(service.findAllByBooks(page,size,connectedUser)) ;
    }

    @GetMapping("/owner")
    public ResponseEntity<PageResponse<BookResponse>> getAllBooksBYOwner(
        @RequestParam(name = "page" ,defaultValue = "0",required = false) Integer page,
        @RequestParam(name = "size",defaultValue = "10",required = false) Integer size,Authentication connectUser){
            return ResponseEntity.ok(service.findAllBooksByOwner(page,size,connectUser)) ;
        }
    
}
