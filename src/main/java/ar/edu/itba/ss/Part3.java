package ar.edu.itba.ss;

import ar.edu.itba.ss.planet.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by scamisay on 22/04/18.
 */
public class Part3 {

    public static void main(String[] args){

        double dt = 36;
        long dt2 = 10000;
        //int month = 1;
        Planet target = Planet.Saturn;
        //StopCondition stopCondition = new ProgrammedTimeCondition(1e9);
       /* StopCondition stopCondition = new ObserverCondition(1 ,3.2e11,4);

        PlanetsSimulation simulation = new PlanetsSimulation(dt, dt2, month, target, stopCondition);
        simulation.simulate();
        System.out.println("min distance = " + simulation.getMinDistance());
        System.out.println("last year = " + simulation.getLastYear());*/

        //StopCondition stopCondition = new ObserverCondition(1 ,3e11,7);
        //StopCondition stopCondition = new MimCounterCondition(8);
        StopCondition stopCondition = new ProgrammedTimeCondition(60*60*24*365*10);

        List<SimulationResult> results = new ArrayList<>();
        for(int month = 1 ; month <= 12 ; month++){
            System.out.println("Mes: " + month);
            PlanetsSimulation simulation = new PlanetsSimulation(dt, dt2, month, target, stopCondition,-0.174533/2);
            simulation.simulate();
            results.add(new SimulationResult(simulation, month));
        }

        SimulationResult bestResult =
                results.stream()
                .min((r1,r2)-> r1.getMinDistanceForTwoTargets().compareTo(r2.getMinDistanceForTwoTargets())).get();

        String tableSorted =
                results.stream()
                        .sorted((r1,r2)-> r1.getMinDistanceForTwoTargets().compareTo(r2.getMinDistanceForTwoTargets()))
                        .map( r -> String.format("%d | %6.3e | %f", r.getMonth(), r.getMinDistanceForTwoTargets(), r.getMinDistanceYearForTwoTargets()))
                        .collect(Collectors.joining("\n"));

        String tableSortedOnlySaturn = results.stream()
                .sorted((r1,r2)-> r1.getMinDistance().compareTo(r2.getMinDistance()))
                .map( r -> String.format("%d | %6.3e | %f", r.getMonth(), r.getMinDistance(), r.getMinDistanceYear()))
                .collect(Collectors.joining("\n"));
        System.out.println("info = " + bestResult);
    }

    static class SimulationResult{
        private Double lastYear;
        private Double minDistanceYear;
        private Double minDistance;
        private Double minDistanceYearForTwoTargets;
        private Double minDistanceForTwoTargets;
        private Integer month;

        @Override
        public String toString() {
            return "SimulationResult{" +
                    "lastYear=" + lastYear +
                    ", minDistanceYear=" + minDistanceYear +
                    ", minDistance=" + minDistance +
                    ", minDistanceYearForTwoTargets=" + minDistanceYearForTwoTargets +
                    ", minDistanceForTwoTargets=" + minDistanceForTwoTargets +
                    ", month=" + month +
                    '}';
        }

        public SimulationResult(PlanetsSimulation simulation, int month) {
            lastYear = simulation.getLastYear();
            minDistanceYear = simulation.getMinDistanceYear();
            minDistance = simulation.getMinDistance();
            minDistanceYearForTwoTargets = simulation.getMinDistanceYearForTwoTargets();
            minDistanceForTwoTargets = simulation.getMinDistanceForTwoTargets();
            this.month = month;
        }

        public Double getLastYear() {
            return lastYear;
        }

        public Double getMinDistance() {
            return minDistance;
        }

        public Double getMinDistanceYear() {
            return minDistanceYear;
        }

        public Double getMinDistanceYearForTwoTargets() {
            return minDistanceYearForTwoTargets;
        }

        public Double getMinDistanceForTwoTargets() {
            return minDistanceForTwoTargets;
        }

        public Integer getMonth() {
            return month;
        }
    }
}
