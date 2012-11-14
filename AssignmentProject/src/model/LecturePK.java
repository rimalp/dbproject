package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the lectures database table.
 * 
 */
@Embeddable
public class LecturePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer lectureid;

	@Column(name="uploader_email")
	private String uploaderEmail;

	public LecturePK() {
	}
	public Integer getLectureid() {
		return this.lectureid;
	}
	public void setLectureid(Integer lectureid) {
		this.lectureid = lectureid;
	}
	public String getUploaderEmail() {
		return this.uploaderEmail;
	}
	public void setUploaderEmail(String uploaderEmail) {
		this.uploaderEmail = uploaderEmail;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LecturePK)) {
			return false;
		}
		LecturePK castOther = (LecturePK)other;
		return 
			this.lectureid.equals(castOther.lectureid)
			&& this.uploaderEmail.equals(castOther.uploaderEmail);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.lectureid.hashCode();
		hash = hash * prime + this.uploaderEmail.hashCode();
		
		return hash;
	}
}