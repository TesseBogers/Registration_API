package com.example.clientapi.controller;


import com.example.clientapi.model.Visit;
import com.example.clientapi.repository.VisitRepository;
//import com.example.clientapi.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class VisitController {

    @Autowired
    VisitRepository visitRepository;

    @GetMapping("/visits")
    public ResponseEntity<List<Visit>> getAllVisits(@RequestParam(required = false) String name) {
        try {
            List<Visit> visits = new ArrayList<Visit>();

            if (name == null)
                visitRepository.findAll().forEach(visits::add);
            else
                visitRepository.findByNameContaining(name).forEach(visits::add);

            if (visits.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(visits, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/visits/{id}")
    public ResponseEntity<Visit> getVisitById(@PathVariable("id") long id) {
        //return visitService.getDataByID(id);

        Optional<Visit> visitData = visitRepository.findById(id);

        if (visitData.isPresent()) {
            return new ResponseEntity<>(visitData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/visits")
    public ResponseEntity<Visit> createVisit(@RequestBody Visit visit) {
        try {
            Visit _visit = visitRepository
                    .save(new Visit(visit.getName(), visit.getLastName(), false));
            return new ResponseEntity<>(_visit, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/visits/{id}")
    public ResponseEntity<Visit> updateVisit(@PathVariable("id") long id, @RequestBody Visit visit) {
        Optional<Visit> visitData = visitRepository.findById(id);

        if (visitData.isPresent()) {
            Visit _visit = visitData.get();
            _visit.setName(visit.getName());
            _visit.setLastName(visit.getLastName());
            _visit.setVisitsRole(visit.isVisitsRole());
            return new ResponseEntity<>(visitRepository.save(_visit), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/visits/{id}")
    public ResponseEntity<HttpStatus> deleteVisit(@PathVariable("id") long id) {
        try {
            visitRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/visits")
    public ResponseEntity<HttpStatus> deleteAllvisits() {
        try {
            visitRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/visits/registrations")
    public ResponseEntity<List<Visit>> findByPublished() {
        try {
            List<Visit> visits = visitRepository.findByVisitsRole(true);

            if (visits.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(visits, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/visits/date/{date}")
    public ResponseEntity<List<Visit>> getDataByDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Visit> visitData = visitRepository.findAllByTimeStamp(date);


        if (!visitData.isEmpty()) {
            return new ResponseEntity<>(visitData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/visits/date")
    //e.g URL: http://localhost:8080/api/visits/date?start=2023-05-08&end=2023-05-09
    public ResponseEntity<List<Visit>> getDataBetweenDates(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Visit> visitData = visitRepository.findAllByTimeStampBetween(startDate, endDate);

        if (!visitData.isEmpty()) {
            return new ResponseEntity<>(visitData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

