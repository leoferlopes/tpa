package br.uff.ic.tpa.smartpet.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Dono.class)
public abstract class Dono_ {

	public static volatile SingularAttribute<Dono, String> telefone;
	public static volatile SingularAttribute<Dono, String> endereco;
	public static volatile SingularAttribute<Dono, Integer> idDono;
	public static volatile SingularAttribute<Dono, String> nome;
	public static volatile SingularAttribute<Dono, Date> dataNascimento;
	public static volatile ListAttribute<Dono, Paciente> pacienteList;
	public static volatile SingularAttribute<Dono, String> email;

}

