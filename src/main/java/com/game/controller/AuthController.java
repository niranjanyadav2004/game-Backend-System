package com.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.game.DTO.JwtRequest;
import com.game.DTO.JwtResponse;
import com.game.DTO.RefreshTokenRequest;
import com.game.entity.Player;
import com.game.entity.RefreshToken;
import com.game.security.JwtHelper;
import com.game.service.RefreshTokenService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;
    

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getUsername(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.helper.generateToken(userDetails);
        
        RefreshToken refreshToken = this.refreshTokenService.createRefreshToken(userDetails.getUsername());

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .refreshToken(refreshToken.getRefresToken())
                .username(userDetails.getUsername()).
                id(refreshToken.getPlayer().getId()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PostMapping("/refresh")
    public JwtResponse refreshJwtToken(@RequestBody RefreshTokenRequest request){
    	  RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(request.getRefreshToken());
    	  Player player = refreshToken.getPlayer();
    	  String token = helper.generateToken(player);
    	  
    	  return JwtResponse.builder()
    			            .jwtToken(token)
    			            .refreshToken(refreshToken.getRefresToken())
    			            .username(player.getUsername())
    			            .build();
    }
    

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

}
