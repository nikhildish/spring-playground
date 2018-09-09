package com.example.demo.crud;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson, Long> {

    Lesson findByTitle(String title);

    String sql = "SELECT * FROM lessons WHERE deliveredon BETWEEN :date1 AND :date2";
    @Query(value = sql, nativeQuery = true)
    List<Lesson> findLessonsBetweenDates(@Param("date1") Date date1, @Param("date2") Date date2);
}
