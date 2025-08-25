package trip.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DaejeonTourSpotApiResponse {
    private Response response;

    @Getter
    @NoArgsConstructor
    public static class Response {
        private Header header;
        private Body body;
    }

    @Getter
    @NoArgsConstructor
    public static class Header {
        private String resultCode;
        private String resultMsg;
    }

    @Getter
    @NoArgsConstructor
    public static class Body {
        private int totalCount;
        private List<Item> items;
    }

    @Getter
    @NoArgsConstructor
    public static class Item {
        private String tourspotNm;
        private String tourspotZip;
        private String tourspotAddr;
        private String tourspotDtlAddr;
        private String refadNo;
        private String mngTime;
        private String tourUtlzAmt;
        private String pkgFclt;
        private String cnvenFcltGuid;
        private String urlAddr;
        private String tourspotSumm;

        // JSON 필드명(mapLat)과 Java 변수명(latitude)이 다를 경우 매핑
        @JsonProperty("mapLat")
        private String latitude;

        @JsonProperty("mapLot")
        private String longitude;
    }
}