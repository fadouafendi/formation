package com.example.questionservice.utils;

import com.example.questionservice.dto.OpenTDBResponse;
import com.example.questionservice.model.Question;

import java.util.List;

public class OpenTDBConverter {
    public static List<Question> convert(OpenTDBResponse response) {
        return response.getResults().stream().map(q -> {
            Question question = new Question();
            question.setType(q.getType());
            question.setQuestion(q.getQuestion());
            question.setCorrectAnswer(q.getCorrect_answer());
            question.setIncorrectAnswers(q.getIncorrect_answers());
            return question;
        }).toList();
    }
}