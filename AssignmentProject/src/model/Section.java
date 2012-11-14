package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sections database table.
 * 
 */
@Entity
@Table(name="sections")
public class Section implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String crn;

	private String course;

	private String day;

	private String room;

	private String time;

	//bi-directional many-to-one association to Assignment
	@OneToMany(mappedBy="section")
	private List<Assignment> assignments;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="sections1")
	private List<User> users1;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="sections2")
	private List<User> users2;

	public Section() {
	}

	public String getCrn() {
		return this.crn;
	}

	public void setCrn(String crn) {
		this.crn = crn;
	}

	public String getCourse() {
		return this.course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getDay() {
		return this.day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getRoom() {
		return this.room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public List<Assignment> getAssignments() {
		return this.assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}

	public List<User> getUsers1() {
		return this.users1;
	}

	public void setUsers1(List<User> users1) {
		this.users1 = users1;
	}

	public List<User> getUsers2() {
		return this.users2;
	}

	public void setUsers2(List<User> users2) {
		this.users2 = users2;
	}

}