package br.uff.ic.tpa.smartpet.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Paciente.class)
public abstract class Paciente_ {

	public static volatile SingularAttribute<Paciente, Integer> idPaciente;
	public static volatile SingularAttribute<Paciente, Especie> especie;
	public static volatile SingularAttribute<Paciente, Dono> dono;
	public static volatile ListAttribute<Paciente, Consulta> consultaList;
	public static volatile SingularAttribute<Paciente, String> raca;
	public static volatile SingularAttribute<Paciente, String> nome;

}

