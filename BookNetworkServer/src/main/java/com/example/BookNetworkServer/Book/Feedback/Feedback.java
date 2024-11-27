package com.example.BookNetworkServer.Book.Feedback;

import java.util.List;

import com.example.BookNetworkServer.Book.Book;
import com.example.BookNetworkServer.Common.BaseEntity;
import com.example.BookNetworkServer.User.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Feedback extends BaseEntity{


   
    private Double note ; 

    private String comment ; 

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book ;

    


}
