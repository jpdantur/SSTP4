package ar.edu.itba.ss.utils;

import ar.edu.itba.ss.planet.*;

/**
 * Created by scamisay on 22/04/18.
 */
public class Part3 {

    public static void main(String[] args) throws Exception{

        double dt = 3600;
        long dt2 = 24;
        int month = 8;
        Planet target = Planet.Saturn;
        StopCondition stopCondition = new ProgrammedTime(1e9);
        //StopCondition stopCondition = new Observer(1 ,3.4e11);

        PlanetsSimulation simulation = new PlanetsSimulation(dt, dt2, month, target, stopCondition);
        simulation.simulate();
        System.out.println("min distance = " + simulation.getMinDistance());
        System.out.println("last year = " + simulation.getLastYear());
    }
}
