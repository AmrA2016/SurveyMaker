package Entities;

import Entities.user;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-02T22:54:01")
@StaticMetamodel(survey.class)
public class survey_ { 

    public static volatile SingularAttribute<survey, Long> ID;
    public static volatile SingularAttribute<survey, String> title;
    public static volatile SingularAttribute<survey, String> creationDate;
    public static volatile SingularAttribute<survey, user> user;
    public static volatile SingularAttribute<survey, String> url;
    public static volatile SingularAttribute<survey, Integer> suspended;

}