package com.example.questionservice.controller;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import com.example.questionservice.model.Question;
import org.springframework.web.bind.annotation.*;
import com.example.questionservice.service.QuestionService;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService service;

    public QuestionController(QuestionService service) {
        this.service = service;
    }

    @GetMapping("/random")
    public Question getRandom() {
        return service.getRandom();
    }

    @GetMapping
    public List<Question> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Question create(@RequestBody @Valid Question question) {
        return service.save(question);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    @GetMapping("/type")
    public List<Question> byType(@RequestParam String type) {
        return service.findByType(type);
    }

    // Import automatique au démarrage
    @PostConstruct
    public void init() {
        service.importFromOpenTDB();
    }
}
