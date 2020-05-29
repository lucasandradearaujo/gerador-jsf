package br.com.psi.geradorjsf.persistence.dao;

import br.com.psi.geradorjsf.annotation.ExceptionHandler;
import br.com.psi.geradorjsf.custom.CustomRestTemplate;
import br.com.psi.geradorjsf.persistence.model.Course;
import br.com.psi.geradorjsf.util.ApiUtil;
import br.com.psi.geradorjsf.util.JsonUtil;
import org.springframework.core.ParameterizedTypeReference;
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
public class CourseDAO implements Serializable {
    private final String LIST_URL = ApiUtil.BASE_URL + "/professor/course/list";
    private final String DELETE_OR_FIND_ONE_URL = ApiUtil.BASE_URL + "/professor/course/{id}";
    private final String CREATE_UPDATE_URL = ApiUtil.BASE_URL + "/professor/course/";
    private final CustomRestTemplate restRemplate;
    private final JsonUtil jsonUtil;
    private final ParameterizedTypeReference<List<Course>> courseListTypeReference = new ParameterizedTypeReference<List<Course>>() {
    };

    @Inject
    public CourseDAO(CustomRestTemplate restTemplate, JsonUtil jsonUtil) {
        this.restTemplate = restTemplate;
        this.jsonUtil = jsonUtil;
        this.listCourse = listCourse;
    }

    @ExceptionHandler
    public List<Course> list(String name) {
        UriComponents url = UriComponentsBuilder.fromUriString(LIST_URL).queryParam("name", name).build();
        ResponseEntity<List<Course>> exchange = restTemplate.exchange(url.toUriString(), GET, jsonUtil.tokenizedHttpEntityHeader(), courseListTypeReference);
        return exchange.getBody();
    }

    @ExceptionHandler
    public Course findOne(long id) {
        return restTemplate.exchange(DELETE_OR_FIND_ONE_URL, GET, jsonUtil.tokenizedHttpEntityHeader(), Course.class, id).getBody();
    }

    public Course update(Course course) {
        return createOrUpdate(PUT, course);
    }

    public Course create(Course course) {
        return createOrUpdate(POST, course);
    }

    private Course createOrUpdate(HttpMethod httpMethod, Course course) {
        return restTemplate.exchange(CREATE_UPDATE_URL, httpMethod, jsonUtil.tokenizedHttpEntityHeader(course), Course.class).getBody();
    }

    public void delete(Course course) {
        restTemplate.exchange(DELETE_OR_FIND_ONE_URL, DELETE,
                jsonUtil.tokenizedHttpEntityHeader(course),
                Course.class, course.getId());
    }
}
