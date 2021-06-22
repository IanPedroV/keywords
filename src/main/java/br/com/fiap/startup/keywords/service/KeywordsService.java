package br.com.fiap.startup.keywords.service;


import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KeywordsService {
    public List<String> getKeywords(String text) {
        return TFIDFCalculator.getKeywords(text);
    }
}
