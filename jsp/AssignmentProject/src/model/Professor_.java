package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-12-01T13:56:37.678-0500")
@StaticMetamodel(Professor.class)
public class Professor_ {
	public static volatile SingularAttribute<Professor, String> email;
	public static volatile SingularAttribute<Professor, String> hours;
	public static volatile SingularAttribute<Professor, String> office;
	public static volatile SingularAttribute<Professor, User> user;
}
