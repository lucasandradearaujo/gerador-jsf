package br.com.psi.geradorjsf.bean.exam;

import br.com.psi.geradorjsf.annotation.ExceptionHandler;
import br.com.psi.geradorjsf.persistence.dao.ExamDAO;
import br.com.psi.geradorjsf.persistence.model.Choice;
import br.com.psi.geradorjsf.persistence.model.Question;
import org.omnifaces.util.Messages;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author Hiago.
 */
@Named
@ViewScoped
public class ExamBean implements Serializable {
    private ExamDAO examDAO;
    private String accessCode;
    private Map<Question, List<Choice>> questionListMap;
    private String multipleChoiceAnswer;
    private Map<Long, Long> questionChoiceIdsMap = new HashMap<>();

    @Inject
    public ExamBean(ExamDAO examDAO) {
        this.examDAO = examDAO;
    }

    public void init(){
        questionListMap = examDAO.list(accessCode);
    }

    @ExceptionHandler
    public String accessExam(){
        return "exam.xhtml?faces-redirect=true&accessCode="+accessCode;
    }

    public void storeAnswer(){
        if(multipleChoiceAnswer != null && !multipleChoiceAnswer.isEmpty()){
            //[0] question.id [1] choice.id
            String questionChoiceIds[] = multipleChoiceAnswer.split("#");
            questionChoiceIdsMap.put(Long.parseLong(questionChoiceIds[0]), Long.parseLong(questionChoiceIds[1]));
        }
    }

    @ExceptionHandler
    public String save(){
        examDAO.save(accessCode, questionChoiceIdsMap);
        Messages.create("The assignment was successfully submitted.").flash().add();
        return "index-student.xhtml?faces-redirect=true";
    }

    public String getMultipleChoiceAnswer() {
        return multipleChoiceAnswer;
    }

    public void setMultipleChoiceAnswer(String multipleChoiceAnswer) {
        this.multipleChoiceAnswer = multipleChoiceAnswer;
    }

    public Map<Long, Long> getQuestionChoiceIdsMap() {
        return questionChoiceIdsMap;
    }

    public void setQuestionChoiceIdsMap(Map<Long, Long> questionChoiceIdsMap) {
        this.questionChoiceIdsMap = questionChoiceIdsMap;
    }

    public Map<Question, List<Choice>> getQuestionListMap() {
        return questionListMap;
    }

    public void setQuestionListMap(Map<Question, List<Choice>> questionListMap) {
        this.questionListMap = questionListMap;
    }

    public String getAccessCode(){
        return accessCode;
    }

    public void setAccessCode(String accessCode){
        this.accessCode = accessCode;
    }
}
