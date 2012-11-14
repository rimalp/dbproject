package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the assigned database table.
 * 
 */
@Entity
public class Assigned implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AssignedPK id;

	private double grade;

	@Temporal(TemporalType.DATE)
	@Column(name="submit_time")
	private Date submitTime;

	//bi-directional many-to-one association to Assignment
	@ManyToOne
	@JoinColumn(name="assignmentid")
	private Assignment assignment;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="email")
	private User user;

	public Assigned() {
	}

	public AssignedPK getId() {
		return this.id;
	}

	public void setId(AssignedPK id) {
		this.id = id;
	}

	public double getGrade() {
		return this.grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public Date getSubmitTime() {
		return this.submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public Assignment getAssignment() {
		return this.assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}