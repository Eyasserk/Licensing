package es.etsit.silcam.filter.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import es.etsit.silcam.entity.Pais;
import es.etsit.silcam.entity.PersonaFisica;
import es.etsit.silcam.entity.Provincia;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PersonaFisica.class)
public class PersonaFisica_ {

	public static volatile SingularAttribute<PersonaFisica, Long> id;
	public static volatile SingularAttribute<PersonaFisica, String> nombre;
	public static volatile SingularAttribute<PersonaFisica, String> apellido1;
	public static volatile SingularAttribute<PersonaFisica, String> apellido2;
	public static volatile SingularAttribute<PersonaFisica, String> numeroIdentificacion;
	public static volatile SingularAttribute<PersonaFisica, Pais> nacionalidad;
	public static volatile SingularAttribute<PersonaFisica, Date> fechaNacimiento;
	public static volatile SingularAttribute<PersonaFisica, Provincia> provinciaResidencia;
	public static volatile SingularAttribute<PersonaFisica, Pais> paisResidencia;
	public static volatile SingularAttribute<PersonaFisica, Boolean> activo;
	
}
