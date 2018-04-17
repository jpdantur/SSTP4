package ar.edu.itba.ss.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by scamisay on 16/04/18.
 */
public class Util {

    public static Stream<Double> range(double from, double step, double to){
        List<Double> range = new ArrayList<>();
        for(double i = from; i<= to ; i+=step){
            range.add(i);
        }
        return range.stream();
    }
}
