package emlakburada.client;

import emlakburada.client.request.BannerRequest;
import emlakburada.client.response.BannerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "EmlakBurada-banner", url = "http://localhost:8081")
public interface BannerClient {

    @PostMapping("/banners")
    ResponseEntity<BannerResponse> saveBanner(@RequestBody BannerRequest bannerRequest);

}
