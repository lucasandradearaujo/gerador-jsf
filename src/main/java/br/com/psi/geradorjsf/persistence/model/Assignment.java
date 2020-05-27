package br.com.psi.geradorjsf.persistence.model;

import java.time.LocalDateTime;

/**
 * @author Hiago.
 */

public class Assignment extends AbstractEntity {
    //    @NotEmpty(message = "The field title cannot be empty")
//    @ApiModelProperty(notes = "The title of the assignment")
    private String title;
    private LocalDateTime createdAt = LocalDateTime.now();
    private Course course;
    private Professor professor;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public static final class Builder {
        private Assignment assignment;

        private Builder() {
            assignment = new Assignment();
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder id(Long id) {
            assignment.setId(id);
            return this;
        }

        public Builder enabled(boolean enabled) {
            assignment.setEnabled(enabled);
            return this;
        }

        public Builder title(String title) {
            assignment.setTitle(title);
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            assignment.setCreatedAt(createdAt);
            return this;
        }

        public Builder course(Course course) {
            assignment.setCourse(course);
            return this;
        }

        public Builder professor(Professor professor) {
            assignment.setProfessor(professor);
            return this;
        }

        public Assignment build() {
            return assignment;
        }
    }
}
