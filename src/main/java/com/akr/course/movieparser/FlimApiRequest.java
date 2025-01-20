package com.akr.course.movieparser;

import com.akr.course.movieparser.site_entities.MovieSuggestions;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class FlimApiRequest {
    public static MovieSuggestions getMovieSuggestions(String movieName) {
        // Создание RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // URL API
        String url = "https://api.flim.ai/2.0.0/suggest-entities";

        // Установка заголовков
        HttpHeaders headers = new HttpHeaders();
        headers.add("accept", "*/*");
        headers.add("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");
        headers.add("content-type", "application/json");
        headers.add("origin", "https://app.flim.ai");
        headers.add("priority", "u=1, i");
        headers.add("referer", "https://app.flim.ai/");
        headers.add("sec-ch-ua", "\"Google Chrome\";v=\"131\", \"Chromium\";v=\"131\", \"Not_A Brand\";v=\"24\"");
        headers.add("sec-ch-ua-mobile", "?0");
        headers.add("sec-ch-ua-platform", "\"Windows\"");
        headers.add("sec-fetch-dest", "empty");
        headers.add("sec-fetch-mode", "cors");
        headers.add("sec-fetch-site", "same-site");
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36");

        // Тело запроса
        String requestBody = "{\"media_type\":\"\",\"suggest\":\"" + movieName + "\"}";

        // Объединение заголовков и тела
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Отправка запроса
        ResponseEntity<MovieSuggestions> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, MovieSuggestions.class);
        return response.getBody();
    }
}
