package br.com.mobibike.autenticacao.expose.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;

import br.com.mobibike.autenticacao.expose.security.enums.Access;
import br.com.mobibike.autenticacao.expose.security.enums.Role;

@InterceptorBinding
@Retention(RUNTIME)
@Target({TYPE, METHOD})
@Documented
public @interface BikeAuthorization {
	@Nonbinding Role[] roles() default Role.DEFAULT;
	@Nonbinding Access[] access() default Access.ANY;
}
