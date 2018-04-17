package ar.edu.itba.ss;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;

import java.math.BigDecimal;
import java.util.Objects;

public class Particle {
    private Vector2D position;
    private Vector2D velocity;
    private Vector2D acceleration;
    private BigDecimal mass;
    private static final BigDecimal G = new BigDecimal("0.00000000006693");

    public Particle(Vector2D position, Vector2D velocity, BigDecimal mass) {
        this.position = position;
        this.velocity = velocity;
        this.mass = mass;
    }

    public Vector2D getPosition() {
        return position;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public BigDecimal getMass() {
        return mass;
    }

    public void interact(Particle other) {
        BigDecimal force = G.multiply(mass).multiply(other.mass).
                divide(BigDecimal.valueOf(FastMath.pow(Vector2D.distance(position,other.position),2)));

        double xFactor = (position.getX()-other.position.getX())/Vector2D.distance(position,other.position);
        double yFactor = (position.getY()-other.position.getY())/Vector2D.distance(position,other.position);
        acceleration = acceleration.add(force.divide(mass).doubleValue(),new Vector2D(xFactor,yFactor));
    }

    public void update() {
        //Inserte sarasa de alguno de los metodos de arriba
    }


}
