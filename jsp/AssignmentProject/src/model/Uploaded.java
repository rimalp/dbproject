package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;


/**
 * The persistent class for the uploaded database table.
 * 
 */
@Entity
public class Uploaded implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer assignmentid;

	@Column(name="confirm_time")
	private Time confirmTime;

	//bi-directional one-to-one association to Assignment
	@OneToOne
	@JoinColumn(name="assignmentid")
	private Assignment assignment;

	public Uploaded() {
	}

	public Integer getAssignmentid() {
		return this.assignmentid;
	}

	public void setAssignmentid(Integer assignmentid) {
		this.assignmentid = assignmentid;
	}

	public Time getConfirmTime() {
		return this.confirmTime;
	}

	public void setConfirmTime(Time confirmTime) {
		this.confirmTime = confirmTime;
	}

	public Assignment getAssignment() {
		return this.assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

}