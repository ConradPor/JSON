package flights;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import entity.Baggage;
import entity.Cargo;
import entity.Flights;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReadAndCalc {

    private ArrayList<Flights> flights = new ArrayList<>();
    private ArrayList<Baggage> baggages = new ArrayList<>();
    private ArrayList<Cargo> cargos = new ArrayList<>();


    public void readFile() throws Exception {

        String file = "E:/Informatyka/Java/Exercice/JSON/flight.json";
        String file2 = "E:/Informatyka/Java/Exercice/JSON/cargo.json";
        try {
            String json = readFileAsString(file);
            JSONArray arr = new JSONArray(json);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject jsonObject = arr.getJSONObject(i);
                int flightId = jsonObject.getInt("flightId");
                int flightNo = jsonObject.getInt("flightNumber");
                String depAir = jsonObject.getString("departureAirportIATACode");
                String arrAir = jsonObject.getString("arrivalAirportIATACode");
                String depDate = jsonObject.getString("departureDate");
                Flights info = new Flights(flightId, flightNo, depAir, arrAir, depDate);
                flights.add(info);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            String json = readFileAsString(file2);
            JSONArray arr = new JSONArray(json);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject jsonObject = arr.getJSONObject(i);
                int flightId = jsonObject.getInt("flightId");
                JSONArray arrayBag = jsonObject.getJSONArray("baggage");
                for (int k = 0; k < arrayBag.length(); k++) {
                    JSONObject object = arrayBag.getJSONObject(k);
                    int idBag = object.getInt("id");
                    //System.out.println(idBag);
                    int weightBag = object.getInt("weight");
                    //System.out.println(weightBag);
                    String weightUnBag = object.getString("weightUnit");
                    //System.out.println(weightUnBag);
                    int piecesBag = object.getInt("pieces");
                    //System.out.println(piecesBag);
                    Baggage bag = new Baggage(flightId, idBag, weightBag, weightUnBag, piecesBag);
                    baggages.add(bag);
                }
                JSONArray arrayCargo = jsonObject.getJSONArray("cargo");
                for (int k = 0; k < arrayCargo.length(); k++) {
                    JSONObject object = arrayCargo.getJSONObject(k);
                    int idCar = object.getInt("id");
                    //System.out.println(idCar);
                    int weightCar = object.getInt("weight");
                    //System.out.println(weightCar);
                    String weightUnCar = object.getString("weightUnit");
                    //System.out.println(weightUnCar);
                    int piecesCar = object.getInt("pieces");
                    //System.out.println(piecesCar);
                    Cargo car = new Cargo(flightId, idCar, weightCar, weightUnCar, piecesCar);
                    cargos.add(car);
                    //System.out.println(car);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static String readFileAsString(String file) throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    public String listOfDate() {
        ArrayList<String> list = new ArrayList<>();
        for (Flights f : flights) {
            String depD = f.getDepDate();
            String date = depD.substring(0,10);
            list.add(date);
            }
        return list.toString().replace("[", "").replace("]", "");
    }


    public int findId(int flNumber, String date) {
        int num = -1;
        for (Flights fl : flights) {
            int fNo = fl.getFlightNo();
            String depD = fl.getDepDate();
            if ((fNo == flNumber) && (depD.contains(date))) {
                num = fl.getFlightId();
            }
        }
        return num;
    }

    public int findCargoWeightInkilograms(int foundID) {
        int weight = 0;
        for (Cargo c : cargos) {
            String weightUn = c.getWeightUnCar();
            int flightId = c.getFlightId();
            if ((flightId == foundID) && (weightUn.contains("kg"))) {
                int kg = c.getWeightCar();
                weight = weight + kg;
            }
        }
        return weight;
    }

    public int findCargoWeightInPounds(int foundID) {
        int weight = 0;
        for (Cargo c : cargos) {
            String weightUn = c.getWeightUnCar();
            int flightId = c.getFlightId();
            if ((flightId == foundID) && (weightUn.contains("lb"))) {
                int kg = c.getWeightCar();
                weight = weight + kg;
            }
        }
        return weight;
    }

    public int findBaggageWeightInkilograms(int foundID) {
        int weightB = 0;
        for (Baggage b : baggages) {
            String weightUn = b.getWeightUnBag();
            int flightId = b.getFlightId();
            if ((flightId == foundID) && (weightUn.contains("kg"))) {
                int kg = b.getWeightBag();
                weightB = weightB + kg;
            }
        }
        return weightB;
    }

    public int findBaggageWeightInPounds(int foundID) {
        int weightB = 0;
        for (Baggage b : baggages) {
            String weightUn = b.getWeightUnBag();
            int flightId = b.getFlightId();
            if ((flightId == foundID) && (weightUn.contains("lb"))) {
                int kg = b.getWeightBag();
                weightB = weightB + kg;
            }
        }
        return weightB;
    }

    //Two methods to check that exist flight

    public int checkDep(String iataCode, String date) {
        int num = -1;
        for (Flights fl : flights) {
            String depCode = fl.getDepAir();
            String depD = fl.getDepDate();
            if ((depCode.contains(iataCode)) && (depD.contains(date))) {
                num = fl.getFlightId();
            }
        }
        return num;
    }

    public int checkArr(String iataCode, String date) {
        int num = -1;
        for (Flights fl : flights) {
            String arrCode = fl.getArrAir();
            String depD = fl.getDepDate();
            if ((arrCode.contains(iataCode)) && (depD.contains(date))) {
                num = fl.getFlightId();
            }
        }
        return num;
    }



    // This method found list the flights numbers.

    public ArrayList<Integer> findArrFlightNo(String iataCode, String date) {
        ArrayList<Integer> numberOfFlight = new ArrayList<>();
        for (Flights f : flights) {
            String depCode = f.getArrAir();
            String depD = f.getDepDate();
            if ((depCode.contains(iataCode)) && (depD.contains(date))) {
                numberOfFlight.add(f.getFlightNo());
            }
        }
        return numberOfFlight;
    }

    public ArrayList<Integer> findDepFlightNo(String iataCode, String date) {
        ArrayList<Integer> numberOfFlight = new ArrayList<>();
        for (Flights f : flights) {
            String depCode = f.getDepAir();
            String depD = f.getDepDate();
            if ((depCode.contains(iataCode)) && (depD.contains(date))) {
                numberOfFlight.add(f.getFlightNo());
            }
        }
        return numberOfFlight;
    }


    public int findArrivedBaggage(String iataCode, String date) {
        int pieces = 0;
        for (Flights f : flights) {
            String depCode = f.getArrAir();
            String depD = f.getDepDate();
            if ((depCode.contains(iataCode)) && (depD.contains(date))) {
                ArrayList<Integer> arrivedId = new ArrayList<>();
                arrivedId.add(f.getFlightId());
                for (Baggage b : baggages) {
                    for (Integer n : arrivedId)
                        if (b.getFlightId() == n) {
                            int l = b.getPiecesBag();
                            pieces = pieces + l;
                        }
                }
            }
        }
        return pieces;
    }

    public int findDepartedBaggage(String iataCode, String date) {
        int pieces = 0;
        for (Flights f : flights) {
            String depCode = f.getDepAir();
            String depD = f.getDepDate();
            if ((depCode.contains(iataCode)) && (depD.contains(date))) {
                ArrayList<Integer> depId = new ArrayList<>();
                depId.add(f.getFlightId());
                for (Baggage b : baggages) {
                    for (Integer n : depId)
                        if (b.getFlightId() == n) {
                            int l = b.getPiecesBag();
                            pieces = pieces + l;
                        }
                }
            }
        }
        return pieces;
    }
}




