package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor

public class Baggage {


    private int flightId;
    private int idBag;
    private int weightBag;
    private String weightUnBag;
    private int piecesBag;
}
