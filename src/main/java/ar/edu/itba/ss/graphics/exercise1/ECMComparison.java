package ar.edu.itba.ss.graphics.exercise1;

import ar.edu.itba.ss.approximations.BeemanPC;
import ar.edu.itba.ss.approximations.GearPC;
import ar.edu.itba.ss.approximations.LeapFrog;
import ar.edu.itba.ss.approximations.Oscillation;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class ECMComparison {

    public static void main(String[] args){
        List<Double> dtList = Arrays.asList(0.001, 0.005, 0.01, 0.02, 0.025, 0.03, 0.05);
        List<Function<Double, Oscillation>> methods = Arrays.asList(LeapFrog::new, BeemanPC::new, GearPC::new);

        String dtValues = dtList.stream().map(x -> x.toString()).collect(joining(", "));
        List<List<Double>> ecms =
                methods.stream().map(
                        m -> new ECM(dtList, m, 5)
                            .calculateECMs()
                ).collect(toList());
        List<String> values = ecms.stream().map(l -> l.stream()
                .map( x -> x.toString())
                .collect(joining(", "))
        ).collect(toList());
        System.out.println(ecms);
    }

}
