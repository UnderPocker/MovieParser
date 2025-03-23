package com.akr.course.movieparser;

import com.akr.course.movieparser.site_entities.MovieSuggestions;
import com.akr.course.movieparser.site_entities.QueryResponse;
import com.akr.course.movieparser.site_entities.SearchResponse;
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

    public static QueryResponse getQueryResponse(String movieId, int page){
        // Создаём экземпляр RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // URL API
        String url = "https://api.flim.ai/2.0.0/search";

        // Заголовки запроса
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
        String requestBody = """
            {
              "search": {
                "saved_images": false,
                "full_text": "",
                "similar_picture_id": "",
                "movie_id": "%s",
                "dop": "",
                "director": "",
                "brand": "",
                "agency": "",
                "production_company": "",
                "actor": "",
                "creator": "",
                "artist": "",
                "collection_id": "",
                "board_id": "",
                "filters": {
                  "genres": [],
                  "colors": [],
                  "number_of_persons": [],
                  "years": [],
                  "shot_types": [],
                  "movie_types": [],
                  "aspect_ratio": [],
                  "safety_content": [],
                  "has_video_cuts": false,
                  "camera_motions": []
                },
                "negative_filters": {
                  "aspect_ratio": [],
                  "genres": [],
                  "movie_types": [],
                  "colors": [],
                  "shot_types": [],
                  "number_of_persons": [],
                  "years": [],
                  "safety_content": ["nudity", "violence"]
                }
              },
              "page": %d,
              "sort_by": "",
              "order_by": "",
              "number_per_pages": 200
            }
            """;

        // Создаём объект HttpEntity с заголовками и телом запроса
        HttpEntity<String> requestEntity = new HttpEntity<>(String.format(requestBody, movieId, page), headers);

        // Выполняем запрос
        ResponseEntity<SearchResponse> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, SearchResponse.class);
        SearchResponse body = response.getBody();
        return body.getQueryResponse();
    }



}
