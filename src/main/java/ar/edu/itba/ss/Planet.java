package ar.edu.itba.ss;

public enum Planet {
    Ship("Ship"),Sun("Sun"),Earth("Earth"),Jupiter("Jupiter"),Saturn("Saturn");

    private final String text;

    Planet(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
