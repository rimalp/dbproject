package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the files database table.
 * 
 */
@Embeddable
public class FilePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer fileid;

	@Column(name="uploader_email")
	private String uploaderEmail;

	private Integer assignmentid;

	public FilePK() {
	}
	public Integer getFileid() {
		return this.fileid;
	}
	public void setFileid(Integer fileid) {
		this.fileid = fileid;
	}
	public String getUploaderEmail() {
		return this.uploaderEmail;
	}
	public void setUploaderEmail(String uploaderEmail) {
		this.uploaderEmail = uploaderEmail;
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
		if (!(other instanceof FilePK)) {
			return false;
		}
		FilePK castOther = (FilePK)other;
		return 
			this.fileid.equals(castOther.fileid)
			&& this.uploaderEmail.equals(castOther.uploaderEmail)
			&& this.assignmentid.equals(castOther.assignmentid);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fileid.hashCode();
		hash = hash * prime + this.uploaderEmail.hashCode();
		hash = hash * prime + this.assignmentid.hashCode();
		
		return hash;
	}
}