package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the professors database table.
 * 
 */
@Entity
@Table(name="professors")
public class Professor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String email;

	private String hours;

	private String office;

	//bi-directional one-to-one association to User
	@OneToOne
	@JoinColumn(name="email")
	private User user;

	public Professor() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHours() {
		return this.hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getOffice() {
		return this.office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}