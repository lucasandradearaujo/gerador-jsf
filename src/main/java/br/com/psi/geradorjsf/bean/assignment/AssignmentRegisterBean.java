package br.com.psi.geradorjsf.bean.assignment;

import br.com.psi.geradorjsf.annotation.ExceptionHandler;
import br.com.psi.geradorjsf.persistence.dao.AssignmentDAO;
import br.com.psi.geradorjsf.persistence.dao.CourseDAO;
import br.com.psi.geradorjsf.persistence.model.Assignment;
import br.com.psi.geradorjsf.persistence.model.Course;
import org.omnifaces.util.Messages;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * @author Hiago.
 */
@Named
@ViewScoped
public class AssignmentRegisterBean implements Serializable {
    private final CourseDAO courseDAO;
    private final AssignmentDAO assignmentDAO;
    private Course course;
    private Assignment assignment = new Assignment();
    private long courseId;

    @Inject
    public AssignmentRegisterBean(CourseDAO courseDAO, AssignmentDAO assignmentDAO) {
        this.courseDAO = courseDAO;
        this.assignmentDAO = assignmentDAO;
    }

    @ExceptionHandler
    public void init() {
        course = courseDAO.findOne(courseId);
    }

    @ExceptionHandler
    public void save() {
        assignment.setCourse(course);
        assignmentDAO.create(assignment);
        Messages.addGlobalInfo("The assignment {0} was successfully added.", assignment.getTitle());
        assignment = new Assignment();
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
