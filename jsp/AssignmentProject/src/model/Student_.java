package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-12-01T13:56:37.689-0500")
@StaticMetamodel(Student.class)
public class Student_ {
	public static volatile SingularAttribute<Student, String> email;
	public static volatile SingularAttribute<Student, Integer> answerid;
	public static volatile SingularAttribute<Student, String> major;
	public static volatile SingularAttribute<Student, Integer> year;
	public static volatile SingularAttribute<Student, User> user;
}
