package ar.edu.itba.ss.graphics.exercise1;

import ar.edu.itba.ss.approximations.BeemanPC;
import ar.edu.itba.ss.approximations.GearPC;
import ar.edu.itba.ss.approximations.LeapFrog;
import ar.edu.itba.ss.approximations.Oscillation;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public class ECMComparison {

    public static void main(String[] args){
        List<Double> dtList = Arrays.asList(0.01, 0.02, 0.25, 0.03, 0.5);
        List<Function<Double, Oscillation>> methods = Arrays.asList(LeapFrog::new, BeemanPC::new, GearPC::new);

        List<List<Double>> ecms = methods.stream().map( m -> new ECM(dtList, m, 5).calculateECMs()).collect(toList());
        System.out.println(ecms);
    }

}
