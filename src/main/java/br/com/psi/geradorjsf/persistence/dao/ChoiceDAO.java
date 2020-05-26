package br.com.psi.geradorjsf.persistence.dao;

import br.com.psi.geradorjsf.custom.CustomRestRemplate;
import br.com.psi.geradorjsf.custom.CustomTypeReference;
import br.com.psi.geradorjsf.persistence.model.Choice;
import br.com.psi.geradorjsf.util.ApiUtil;
import br.com.psi.geradorjsf.util.JsonUtil;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

/**
 * @author Hiago.
 */
public class ChoiceDAO implements Serializable {
    private final String LIST_URL = ApiUtil.BASE_URL + "/professor/course/question/choice/list/{questionId}/";
    private final String DELETE_OR_FIND_ONE_URL = ApiUtil.BASE_URL + "/professor/course/question/choice/{id}";
    private final String CREATE_UPDATE_URL = ApiUtil.BASE_URL + "/professor/course/question/choice";
    private final CustomRestRemplate restRemplate;
    private final JsonUtil jsonUtil;
    private final CustomTypeReference<List<Choice>> choiceList;

    @Inject
    public ChoiceDAO(CustomRestRemplate restRemplate, JsonUtil jsonUtil, CustomTypeReference<List<Choice>> choiceList) {
        this.restRemplate = restRemplate;
        this.jsonUtil = jsonUtil;
        this.choiceList = choiceList;
    }

    public List<Choice> list(long questionId) {
        ResponseEntity<List<Choice>> exchange = restRemplate.exchange(LIST_URL, GET, jsonUtil.tokenizedHttpEntityHeader(), choiceList.typeReference(), questionId);
        return exchange.getBody();
    }

    public Choice create(Choice choice) {
        return createOrUpdate(POST, choice);
    }

    private Choice createOrUpdate(HttpMethod httpMethod, Choice choice) {
        return restRemplate.exchange(CREATE_UPDATE_URL, httpMethod, jsonUtil.tokenizedHttpEntityHeader(choice), Choice.class).getBody();
    }
}
