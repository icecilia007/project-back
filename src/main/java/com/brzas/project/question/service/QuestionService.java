package com.brzas.project.question.service;

import com.brzas.project.question.models.Question;

import java.util.List;

public interface QuestionService {
    Question getQuestionById(long id);
    List<Question> getAllQuestions();
    Question createQuestion(Question question);
    Question updateQuestion(Question question);
    Question deleteQuestion(long id);
}
