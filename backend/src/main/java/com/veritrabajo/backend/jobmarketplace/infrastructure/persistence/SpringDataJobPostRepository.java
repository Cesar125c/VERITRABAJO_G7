package com.veritrabajo.backend.jobmarketplace.infrastructure.persistence;

import com.veritrabajo.backend.jobmarketplace.infrastructure.persistence.entity.JobPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SpringDataJobPostRepository extends JpaRepository<JobPostEntity, UUID> {

    List<JobPostEntity> findBySelectedWorkerProfileIdIsNull();

    List<JobPostEntity> findByClientId(String clientId);

    @Query("""
            select distinct job
            from JobPostEntity job
            join job.applications application
            where application.workerProfileId = :workerProfileId
            """)
    List<JobPostEntity> findByApplicantProfileId(@Param("workerProfileId") String workerProfileId);
}
