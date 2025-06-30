package com.example.questionservice.service;

import com.example.questionservice.dto.OpenTDBResponse;
import com.example.questionservice.model.Question;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.questionservice.repository.QuestionRepository;
import com.example.questionservice.utils.OpenTDBConverter;

import java.util.List;
import java.util.Random;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository repo;

    public QuestionServiceImpl(QuestionRepository repo) {
        this.repo = repo;
    }

    @Override
    public Question getRandom() {
        List<Question> list = repo.findAll();
        return list.isEmpty() ? null : list.get(new Random().nextInt(list.size()));
    }

    @Override
    public List<Question> findAll() {
        return repo.findAll();
    }

    @Override
    public Question save(Question q) {
        return repo.save(q);
    }

    @Override
    public void delete(String id) {
        repo.deleteById(id);
    }

    @Override
    public List<Question> findByType(String type) {
        return repo.findByType(type);
    }

    @Override
    public void importFromOpenTDB() {
        if (!repo.findAll().isEmpty()) return;

        RestTemplate rest = new RestTemplate();
        String url = "https://opentdb.com/api.php?amount=50";
        OpenTDBResponse resp = rest.getForObject(url, OpenTDBResponse.class);

        if (resp != null && resp.getResults() != null) {
            List<Question> questions = OpenTDBConverter.convert(resp);
            repo.saveAll(questions);
        }
    }
}