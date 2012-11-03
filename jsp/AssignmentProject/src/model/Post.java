package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the posts database table.
 * 
 */
@Entity
@Table(name="posts")
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PostPK id;

	private String content;

	@Temporal(TemporalType.DATE)
	private Date date;

	//bi-directional many-to-one association to Assignment
	@ManyToOne
	@JoinColumn(name="assignmentid")
	private Assignment assignment;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="poster_email")
	private User user;

	public Post() {
	}

	public PostPK getId() {
		return this.id;
	}

	public void setId(PostPK id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Assignment getAssignment() {
		return this.assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}