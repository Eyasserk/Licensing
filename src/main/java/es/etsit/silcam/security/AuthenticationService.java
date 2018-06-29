package es.etsit.silcam.security;

import es.etsit.silcam.core.AbstractPerson;

public interface AuthenticationService{

	public <P extends AbstractPerson> String generateJWT(P person);
	
	public boolean validate(String jwt);
	
}
