package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-12-01T13:56:37.682-0500")
@StaticMetamodel(Reading.class)
public class Reading_ {
	public static volatile SingularAttribute<Reading, ReadingPK> id;
	public static volatile SingularAttribute<Reading, String> book;
	public static volatile SingularAttribute<Reading, String> page;
	public static volatile SingularAttribute<Reading, String> topic;
	public static volatile SingularAttribute<Reading, String> url;
	public static volatile SingularAttribute<Reading, User> user;
}
