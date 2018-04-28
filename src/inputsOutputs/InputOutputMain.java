package inputsOutputs;

import java.util.*;

public class InputOutputMain {

    private static Locations locations = new Locations();

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        HashMap<String, String> directions = new HashMap<String, String>();
        directions.put("NORTH", "N");
        directions.put("EAST", "E");
        directions.put("SOUTH", "S");
        directions.put("WEST", "W");

        int loc = 1;
        while(true){
            System.out.println(locations.get(loc).getDescription());
            if(loc == 0){
                break;
            }

            Map<String, Integer> exits = locations.get(loc).getExits();
            System.out.println("Available exits for this location : ");
            for(String exit : exits.keySet()){
                System.out.println(exit + ",");
            }
            System.out.println();

            String direction = scanner.nextLine().toUpperCase();

            String[] directionPhrase = direction.split(" ");
            for(String word : directionPhrase){
                if(directions.containsKey(word)){
                    direction = directions.get(word);
                }
            }

            if(exits.containsKey(direction)){
                loc = exits.get(direction);
            }else{
                System.out.println("You cannot go in that direction");
            }

            if(!locations.containsKey(loc)){
                System.out.println("You cannot go in the direction");
                break;
            }
        }
    }
}
