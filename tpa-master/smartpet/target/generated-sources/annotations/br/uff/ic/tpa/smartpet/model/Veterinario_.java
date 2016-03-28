package br.uff.ic.tpa.smartpet.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Veterinario.class)
public abstract class Veterinario_ {

	public static volatile ListAttribute<Veterinario, Consulta> consultaList;
	public static volatile SingularAttribute<Veterinario, String> especializacao;
	public static volatile SingularAttribute<Veterinario, String> nome;
	public static volatile SingularAttribute<Veterinario, Integer> idVeterinario;

}

