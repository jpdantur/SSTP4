package ar.edu.itba.ss.graphics.exercise1;

import ar.edu.itba.ss.approximations.*;
import ar.edu.itba.ss.utils.Util;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * Created by scamisay on 16/04/18.
 */
public class MethodsComparison {

    public static void main(String[] args){

        double dt = 0.01;

        List<? extends Oscillation> oscillations = Arrays.asList(
                new LeapFrog(dt),
                new BeemanPC(dt),
                new GearPC(dt),
                new Analitical(dt)
        );

        List<String> values =
            oscillations.stream()
            .map( o -> values(o, 0, dt, 5))
            .collect(toList());

        System.out.print(values);
    }

    private static <T extends Oscillation> String values(T oscillation, double from, double step, double to){
        return Util.range(from, step, to)
                .map( t -> oscillation.getNextValue())
                .map( x -> x.toString())
                .collect(joining(", "));
    }
}
