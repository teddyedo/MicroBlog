package entity;

import entity.Post;
import entity.Utente;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-13T20:48:29")
@StaticMetamodel(Commento.class)
public class Commento_ { 

    public static volatile SingularAttribute<Commento, Utente> utente;
    public static volatile SingularAttribute<Commento, String> Testo;
    public static volatile SingularAttribute<Commento, Post> post;
    public static volatile SingularAttribute<Commento, String> Titolo;
    public static volatile SingularAttribute<Commento, Date> DataOra;
    public static volatile SingularAttribute<Commento, Long> id;

}