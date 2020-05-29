package br.com.psi.geradorjsf.custom;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author Hiago
 */
public class CustomRestTemplate extends RestTemplate{
    public CustomRestTemplate(){
        this.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }
}
