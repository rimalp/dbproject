package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-12-01T13:56:37.656-0500")
@StaticMetamodel(Assigned.class)
public class Assigned_ {
	public static volatile SingularAttribute<Assigned, AssignedPK> id;
	public static volatile SingularAttribute<Assigned, Double> grade;
	public static volatile SingularAttribute<Assigned, Date> submitTime;
	public static volatile SingularAttribute<Assigned, Assignment> assignment;
	public static volatile SingularAttribute<Assigned, User> user;
}
