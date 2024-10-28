package xCodeSoftware.Zadanie1;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import xCodeSoftware.Zadanie1.Classes.RateRequest;
import xCodeSoftware.Zadanie1.Repo.RequestsRepo;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RestApiTest {
    private final String BaseURL = "https://api.nbp.pl/api/exchangerates/rates/A/";
    private final String EndURL = "?format=json";

    @Mock
    private final WebClient.Builder webClient;
    @Mock
    private final RequestsRepo requestsRepo;


    public RestApiTest(WebClient.Builder webClient, RequestsRepo requestsRepo) {
        this.webClient = webClient;
        this.requestsRepo = requestsRepo;
    }

    @Test
    void contextLoads() {
    }
    @Test
    void f1(){
        //given
        RateRequest request = new RateRequest("EUR", "Jan Nowak");
        RateAPI rateAPI = new RateAPI(webClient, requestsRepo);
        Map.of("Currency", 4.3533);
        //when
        ResponseEntity<Map<String, String>> getRate =  rateAPI.getRate(request);
        //then
        assertEquals(getRate, Map.of("Currency", 4.3533));
    }
}
