package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the posts database table.
 * 
 */
@Embeddable
public class PostPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer postid;

	@Column(name="last_postid")
	private Integer lastPostid;

	@Column(name="poster_email")
	private String posterEmail;

	private Integer assignmentid;

	public PostPK() {
	}
	public Integer getPostid() {
		return this.postid;
	}
	public void setPostid(Integer postid) {
		this.postid = postid;
	}
	public Integer getLastPostid() {
		return this.lastPostid;
	}
	public void setLastPostid(Integer lastPostid) {
		this.lastPostid = lastPostid;
	}
	public String getPosterEmail() {
		return this.posterEmail;
	}
	public void setPosterEmail(String posterEmail) {
		this.posterEmail = posterEmail;
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
		if (!(other instanceof PostPK)) {
			return false;
		}
		PostPK castOther = (PostPK)other;
		return 
			this.postid.equals(castOther.postid)
			&& this.lastPostid.equals(castOther.lastPostid)
			&& this.posterEmail.equals(castOther.posterEmail)
			&& this.assignmentid.equals(castOther.assignmentid);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.postid.hashCode();
		hash = hash * prime + this.lastPostid.hashCode();
		hash = hash * prime + this.posterEmail.hashCode();
		hash = hash * prime + this.assignmentid.hashCode();
		
		return hash;
	}
}