package entity;

import entity.Utente;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-14T11:35:26")
@StaticMetamodel(Post.class)
public class Post_ { 

    public static volatile SingularAttribute<Post, Utente> utente;
    public static volatile SingularAttribute<Post, String> Testo;
    public static volatile SingularAttribute<Post, String> Titolo;
    public static volatile SingularAttribute<Post, Date> DataOra;
    public static volatile SingularAttribute<Post, Long> id;

}