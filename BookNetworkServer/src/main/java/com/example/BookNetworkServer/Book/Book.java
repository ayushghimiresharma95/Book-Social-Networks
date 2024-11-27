package com.example.BookNetworkServer.Book;

import java.beans.Transient;
import java.util.List;

import com.example.BookNetworkServer.Book.Feedback.Feedback;
import com.example.BookNetworkServer.Common.BaseEntity;
import com.example.BookNetworkServer.History.BookTransactionHistory;
import com.example.BookNetworkServer.User.User;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book extends BaseEntity {

   

    private String title;
    private String bookcover;
    private String isbn;
    private String authorName ;
    private String synopsis ;
    private Boolean shareable;
    private Boolean archived ;
    

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner ; 

    @OneToMany(mappedBy = "book") // "Book is the field in the feedback class"
    private List<Feedback> feedbacks ; 

    @OneToMany(mappedBy = "book") // "Book" is the field in the Booktransactionhistory
    private List<BookTransactionHistory> histories ;

    @Transient
    public double getRate(){
        
        if (feedbacks.isEmpty() || feedbacks == null){
            return 0.0 ;
        }

        var rate = feedbacks.stream().mapToDouble(Feedback::getNote).average().orElse(0.0) ;
        return rate ;
    }

}
