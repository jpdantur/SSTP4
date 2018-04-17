package ar.edu.itba.ss;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.util.HashMap;
import java.util.Map;

public class Part3 {
    private Map<Planet,Particle> planets = new HashMap<>();


    public static void main (String[] args) {
        System.out.println("Hola");
    }

    private Vector2D getShipLocation() {
        return planets.get(Planet.Earth).getPosition().;
    }



}
