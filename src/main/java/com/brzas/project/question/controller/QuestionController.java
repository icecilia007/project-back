package com.brzas.project.question.controller;

import com.brzas.project.helpers.ErrorResponse;
import com.brzas.project.question.models.Question;
import com.brzas.project.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/")
    public ResponseEntity<Question> getQuestion(@RequestParam long id) {
        Question question = questionService.getQuestionById(id);
        if (question != null) {
            return ResponseEntity.ok(question);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }
    @PostMapping
    public ResponseEntity<Object> createQuestion(@RequestBody Question question){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(questionService.createQuestion(question));
        }catch (IllegalArgumentException ex){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), "/api/v1/questions");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
    @PutMapping("/")
    public ResponseEntity<Object> updateAlternative(@RequestParam("id") long id, @RequestBody Question question) {
        Question questionUpdate = questionService.getQuestionById(id);

        if (questionUpdate == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            questionUpdate.setDescription(question.getDescription());
            return ResponseEntity.ok().body(questionService.updateQuestion(questionUpdate));
        } catch (IllegalArgumentException ex) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), "/api/v1/questions/" + id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
    @DeleteMapping("/")
    public ResponseEntity<Object> deleteQuestion(@RequestParam("id") long id){
        try{
            Question deletedQuestion = questionService.deleteQuestion(id);
            return ResponseEntity.ok(deletedQuestion);
        }catch (IllegalArgumentException ex){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), "/api/v1/questions/");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
