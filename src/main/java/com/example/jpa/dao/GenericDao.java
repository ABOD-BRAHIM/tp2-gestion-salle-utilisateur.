package com.example.jpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;


public abstract class GenericDao<T> {

    private final Class<T> entityClass;
    protected  EntityManager em;

    public GenericDao(Class<T>  entityClass , EntityManager em) {
        this.entityClass = entityClass;
        this.em = em;
    }

    public void create(T entity) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(entity);
            transaction.commit();
        }catch (Exception e){
            if(transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    public T find(Object id) {
        return em.find(entityClass, id);
    }

    public T ubdate(T entity){
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            T merged = em.merge(entity);
            transaction.commit();
            return merged;
        }catch (Exception e){
            if(transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    public void remove(T entity){
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            transaction.commit();
        }catch (Exception e){
            if(transaction.isActive()) transaction.rollback();
            throw e;
        }
    }
    public List<T> findAll(){
        return em.createQuery("select e from " + entityClass.getSimpleName() + " e ", entityClass).getResultList();
    }
}
