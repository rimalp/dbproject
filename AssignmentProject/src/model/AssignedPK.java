package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the assigned database table.
 * 
 */
@Embeddable
public class AssignedPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer assignmentid;

	private String email;

	public AssignedPK() {
	}
	public Integer getAssignmentid() {
		return this.assignmentid;
	}
	public void setAssignmentid(Integer assignmentid) {
		this.assignmentid = assignmentid;
	}
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AssignedPK)) {
			return false;
		}
		AssignedPK castOther = (AssignedPK)other;
		return 
			this.assignmentid.equals(castOther.assignmentid)
			&& this.email.equals(castOther.email);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.assignmentid.hashCode();
		hash = hash * prime + this.email.hashCode();
		
		return hash;
	}
}