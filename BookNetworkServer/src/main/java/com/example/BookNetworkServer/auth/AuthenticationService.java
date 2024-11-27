package com.example.BookNetworkServer.auth;


import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.IllegalTransactionStateException;

import com.example.BookNetworkServer.Email.EmailService;
import com.example.BookNetworkServer.Email.EmailTemplateName;
import com.example.BookNetworkServer.Role.RoleRepositary;
import com.example.BookNetworkServer.Security.JwtService;
import com.example.BookNetworkServer.User.Token;
import com.example.BookNetworkServer.User.TokenRepositary;
import com.example.BookNetworkServer.User.User;
import com.example.BookNetworkServer.User.UserRepositary;


import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepositary userRepositary ;
    private final PasswordEncoder passwordEncoder ;
    private final AuthenticationManager authenticationManager ;
    private final RoleRepositary roleRepository ;
    private final TokenRepositary tokenRepositary ;
    private final EmailService emailservice ;
    private final JwtService jwtService ;
   


    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;
    

    public void register(RegistrationRequest request) throws MessagingException {
        var userRole = roleRepository.findByName("USER").orElseThrow(() -> new IllegalTransactionStateException("ROLE USEr was not found ")) ;
        var user  = User.builder().
                    firstname(request.getFirstname()).
                    lastname(request.getLastname()).
                    email(request.getEmail()).enabled(false).
                    password(passwordEncoder.encode(request.getPassword())).accountLocked(false).roles(List.of(userRole)).build() ;
        userRepositary.save(user) ;
        SendValidationEmail(user) ;

    }

    private void SendValidationEmail(User user ) throws MessagingException {
        var newToken  = generateAndSaveActivationCode(user) ;
        emailservice.sendMail(user.getEmail(),user.getUsername(), EmailTemplateName.ACTIVATE_ACCOUNT, activationUrl,  newToken, "Account_Activated");
        
    }

    private String generateAndSaveActivationCode(User user) {
        String generatedToken = generateActivationCode(6) ;
        var token = Token.builder().token(generatedToken).createdAt(LocalDateTime.now()).expiredAt(LocalDateTime.now().plusMinutes(15)).user(user).build() ;

        tokenRepositary.save(token) ;
        return generatedToken ;
    }

    private String generateActivationCode(int length) {
        String characters = "0123456789" ;
        StringBuilder codeBuilder = new StringBuilder() ;
        SecureRandom secureRandom  = new SecureRandom() ;

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        return codeBuilder.toString() ;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        var auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())) ; 
        var claims = new HashMap<String,Object>() ;
        var user = ((User) auth.getPrincipal()) ;
        claims.put("Username", user.getUsername()) ;
        var jwtToken = jwtService.generateToken(claims, (User) auth.getPrincipal()) ;

        return AuthenticationResponse.builder().token(jwtToken).build() ; 

    }

    public void activateAaccount(String token){
        
        Token savedToken = tokenRepositary.findByToken(token).orElseThrow(() -> new RuntimeException("Invalid token")) ;
        if(LocalDateTime.now().isAfter(savedToken.getExpiredAt())){
            throw new RuntimeException("Activation code has been expired. A new token has been send to your email") ;
        }

        var user  = userRepositary.findById(savedToken.getUser().getId()).orElseThrow(() -> new RuntimeException("User can not be find") ) ;
        user.setEnabled(true);
        userRepositary.save(user) ;

        savedToken.setExpiredAt(LocalDateTime.now());
        tokenRepositary.save(savedToken) ;
        
    }

    

}
