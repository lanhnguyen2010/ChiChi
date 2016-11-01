package com.chichi.service;

import com.chichi.domain.Job;

import java.util.List;

/**
 * @author  lanhnguyen on 27/10/2016.
 */
public interface JobService {

    Job create(Job newJob);
    List<Job> findAll();
    List<Job> findPostedBy(Long id);
    Job pick(Long jobId, Long userId);
    List<Job> findPickedBy(Long id);
}
