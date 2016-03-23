package br.uff.ic.tpa.smartpet.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Especie.class)
public abstract class Especie_ {

	public static volatile SingularAttribute<Especie, String> nome;
	public static volatile SingularAttribute<Especie, Integer> idEspecie;
	public static volatile ListAttribute<Especie, Paciente> pacienteList;

}

