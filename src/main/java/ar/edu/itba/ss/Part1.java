package ar.edu.itba.ss;

import ar.edu.itba.ss.approximations.BeemanPC;
import ar.edu.itba.ss.approximations.GearPC;
import ar.edu.itba.ss.approximations.Oscillation;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class Part1
{
    public static void main( String[] args ) throws Exception
    {
        Files.write(Paths.get("result.txt"),"".getBytes());
        Oscillation o = new BeemanPC(0.0001);
        //List<Double> results = new LinkedList<>();
        double x=0;
        do {
            Files.write(Paths.get("result.txt"),(x + " " + o.getNextValue()+"\n").getBytes(),StandardOpenOption.APPEND);
            x+=0.0001;
        } while (x<=5);
        Files.write(Paths.get("result.txt"),(x + " " + o.getNextValue()+"\n").getBytes(),StandardOpenOption.APPEND);
    }
}
