/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.tpa.smartpet.dao.impl;

/**
 *
 * @author leonardo
 */
import br.uff.ic.tpa.smartpet.dao.DaoGenerico;
import br.uff.ic.tpa.smartpet.excecao.InfraestruturaException;
import br.uff.ic.tpa.smartpet.excecao.ObjetoNaoEncontradoException;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * A implementação de um DAO genérico para a JPA Uma implementação "typesafe"
 * dos métodos CRUD e dos métodos de busca.
 */
public class JPADaoGenerico<T, PK> implements DaoGenerico<T, PK> {

    private Class<T> tipo;

    @PersistenceContext
    protected EntityManager em;            // Para subclasses enchergarem

    public JPADaoGenerico(Class<T> tipo) {
        this.tipo = tipo;
    }

    public final T inclui(T o) {
        try {
            em.persist(o);
        } catch (RuntimeException e) {
            throw new InfraestruturaException(e);
        }

        return o;
    }

    public final void altera(T o) {
        try {
            em.merge(o);
        } catch (RuntimeException e) {
            throw new InfraestruturaException(e);
        }
    }

    public final void exclui(T o) {
        try {
            em.remove(o);
        } catch (RuntimeException e) {
            throw new InfraestruturaException(e);
        }
    }

    public final T getPorId(PK id) throws ObjetoNaoEncontradoException {
        T t = null;
        try {
            t = em.find(tipo, id);

            if (t == null) {
                throw new ObjetoNaoEncontradoException();
            }
        } catch (RuntimeException e) {
            throw new InfraestruturaException(e);
        }
        return t;
    }

    public final T getPorIdComLock(PK id) throws ObjetoNaoEncontradoException {
        T t = null;
        try {
            t = em.find(tipo, id, LockModeType.PESSIMISTIC_WRITE);

            if (t == null) {
                throw new ObjetoNaoEncontradoException();
            }
        } catch (RuntimeException e) {
            throw new InfraestruturaException(e);
        }

        return t;
    }

    @SuppressWarnings("unchecked")
    public final T busca(Method metodo, Object[] argumentos)
            throws ObjetoNaoEncontradoException {
        T t = null;
        try {
            String nomeDaBusca = getNomeDaBuscaPeloMetodo(metodo);
            Query namedQuery = em.createNamedQuery(nomeDaBusca);

            if (argumentos != null) {
                for (int i = 0; i < argumentos.length; i++) {
                    Object arg = argumentos[i];
                    namedQuery.setParameter(i + 1, arg);  // Parâmetros de buscas são 1-based.
                }
            }
            t = (T) namedQuery.getSingleResult();

            return t;
        } catch (NoResultException e) {
            throw new ObjetoNaoEncontradoException();
        } catch (RuntimeException e) {
            throw new InfraestruturaException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public final T buscaUltimoOuPrimeiro(Method metodo,
            Object[] argumentos)
            throws ObjetoNaoEncontradoException {
        T t = null;
        try {
            List<T> lista;
            String nomeDaBusca = getNomeDaBuscaPeloMetodo(metodo);
            Query namedQuery = em.createNamedQuery(nomeDaBusca);

            if (argumentos != null) {
                for (int i = 0; i < argumentos.length; i++) {
                    Object arg = argumentos[i];
                    namedQuery.setParameter(i + 1, arg);
                }
            }
            lista = namedQuery.getResultList();

            t = (lista.size() == 0) ? null : (T) lista.get(0);

            if (t == null) {
                throw new ObjetoNaoEncontradoException();
            }

            return t;
        } catch (RuntimeException e) {
            throw new InfraestruturaException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public final List<T> buscaLista(Method metodo,
            Object[] argumentos) {
        try {
            String nomeDaBusca = getNomeDaBuscaPeloMetodo(metodo);
            Query namedQuery = em.createNamedQuery(nomeDaBusca);

            if (argumentos != null) {
                for (int i = 0; i < argumentos.length; i++) {
                    Object arg = argumentos[i];
                    namedQuery.setParameter(i + 1, arg); // Parâmetros de buscas são 1-based.
                }
            }
            return (List<T>) namedQuery.getResultList();
        } catch (RuntimeException e) {
            throw new InfraestruturaException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public final Long buscaListaCount(Method metodo,
            Object[] argumentos) {
        try {
            String nomeDaBusca = getNomeDaBuscaPeloMetodo(metodo);
            Query namedQuery = em.createNamedQuery(nomeDaBusca);

            if (argumentos != null) {
                for (int i = 0; i < argumentos.length; i++) {
                    Object arg = argumentos[i];
                    namedQuery.setParameter(i + 1, arg); // Parâmetros de buscas são 1-based.
                }
            }
            return (Long) namedQuery.getSingleResult();
        } catch (RuntimeException e) {
            throw new InfraestruturaException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public final List<T> buscaPagina(Method metodo,
            Object[] argumentos) {
        try {
            String nomeDaBusca = getNomeDaBuscaPeloMetodo(metodo);

            Integer startPosition, length;
            if (argumentos[0] instanceof Integer && argumentos[1] instanceof Integer) {
                startPosition = (Integer) argumentos[0];
                length = (Integer) argumentos[1];
            } else {
                throw new NumberFormatException("Os parâmetros startPosition e length precisam ser do tipo inteiro");
            }

            Query namedQuery = em.createNamedQuery(nomeDaBusca)
                    .setFirstResult(startPosition)
                    .setMaxResults(length);

            if (argumentos != null) {
                for (int i = 2; i < argumentos.length; i++) {
                    Object arg = argumentos[i];
                    namedQuery.setParameter(i - 1, arg); // Parâmetros de buscas são 1-based.
                }
            }
            return (List<T>) namedQuery.getResultList();
        } catch (RuntimeException e) {
            throw new InfraestruturaException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public final Set<T> buscaConjunto(Method metodo,
            Object[] argumentos) {
        try {
            String nomeDaBusca = getNomeDaBuscaPeloMetodo(metodo);
            Query namedQuery = em.createNamedQuery(nomeDaBusca);

            if (argumentos != null) {
                for (int i = 0; i < argumentos.length; i++) {
                    Object arg = argumentos[i];
                    namedQuery.setParameter(i + 1, arg); // Parâmetros de buscas são 1-based.
                }
            }

            List<T> lista = namedQuery.getResultList();

            return new LinkedHashSet<T>(lista);
        } catch (RuntimeException e) {
            throw new InfraestruturaException(e);
        }
    }

    private String getNomeDaBuscaPeloMetodo(Method metodo) {
        return tipo.getSimpleName() + "." + metodo.getName();
    }

//    public final List<T> getPagina(Number inicio, Number tamanho) {
//        List<T> pagina = new ArrayList<>();
//        try {
//            
//        } catch (RuntimeException e) {
//
//        }
//        return pagina;
//    }
}
