package com.brzas.project.alternative.controller;

import com.brzas.project.alternative.models.Alternative;
import com.brzas.project.alternative.models.AlternativeDTO;
import com.brzas.project.alternative.service.AlternativeService;
import com.brzas.project.helpers.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/alternatives")
public class AlternativeController {
    private final AlternativeService alternativeService;

    @Autowired
    public AlternativeController(AlternativeService alternativeService) {
        this.alternativeService = alternativeService;
    }
    @GetMapping("/")
    public ResponseEntity<Alternative> getAlternative( @RequestParam long id) {
        Alternative alternative = alternativeService.getAlternativeById(id);
        if (alternative != null) {
            return ResponseEntity.ok(alternative);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public List<Alternative> getAllAlternatives() {
        return alternativeService.getAllAlternatives();
    }
    @PostMapping
    public ResponseEntity<Object> createAlternative(@RequestBody AlternativeDTO alternativeDTO) {
        Alternative novaAlternative = new Alternative();
        novaAlternative.setDescription(alternativeDTO.getDescription());
        novaAlternative.setOption(alternativeDTO.getOption());
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(alternativeService.createAlternative(novaAlternative));
        } catch (IllegalArgumentException ex) {
            ErrorResponse errorResponse =  new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(),"/api/v1/alternatives");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
    @PutMapping("/")
    public ResponseEntity<Object> updateAlternative(@RequestParam("id") long id, @RequestBody AlternativeDTO alternativeDTO) {
        Alternative alternative = alternativeService.getAlternativeById(id);

        if (alternative == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            alternative.setDescription(alternativeDTO.getDescription());
            alternative.setOption(alternativeDTO.getOption());

            return ResponseEntity.ok().body(alternativeService.updateAlternative(alternative));
        } catch (IllegalArgumentException ex) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), "/api/v1/alternatives/" + id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
    @DeleteMapping("/")
    public ResponseEntity<Object> deleteAlternative(@RequestParam("id") long id) {
        try {
            Alternative deletedAlternative = alternativeService.deleteAlternative(id);
            return ResponseEntity.ok(deletedAlternative);
        } catch (IllegalArgumentException ex) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), "/api/v1/alternatives/" + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

}
