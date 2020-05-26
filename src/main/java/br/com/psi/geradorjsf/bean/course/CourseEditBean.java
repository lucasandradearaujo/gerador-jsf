package br.com.psi.geradorjsf.bean.course;

import br.com.psi.geradorjsf.annotation.ExceptionHandler;
import br.com.psi.geradorjsf.persistence.dao.CourseDAO;
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
public class CourseEditBean implements Serializable {
    private final CourseDAO courseDAO;
    private long id;
    private Course course;

    @Inject
    public CourseEditBean(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public void init() {
        course = courseDAO.findOne(id);
    }

    @ExceptionHandler
    public String update() {
        courseDAO.update(course);
        Messages.create("The course {0} was successfully updated.", course.getName()).flash().add();
        return "list.xhtml?faces-redirect=true";
    }

    @ExceptionHandler
    public String delete() {
        courseDAO.delete(course);
        Messages.create("The course {0} was successfully deleted.", course.getName()).flash().add();
        return "list.xhtml?faces-redirect=true";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
