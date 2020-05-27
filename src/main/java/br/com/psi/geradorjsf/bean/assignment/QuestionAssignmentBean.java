package br.com.psi.geradorjsf.bean.assignment;

import br.com.psi.geradorjsf.persistence.dao.QuestionAssignmentDAO;
import br.com.psi.geradorjsf.persistence.model.QuestionAssignment;

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
public class QuestionAssignmentBean implements Serializable{
    private List<QuestionAssignment> questionAssignmentList;
    private long assignmentId;
    private final QuestionAssignmentDAO questionAssignmentDAO;
    @Inject
    public QuestionAssignmentBean(QuestionAssignmentDAO questionAssignmentDAO) {
        this.questionAssignmentDAO = questionAssignmentDAO;
    }

    public void init(){
        questionAssignmentList = questionAssignmentDAO.list(assignmentId);
    }

    public List<QuestionAssignment> getQuestionAssignmentList() {
        return questionAssignmentList;
    }

    public void setQuestionAssignmentList(List<QuestionAssignment> questionAssignmentList) {
        this.questionAssignmentList = questionAssignmentList;
    }

    public long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(long assignmentId) {
        this.assignmentId = assignmentId;
    }
}
