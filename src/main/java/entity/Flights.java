package entity;

import lombok.Data;
import lombok.AllArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor


public class Flights {

    private int flightId;
    private int flightNo;
    private String depAir;
    private String arrAir;
    private String depDate;

}

