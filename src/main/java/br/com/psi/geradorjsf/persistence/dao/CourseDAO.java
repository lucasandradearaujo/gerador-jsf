package br.com.psi.geradorjsf.persistence.dao;

import br.com.psi.geradorjsf.annotation.ExceptionHandler;
import br.com.psi.geradorjsf.custom.CustomRestRemplate;
import br.com.psi.geradorjsf.custom.CustomTypeReference;
import br.com.psi.geradorjsf.persistence.model.Course;
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
public class CourseDAO implements Serializable {
    private final String LIST_URL = ApiUtil.BASE_URL + "/professor/course/list";
    private final String DELETE_OR_FIND_ONE_URL = ApiUtil.BASE_URL + "/professor/course/{id}";
    private final String CREATE_UPDATE_URL = ApiUtil.BASE_URL + "/professor/course/";
    private final CustomRestRemplate restRemplate;
    private final JsonUtil jsonUtil;
    private final CustomTypeReference<List<Course>> listCourse;

    @Inject
    public CourseDAO(CustomRestRemplate restRemplate, JsonUtil jsonUtil, CustomTypeReference<List<Course>> listCourse) {
        this.restRemplate = restRemplate;
        this.jsonUtil = jsonUtil;
        this.listCourse = listCourse;
    }

    @ExceptionHandler
    public List<Course> list(String name) {
        UriComponents url = UriComponentsBuilder.fromUriString(LIST_URL).queryParam("name", name).build();
        ResponseEntity<List<Course>> exchange = restRemplate.exchange(url.toUriString(), GET, jsonUtil.tokenizedHttpEntityHeader(), listCourse.typeReference());
        return exchange.getBody();
    }

    @ExceptionHandler
    public Course findOne(long id) {
        return restRemplate.exchange(DELETE_OR_FIND_ONE_URL, GET, jsonUtil.tokenizedHttpEntityHeader(), Course.class, id).getBody();
    }

    public Course update(Course course) {
        return createOrUpdate(PUT, course);
    }

    public Course create(Course course) {
        return createOrUpdate(POST, course);
    }

    private Course createOrUpdate(HttpMethod httpMethod, Course course) {
        return restRemplate.exchange(CREATE_UPDATE_URL, httpMethod, jsonUtil.tokenizedHttpEntityHeader(course), Course.class).getBody();
    }

    public void delete(Course course) {
        restRemplate.exchange(DELETE_OR_FIND_ONE_URL, DELETE,
                jsonUtil.tokenizedHttpEntityHeader(course),
                Course.class, course.getId());
    }
}
