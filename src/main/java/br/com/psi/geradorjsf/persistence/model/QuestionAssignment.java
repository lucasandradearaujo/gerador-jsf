package br.com.psi.geradorjsf.persistence.model;

/**
 * @author Hiago.
 */
public class QuestionAssignment extends AbstractEntity {
    private Professor professor;
    private Question question;
    private Assignment assignment;
    private double grade;


    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }


}
