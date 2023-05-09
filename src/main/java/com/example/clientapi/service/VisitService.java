//package com.example.clientapi.service;
//
//import com.example.clientapi.repository.VisitRepository;
//import org.springframework.stereotype.Service;
//import com.example.clientapi.model.Visit;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class VisitService {
//    VisitRepository visitRepository;
//    List<Visit> visitors = new ArrayList<>();
//
//    Visit visit = new Visit();
//
//    public Optional<Visit> getDataByID(long id) {
//        Optional<Visit> visitData = visitRepository.findById(id);
//
//
//        return Optional.of(visitData.get());
//    }
//}
