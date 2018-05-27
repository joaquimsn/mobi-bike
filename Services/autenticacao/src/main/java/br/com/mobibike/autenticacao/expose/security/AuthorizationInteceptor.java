package br.com.mobibike.autenticacao.expose.security;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;

import br.com.mobibike.autenticacao.expose.security.enums.Role;
import br.com.mobibike.autenticacao.main.exceptions.enums.TypeExposeBusinessException;

/**
 * @author Joaquim Neto
 * Github joaquimsn
 */
@Interceptor
@BikeAuthorization
public class AuthorizationInteceptor {
	
	@Inject
	private HttpServletRequest request;
	
	@AroundInvoke
	public Object checkAccess(InvocationContext ctx) throws Exception {
		ctx.getClass().getAnnotation(BikeAuthorization.class);
		Optional<BikeAuthorization> annotation = Optional.ofNullable(ctx.getMethod().getAnnotation(BikeAuthorization.class));
		checkAuthorization(annotation);
		
		return ctx.proceed();
	}
	
	private void checkAuthorization(Optional<BikeAuthorization> annotation) {
		if (annotation.isPresent()) {
			Credential credential = (Credential) request.getAttribute("credential");
			List<Role> roles = Arrays.asList(annotation.get().roles());
			
			br.com.mobibike.autenticacao.expose.security.Role roleFound = null;
			for (Role role : roles) {
				roleFound = credential.contains(role, annotation.get().access());
				if (roleFound != null) {
					return;
				}				
			}
			
			throw TypeExposeBusinessException.NAO_AUTORIZADO.buildException();
		}
	}

}
