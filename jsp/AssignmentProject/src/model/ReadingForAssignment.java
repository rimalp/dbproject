package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the reading_for_assignment database table.
 * 
 */
@Entity
@Table(name="reading_for_assignment")
public class ReadingForAssignment implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ReadingForAssignmentPK id;

	//bi-directional many-to-one association to Assignment
	@ManyToOne
	@JoinColumn(name="assignmentid")
	private Assignment assignment;

	public ReadingForAssignment() {
	}

	public ReadingForAssignmentPK getId() {
		return this.id;
	}

	public void setId(ReadingForAssignmentPK id) {
		this.id = id;
	}

	public Assignment getAssignment() {
		return this.assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

}