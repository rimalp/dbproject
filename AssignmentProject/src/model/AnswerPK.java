package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the answers database table.
 * 
 */
@Embeddable
public class AnswerPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer answerid;

	private Integer assignmentid;

	private String prompt;

	public AnswerPK() {
	}
	public Integer getAnswerid() {
		return this.answerid;
	}
	public void setAnswerid(Integer answerid) {
		this.answerid = answerid;
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
		if (!(other instanceof AnswerPK)) {
			return false;
		}
		AnswerPK castOther = (AnswerPK)other;
		return 
			this.answerid.equals(castOther.answerid)
			&& this.assignmentid.equals(castOther.assignmentid)
			&& this.prompt.equals(castOther.prompt);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.answerid.hashCode();
		hash = hash * prime + this.assignmentid.hashCode();
		hash = hash * prime + this.prompt.hashCode();
		
		return hash;
	}
}