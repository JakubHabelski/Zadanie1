package xCodeSoftware.Zadanie1;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import xCodeSoftware.Zadanie1.Classes.RateRequest;
import xCodeSoftware.Zadanie1.Classes.request;
import xCodeSoftware.Zadanie1.Repo.RequestsRepo;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest

public class RestApiTest {
    private final String BaseURL = "https://api.nbp.pl/api/exchangerates/rates/A/";
    private final String EndURL = "?format=json";


    @MockBean
    private final WebClient.Builder builder;
    @Mock
    private final WebClient webClient;
    @Autowired
    private final RequestsRepo requestsRepo;





    public RestApiTest(WebClient.Builder builder, WebClient webClient, RequestsRepo requestsRepo) {
        this.builder = builder;
        this.webClient = webClient;
        this.requestsRepo = requestsRepo;
    }


    @Test
    void f1(){
        //given
        RateRequest request = new RateRequest("EUR", "Jan Nowak");
        RateAPI rateAPI = new RateAPI(builder, requestsRepo);
        Map.of("Currency", 4.3533);
        //when
        ResponseEntity<Map<String, String>> getRate =  rateAPI.getRate(request);
        //then
        assertEquals(getRate, Map.of("Currency", 4.3533));
    }

    @Test
    void get_all_requests_from_db(){
        //given
        RateAPI rateAPI = new RateAPI(builder, requestsRepo);
        //when
       ArrayList<request> RequestsArray = rateAPI.getRequests().getBody();
        //then
        assertEquals(RequestsArray, requestsRepo.findAll());
    }
}
