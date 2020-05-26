package br.com.psi.geradorjsf.persistence.dao;

import br.com.psi.geradorjsf.annotation.ExceptionHandler;
import br.com.psi.geradorjsf.custom.CustomRestRemplate;
import br.com.psi.geradorjsf.custom.CustomTypeReference;
import br.com.psi.geradorjsf.persistence.model.Question;
import br.com.psi.geradorjsf.util.ApiUtil;
import br.com.psi.geradorjsf.util.JsonUtil;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

import static org.springframework.http.HttpMethod.*;

/**
 * @author Hiago.
 */
public class QuestionDAO implements Serializable {
    private final String LIST_URL = ApiUtil.BASE_URL + "/professor/course/question/list/{courseId}/";
    private final String DELETE_OR_FIND_ONE_URL = ApiUtil.BASE_URL + "/professor/course/question/{id}";
    private final String CREATE_UPDATE_URL = ApiUtil.BASE_URL + "/professor/course/question";
    private final CustomRestRemplate restRemplate;
    private final JsonUtil jsonUtil;
    private final CustomTypeReference<List<Question>> questionList;

    @Inject
    public QuestionDAO(CustomRestRemplate restRemplate, JsonUtil jsonUtil, CustomTypeReference<List<Question>> questionList) {
        this.restRemplate = restRemplate;
        this.jsonUtil = jsonUtil;
        this.questionList = questionList;
    }

    @ExceptionHandler
    public List<Question> list(long courseId, String title) {
        UriComponents url = UriComponentsBuilder.fromUriString(LIST_URL).queryParam("title", title).build();
        ResponseEntity<List<Question>> exchange = restRemplate.exchange(url.toUriString(), GET, jsonUtil.tokenizedHttpEntityHeader(), questionList.typeReference(), courseId);
        return exchange.getBody();
    }

    @ExceptionHandler
    public Question findOne(long id) {
        return restRemplate.exchange(DELETE_OR_FIND_ONE_URL, GET, jsonUtil.tokenizedHttpEntityHeader(), Question.class, id).getBody();
    }

    public Question update(Question question) {
        return createOrUpdate(PUT, question);
    }

    public Question create(Question question) {
        return createOrUpdate(POST, question);
    }

    private Question createOrUpdate(HttpMethod httpMethod, Question question) {
        return restRemplate.exchange(CREATE_UPDATE_URL, httpMethod, jsonUtil.tokenizedHttpEntityHeader(question), Question.class).getBody();
    }

    public void delete(Question question) {
        restRemplate.exchange(DELETE_OR_FIND_ONE_URL, DELETE,
                jsonUtil.tokenizedHttpEntityHeader(question),
                Question.class, question.getId());
    }
}
