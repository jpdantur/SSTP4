package ar.edu.itba.ss.graphics.exercise1;

import ar.edu.itba.ss.approximations.Analitical;
import ar.edu.itba.ss.approximations.Oscillation;
import ar.edu.itba.ss.utils.Util;
import org.apache.commons.math3.util.FastMath;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public class ECM {

    private List<Double> dtList;
    private Function<Double, Oscillation> comparedFunction;
    private double limit;

    public ECM(List<Double> dtList, Function<Double, Oscillation> comparedFunction, double limit) {
        this.dtList = dtList;
        this.comparedFunction = comparedFunction;
        this.limit = limit;
    }

    public List<Double> calculateECMs(){
        return dtList.stream().map(dt -> calculateECMforDt(dt)).collect(toList());
    }

    private double calculateECMforDt(Double dt) {
        Analitical analitical = new Analitical(dt);
        Oscillation compOsc = comparedFunction.apply(dt);

        return Util.range(0, dt, limit)
                .mapToDouble( t -> FastMath.pow( compOsc.getNextValue() - analitical.getNextValue(), 2))
                .average()
                .getAsDouble();
    }
}
