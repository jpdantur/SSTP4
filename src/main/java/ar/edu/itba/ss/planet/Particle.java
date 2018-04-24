package ar.edu.itba.ss.planet;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.util.FastMath;


public class Particle {
    private Vector2D position;
    private Vector2D velocity;
    private Vector2D force;
    private Vector2D lastForce;
    private double mass;
    private static final double G = 6.693e-11;

    public Particle(Vector2D position, Vector2D velocity, double mass) {
        this.position = position;
        this.velocity = velocity;
        this.mass = mass;
        this.force = new Vector2D(0,0);
    }

    public Vector2D getForce() {
        return force;
    }

    Vector2D getPosition() {
        return position;
    }
    Vector2D getVelocity() {
        return velocity;
    }

    void interact(Particle other) {
        double strength = (G*mass*other.mass)/
                Vector2D.distanceSq(position,other.position);

        double xFactor = (other.position.getX()-position.getX())/position.subtract(other.position).getNorm();
        double yFactor = (other.position.getY()-position.getY())/position.subtract(other.position).getNorm();
        force = force.add(strength,new Vector2D(xFactor,yFactor));
    }

    void updateForce(){
        lastForce=force;
        force = new Vector2D(0,0);
    }

    void updatePosition(double dt) {
        double newPosX = position.getX() + dt*velocity.getX() +(FastMath.pow(dt,2)/mass) *force.getX();
        double newPosY = position.getY() + dt*velocity.getY() +(FastMath.pow(dt,2)/mass) *force.getY();
        position = new Vector2D(newPosX,newPosY);
    }


    @Override
    public String toString() {
        return position.getX() + " " +position.getY()+" "+ velocity.getX()+" " +velocity.getY()+  " "
                + force.getX() + " " + force.getY() + " " + mass;
    }

    void updateVelocity(double dt) {
        double newVx = velocity.getX() + (dt/(2*mass))*(lastForce.getX()+force.getX());
        double newVy = velocity.getY() + (dt/(2*mass))*(lastForce.getY()+force.getY());
        velocity = new Vector2D(newVx,newVy);
    }

    public double getMass() {
        return mass;
    }
}
