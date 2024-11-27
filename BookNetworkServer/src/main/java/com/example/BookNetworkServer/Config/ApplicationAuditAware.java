package com.example.BookNetworkServer.Config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.BookNetworkServer.User.User;

public class ApplicationAuditAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication() ; 
        if(authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken){
            return Optional.empty() ;
        }

        User userPrinciple = (User) authentication.getPrincipal() ;
        return Optional.ofNullable(authentication.getName()) ;

    }

}
