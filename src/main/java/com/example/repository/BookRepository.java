package com.example.repository;

import com.example.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Page<Book> findAll(Pageable pageable);

    Page<Book> findAll(Specification<Book> specification, Pageable pageable);
}
