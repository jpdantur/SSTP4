package ar.edu.itba.ss.planet;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumMap;
import java.util.Map;

import static ar.edu.itba.ss.planet.PlanetPositionReader.AU;
import static ar.edu.itba.ss.planet.PlanetPositionReader.DAY;

/**
 * Created by scamisay on 22/04/18.
 */
public class PlanetsSimulation {

    private Map<Planet, Particle> planets;
    private double dt;
    private long dt2;
    private int month;
    private Planet target;
    private Planet secondTarget;
    private final Planet observer = Planet.Ship;
    private StopCondition stopCondition;


    public PlanetsSimulation(double dt, long dt2, int month, Planet target, StopCondition stopCondition) {
        this.dt = dt;
        this.dt2 = dt2;
        this.month = month;
        this.target = target;
        this.stopCondition = stopCondition;
        secondTarget = Planet.Earth;
        planetsInitializer();
        stopConditionInitializer();
    }

    private void stopConditionInitializer() {
        stopCondition.setBasicValues(observer, target, secondTarget, planets);
    }

    private void planetsInitializer(){
        PlanetPositionReader pp = new PlanetPositionReader();
        planets = new EnumMap<>(Planet.class);
        planets.put(Planet.Earth, pp.getPlanetByMonth(Planet.Earth, month));
        planets.put(Planet.Sun, pp.getPlanetByMonth(Planet.Sun, month));
        planets.put(Planet.Jupiter, pp.getPlanetByMonth(Planet.Jupiter, month));

        planets.put(Planet.Saturn, pp.getPlanetByMonth(Planet.Saturn, month));
        planets.put(Planet.Ship, new Particle(getShipLocation(), getShipVelocity(), 721));
    }

    private Vector2D getShipVelocity() {
        double earthSpeed = planets.get(Planet.Earth).getVelocity().getNorm();
        Vector2D normalSpeed = planets.get(Planet.Earth).getPosition().normalize();
        return new Vector2D(11_000 + earthSpeed, new Vector2D(normalSpeed.getY(), -normalSpeed.getX()));
        //return new Vector2D(11_000 , new Vector2D(normalSpeed.getY(), -normalSpeed.getX()));
    }

    private Vector2D getShipLocation() {
        return planets.get(Planet.Earth).getPosition()
                .add(1_500_000.0, planets.get(Planet.Earth).getPosition().normalize());
    }

    public void simulate() {
        try{
            double t = 0.0;
            long i = 0;
            Files.write(Paths.get("res.xyz"), "".getBytes());
            setPlanetsForce();
            long j = 0;

            while(stopCondition.canContinue(t)){
                if (i % dt2 == 0) {
                    Files.write(Paths.get("res.xyz"), (planets.size()+2 + "\n").getBytes(), StandardOpenOption.APPEND);
                    Files.write(Paths.get("res.xyz"), (j + "\n").getBytes(), StandardOpenOption.APPEND);
                    Files.write(Paths.get("res.xyz"), ("10 10 0 0 0\n").getBytes(), StandardOpenOption.APPEND);
                    Files.write(Paths.get("res.xyz"), ("-10 -10 0 0 0\n").getBytes(), StandardOpenOption.APPEND);
                    printParticlesInAu();
                    //System.out.println(t);
                    System.out.println(String.format("min distance = %f - current distance = %f",
                            stopCondition.getMinDistance(),
                            stopCondition.getCurrentDistance()));
                    j++;
                }
                i++;
                updatePlanetsPosition(dt);
                updatePlanetsForce();
                updatePlanetsVelocity(dt);
                t += dt;
            }
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }

    private void setPlanetsForce() {
        for (Map.Entry<Planet, Particle> e1 : planets.entrySet()) {
            for (Map.Entry<Planet, Particle> e2 : planets.entrySet()) {
                if (!e1.getKey().equals(e2.getKey())) {
                    e1.getValue().interact(e2.getValue());
                }
            }
        }
    }

    private void printParticles() throws Exception {
        for (Map.Entry<Planet, Particle> e : planets.entrySet()) {
            Files.write(Paths.get("res.xyz"), (e.getValue() + "\n").getBytes(), StandardOpenOption.APPEND);
        }
    }

    private void printParticlesInAu() throws Exception {
        for (Map.Entry<Planet, Particle> e : planets.entrySet()) {
            Files.write(Paths.get("res.xyz"), (printParticleInAu(e.getValue(),e.getKey()) + "\n").getBytes(), StandardOpenOption.APPEND);
        }
    }

    private String printParticleInAu(Particle particle, Planet planet){
        return String.format("%f %f %f %f %f",
                particle.getPosition().getX()/AU,
                particle.getPosition().getY()/AU,
                particle.getVelocity().getX()/(AU/DAY),
                particle.getVelocity().getY()/(AU/DAY),
                planet.getRadius()

        );
    }

    private void updatePlanetsPosition(double dt) {
        for (Map.Entry<Planet, Particle> e : planets.entrySet()) {
            e.getValue().updatePosition(dt);
        }
    }

    private void updatePlanetsForce() {
        for (Map.Entry<Planet, Particle> e : planets.entrySet()) {
            e.getValue().updateForce();
        }
        setPlanetsForce();
    }

    private void updatePlanetsVelocity(double dt) {
        for (Map.Entry<Planet, Particle> e : planets.entrySet()) {
            e.getValue().updateVelocity(dt);
        }
    }

    public double getLastYear() {
        return 1977 + stopCondition.getLastTime()/(60*60*24*365.4);
    }

    public double getMinDistanceYear() {
        return 1977 + stopCondition.getTimeForMinDistance()/(60*60*24*365.4);
    }

    public Double getMinDistanceYearForTwoTargets() {
        return 1977 + stopCondition.getTimeForMinDistanceForTwoTargets()/(60*60*24*365.4);
    }

    public double getMinDistance(){
        return stopCondition.getMinDistance();
    }

    public Double getMinDistanceForTwoTargets() {
        return stopCondition.getMinDistanceForTwoTargets();
    }
}
