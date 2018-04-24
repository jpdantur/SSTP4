package ar.edu.itba.ss;

import ar.edu.itba.ss.planet.Planet;
import ar.edu.itba.ss.planet.PlanetsSimulation;
import ar.edu.itba.ss.planet.ProgrammedTimeCondition;
import ar.edu.itba.ss.planet.StopCondition;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by scamisay on 23/04/18.
 */
public class VelocityModuloForShip {

    public static void main(String[] args) {
        Planet target = Planet.Saturn;
        StopCondition stopCondition = new ProgrammedTimeCondition(5e8);
        double dt = 36;
        long dt2 = 100000;
        int month = 10;
        PlanetsSimulation simulation = new PlanetsSimulation(dt, dt2, month, target, stopCondition);
        simulation.calculateVelocityModuloForShip();
        simulation.simulate();

        List<PlanetsSimulation.DoublePair> results =simulation.getVelocityModuloForShip();
        String velocities = results.stream()
                .map( pair -> pair.getY())
                .map( vel -> String.format("%f",vel))
                .collect(Collectors.joining(", "));

        String years = results.stream()
                .map( pair -> pair.getX())
                .map( t -> t/(60*60*24*365))
                .map( year -> 1977 + new Double(year).intValue())
                .map( year -> String.format("%d",year))
                .collect(Collectors.joining(", "));
        System.out.println("velocities = " + velocities);
    }
}
