package inputsOutputs;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Locations implements Map<Integer, Location> {

    private static Map<Integer, Location> locations = new LinkedHashMap<Integer, Location>();

    public static void main(String[] args) {
      Path locPath = FileSystems.getDefault().getPath("locations.txt");
      Path dirPath = FileSystems.getDefault().getPath("directions.txt");
            try(BufferedWriter locFile = Files.newBufferedWriter(locPath);
                BufferedWriter dirFile = Files.newBufferedWriter(dirPath)){
                for(Location location : locations.values()){
                    locFile.write(location.getLocationID() + "\t" + location.getDescription() + "\t" + location.getExits() + "\n");
                    for(String direction : location.getExits().keySet()){
                        if(!direction.equalsIgnoreCase("Q")){
                            dirFile.write(location.getLocationID() + "\t" + direction + "\t" + location.getExits().get(direction) + "\n");
                        }
                    }
                }
            }catch(IOException e){
                e.printStackTrace();
            }
    }

        //static initialisation
    static {
        Path locPath = FileSystems.getDefault().getPath("locations.txt");
        Path dirPath = FileSystems.getDefault().getPath("directions.txt");

        try(Scanner scanner = new Scanner(Files.newBufferedReader(locPath))){
            scanner.useDelimiter("\t");
            while(scanner.hasNextLine()){
                int loc = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                String description = scanner.nextLine();
                System.out.println("Imported " + loc + " desctription " + description);
                locations.put(loc, new Location(loc, description, null));
            }

        }catch(IOException e){
            e.printStackTrace();
        }
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
