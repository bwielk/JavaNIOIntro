package inputsOutputs;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.nio.file.*;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.*;

public class Locations implements Map<Integer, Location> {

    private static Map<Integer, Location> locations = new LinkedHashMap<Integer, Location>();

    public static void main(String[] args) throws IOException {

        //Serialisation with NIO

        Path locPath = FileSystems.getDefault().getPath("locations.dat");
        Path dirPath = FileSystems.getDefault().getPath("directions.dat");

        try(ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(locPath)))){
            for(Location location : locations.values()){
                locFile.writeObject(location);
            }
        }

        //Using BufferedWriter. No serialisation

//      Path locPath = FileSystems.getDefault().getPath("locations.txt");
//      Path dirPath = FileSystems.getDefault().getPath("directions.txt");
//            try(BufferedWriter locFile = Files.newBufferedWriter(locPath);
//                BufferedWriter dirFile = Files.newBufferedWriter(dirPath)){
//                for(Location location : locations.values()){
//                    locFile.write(location.getLocationID() + "\t" + location.getDescription() + "\t" + location.getExits() + "\n");
//                    for(String direction : location.getExits().keySet()){
//                        if(!direction.equalsIgnoreCase("Q")){
//                            dirFile.write(location.getLocationID() + "\t" + direction + "\t" + location.getExits().get(direction) + "\n");
//                        }
//                    }
//                }
//            }catch(IOException e){
//                e.printStackTrace();
//            }
    }

        //static initialisation
    static {

        //Serialisation with NIO

        Path locPath = FileSystems.getDefault().getPath("locations.dat");
        try(ObjectInputStream locFile = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(locPath)))){
            boolean endOfFile = false;
            try{
                Location location = (Location) locFile.readObject();
                locations.put(location.getLocationID(), location);
                System.out.println("Object Input Stream processing complete for " + location.getLocationID() + " location");
            }catch(EOFException e){
                endOfFile = true;
                e.printStackTrace();
            }
        }catch(IOException e) {
            e.printStackTrace();
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

            //No serialisation with NIO. Using the BufferedReader

//        Path locPath = FileSystems.getDefault().getPath("locations.txt");
//        Path dirPath = FileSystems.getDefault().getPath("directions.txt");
//
//        try(Scanner scanner = new Scanner(Files.newBufferedReader(locPath))){
//            scanner.useDelimiter("\t");
//            while(scanner.hasNextLine()){
//                int loc = scanner.nextInt();
//                scanner.skip(scanner.delimiter());
//                String description = scanner.nextLine();
//                locations.put(loc, new Location(loc, description, null));
//                System.out.println("Imported " + loc + " desctription " + description + ". Size : " + locations.size());
//            }
//
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//
//        try(BufferedReader dirFile = Files.newBufferedReader(dirPath)){
//            String input;
//            while((input = dirFile.readLine()) != null){
//                String[] data = input.split("\t");
//                int loc = Integer.parseInt(data[0]);
//                String direction = data[1];
//                int destination = Integer.parseInt(data[2]);
//                System.out.println(loc + ": " + direction + ": " + destination);
//                Location location = locations.get(loc);
//                location.addExit(direction, destination);
//                System.out.println("Added a new exit to location " + loc + " : added destination " + location.getExits().size());
//            }
//        }catch(IOException e){
//            e.printStackTrace();
//        }
    }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {}

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
