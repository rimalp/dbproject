package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the questions database table.
 * 
 */
@Embeddable
public class QuestionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer assignmentid;

	private String prompt;

	public QuestionPK() {
	}
	public Integer getAssignmentid() {
		return this.assignmentid;
	}
	public void setAssignmentid(Integer assignmentid) {
		this.assignmentid = assignmentid;
	}
	public String getPrompt() {
		return this.prompt;
	}
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof QuestionPK)) {
			return false;
		}
		QuestionPK castOther = (QuestionPK)other;
		return 
			this.assignmentid.equals(castOther.assignmentid)
			&& this.prompt.equals(castOther.prompt);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.assignmentid.hashCode();
		hash = hash * prime + this.prompt.hashCode();
		
		return hash;
	}
}