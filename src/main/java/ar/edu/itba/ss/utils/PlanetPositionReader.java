package ar.edu.itba.ss.utils;

import ar.edu.itba.ss.Particle;
import ar.edu.itba.ss.Planet;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by scamisay on 18/04/18.
 */
public class PlanetPositionReader {

    private Map<Planet,Map<Integer, Particle>> data = new HashMap<>();

    public PlanetPositionReader() {
        Stream<String> lines = null;
        try{
            Path path = Paths.get(getClass().getClassLoader()
                    .getResource("spacial_coordinates.txt").toURI());

            StringBuilder data = new StringBuilder();
            lines = Files.lines(path);
            lines.forEach(line -> parseLine(line));
        }catch (Exception e){
            throw new RuntimeException("Se ha producido un error leyendo el archivo. "+e);
        } finally {
            if (lines != null)
                lines.close();
        }


    }

    private void parseLine(String line) {
        if(line.isEmpty()){
            return;
        }

        String[] values = line.split("[ \t]+");

        Planet planet = Planet.valueOf(values[1]);
        int month = Integer.parseInt(values[0]);
        double x = Double.valueOf(values[2]);
        double y = Double.valueOf(values[3]);
        double vx = Double.valueOf(values[4]);
        double vy = Double.valueOf(values[5]);
        BigDecimal mass = new BigDecimal(values[6]);
        Particle particle = new Particle(new Vector2D(x,y), new Vector2D(vx,vy), mass);

        addData(planet,month,particle);

    }

    private void addData(Planet planet, int month, Particle particle) {
        if(!data.keySet().contains(planet)){
            data.put(planet, new HashMap<>());
        }
        data.get(planet).put(month,particle);
    }

    public Particle getPlanetByMonth(Planet planet, Integer month){
        return data.get(planet).get(month);
    }

    public static void main(String [] args){
        PlanetPositionReader pp = new PlanetPositionReader();
        pp.getPlanetByMonth(Planet.Earth, 8);
    }
}
