package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the questions database table.
 * 
 */
@Entity
@Table(name="questions")
public class Question implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private QuestionPK id;

	//bi-directional many-to-one association to Assignment
	@ManyToOne
	@JoinColumn(name="assignmentid")
	private Assignment assignment;

	public Question() {
	}

	public QuestionPK getId() {
		return this.id;
	}

	public void setId(QuestionPK id) {
		this.id = id;
	}

	public Assignment getAssignment() {
		return this.assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

}