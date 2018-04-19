package ar.edu.itba.ss;

import ar.edu.itba.ss.utils.PlanetPositionReader;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Part3 {
    private static Map<Planet,Particle> planets = new HashMap<>();


    public static void main (String[] args) {
        int month=8;
        PlanetPositionReader pp = new PlanetPositionReader();
        planets.put(Planet.Earth,pp.getPlanetByMonth(Planet.Earth, month));
        planets.put(Planet.Sun,pp.getPlanetByMonth(Planet.Sun, month));
        planets.put(Planet.Jupiter,pp.getPlanetByMonth(Planet.Jupiter, month));
        planets.put(Planet.Ship, new Particle(getShipLocation(), getShipVelocity(),new BigDecimal("721")));
        for (Map.Entry<Planet,Particle> e: planets.entrySet()) {
            System.out.println(e.getValue());
        }
    }

    private static Vector2D getShipVelocity() {
        double angle = Vector2D.angle(new Vector2D(0,-1),planets.get(Planet.Earth).getPosition());
        return new Vector2D(FastMath.cos(angle),FastMath.sin(angle)).scalarMultiply(11);
    }

    private static Vector2D getShipLocation() {
        double angle = Vector2D.angle(new Vector2D(1,0),planets.get(Planet.Earth).getPosition());
        return planets.get(Planet.Earth).getPosition().add(1500,new Vector2D(FastMath.cos(angle),FastMath.sin(angle)));
    }



}
