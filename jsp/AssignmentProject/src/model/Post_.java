package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-12-01T13:56:37.673-0500")
@StaticMetamodel(Post.class)
public class Post_ {
	public static volatile SingularAttribute<Post, PostPK> id;
	public static volatile SingularAttribute<Post, String> content;
	public static volatile SingularAttribute<Post, Date> date;
	public static volatile SingularAttribute<Post, Assignment> assignment;
	public static volatile SingularAttribute<Post, User> user;
}
