package com.sim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sim.model.Book;


public interface BookRepository  extends JpaRepository<Book, Long>{
}
