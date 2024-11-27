package com.example.BookNetworkServer.History;

import com.example.BookNetworkServer.Book.Book;
import com.example.BookNetworkServer.Common.BaseEntity;
import com.example.BookNetworkServer.User.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
public class BookTransactionHistory extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user ;

    @ManyToOne
    @JoinColumn(name = "book_id") 
    private Book book ;


    private Boolean returned ;
    private Boolean returnedApproved ; 


}
