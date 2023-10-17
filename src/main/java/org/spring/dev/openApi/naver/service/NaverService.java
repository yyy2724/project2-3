package org.spring.dev.openApi.naver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class NaverService {


    @Value("${navar.api.client-id}")
    String CLIENT_ID;

    @Value("${navar.api.client-secret}")
    String CLIENT_SECRET;

    public String getNaverCode() {
        String naverAuthHtml = null;

        RestTemplate restTemplate = new RestTemplate();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = null;

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        URI uri = UriComponentsBuilder
                .fromUriString("https://auth.worksmobile.com")
                .path("/oauth2/v2.0/authorize")
                .queryParam("redirect_uri", "https://localhost:8023/api/naver/code") //code를 받아올 return url
                .queryParam("client_id", CLIENT_ID)
                .queryParam("response_type", "code") // code로 지정 값
                .queryParam("scope", "directory,directory.read,orgunit,orgunit.read,user,user.read") //사용할 권환들
                .queryParam("state", "CSRF") //
                .encode()
                .build()
                .toUri();

        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

        if (result.getStatusCode() == HttpStatus.OK){
            try {
                naverAuthHtml = result.getBody();
                jsonStr = objectMapper.writeValueAsString(result.getBody());
                System.out.println(jsonStr);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("json JsonProcessingException : " + e);
            }
        } else{
            System.out.println(result.getStatusCode());
        }


        return naverAuthHtml;
    }
}
