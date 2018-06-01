package es.etsit.silcam.filter.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import es.etsit.silcam.entity.Pais;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pais.class)
public class Pais_ {
	
	public static volatile SingularAttribute<Pais, Long> id;
	public static volatile SingularAttribute<Pais, Integer> codigo;
	public static volatile SingularAttribute<Pais, String> iso;
	public static volatile SingularAttribute<Pais, String> nombre;

}
