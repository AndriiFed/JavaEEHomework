package com;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;


@Repository("authorDao")
public class AuthorDao {
  private EntityManager entityManager = Persistence.
      createEntityManagerFactory("NewPersistenceUnit").
      createEntityManager();

  public List getAllAuthors() {
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    Query query = entityManager.createQuery("select u from AuthorEntity u");
    List bookList = query.getResultList();
    transaction.commit();
    return bookList;
  }

}
