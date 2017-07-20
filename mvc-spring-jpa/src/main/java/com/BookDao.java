package com;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;


@Repository("bookDao")
public class BookDao {
  private EntityManager entityManager = Persistence.
      createEntityManagerFactory("NewPersistenceUnit").
      createEntityManager();

  public List getAllBooks() {
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    Query query = entityManager.createQuery("select u from BookEntity u");
    List bookList = query.getResultList();
    transaction.commit();
    return bookList;
  }

}
