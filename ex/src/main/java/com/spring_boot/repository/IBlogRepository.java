package com.spring_boot.repository;

import com.spring_boot.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

public interface IBlogRepository extends JpaRepository<Blog, Integer> {
    @Query(value = " select * from blog where name like :name and note like :note ", nativeQuery = true)
    Page<Blog> findAllByNameAndAuthor(Pageable pageable, @Param("name") String name, @Param("note") String note);

}