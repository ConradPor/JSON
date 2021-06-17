package entity;

import lombok.Data;
import lombok.AllArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor

public class Cargo {


    private int flightId;
    private int idCar;
    private int weightCar;
    private String weightUnCar;
    private int piecesCar;


}
