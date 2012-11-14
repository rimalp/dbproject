package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the practices database table.
 * 
 */
@Entity
@Table(name="practices")
public class Practice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer assignmentid;

	@Column(name="num_attempts")
	private Integer numAttempts;

	//bi-directional one-to-one association to Assignment
	@OneToOne
	@JoinColumn(name="assignmentid")
	private Assignment assignment;

	public Practice() {
	}

	public Integer getAssignmentid() {
		return this.assignmentid;
	}

	public void setAssignmentid(Integer assignmentid) {
		this.assignmentid = assignmentid;
	}

	public Integer getNumAttempts() {
		return this.numAttempts;
	}

	public void setNumAttempts(Integer numAttempts) {
		this.numAttempts = numAttempts;
	}

	public Assignment getAssignment() {
		return this.assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

}