package model;

import java.sql.Time;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-12-01T13:56:37.691-0500")
@StaticMetamodel(Uploaded.class)
public class Uploaded_ {
	public static volatile SingularAttribute<Uploaded, Integer> assignmentid;
	public static volatile SingularAttribute<Uploaded, Time> confirmTime;
	public static volatile SingularAttribute<Uploaded, Assignment> assignment;
}
