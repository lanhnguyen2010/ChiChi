package com.chichi.repository;

import com.chichi.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author  lanhnguyen on 27/10/2016.
 */
@Repository
public interface JobRepository extends JpaRepository<Job, Long>{
    @Query("select j from Job j where j.postBy=?1")
    List<Job> findPostedBy(long id);

    @Query("select j from Job j where j.pickedBy=?1")
    List<Job> findPickedBy(long id);
}
