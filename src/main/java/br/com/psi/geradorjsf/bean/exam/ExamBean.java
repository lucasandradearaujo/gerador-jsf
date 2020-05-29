package br.com.psi.geradorjsf.bean.exam;

import br.com.psi.geradorjsf.persistence.dao.ExamDAO;
import br.com.psi.geradorjsf.persistence.model.Choice;
import br.com.psi.geradorjsf.persistence.model.Question;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
/**
 * @author Hiago.
 */
@Named
@ViewScoped
public class ExamBean implements Serializable {
    private ExamDAO examDAO;
    @Inject
    public ExamBean(ExamDAO examDAO) {
        this.examDAO = examDAO;
    }

    public void init(){
        Map<Question, List<Choice>> list = examDAO.list("1234");
        System.out.println(list);
    }
}
