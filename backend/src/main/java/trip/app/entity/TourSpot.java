package trip.app.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import trip.app.dto.DaejeonTourSpotApiResponse;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TourSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name; // 관광지 이름

    @Column(length = 1000)
    private String summary; // 관광지 요약

    private String address; // 주소
    
    @Column(nullable = false)
    private String latitude; // 위도
    
    @Column(nullable = false)
    private String longitude; // 경도
    
    private String operatingTime; // 운영 시간
    
    private String fee; // 이용 요금
    
    private String phoneNumber; // 연락처
    
    private String homepage; // 홈페이지 주소
    
    private String parking; // 주차 공간

    public static TourSpot from(DaejeonTourSpotApiResponse.Item itemDto){
        TourSpot tourSpot = new TourSpot();

        tourSpot.name = itemDto.getTourspotNm();
        tourSpot.summary = itemDto.getTourspotSumm();
        tourSpot.address = itemDto.getTourspotDtlAddr();
        tourSpot.latitude = itemDto.getLatitude();
        tourSpot.longitude = itemDto.getLongitude();
        tourSpot.operatingTime = itemDto.getMngTime();
        tourSpot.fee = itemDto.getTourUtlzAmt();
        tourSpot.phoneNumber = itemDto.getRefadNo();
        tourSpot.homepage = itemDto.getUrlAddr();
        tourSpot.parking = itemDto.getPkgFclt();

        return tourSpot;
    }
}
