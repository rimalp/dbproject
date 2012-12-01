package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-12-01T13:56:37.687-0500")
@StaticMetamodel(Section.class)
public class Section_ {
	public static volatile SingularAttribute<Section, String> crn;
	public static volatile SingularAttribute<Section, String> course;
	public static volatile SingularAttribute<Section, String> day;
	public static volatile SingularAttribute<Section, String> room;
	public static volatile SingularAttribute<Section, String> time;
	public static volatile ListAttribute<Section, Assignment> assignments;
	public static volatile ListAttribute<Section, User> users1;
	public static volatile ListAttribute<Section, User> users2;
}
