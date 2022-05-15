package com.meital.booksweb.repo;

import com.meital.booksweb.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository <Book, Integer> {

    List<Book> getAllByYearBetween(int startYear, int endYear);


    @Query(value = "SELECT avg(year) FROM Book")
    public Double avg();


}
