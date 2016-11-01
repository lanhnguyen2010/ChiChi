package com.chichi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static com.chichi.util.Constants.*;

/**
 * @author lanhnguyen on 27/10/2016.
 */
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "JOBS")
public class Job implements Serializable {

    private static final long serialVersionUID = -1283678438406162057L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION", columnDefinition = "text")
    private String description;

    @Column(name = "TOTAL_HOURS")
    private long totalHours;

    @Column(name = "START_TIME")
    @JsonFormat(pattern = DATE_FORMAT)
    private Date startTime;

    @Column(name = "DEAD_LINE")
    @JsonFormat(pattern = DATE_FORMAT)
    private Date deadLine;

    @Column(name = "POSTED_BY")
    private long postBy;

    @Column(name = "POSTED_AT")
    @JsonFormat(pattern = DATETIME_FORMAT, timezone = HCM_TIMEZONE)
    private Date postAt;

    @Column(name = "LOGO", columnDefinition = "blob")
    private byte[] logo;

    @Column(name = "RESOURCES")
    private String resource;

    @Column(name = "PICKED_BY")
    private long pickedBy;

    @Column(name = "PICKED_AT")
    private Date pickedAt;

    @Column(name = "REVIEW_BY")
    private long reviewBy;

    @Column(name = "RESULT_HOURS")
    private long resultsHours;

    @Column(name = "REQUIRED_LEVEL")
    private String requiredLevel;

}
