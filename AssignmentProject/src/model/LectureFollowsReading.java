package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the lecture_follows_reading database table.
 * 
 */
@Entity
@Table(name="lecture_follows_reading")
public class LectureFollowsReading implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LectureFollowsReadingPK id;

	public LectureFollowsReading() {
	}

	public LectureFollowsReadingPK getId() {
		return this.id;
	}

	public void setId(LectureFollowsReadingPK id) {
		this.id = id;
	}

}