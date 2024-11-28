package com.example.BookNetworkServer.Book.Feedback;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.example.BookNetworkServer.Book.Book;

@Service
public class FeedBackMapper {
    public Feedback toFeedBack(FeedbackRequest feedbackRequest) {
        return Feedback.builder()
                .comment(feedbackRequest.getComment())
                .note(feedbackRequest.getNote())
                .book(
                    Book.builder()
                    .id(feedbackRequest.getBookId())
                    .shareable(false)
                    .archived(false)
                    .build()
                )
                .build();
     }

     public FeedbackResponse toFeedBackResponse(Feedback feedback,Integer Id) {
        return FeedbackResponse.builder()
                .comment(feedback.getComment())
                .note(feedback.getNote())
                .ownFeedback(Objects.equals(feedback.getBook().getId(), Id))
                .build();
     }
}
