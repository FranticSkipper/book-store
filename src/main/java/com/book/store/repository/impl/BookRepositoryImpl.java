package com.book.store.repository.impl;

import com.book.store.model.Book;
import com.book.store.repository.BookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl implements BookRepository {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Book save(Book book) {
        try (EntityManager entityManager = this.entityManagerFactory.createEntityManager()) {
            EntityTransaction entityTransaction = entityManager.getTransaction();

            try {
                entityTransaction.begin();
                entityManager.persist(book);
                entityTransaction.commit();

                return book;
            } catch (Exception ex) {
                if (entityTransaction != null) {
                    entityTransaction.rollback();
                }

                throw ex;
            }
        } catch (Exception ex) {
            throw new RuntimeException("Can't save new book. Params: book=" + book, ex);
        }
    }

    @Override
    public List<Book> findAll() {
        try (EntityManager entityManager = this.entityManagerFactory.createEntityManager()) {
            return entityManager.createQuery("FROM Book b",
                            Book.class)
                    .getResultList();
        } catch (Exception ex) {
            throw new RuntimeException("Can't find all books", ex);
        }
    }
}
