package br.com.psi.geradorjsf.persistence.dao;

import br.com.psi.geradorjsf.custom.CustomRestRemplate;
import br.com.psi.geradorjsf.persistence.model.QuestionAssignment;
import br.com.psi.geradorjsf.util.ApiUtil;
import br.com.psi.geradorjsf.util.JsonUtil;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;

/**
 * @author Hiago.
 */
public class QuestionAssignmentDAO implements Serializable{
    private final String LIST_URL = ApiUtil.BASE_URL + "/professor/course/assignment/questionassignment/{assignmentId}";
    private final CustomRestRemplate restRemplate;
    private final JsonUtil jsonUtil;
    private final ParameterizedTypeReference<List<QuestionAssignment>> questionAssignmentListTypeReference = new ParameterizedTypeReference<List<QuestionAssignment>>() {
    };

    @Inject
    public QuestionAssignmentDAO(CustomRestRemplate restRemplate, JsonUtil jsonUtil) {
        this.restRemplate = restRemplate;
        this.jsonUtil = jsonUtil;
    }

    public List<QuestionAssignment> list(long assignmentId) {
        ResponseEntity<List<QuestionAssignment>> exchange = restRemplate.exchange(LIST_URL, GET, jsonUtil.tokenizedHttpEntityHeader(), questionAssignmentListTypeReference, assignmentId);
        return exchange.getBody();
    }
}
