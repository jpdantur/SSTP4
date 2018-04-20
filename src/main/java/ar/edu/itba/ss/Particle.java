package ar.edu.itba.ss;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Particle {
    private Vector2D position;
    private Vector2D velocity;
    private Vector2D force;
    private Vector2D lastForce;
    private BigDecimal mass;
    private static final BigDecimal G = new BigDecimal("0.00000000006693");

    public Particle(Vector2D position, Vector2D velocity, BigDecimal mass) {
        this.position = position;
        this.velocity = velocity;
        this.mass = mass;
    }

    Vector2D getPosition() {
        return position;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public BigDecimal getMass() {
        return mass;
    }

    void interact(Particle other) {
        BigDecimal strength = G.multiply(mass).multiply(other.mass).
                divide(BigDecimal.valueOf(Vector2D.distanceSq(position,other.position)),RoundingMode.HALF_UP);

        double xFactor = (position.getX()-other.position.getX())/Vector2D.distance(position,other.position);
        double yFactor = (position.getY()-other.position.getY())/Vector2D.distance(position,other.position);
        force = force.add(strength.doubleValue(),new Vector2D(xFactor,yFactor));
    }

    void updateForce(Particle other){
        lastForce=force;
        interact(other);
    }

    void updatePosition(double dt) {
        double newPosX = position.getX() + dt*velocity.getX() +(FastMath.pow(dt,2)/mass.doubleValue()) *force.getX();
        double newPosY = position.getY() + dt*velocity.getY() +(FastMath.pow(dt,2)/mass.doubleValue()) *force.getY();
        position = new Vector2D(newPosX,newPosY);
    }


    @Override
    public String toString() {
        return position.getX() + " " +position.getY()+" "+ velocity.getX()+" " +velocity.getY()+ " " + mass;
    }

    void updateVelocity(double dt) {
        double newVx = velocity.getX() + (dt/(2*mass.doubleValue()))*(lastForce.getX()+force.getX());
        double newVy = velocity.getY() + (dt/(2*mass.doubleValue()))*(lastForce.getY()+force.getY());
        velocity = new Vector2D(newVx,newVy);
    }
}
