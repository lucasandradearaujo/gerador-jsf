package br.com.psi.geradorjsf.custom;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author Hiago
 */
public class CustomRestRemplate extends RestTemplate{
    public CustomRestRemplate(){
        this.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }
}
