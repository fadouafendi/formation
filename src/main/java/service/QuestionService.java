package service;

import model.Question;

import java.util.List;

public interface QuestionService {
    Question getRandom();
    List<Question> findAll();
    Question save(Question q);
    void delete(String id);
    List<Question> findByType(String type);
    void importFromOpenTDB();
}