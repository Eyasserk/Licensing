package es.etsit.silcam.security;

import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import es.etsit.silcam.core.AbstractPerson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService{

	
	@Value("${silcam.security.jwt.key:silcam}")
	private String signKey;
	
	@Value("${app.name}")
	private String applicationName;
	
	@Override
	public <P extends AbstractPerson> String generateJWT(P person) {
		JwtBuilder builder = Jwts.builder().setId(String.valueOf(person.getId()))
			                .setIssuedAt(new Date())
			                .setIssuer(applicationName)
			                .signWith(SignatureAlgorithm.HS256,
			                new SecretKeySpec(DatatypeConverter.parseBase64Binary(signKey), SignatureAlgorithm.HS256.getJcaName()));
		return builder.compact();
	}

	@Override
	public boolean validate(String jwt) {
		try {
			Claims claims = Jwts.parser()         
				       .setSigningKey(DatatypeConverter.parseBase64Binary(signKey))
				       .parseClaimsJws(jwt).getBody();
				    log.info("ID: {}", claims.getId());
				    log.info("Subject: {}", claims.getSubject());
				    log.info("Issuer: {}", claims.getIssuer());
				    log.info("Expiration: {}", claims.getExpiration());
		    return true;
		}catch(Exception e) {
			log.info("Validation failed: {}",e.getMessage());
			return false;
		}	   
	}

}
