package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-12-01T13:56:37.665-0500")
@StaticMetamodel(Lecture.class)
public class Lecture_ {
	public static volatile SingularAttribute<Lecture, LecturePK> id;
	public static volatile SingularAttribute<Lecture, String> comment;
	public static volatile SingularAttribute<Lecture, Date> date;
	public static volatile SingularAttribute<Lecture, String> header;
	public static volatile SingularAttribute<Lecture, String> mimeType;
	public static volatile SingularAttribute<Lecture, Integer> size;
	public static volatile SingularAttribute<Lecture, String> url;
	public static volatile SingularAttribute<Lecture, User> user;
}
