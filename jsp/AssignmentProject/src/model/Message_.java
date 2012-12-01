package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-12-01T13:56:37.672-0500")
@StaticMetamodel(Message.class)
public class Message_ {
	public static volatile SingularAttribute<Message, Integer> msgid;
	public static volatile SingularAttribute<Message, String> body;
	public static volatile SingularAttribute<Message, Date> date;
	public static volatile SingularAttribute<Message, String> header;
	public static volatile SingularAttribute<Message, User> user1;
	public static volatile SingularAttribute<Message, User> user2;
}
