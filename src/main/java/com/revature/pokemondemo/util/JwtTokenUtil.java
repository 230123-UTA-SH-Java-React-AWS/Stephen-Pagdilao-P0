package com.revature.pokemondemo.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.revature.pokemondemo.model.Trainer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {
    private static final long serialVersionUID = -2550185165626007488L;

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${jwt.secret}")//this information is from the application.yml
    private String secret;

	public String generateToken(Trainer trainer)
	{
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, trainer.getUsername()+":"+trainer.getPassword());
	}

    private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact(); //Using 512 bit encryption
	}

    //retrieve username from jwt token
	public String getCredentialsFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

    //for retrieving any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

    //check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

    //retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

    public Boolean validateToken(String token, Trainer trainer)
    {
        String[] creds = getCredentialsFromToken(token).split(":");

        return (creds[0].equals(trainer.getUsername()) && creds[1].equals(trainer.getPassword()) && !isTokenExpired(token));
    }
}
