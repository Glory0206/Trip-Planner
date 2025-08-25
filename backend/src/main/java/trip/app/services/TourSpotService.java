package trip.app.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import trip.app.dto.DaejeonTourSpotApiResponse;
import trip.app.entity.TourSpot;
import trip.app.repository.TourSpotRepository;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TourSpotService {

    private final TourSpotRepository tourSpotRepository;
    private final RestTemplate restTemplate;

    // application.yml을 통해 주입된 API 키
    @Value("${api.serviceKey}")
    private String serviceKey;

    @Transactional
    public void fetchAndSaveTourSpots() {
        // 1. 파라미터를 제외한 기본 URL
        String baseUrl = "https://apis.data.go.kr/6300000/openapi2022/tourspot/gettourspot";

        // 2. UriComponentsBuilder로 파라미터를 동적으로 추가
        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .queryParam("serviceKey", serviceKey) // 주입받은 키 사용
                .queryParam("numOfRows", "150")       // 전체 데이터를 한번에 가져오기 위해 넉넉하게 설정
                .queryParam("pageNo", "1")
                .queryParam("type", "json")           // DTO가 JSON을 처리하도록 설계되었으므로 필수
                .build(true)                          // URL 인코딩 처리
                .toUri();

        // 생성된 최종 URL을 확인하기 위한 로그
        System.out.println("Request URL: " + uri);

        // 3. API 호출
        DaejeonTourSpotApiResponse response = restTemplate.getForObject(uri, DaejeonTourSpotApiResponse.class);

        // 4. 데이터 변환 및 저장
        if (response != null && response.getResponse().getBody() != null) {
            List<DaejeonTourSpotApiResponse.Item> items = response.getResponse().getBody().getItems();

            for (DaejeonTourSpotApiResponse.Item item : items) {
                // DB에 해당 이름의 관광지가 이미 있는지 확인
                if (!tourSpotRepository.existsByName(item.getTourspotNm())) {
                    TourSpot tourSpot = TourSpot.from(item);
                    tourSpotRepository.save(tourSpot);
                }
            }
        }
    }
}