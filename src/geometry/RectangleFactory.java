package geometry;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import render.Color;

public class RectangleFactory {
    GeometryFactory geometryFactory = new GeometryFactory();
    public RectangleFactory() {
    }
    public Polygon createRectangle(double x, double y, double width, double height) {
        return geometryFactory.createPolygon(new Coordinate[] { new Coordinate(x, y), new Coordinate(x + width, y),
                new Coordinate(x + width, y + height), new Coordinate(x, y + height), new Coordinate(x, y)});
    }
}
