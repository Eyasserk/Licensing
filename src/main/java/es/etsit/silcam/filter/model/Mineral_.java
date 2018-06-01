package es.etsit.silcam.filter.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import es.etsit.silcam.entity.GrupoMineral;
import es.etsit.silcam.entity.Mineral;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Mineral.class)
public class Mineral_ {
	public static volatile SingularAttribute<Mineral, Long> id;
	public static volatile SingularAttribute<Mineral, String> nombre;
	public static volatile SingularAttribute<Mineral, GrupoMineral> grupo;
}
