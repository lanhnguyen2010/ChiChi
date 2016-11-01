package com.chichi.service.imp;

import com.chichi.domain.Job;
import com.chichi.repository.JobRepository;
import com.chichi.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author  lanhnguyen on 27/10/2016.
 */

@Service
@Transactional
public class JobServiceImp implements JobService {

    @Autowired
    JobRepository jobRepository;

    @Override
    public Job create(Job newJob) {
        return jobRepository.save(newJob);
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public List<Job> findPostedBy(Long id) {
        if (jobRepository.findPostedBy(id) != null){
            return jobRepository.findPostedBy(id);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Job pick(Long jobId, Long userId) {
        Job job = jobRepository.findOne(jobId);
        job.setPickedBy(userId);
        job.setPickedAt(new Date());
        return jobRepository.save(job);
    }

    @Override
    public List<Job> findPickedBy(Long id) {
        return null;
    }
}
