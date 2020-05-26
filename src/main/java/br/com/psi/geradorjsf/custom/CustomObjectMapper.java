package br.com.psi.geradorjsf.custom;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * @author Hiago.
 */
public class CustomObjectMapper extends ObjectMapper {
    public CustomObjectMapper() {
        this.registerModule(new JavaTimeModule());
    }
}
