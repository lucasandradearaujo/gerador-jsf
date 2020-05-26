package br.com.psi.geradorjsf.bean.question;

import br.com.psi.geradorjsf.annotation.ExceptionHandler;
import br.com.psi.geradorjsf.persistence.dao.CourseDAO;
import br.com.psi.geradorjsf.persistence.dao.QuestionDAO;
import br.com.psi.geradorjsf.persistence.model.Course;
import br.com.psi.geradorjsf.persistence.model.Question;
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
public class QuestionEditBean implements Serializable {
    private final QuestionDAO questionDAO;
    private Question question;
    private long questionId;

    @Inject
    public QuestionEditBean(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    @ExceptionHandler
    public void init() {
        question = questionDAO.findOne(questionId);
    }

    @ExceptionHandler
    public String update() {
        questionDAO.update(question);
        Messages.create("The question {0} was successfully updated.", question.getTitle()).flash().add();
        return "list.xhtml?faces-redirect=true&courseId=" + question.getCourse().getId();
    }

    @ExceptionHandler
    public String delete() {
        questionDAO.delete(question);
        Messages.create("The course {0} was successfully deleted.", question.getTitle()).flash().add();
        return "list.xhtml?faces-redirect=true&courseId=" + question.getCourse().getId();
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }
}
