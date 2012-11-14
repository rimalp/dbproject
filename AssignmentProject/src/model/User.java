package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String email;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	private String password;

	private String school;

	//bi-directional many-to-one association to Assigned
	@OneToMany(mappedBy="user")
	private List<Assigned> assigneds;

	//bi-directional many-to-one association to Assignment
	@OneToMany(mappedBy="user")
	private List<Assignment> assignments;

	//bi-directional many-to-many association to User
	@ManyToMany
	@JoinTable(
		name="friends"
		, joinColumns={
			@JoinColumn(name="email2")
			}
		, inverseJoinColumns={
			@JoinColumn(name="email1")
			}
		)
	private List<User> users1;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="users1")
	private List<User> users2;

	//bi-directional many-to-one association to Lecture
	@OneToMany(mappedBy="user")
	private List<Lecture> lectures;

	//bi-directional many-to-one association to Message
	@OneToMany(mappedBy="user1")
	private List<Message> messages1;

	//bi-directional many-to-one association to Message
	@OneToMany(mappedBy="user2")
	private List<Message> messages2;

	//bi-directional many-to-one association to Post
	@OneToMany(mappedBy="user")
	private List<Post> posts;

	//bi-directional one-to-one association to Professor
	@OneToOne(mappedBy="user")
	private Professor professor;

	//bi-directional many-to-one association to Reading
	@OneToMany(mappedBy="user")
	private List<Reading> readings;

	//bi-directional one-to-one association to Student
	@OneToOne(mappedBy="user")
	private Student student;

	//bi-directional many-to-many association to Section
	@ManyToMany
	@JoinTable(
		name="takes"
		, joinColumns={
			@JoinColumn(name="email")
			}
		, inverseJoinColumns={
			@JoinColumn(name="crn")
			}
		)
	private List<Section> sections1;

	//bi-directional many-to-many association to Section
	@ManyToMany
	@JoinTable(
		name="teaches"
		, joinColumns={
			@JoinColumn(name="email")
			}
		, inverseJoinColumns={
			@JoinColumn(name="crn")
			}
		)
	private List<Section> sections2;

	public User() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public List<Assigned> getAssigneds() {
		return this.assigneds;
	}

	public void setAssigneds(List<Assigned> assigneds) {
		this.assigneds = assigneds;
	}

	public List<Assignment> getAssignments() {
		return this.assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}

	public List<User> getUsers1() {
		return this.users1;
	}

	public void setUsers1(List<User> users1) {
		this.users1 = users1;
	}

	public List<User> getUsers2() {
		return this.users2;
	}

	public void setUsers2(List<User> users2) {
		this.users2 = users2;
	}

	public List<Lecture> getLectures() {
		return this.lectures;
	}

	public void setLectures(List<Lecture> lectures) {
		this.lectures = lectures;
	}

	public List<Message> getMessages1() {
		return this.messages1;
	}

	public void setMessages1(List<Message> messages1) {
		this.messages1 = messages1;
	}

	public List<Message> getMessages2() {
		return this.messages2;
	}

	public void setMessages2(List<Message> messages2) {
		this.messages2 = messages2;
	}

	public List<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Professor getProfessor() {
		return this.professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Reading> getReadings() {
		return this.readings;
	}

	public void setReadings(List<Reading> readings) {
		this.readings = readings;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Section> getSections1() {
		return this.sections1;
	}

	public void setSections1(List<Section> sections1) {
		this.sections1 = sections1;
	}

	public List<Section> getSections2() {
		return this.sections2;
	}

	public void setSections2(List<Section> sections2) {
		this.sections2 = sections2;
	}

}