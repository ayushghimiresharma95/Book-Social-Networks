package com.example.BookNetworkServer.Book;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



public record BookRequest(
        Integer id,
        
        @NotEmpty(message = "100")
        String title,
        
        @NotEmpty(message = "101")
        String authorName,
        
        @NotEmpty(message = "102")
        String isbn,
        
        @NotEmpty(message = "103")
        String synopsis,
        Boolean shareable
) {
}