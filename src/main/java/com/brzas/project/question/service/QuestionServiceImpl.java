package com.brzas.project.question.service;

import com.brzas.project.question.models.Question;
import com.brzas.project.question.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl  implements QuestionService{

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question getQuestionById(long id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        return optionalQuestion.orElse(null);
    }
    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
    @Override
    public Question createQuestion(Question question) {
        validateEntry(question);
        return questionRepository.save(question);
    }
    private static void validateEntry(Question question) {
        if (question.getDescription().isEmpty() ||  question.getDescription().equals(" ") ) {
            throw new IllegalArgumentException("The request is not valid. Description are required.");
        }
    }
    @Override
    public Question updateQuestion(Question question){
        validateEntry(question);
        return questionRepository.save(question);
    }
    @Override
    public Question deleteQuestion(long id){
        Optional<Question> optionalQuestion = questionRepository.findById(id);

        if(optionalQuestion.isPresent()){
            Question question = optionalQuestion.get();
            questionRepository.deleteById(id);
            return question;
        }else{
            throw new IllegalArgumentException("Question not found");
        }
    }
}
