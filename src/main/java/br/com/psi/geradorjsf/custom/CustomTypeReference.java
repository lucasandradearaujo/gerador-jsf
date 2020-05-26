package br.com.psi.geradorjsf.custom;

import org.springframework.core.ParameterizedTypeReference;

/**
 * @author Hiago.
 */
public class CustomTypeReference<T> extends ParameterizedTypeReference<T> {

    public ParameterizedTypeReference<T> typeReference() {
        return new ParameterizedTypeReference<T>() {
        };
    }
}
