package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the students database table.
 * 
 */
@Entity
@Table(name="students")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String email;

	private Integer answerid;

	private String major;

	private Integer year;

	//bi-directional one-to-one association to User
	@OneToOne
	@JoinColumn(name="email")
	private User user;

	public Student() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAnswerid() {
		return this.answerid;
	}

	public void setAnswerid(Integer answerid) {
		this.answerid = answerid;
	}

	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}