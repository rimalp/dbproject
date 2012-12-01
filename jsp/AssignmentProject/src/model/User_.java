package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-12-01T13:56:37.692-0500")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> firstName;
	public static volatile SingularAttribute<User, String> lastName;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> school;
	public static volatile ListAttribute<User, Assigned> assigneds;
	public static volatile ListAttribute<User, Assignment> assignments;
	public static volatile ListAttribute<User, User> users1;
	public static volatile ListAttribute<User, User> users2;
	public static volatile ListAttribute<User, Lecture> lectures;
	public static volatile ListAttribute<User, Message> messages1;
	public static volatile ListAttribute<User, Message> messages2;
	public static volatile ListAttribute<User, Post> posts;
	public static volatile SingularAttribute<User, Professor> professor;
	public static volatile ListAttribute<User, Reading> readings;
	public static volatile SingularAttribute<User, Student> student;
	public static volatile ListAttribute<User, Section> sections1;
	public static volatile ListAttribute<User, Section> sections2;
}
