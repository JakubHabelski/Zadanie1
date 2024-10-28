package xCodeSoftware.Zadanie1;


import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import xCodeSoftware.Zadanie1.Classes.RateRequest;
import xCodeSoftware.Zadanie1.Classes.RootRate;
import xCodeSoftware.Zadanie1.Classes.request;
import xCodeSoftware.Zadanie1.Repo.RequestsRepo;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/currencies")
public class RateAPI {

    private final String BaseURL = "https://api.nbp.pl/api/exchangerates/rates/A/";
    private final String EndURL = "?format=json";


    private final WebClient.Builder webClient;
    private final RequestsRepo requestsRepo;

    public RateAPI( WebClient.Builder webClient, RequestsRepo requestsRepo) {
        this.webClient = webClient;
        this.requestsRepo = requestsRepo;
    }

    @PostMapping("/get-current-currency-value-command")
    public ResponseEntity<Map<String, String>> getRate(@RequestBody RateRequest request){
        String finalURl = BaseURL+request.getCurrency()+EndURL;
        try{
            RootRate rootRate = webClient.build()
                    .get()
                    .uri(finalURl)
                    .retrieve()
                    .bodyToMono(RootRate.class)
                    .block();
            Double currencyValue = rootRate.getRates().getFirst().getMid();
            requestsRepo.save(new request(rootRate.getCurrency(), request.getName(), getCurrentTime(), rootRate.getRates().getFirst().getMid()));
            return ResponseEntity.ok(Map.of("Currency", currencyValue.toString()));
        } catch (WebClientResponseException exception){
            return ResponseEntity.ok(Map.of("Error", "No currency found"));
        }

    }

    @GetMapping("/requests")
    public ResponseEntity<ArrayList<request>> getRequests(){
        ArrayList<request> all_requests = (ArrayList<request>) requestsRepo.findAll();
        return ResponseEntity.ok(all_requests);
    }
    public Date getCurrentTime(){
        Instant now = Instant.now();
        String isoDate = DateTimeFormatter.ISO_INSTANT.format(now);
        Instant instant = Instant.parse(isoDate);
        return  Date.from(instant);
    }


}
