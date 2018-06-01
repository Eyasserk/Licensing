package es.etsit.silcam.filter.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import es.etsit.silcam.entity.Pais;
import es.etsit.silcam.entity.PersonaFisica;
import es.etsit.silcam.entity.PersonaJuridica;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PersonaJuridica.class)
public class PersonaJuridica_ {

	public static volatile SingularAttribute<PersonaJuridica, Long> id;
	public static volatile SingularAttribute<PersonaJuridica, String> razonSocial;
	public static volatile SingularAttribute<PersonaJuridica, String> numeroIdentificacion;
	public static volatile SingularAttribute<PersonaJuridica, PersonaFisica> representante;
	public static volatile SingularAttribute<PersonaJuridica, Pais> paisFiscal;
	public static volatile SingularAttribute<PersonaJuridica, Pais> paisNacionalidad;
	public static volatile SingularAttribute<PersonaJuridica, Boolean> activo;
	
}
