package com.peytosoft.APIGateway.Util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


import org.springframework.stereotype.Component;



import io.jsonwebtoken.Jwts;



@Component
public class JWTUtils {
	
	
	private SecretKey Key;
    private  static  final long EXPIRATION_TIME = 86400000; //24hours or 86400000 milisecs
    public JWTUtils(){
        String secreteString = "843567893696976453275974432697R634976R738467TR678T34865R6834R8763T478378637664538745673865783678548735687R3";
        byte[] keyBytes = Base64.getDecoder().decode(secreteString.getBytes(StandardCharsets.UTF_8));
        this.Key = new SecretKeySpec(keyBytes, "HmacSHA256");
    }
  

 
    public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(Key).build().parseClaimsJws(token);
    }
    

}
