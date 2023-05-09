package com.example.clientapi.repository;

import com.example.clientapi.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> findByVisitsRole(boolean visitsRole);
    List<Visit> findByNameContaining(String name);
    List<Visit> findAllByTimeStamp(LocalDate publicationDate);
    List<Visit> findAllByTimeStampBetween(LocalDate publicationTimeStart, LocalDate publicationTimeEnd);
}
