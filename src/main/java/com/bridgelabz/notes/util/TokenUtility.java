package com.bridgelabz.notes.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

@Component
public class TokenUtility {
    private final String SECRET= "Mohammed";
    public String createToken(int user_id){
        return JWT.create()
                .withClaim("user_id", user_id)
                .sign(Algorithm.HMAC256(SECRET));
    }

    public int decodeToken(String token) {
        int id=0;
        if(token!=null){
            id=JWT.require(Algorithm.HMAC256(SECRET)).
                    build().verify(token).
                    getClaim("user_id").asInt();
        }
        return id;
    }
}
