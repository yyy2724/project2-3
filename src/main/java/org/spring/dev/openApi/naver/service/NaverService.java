package org.spring.dev.openApi.naver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.spring.dev.openApi.naver.dto.NaverTokenDto;
import org.spring.dev.openApi.naver.dto.OrgResponse;
import org.spring.dev.openApi.naver.entity.NaverTokenEntity;
import org.spring.dev.openApi.naver.repository.NaverRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class NaverService {

    private final NaverRepository naverRepository;

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
                .queryParam("redirect_uri", "http://localhost:8023/naver/token") //code를 받아올 return url
                .queryParam("client_id", CLIENT_ID)
                .queryParam("response_type", "code") // code로 지정 값
                .queryParam("scope", "directory,directory.read,orgunit,orgunit.read,user,user.read") //사용할 권환들
                .queryParam("state", "CSRF") //
                .encode()
                .build()
                .toUri();

        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

        if (result.getStatusCode() == HttpStatus.OK) {
            try {
                naverAuthHtml = result.getBody();
                jsonStr = objectMapper.writeValueAsString(result.getBody());
                System.out.println(jsonStr);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("json JsonProcessingException : " + e);
            }
        } else {
            System.out.println(result.getStatusCode());
        }


        return naverAuthHtml;
    }

    public String getNaverToken(String code, String errorCode, String state) {

        RestTemplate restTemplate = new RestTemplate();
        String accessToken = "토큰 발급이 안되었습니다";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        URI uri = UriComponentsBuilder
                .fromUriString("https://auth.worksmobile.com")
                .path("/oauth2/v2.0/token")
                .queryParam("code", code)
                .queryParam("client_id", CLIENT_ID)
                .queryParam("grant_type", "authorization_code") //
                .queryParam("client_secret", CLIENT_SECRET) //사용할 권환들
                .encode()
                .build()
                .toUri();

        ResponseEntity<NaverTokenDto> result = restTemplate.exchange(uri, HttpMethod.POST, entity, NaverTokenDto.class);

        if (result.getStatusCode() == HttpStatus.OK) {

            accessToken = naverRepository.save(NaverTokenEntity.toEntity(result.getBody())).getAccess_token();

        } else {
            System.out.println(result.getStatusCode());
        }

        return accessToken;
    }

    public OrgResponse getOrgUnitList() {

        //db에 저장한 가장 최신 토큰을 가져온다.
        NaverTokenEntity naverTokenEntity =naverRepository.findFirstByOrderByIdDesc();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+naverTokenEntity.getAccess_token());
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        URI uri = UriComponentsBuilder
                .fromUriString("https://www.worksapis.com")
                .path("/v1.0/orgunits")
                .encode()
                .build()
                .toUri();

        ResponseEntity<OrgResponse> result = restTemplate.exchange(uri, HttpMethod.GET, entity, OrgResponse.class);

        if (result.getStatusCode() == HttpStatus.OK) {

            System.out.println("정상적으로 실행");

        } else {
            System.out.println(result.getStatusCode());
        }

        return result.getBody();
    }
}
