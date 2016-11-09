package com.chichi.resource;

import com.chichi.domain.Job;
import com.chichi.domain.User;
import com.chichi.service.JobService;
import com.chichi.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lanhnguyen on 27/10/2016.
 */

@RestController
@RequestMapping(value = "/api/job")
public class JobController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobController.class);

    @Autowired
    JobService jobService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity create(@RequestParam("title") String title,
                                 @RequestParam("totalHours") int totalHours,
                                 @RequestParam("description") String description,
                                 @RequestParam("startTime") Date startTime,
                                 @RequestParam("deadLine") Date deadLine,
                                 @RequestParam("requiredLevel") String requiredLevel,
                                 @RequestParam("file") MultipartFile file) {
        try {

            User user = SecurityUtil.getCurrentUser();

            Job newJob = new Job();
            newJob.setDescription(description);
            if (!StringUtils.isEmpty(deadLine)) {
                newJob.setDeadLine(deadLine);
            }
            if (!StringUtils.isEmpty(startTime)) {
                newJob.setStartTime(startTime);
            }
            if (user != null) {
                newJob.setPostBy(user.getId());
            }
            newJob.setPostAt(new Date());
            newJob.setTitle(title);
            newJob.setTotalHours(totalHours);
            newJob.setRequiredLevel(requiredLevel);
            if (file != null) {
                newJob.setLogo(file.getBytes());
            }
            return ResponseEntity.ok(jobService.create(newJob));
        } catch (IOException e) {
            LOGGER.warn(e.getMessage(), e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity getAll() {
        List<Job> jobs = jobService.findAll();
        if (jobs.isEmpty()) {
            return ResponseEntity.ok(new ArrayList<>());
        } else {
            return ResponseEntity.ok(jobs);
        }
    }

    @RequestMapping(value = "/getPostedBy", method = RequestMethod.GET)
    public ResponseEntity getPostedBy(@RequestParam("userId") Long id) {
        return ResponseEntity.ok(jobService.findPostedBy(id));
    }

    @RequestMapping(value = "/pick", method = RequestMethod.GET)
    public ResponseEntity pick(@RequestParam("jobId") Long jobId) {
        User user = SecurityUtil.getCurrentUser();
        if (user == null){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(jobService.pick(jobId, user.getId()));
    }

    @RequestMapping(value = "/getPickedBy", method = RequestMethod.GET)
    public ResponseEntity getPickBy(@RequestParam("userId") Long id) {
        return ResponseEntity.ok(jobService.findPickedBy(id));
    }
}
