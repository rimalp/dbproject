package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-12-01T13:56:37.676-0500")
@StaticMetamodel(Practice.class)
public class Practice_ {
	public static volatile SingularAttribute<Practice, Integer> assignmentid;
	public static volatile SingularAttribute<Practice, Integer> numAttempts;
	public static volatile SingularAttribute<Practice, Assignment> assignment;
}
