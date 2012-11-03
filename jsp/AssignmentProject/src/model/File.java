package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the files database table.
 * 
 */
@Entity
@Table(name="files")
public class File implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FilePK id;

	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(name="mime_type")
	private String mimeType;

	private String name;

	private Integer size;

	private String url;

	//bi-directional many-to-one association to Assignment
	@ManyToOne
	@JoinColumn(name="assignmentid")
	private Assignment assignment;

	public File() {
	}

	public FilePK getId() {
		return this.id;
	}

	public void setId(FilePK id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMimeType() {
		return this.mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Assignment getAssignment() {
		return this.assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

}