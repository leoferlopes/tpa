package br.uff.ic.tpa.smartpet.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Consulta.class)
public abstract class Consulta_ {

	public static volatile SingularAttribute<Consulta, Integer> preco;
	public static volatile SingularAttribute<Consulta, Veterinario> veterinario;
	public static volatile SingularAttribute<Consulta, String> receita;
	public static volatile SingularAttribute<Consulta, Paciente> paciente;
	public static volatile SingularAttribute<Consulta, Integer> codigoConsulta;
	public static volatile SingularAttribute<Consulta, Date> dataHora;

}

