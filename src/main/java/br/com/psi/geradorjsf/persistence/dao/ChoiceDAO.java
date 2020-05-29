package br.com.psi.geradorjsf.persistence.dao;

import br.com.psi.geradorjsf.custom.CustomRestTemplate;
import br.com.psi.geradorjsf.persistence.model.Choice;
import br.com.psi.geradorjsf.util.ApiUtil;
import br.com.psi.geradorjsf.util.JsonUtil;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

import static org.springframework.http.HttpMethod.*;

/**
 * @author Hiago.
 */
public class ChoiceDAO implements Serializable {
    private final String LIST_URL = ApiUtil.BASE_URL + "/professor/course/question/choice/list/{questionId}/";
    private final String DELETE_OR_FIND_ONE_URL = ApiUtil.BASE_URL + "/professor/course/question/choice/{id}";
    private final String CREATE_UPDATE_URL = ApiUtil.BASE_URL + "/professor/course/question/choice";
    private final CustomRestTemplate restTemplate;
    private final JsonUtil jsonUtil;
    private final ParameterizedTypeReference<List<Choice>> choiceListTypeReference = new ParameterizedTypeReference<List<Choice>>() {
    };

    @Inject
    public ChoiceDAO(CustomRestTemplate restTemplate, JsonUtil jsonUtil) {
        this.restTemplate = restTemplate;
        this.jsonUtil = jsonUtil;
        this.choiceList = choiceList;
    }

    public List<Choice> list(long questionId) {
        ResponseEntity<List<Choice>> exchange = restTemplate.exchange(LIST_URL, GET, jsonUtil.tokenizedHttpEntityHeader(),
                choiceListTypeReference, questionId);
        return exchange.getBody();
    }

    public Choice create(Choice choice) {
        return createOrUpdate(POST, choice);
    }

    public Choice update(Choice choice) {
        return createOrUpdate(PUT, choice);
    }

    private Choice createOrUpdate(HttpMethod httpMethod, Choice choice) {
        return restTemplate.exchange(CREATE_UPDATE_URL, httpMethod, jsonUtil.tokenizedHttpEntityHeader(choice), Choice.class).getBody();
    }

    public void delete(Choice choice) {
        restTemplate.exchange(DELETE_OR_FIND_ONE_URL, DELETE,
                jsonUtil.tokenizedHttpEntityHeader(choice),
                Choice.class, choice.getId());
    }
}
