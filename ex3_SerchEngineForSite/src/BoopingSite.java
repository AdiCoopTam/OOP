import oop.ex3.searchengine.Hotel;
import oop.ex3.searchengine.HotelDataset;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.*;

/**this class represtes the a search engine site of hotels*/
public class BoopingSite {

    /**the name of the database*/
    public String name;

    /** an array of the all the hotels*/
    private Hotel[] hotelsArray;

    /**
     * this is the constructor of the class
     * */
    public BoopingSite(String name){
        this.name = name;
        this.hotelsArray = HotelDataset.getHotels(name);
    }

    /**
     * this function returns an array of hotels of a specific city, ordered by stars and alphabet
     *
     * @param city - the city we are looking for her hotels information
     * @return an array of hotel ordered by the roles above
     * */
    public Hotel[] getHotelsInCityByRating(String city){
        if (hotelsArray.length == 0){
            return new Hotel[0];
        }
        Hotel[][] starArray= starSort(cityFilter(this.hotelsArray, city));
        Hotel[] updatedArray = cityFilter(this.hotelsArray, city);
        Hotel[] sortedArray = new Hotel[updatedArray.length];
        int counter = 0;
        for (Hotel[] hotels: starArray){
            if (hotels == null){
                continue;
            }
            Hotel[] alphaArray = alphabetSort(hotels);
            if (counter < sortedArray.length){
                System.arraycopy(alphaArray, 0, sortedArray, counter, alphaArray.length);
                counter += alphaArray.length;}
        }
        return sortedArray;
    }


    /**
     * this function returns an array of hotels, ordered by their distance from the location given and from
     * attractive points.
     *
     * @param latitude - part of the location coordinations
     * @param longitude - part of the location coordinations.
     * @return an array of hotel ordered by the roles above
     * */
    public Hotel[] getHotelsByProximity(double latitude, double longitude){
        if (hotelsArray.length == 0){
            return new Hotel[0];
        }
        Hotel[][] disArr = disSort(this.hotelsArray, latitude,longitude);
        Hotel[] sortedArray = new Hotel[this.hotelsArray.length];
        int counter = 0;
        for (Hotel[] hotels: disArr){
            if (hotels == null){
                continue;
            }
            Hotel[][] poiArray = sortPOI(hotels);
            for (Hotel[] hotelsPoi : poiArray){
                if (hotelsPoi == null){
                    continue;
                }
                if (counter + hotelsPoi.length <= sortedArray.length){
                    System.arraycopy(hotelsPoi, 0, sortedArray, counter, hotelsPoi.length);
                counter += hotelsPoi.length;
            }}
        }
        return sortedArray;
    };

    /**
     * this function returns an array of hotels of a specific city, ordered by it's distance from the
     * location given and from attractive points.
     *
     * @param city - the city we are looking for her hotel's information.
     * @param latitude - part of the location coordinations
     * @param longitude - part of the location coordinations.
     * @return an array of hotel ordered by the roles above
     * */
    public Hotel[] getHotelsInCityByProximity(String city, double latitude, double longitude){
        if (hotelsArray.length == 0){
            return new Hotel[0];
        }
        Hotel[] unFiltered = getHotelsByProximity(latitude, longitude);
        return cityFilter(unFiltered, city);
    }

    /**
     * this function converts a TreeMap of Double and Hotel[] to an array.
     *
     * @param len - the predicted length of the array
     * @param map the map we'll convert
     *
     * @return an array that contains the values of the map.
     *
     * */
    private Hotel[][] mapNumToArray(TreeMap<Double, Hotel[]> map, int len){
        Hotel[][] endArray = new Hotel[len][];
        int count = 0;
        for (Hotel[] hotels:map.values()){
            endArray[count] = hotels;
            count++;
        }
        return endArray;
    }

    /**
     * this function converts a TreeMap of Double and Hotel[] to an array.
     *
     * @param len - the predicted length of the array
     * @param map the map we'll convert
     *
     * @return an array that contains the values of the map.
     *
     * */
    private Hotel[] mapStrToArray(TreeMap<String, Hotel> map, int len){
        Hotel[] endArray = new Hotel[len];
        int count = 0;
        for (Hotel hotel:map.values()){
            endArray[count] = hotel;
            count++;
        }
        return endArray;
    }

    /**
     * this function calculates the distance between location and an hotel
     *
     * @param longitude - part of the location coordinations
     * @param latitude - part of the location coordinations
     * @param hotel - the hotel we are inspecting
     * @return a double that represents the level of the */
    protected double calculateDistance(Hotel hotel, double latitude, double longitude){
        double latDis = Math.pow(hotel.getLatitude() - latitude, 2);
        double longDis = Math.pow(hotel.getLongitude() - longitude, 2);
        return Math.sqrt(longDis + latDis);
    }

    /**
     * this function lists a hotel array by thr alphabet.
     *
     * @param currentHotels - the hotel array we will sort
     * @return a sorted ArrayList that sorted by the alphabet.
     * */
        protected Hotel[] alphabetSort(Hotel[] currentHotels){
            TreeMap <String, Hotel> alphabetMap = new TreeMap<>();
            for (int i = 0; i < currentHotels.length && currentHotels[i] != null; i++) {
                String name = currentHotels[i].getPropertyName();
                alphabetMap.put(name, currentHotels[i]);}
            return mapStrToArray(alphabetMap, currentHotels.length);
        }

    /**
     * this function lists a hotel array by their star rating or distance.
     *
     * @param currentHotels - the hotel array we will sort
     * @return a sorted ArrayList that sorted by the star rate.
     * */
    protected Hotel[][] starSort (Hotel[] currentHotels){
        TreeMap<Double, Hotel[]> starMap = new TreeMap<>(Comparator.reverseOrder());
        for (Hotel hotel : currentHotels) {
            double starRate = hotel.getStarRating();
            numeralIteration(starRate, hotel, starMap, currentHotels.length);
        }
        return mapNumToArray(starMap, currentHotels.length);
    }
    /**
     * this function lists a hotel array by their distance from certain location.
     *
     * @param currentHotels - the hotel array we will sort
     * @return a sorted ArrayList that sorted by the distance from a specific distance.
     * */
    protected Hotel[][] disSort(Hotel[] currentHotels, double latitude, double longitude){
        TreeMap<Double, Hotel[]> disMap = new TreeMap<>();
        for (Hotel hotel : currentHotels) {
            double distance = calculateDistance(hotel,latitude, longitude);
            numeralIteration(distance, hotel, disMap, currentHotels.length);
        }
        return mapNumToArray(disMap, currentHotels.length);
    }
    /**
     this function lists a hotel array by their number of points of interest.
     *
     * @param currentHotels - the hotel array we will sort
     * @return a sorted TreeMap that sorted by decreasing order of points of interest.
     * */
    protected Hotel[][] sortPOI (Hotel[] currentHotels){
        TreeMap<Double, Hotel[]> poiMap = new TreeMap<>(Comparator.reverseOrder());
        for (int i = 0; i < currentHotels.length && currentHotels[i] != null; i++) {
            double POI = currentHotels[i].getNumPOI();
            numeralIteration(POI, currentHotels[i], poiMap, currentHotels.length);
        }
        return mapNumToArray(poiMap, currentHotels.length);}

        /**
         * this function take care of every iteration of numeralSort, so it can fit to star rate and
         * distance.
         *
         * @param hotel - the spcific hotel we are iteration on in this iteration
         * @param len - the length of the original hotel array
         * @param number - the number relevent to the specific hotel (distance or star rate)
         * @param numMap - the TreeMap of the information
         *  */
        private void numeralIteration(double number, Hotel hotel, TreeMap<Double, Hotel[]> numMap, int len){
            if (!numMap.containsKey(number)) {
                Hotel[] currentKey = new Hotel[len];
                currentKey[0] = hotel;
                numMap.put(number, currentKey);
            } else {
                Hotel[] currentRating = numMap.get(number);
                for (int i = 0; i < len ; i++) {
                    if (currentRating[i] == null) {
                        currentRating[i] = hotel;
                    }
                }

            }
        }


    /**
     * a simple getter for hotelArray.
     * @return - this.hotelArray
     * */
    public Hotel[] getHotelsArray(){
        return this.hotelsArray;}

        /**
         * this function filters hotels by a city.
         *
         * @param currentHotels - the hotel array we will sort
         * @return a sorted ArrayList that sorted by the star rate.
         * */
        protected Hotel[] cityFilter (Hotel[] currentHotels, String city){
            Hotel[] cityHotels = new Hotel[currentHotels.length];
            int count = 0;
            for (Hotel hotel : hotelsArray) {
                if (city.equals(hotel.getCity())){
                    cityHotels[count] = hotel;
                    count++;
                }
            }
            return Arrays.copyOfRange(cityHotels, 0, count);
        }



}
