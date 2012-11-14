package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the assignments database table.
 * 
 */
@Entity
@Table(name="assignments")
public class Assignment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer assignmentid;

	private String deadline;

	private String description;

	private String name;

	//bi-directional many-to-one association to Answer
	@OneToMany(mappedBy="assignment")
	private List<Answer> answers;

	//bi-directional many-to-one association to Assigned
	@OneToMany(mappedBy="assignment")
	private List<Assigned> assigneds;

	//bi-directional many-to-one association to Section
	@ManyToOne
	@JoinColumn(name="crn")
	private Section section;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="assigner_email")
	private User user;

	//bi-directional many-to-one association to File
	@OneToMany(mappedBy="assignment")
	private List<File> files;

	//bi-directional many-to-one association to Post
	@OneToMany(mappedBy="assignment")
	private List<Post> posts;

	//bi-directional one-to-one association to Practice
	@OneToOne(mappedBy="assignment")
	private Practice practice;

	//bi-directional many-to-one association to Question
	@OneToMany(mappedBy="assignment")
	private List<Question> questions;

	//bi-directional many-to-one association to ReadingForAssignment
	@OneToMany(mappedBy="assignment")
	private List<ReadingForAssignment> readingForAssignments;

	//bi-directional one-to-one association to Uploaded
	@OneToOne(mappedBy="assignment")
	private Uploaded uploaded;

	public Assignment() {
	}

	public Integer getAssignmentid() {
		return this.assignmentid;
	}

	public void setAssignmentid(Integer assignmentid) {
		this.assignmentid = assignmentid;
	}

	public String getDeadline() {
		return this.deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Answer> getAnswers() {
		return this.answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public List<Assigned> getAssigneds() {
		return this.assigneds;
	}

	public void setAssigneds(List<Assigned> assigneds) {
		this.assigneds = assigneds;
	}

	public Section getSection() {
		return this.section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<File> getFiles() {
		return this.files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public List<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Practice getPractice() {
		return this.practice;
	}

	public void setPractice(Practice practice) {
		this.practice = practice;
	}

	public List<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<ReadingForAssignment> getReadingForAssignments() {
		return this.readingForAssignments;
	}

	public void setReadingForAssignments(List<ReadingForAssignment> readingForAssignments) {
		this.readingForAssignments = readingForAssignments;
	}

	public Uploaded getUploaded() {
		return this.uploaded;
	}

	public void setUploaded(Uploaded uploaded) {
		this.uploaded = uploaded;
	}

}