package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the readings database table.
 * 
 */
@Entity
@Table(name="readings")
public class Reading implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ReadingPK id;

	private String book;

	private String page;

	private String topic;

	private String url;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="uploader_email")
	private User user;

	public Reading() {
	}

	public ReadingPK getId() {
		return this.id;
	}

	public void setId(ReadingPK id) {
		this.id = id;
	}

	public String getBook() {
		return this.book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getPage() {
		return this.page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getTopic() {
		return this.topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
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