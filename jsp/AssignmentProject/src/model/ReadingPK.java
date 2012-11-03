package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the readings database table.
 * 
 */
@Embeddable
public class ReadingPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer readingid;

	@Column(name="uploader_email")
	private String uploaderEmail;

	public ReadingPK() {
	}
	public Integer getReadingid() {
		return this.readingid;
	}
	public void setReadingid(Integer readingid) {
		this.readingid = readingid;
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
		if (!(other instanceof ReadingPK)) {
			return false;
		}
		ReadingPK castOther = (ReadingPK)other;
		return 
			this.readingid.equals(castOther.readingid)
			&& this.uploaderEmail.equals(castOther.uploaderEmail);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.readingid.hashCode();
		hash = hash * prime + this.uploaderEmail.hashCode();
		
		return hash;
	}
}