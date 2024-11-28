package com.example.BookNetworkServer.Book.Feedback;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.BookNetworkServer.Book.Book;
import com.example.BookNetworkServer.Book.BookRepositary;
import com.example.BookNetworkServer.Common.PageResponse;
import com.example.BookNetworkServer.User.User;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedBackRepositary feedbackRepositary;
    private final BookRepositary bookRepositary;
    private final FeedBackMapper feedBackMapper;

    public Integer save(FeedbackRequest request, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Book book = bookRepositary.findById(request.getBookId()).orElseThrow(() -> new EntityNotFoundException("No Book with this ID Found."));
        
        if (book.getArchived() || !book.getShareable()) {
            throw new SecurityException("You cannot give a feedback for and archived or not shareable book");
        }
        if(Objects.equals(book.getCreatedBy(),user.getId())){
            throw new SecurityException("You are not allowed to give feedback for Your own book book.");
        }
        Feedback feedback = feedBackMapper.toFeedBack(request);

        feedbackRepositary.save(feedback) ;
        return feedback.getId() ;

    }

    @Transactional
    public PageResponse<FeedbackResponse> getFeedbacksByBookId(Integer bookId,int page,int size, Authentication connectedUser){
        Pageable pageable = PageRequest.of(page,size) ;
        User user = ((User) connectedUser.getPrincipal()) ;
        Page<Feedback> feedback = feedbackRepositary.findAllbyBookId(bookId,pageable) ;
        List<FeedbackResponse> feedbackResponse = feedback.stream().map(f -> feedBackMapper.toFeedBackResponse(f, bookId)).toList() ;
        return new PageResponse<>(
            feedbackResponse,
            feedback.getNumber(),
            feedback.getSize(),
            feedback.getTotalElements(),
            feedback.getTotalPages(),
            feedback.isFirst(),
            feedback.isLast()
        ) ;
        
    }

}
