package entity;

import entity.Country;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-08T23:25:20")
@StaticMetamodel(City.class)
public class City_ { 

    public static volatile SingularAttribute<City, Country> countryCode;
    public static volatile SingularAttribute<City, String> district;
    public static volatile SingularAttribute<City, String> name;
    public static volatile CollectionAttribute<City, Country> countryCollection;
    public static volatile SingularAttribute<City, Integer> id;
    public static volatile SingularAttribute<City, Integer> population;

}