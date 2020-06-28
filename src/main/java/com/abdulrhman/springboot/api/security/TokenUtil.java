package com.abdulrhman.springboot.api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtil {


    //expiration
    //sign
    //compact
    private final String CLAIMS_SUBJECT = "sub";
    private final String CLAIMS_created = "created";


    @Value(value = "${auth.expiration}")
    private long TOKEN_VALIDITY = 604800L;

    @Value(value = "${auth.secret}")
    private  String TOKEN_SECRET;

    public String generateToken(UserDetails userDetails){


        Map<String,Object> claims = new HashMap<>();
        claims.put(CLAIMS_SUBJECT,userDetails.getUsername());
        claims.put(CLAIMS_created,new Date());


        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,TOKEN_SECRET)
                .compact();
    }
    public String getUserNameFormToken(String token){
      try{

         Claims claims =  getClaims(token);
         return claims.getSubject();

      }catch(Exception ex){
           return null;
      }
    }

 private Date generateExpirationDate(){

        return new Date(System.currentTimeMillis()+ TOKEN_VALIDITY *1000);
 }

    public boolean isTolkenViled(String token, UserDetails userDetails) {

        String username = getUserNameFormToken(token);
        return (username.equals(userDetails.getUsername()) &&  !isTolkenExpired(token));

    }

    private boolean isTolkenExpired(String token){
        Date expiration = getClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    private Claims getClaims(String token){
        Claims claims;
        try{
          claims = Jwts.parser().setSigningKey(TOKEN_SECRET)
                  .parseClaimsJws(token)
                  .getBody();
        }catch (Exception ex){
            claims= null;
        }
         return claims;
    }

}
