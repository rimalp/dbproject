package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the lectures database table.
 * 
 */
@Entity
@Table(name="lectures")
public class Lecture implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LecturePK id;

	private String comment;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String header;

	@Column(name="mime_type")
	private String mimeType;

	private Integer size;

	private String url;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="uploader_email")
	private User user;

	public Lecture() {
	}

	public LecturePK getId() {
		return this.id;
	}

	public void setId(LecturePK id) {
		this.id = id;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getHeader() {
		return this.header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getMimeType() {
		return this.mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public Integer getSize() {
		return this.size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}