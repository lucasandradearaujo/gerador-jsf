package br.com.psi.geradorjsf.persistence.dao;

import br.com.psi.geradorjsf.annotation.ExceptionHandler;
import br.com.psi.geradorjsf.persistence.model.support.Token;
import br.com.psi.geradorjsf.custom.CustomRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;


import javax.inject.Inject;
import java.io.Serializable;


import static br.com.psi.geradorjsf.util.ApiUtil.LOGIN_URL;
import static org.springframework.http.HttpMethod.POST;
/**
 * @author Hiago
 */
public class LoginDAO implements Serializable {
    private final CustomRestTemplate restTemplate;
    private final JsonUtil jsonUtil;

    @Inject
    public LoginDAO(CustomRestTemplate restTemplate, JsonUtil jsonUtil) {
        this.restTemplate = restTemplate;
        this.jsonUtil = jsonUtil;
    }

    @ExceptionHandler
    public Token loginReturningToken(String username, String password) {
        public Token loginReturningToken (String username, String password){
            String loginJson = "{\"username\":" + addQuotes(username) + ",\"password\":" + addQuotes(password) + "}";
            ResponseEntity<Token> tokenExchange = restTemplate.exchange(LOGIN_URL, POST, new HttpEntity<>(loginJson, jsonUtil.createJsonHeader()), Token.class);
            return tokenExchange.getBody();

        }

        @SuppressWarnings("StringBufferReplaceableByString")
        private String addQuotes (String value){
            return new StringBuilder(300).append("\"").append(value).append("\"").toString();
        }


    }
}