import br.com.psi.geradorjsf.persistence.dao.LoginDAO;
import br.com.psi.geradorjsf.persistence.dao.ProfessorDAO;
import br.com.psi.geradorjsf.persistence.model.support.Token;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * @author Hiago
 */
@Named
@ViewScoped
public class IndexBean implements Serializable {
    private String message = "Projeto";
    private final LoginDAO loginDAO;
    private final ProfessorDAO professorDAO;

    @Inject
    public IndexBean(LoginDAO loginDAO, ProfessorDAO professorDAO) {
        this.loginDAO = loginDAO;
        this.professorDAO = professorDAO;
    }

    public void login() {
        Token token = loginDAO.loginReturningToken("hiago", "1234567890");
        System.out.println(token);
    }

    public void checkProfessor(){
        professorDAO.getProfessorById(1L);
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
