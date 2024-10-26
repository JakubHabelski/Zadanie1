package xCodeSoftware.Zadanie1.Classes;


import jakarta.persistence.criteria.Root;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class RootRate  {

    public String table;
    public String currency;
    public String code;
    public ArrayList<Rate> rates;

}
