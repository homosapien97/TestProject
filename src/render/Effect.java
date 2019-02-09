package render;


import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;

import java.util.HashSet;

public class Effect implements Drawable{
    public static HashSet<Effect> effects = new HashSet<>();
    protected Polygon poly;
    protected Color color;
    protected int ticks_remaining;

    public Effect(Polygon polygon, Color color, int duration) {
        this.poly = polygon;
        this.color = color;
        ticks_remaining = duration;
        effects.add(this);
    }

    public void tick() {
        ticks_remaining -= 1;
        if(ticks_remaining == 0) {
            effects.remove(this);
        }
        color.a = color.a / 2;
    }

    public Coordinate[] getOutline() {
        return poly.getCoordinates();
    }

    public Color getColor() {
        return color;
    }

    public boolean hasTransparency() {
        return true;
    }
}
