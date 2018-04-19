package ar.edu.itba.ss;

import ar.edu.itba.ss.utils.PlanetPositionReader;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;

import java.util.HashMap;
import java.util.Map;

public class Part3 {
    private Map<Planet,Particle> planets = new HashMap<>();


    public static void main (String[] args) {
        PlanetPositionReader pp = new PlanetPositionReader();
        Particle earth = pp.getPlanetByMonth(Planet.Earth, 8);
        System.out.println("tierra para agosto "+earth);
    }

    private Vector2D getShipLocation() {
        double angle = Vector2D.angle(new Vector2D(1,0),planets.get(Planet.Earth).getPosition());
        return planets.get(Planet.Earth).getPosition().add(1500,new Vector2D(FastMath.cos(angle),FastMath.sin(angle)));
    }



}
