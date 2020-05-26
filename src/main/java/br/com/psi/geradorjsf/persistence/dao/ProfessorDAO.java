package br.com.psi.geradorjsf.persistence.dao;

import br.com.psi.geradorjsf.annotation.ExceptionHandler;
import br.com.psi.geradorjsf.persistence.model.Professor;
import br.com.psi.geradorjsf.util.JsonUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.io.Serializable;

import static org.springframework.http.HttpMethod.GET;

/**
 * @author Hiago
 */
public class ProfessorDAO implements Serializable {
    private final String BASE_URL = "http://localhost:8085/v1/professor";
    private final JsonUtil jsonUtil;

    @Inject
    public ProfessorDAO(JsonUtil jsonUtil) {
        this.jsonUtil = jsonUtil;
    }

    @ExceptionHandler
    public Professor getProfessorById(long id) {
        ResponseEntity<Professor> professorEntity = new RestTemplate().exchange(BASE_URL+"/1", GET, new HttpEntity<>(jsonUtil.createTokenizedHeader()), Professor.class);
        Professor professor = professorEntity.getBody();
        return professor;
    }
}
