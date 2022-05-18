package com.projectManagement.configuration.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import com.projectManagement.util.DateCommonUtils;
import com.projectManagement.util.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class TokenComponent {

  private final TokenProperties properties;

  @Autowired
  public TokenComponent(TokenProperties properties) {

    this.properties = properties;
  }

  String getToken(HttpServletRequest request) {

    String header = request.getHeader(properties.getHeader());
    String prefix = properties.getPrefix();
    if (StringUtils.nonNullNonEmpty(header) && header.startsWith(prefix)) {

      //prefix with space char
      return header.substring(prefix.length() + 1);
    }

    return null;
  }

  String generateToken(String username) {

    Date now = DateCommonUtils.now();

    return Jwts.builder().setSubject(username).setIssuedAt(now).setExpiration(DateCommonUtils.addMilliseconds(now, properties.getLifeTime()))
      .signWith(SignatureAlgorithm.HS512, properties.getSecret()).compact();
  }

  String getUsername(String token) {

    return getClaims(token).getSubject();
  }

  boolean isExpirationNear(String token) {

    Claims claims = getClaims(token);

    Date now = DateCommonUtils.now();
    Date expiration = claims.getExpiration();

    return DateCommonUtils.addSeconds(now, properties.getRange()).after(expiration);
  }

  void applyToken(String token, HttpServletResponse response) {

    response.setHeader("Access-Control-Expose-Headers", "Access-Token, Uid");
    response.setHeader(properties.getResponseHeader(), token);
  }

  public void applyToken(String token, HttpHeaders headers) {

    headers.set("Access-Control-Expose-Headers", "Access-Token, Uid");
    headers.set(properties.getResponseHeader(), token);
  }

  private Claims getClaims(String token) {

    return Jwts.parser().setSigningKey(properties.getSecret()).parseClaimsJws(token).getBody();
  }

}
