package xCodeSoftware.Zadanie1;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;
import xCodeSoftware.Zadanie1.Classes.RootRate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@SpringBootApplication
public class Zadanie1Application {

	public static void main(String[] args) {
		SpringApplication.run(Zadanie1Application.class, args);

		String url = "https://api.nbp.pl/api/exchangerates/rates/A/THB?format=json";
		Gson gson = new Gson();


		WebClient.Builder client = WebClient.builder();

		RootRate currency =
				client.build()
						.get()
						.uri(url)
						.retrieve()
						.bodyToMono(RootRate.class)
						.block();

		System.out.println(currency.getRates().getFirst().getMid());
		//RootRate rootRate = gson.fromJson(currency, xCodeSoftware.Zadanie1.Classes.RootRate.class);
		//System.out.println(rootRate.getRates().getFirst().getMid());
		LocalDate localDate = LocalDate.now();
		System.out.println(localDate);
		// Aktualna data i czas w UTC
		Instant now = Instant.now();

		// Formatowanie daty w ISO 8601
		String isoDate = DateTimeFormatter.ISO_INSTANT.format(now);

		System.out.println("Data w formacie ISO 8601: " + isoDate);
	}

}
