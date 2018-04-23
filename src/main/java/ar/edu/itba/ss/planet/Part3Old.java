package ar.edu.itba.ss.planet;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumMap;
import java.util.Map;
import java.util.Scanner;

public class Part3Old {
    private static Map<Planet, Particle> planets = new EnumMap<>(Planet.class);


    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Dame dt:");
        double dt = sc.nextDouble();
        System.out.println("dt2 = n*dt, n=...:");
        long dt2 = sc.nextLong();
        int month = 8;
        PlanetPositionReader pp = new PlanetPositionReader();
        planets.put(Planet.Earth, pp.getPlanetByMonth(Planet.Earth, month));
        planets.put(Planet.Sun, pp.getPlanetByMonth(Planet.Sun, month));
        planets.put(Planet.Jupiter, pp.getPlanetByMonth(Planet.Jupiter, month));

        planets.put(Planet.Saturn, pp.getPlanetByMonth(Planet.Saturn, month));
        planets.put(Planet.Ship, new Particle(getShipLocation(), getShipVelocity(), 721));
        double t = 0.0;
        long i = 0;
        Files.write(Paths.get("res.xyz"), "".getBytes());
        setPlanetsForce();
        long j = 0;

        //parametros de condicion de corte 1
        double limit = 1e12;
        Planet observer = Planet.Ship;
        Planet target = Planet.Saturn;
        Double minDistance = null;

        while (t < limit) { //condicion de corte 1: corte programado

            if (i % dt2 == 0) {
                Files.write(Paths.get("res.xyz"), (planets.size() + "\n").getBytes(), StandardOpenOption.APPEND);
                Files.write(Paths.get("res.xyz"), (j + "\n").getBytes(), StandardOpenOption.APPEND);
                printParticles();
                System.out.println(t);
                j++;
            }
            i++;
            updatePlanetsPosition(dt);
            updatePlanetsForce();
            updatePlanetsVelocity(dt);
            t += dt;

            if(minDistance==null){
                minDistance = getDistanceToTarget(observer,target);
            }else {
                double tempPosition = getDistanceToTarget(observer,target);
                if(tempPosition < minDistance){
                    minDistance = tempPosition;
                }
            }
        }

        System.out.print("distancia minima con condicion de corte 1: "+minDistance);

    }

    private static Double getDistanceToTarget(Planet observer, Planet target) {
        return planets.get(observer).getPosition().distance(planets.get(target).getPosition());
    }

    private static void printParticles() throws Exception {
        for (Map.Entry<Planet, Particle> e : planets.entrySet()) {

            Files.write(Paths.get("res.xyz"), (e.getValue() + "\n").getBytes(), StandardOpenOption.APPEND);
        }
    }

    private static void updatePlanetsForce() {
        for (Map.Entry<Planet, Particle> e : planets.entrySet()) {
            e.getValue().updateForce();
        }
        setPlanetsForce();
    }

    private static void updatePlanetsVelocity(double dt) {
        for (Map.Entry<Planet, Particle> e : planets.entrySet()) {
            e.getValue().updateVelocity(dt);
        }
    }


    private static void updatePlanetsPosition(double dt) {
        for (Map.Entry<Planet, Particle> e : planets.entrySet()) {
            e.getValue().updatePosition(dt);
        }
    }

    private static void setPlanetsForce() {
        for (Map.Entry<Planet, Particle> e1 : planets.entrySet()) {
            for (Map.Entry<Planet, Particle> e2 : planets.entrySet()) {
                if (!e1.getKey().equals(e2.getKey())) {
                    e1.getValue().interact(e2.getValue());
                }
            }
        }
    }

    private static Vector2D getShipVelocity() {
        double earthSpeed = planets.get(Planet.Earth).getVelocity().getNorm();
        Vector2D normalSpeed = planets.get(Planet.Earth).getPosition().normalize();
        return new Vector2D(11_000 + earthSpeed, new Vector2D(normalSpeed.getY(), -normalSpeed.getX()));
    }

    private static Vector2D getShipLocation() {
        return planets.get(Planet.Earth).getPosition()
                .add(1_500_000.0, planets.get(Planet.Earth).getPosition().normalize());
    }


}
