package xCodeSoftware.Zadanie1.Classes;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;



@Entity
@Data
public class request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String currency;
    private String name;
    private Date date;
    @Column(name = "\"value\"")
    private double value;


    public request(String currency, String name, Date date, double value) {
        this.currency = currency;
        this.name = name;
        this.date = date;
        this.value = value;
    }

    public request() {

    }
}
