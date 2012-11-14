package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the reading_for_assignment database table.
 * 
 */
@Embeddable
public class ReadingForAssignmentPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer readingid;

	private Integer assignmentid;

	public ReadingForAssignmentPK() {
	}
	public Integer getReadingid() {
		return this.readingid;
	}
	public void setReadingid(Integer readingid) {
		this.readingid = readingid;
	}
	public Integer getAssignmentid() {
		return this.assignmentid;
	}
	public void setAssignmentid(Integer assignmentid) {
		this.assignmentid = assignmentid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ReadingForAssignmentPK)) {
			return false;
		}
		ReadingForAssignmentPK castOther = (ReadingForAssignmentPK)other;
		return 
			this.readingid.equals(castOther.readingid)
			&& this.assignmentid.equals(castOther.assignmentid);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.readingid.hashCode();
		hash = hash * prime + this.assignmentid.hashCode();
		
		return hash;
	}
}