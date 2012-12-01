package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-12-01T13:56:37.662-0500")
@StaticMetamodel(File.class)
public class File_ {
	public static volatile SingularAttribute<File, FilePK> id;
	public static volatile SingularAttribute<File, Date> date;
	public static volatile SingularAttribute<File, String> mimeType;
	public static volatile SingularAttribute<File, String> name;
	public static volatile SingularAttribute<File, Integer> size;
	public static volatile SingularAttribute<File, String> url;
	public static volatile SingularAttribute<File, Assignment> assignment;
}
