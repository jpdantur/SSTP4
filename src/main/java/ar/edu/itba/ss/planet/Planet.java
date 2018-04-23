package ar.edu.itba.ss.planet;

public enum Planet {
    Ship("Ship",0.05),Sun("Sun",0.2),Earth("Earth",0.05),Jupiter("Jupiter",0.12),Saturn("Saturn",0.1);

    private final String text;
    private final double radius;

    Planet(final String text, final double radius) {
        this.text = text;
        this.radius = radius;
    }

    @Override
    public String toString() {
        return text;
    }

    public double getRadius() {
        return radius;
    }
}
