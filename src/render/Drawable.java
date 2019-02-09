package render;

import org.locationtech.jts.geom.Coordinate;

public interface Drawable {
    Coordinate[] getOutline();
    Color getColor();
    boolean hasTransparency();
}
