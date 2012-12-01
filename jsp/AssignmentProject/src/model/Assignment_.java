package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-12-01T13:56:37.660-0500")
@StaticMetamodel(Assignment.class)
public class Assignment_ {
	public static volatile SingularAttribute<Assignment, Integer> assignmentid;
	public static volatile SingularAttribute<Assignment, String> deadline;
	public static volatile SingularAttribute<Assignment, String> description;
	public static volatile SingularAttribute<Assignment, String> name;
	public static volatile ListAttribute<Assignment, Answer> answers;
	public static volatile ListAttribute<Assignment, Assigned> assigneds;
	public static volatile SingularAttribute<Assignment, Section> section;
	public static volatile SingularAttribute<Assignment, User> user;
	public static volatile ListAttribute<Assignment, File> files;
	public static volatile ListAttribute<Assignment, Post> posts;
	public static volatile SingularAttribute<Assignment, Practice> practice;
	public static volatile ListAttribute<Assignment, Question> questions;
	public static volatile ListAttribute<Assignment, ReadingForAssignment> readingForAssignments;
	public static volatile SingularAttribute<Assignment, Uploaded> uploaded;
}
