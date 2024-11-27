package com.example.BookNetworkServer.User;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Token {

    @Id
    @GeneratedValue
    private Integer id ;

    @Column(unique = true) 
    private String token ;
    private LocalDateTime createdAt ;
    private LocalDateTime expiredAt ;
    private LocalDateTime validedAt ;

    @ManyToOne()
    @JoinColumn(name = "UserID", nullable = false)
    private User user ;
}
