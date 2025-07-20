package com.example.javaservice09.service;


import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LogService {
    private static final String LOG_FILE_PATH = "logs/info.log";
    private static final Pattern SEARCH_PATTERN = Pattern.compile("Search keyword: (.+)");

    public Map<String, Integer> getSearchKeywordStats() {
        Map<String, Integer> keywordStats = new HashMap<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(LOG_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher matcher = SEARCH_PATTERN.matcher(line);
                if (matcher.find()) {
                    String keyword = matcher.group(1).trim().toLowerCase();
                    keywordStats.put(keyword, keywordStats.getOrDefault(keyword, 0) + 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return keywordStats;
    }

    public Set<String> getAllSearchKeywords() {
        Set<String> keywords = new HashSet<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(LOG_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher matcher = SEARCH_PATTERN.matcher(line);
                if (matcher.find()) {
                    keywords.add(matcher.group(1).trim().toLowerCase());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keywords;
    }
}