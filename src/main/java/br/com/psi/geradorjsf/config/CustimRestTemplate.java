package br.com.psi.geradorjsf.config;

import org.springframework.web.client.RestTemplate;

/**
 * @author Hiago
 * Sem essa classe, o @Inject for resttemplate não funcionará
 */

public class CustomRestTemplate extends RestTemplate {
}
