package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the lecture_follows_reading database table.
 * 
 */
@Embeddable
public class LectureFollowsReadingPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer lectureid;

	private Integer readingid;

	public LectureFollowsReadingPK() {
	}
	public Integer getLectureid() {
		return this.lectureid;
	}
	public void setLectureid(Integer lectureid) {
		this.lectureid = lectureid;
	}
	public Integer getReadingid() {
		return this.readingid;
	}
	public void setReadingid(Integer readingid) {
		this.readingid = readingid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LectureFollowsReadingPK)) {
			return false;
		}
		LectureFollowsReadingPK castOther = (LectureFollowsReadingPK)other;
		return 
			this.lectureid.equals(castOther.lectureid)
			&& this.readingid.equals(castOther.readingid);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.lectureid.hashCode();
		hash = hash * prime + this.readingid.hashCode();
		
		return hash;
	}
}