package br.com.psi.geradorjsf.annotation;


import javax.interceptor.InterceptorBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Hiago.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
public @interface ExceptionHandler {
}
