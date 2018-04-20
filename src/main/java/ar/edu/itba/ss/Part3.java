package ar.edu.itba.ss;

import ar.edu.itba.ss.utils.PlanetPositionReader;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumMap;
import java.util.Map;
import java.util.Scanner;

public class Part3 {
    private static Map<Planet,Particle> planets = new EnumMap<>(Planet.class);


    public static void main (String[] args)throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Dame dt: ");
        double dt = sc.nextDouble();
        double total = 1000000000;
        int month=8;
        PlanetPositionReader pp = new PlanetPositionReader();
        planets.put(Planet.Earth,pp.getPlanetByMonth(Planet.Earth, month));
        planets.put(Planet.Sun,pp.getPlanetByMonth(Planet.Sun, month));
        planets.put(Planet.Jupiter,pp.getPlanetByMonth(Planet.Jupiter, month));

        planets.put(Planet.Saturn,pp.getPlanetByMonth(Planet.Saturn, month));
        planets.put(Planet.Ship, new Particle(getShipLocation(), getShipVelocity(),721));
        double t=0.0;
        int i = 0;
        Files.write(Paths.get("res.xyz"),"".getBytes());
        setPlanetsForce();
        int j=0;
        while (t<total) { //TODO: Poner condicion de corte




            if (i%10000==0) {
                Files.write(Paths.get("res.xyz"),(planets.size()+"\n").getBytes(),StandardOpenOption.APPEND);
                Files.write(Paths.get("res.xyz"),(j+"\n").getBytes(),StandardOpenOption.APPEND);
                printParticles();
                System.out.println(j+" - "+t);
                j++;
            }
            i++;
            updatePlanetsPosition(dt);
            updatePlanetsForce();
            updatePlanetsVelocity(dt);
            t+=dt;
        }

    }

    private static void printParticles() throws Exception {
        for (Map.Entry<Planet,Particle> e:planets.entrySet()){

            Files.write(Paths.get("res.xyz"),(e.getValue()+"\n").getBytes(),StandardOpenOption.APPEND);
        }
    }

    private static void updatePlanetsForce() {
        for (Map.Entry<Planet,Particle> e:planets.entrySet()) {
            e.getValue().updateForce();
        }
        setPlanetsForce();
    }

    private static void updatePlanetsVelocity(double dt) {
        for (Map.Entry<Planet,Particle> e:planets.entrySet()){
            e.getValue().updateVelocity(dt);
        }
    }


    private static void updatePlanetsPosition(double dt) {
        for (Map.Entry<Planet,Particle> e:planets.entrySet()){
            e.getValue().updatePosition(dt);
        }
    }

    private static void setPlanetsForce() {
        for (Map.Entry<Planet,Particle> e1:planets.entrySet()) {
            for (Map.Entry<Planet,Particle> e2:planets.entrySet()) {
                if (!e1.getKey().equals(e2.getKey())){
                    e1.getValue().interact(e2.getValue());
                }
            }
        }
    }

    private static Vector2D getShipVelocity() {
        double angle = Vector2D.angle(new Vector2D(0,-1),planets.get(Planet.Earth).getPosition());
        Vector2D ret = new Vector2D(FastMath.cos(angle),FastMath.sin(angle)).scalarMultiply(11);
        //System.out.println(ret.getY());
        return ret;
    }

    private static Vector2D getShipLocation() {
        double angle = Vector2D.angle(new Vector2D(1,0),planets.get(Planet.Earth).getPosition());
        return planets.get(Planet.Earth).getPosition().add(1500,new Vector2D(FastMath.cos(angle),FastMath.sin(angle)));
    }



}
