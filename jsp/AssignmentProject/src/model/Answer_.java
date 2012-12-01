package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-12-01T13:56:37.644-0500")
@StaticMetamodel(Answer.class)
public class Answer_ {
	public static volatile SingularAttribute<Answer, AnswerPK> id;
	public static volatile SingularAttribute<Answer, String> content;
	public static volatile SingularAttribute<Answer, Boolean> correct;
	public static volatile SingularAttribute<Answer, Assignment> assignment;
}
