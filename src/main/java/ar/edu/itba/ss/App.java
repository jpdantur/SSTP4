package ar.edu.itba.ss;

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
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        Files.write(Paths.get("result.txt"),"".getBytes());
        Oscillation o = new GearPC(0.0001);
        //List<Double> results = new LinkedList<>();
        for (double x = 0.0001; x<=5; x+=0.0001){
            Files.write(Paths.get("result.txt"),(x + " " + o.getNextValue()+"\n").getBytes(),StandardOpenOption.APPEND);
        }
    }
}
