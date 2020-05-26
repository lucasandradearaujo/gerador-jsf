package br.com.psi.geradorjsf.bean.question;

import br.com.psi.geradorjsf.persistence.dao.CourseDAO;
import br.com.psi.geradorjsf.persistence.dao.QuestionDAO;
import br.com.psi.geradorjsf.persistence.model.Course;
import br.com.psi.geradorjsf.persistence.model.Question;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author Hiago.
 */
@Named
@ViewScoped
public class QuestionListBean implements Serializable {
    private final QuestionDAO questionDAO;
    private final CourseDAO courseDAO;
    private List<Question> questionList;
    private Course course;
    private String title = "";
    private long courseId;

    @Inject
    public QuestionListBean(QuestionDAO questionDAO, CourseDAO courseDAO) {
        this.questionDAO = questionDAO;
        this.courseDAO = courseDAO;
    }

    public void init() {
        course = courseDAO.findOne(courseId);
        search();
    }

    public void search() {
        questionList = questionDAO.list(courseId, title);
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }
}
