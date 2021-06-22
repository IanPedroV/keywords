package br.com.fiap.startup.keywords.controller;


import br.com.fiap.startup.keywords.service.KeywordsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/keywords")
public class KeywordsController {
    private final KeywordsService keywordsService;

    public KeywordsController(KeywordsService keywordsService) {
        this.keywordsService = keywordsService;
    }

    @GetMapping
    public List<String> getKeywords(String text) {
        return keywordsService.getKeywords(text);
    }
}
