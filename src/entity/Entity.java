package entity;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateFilter;
import org.locationtech.jts.geom.Polygon;
import render.Color;
import render.Drawable;
import world.Room;
import world.Terrain;

import java.util.HashSet;

public abstract class Entity implements Drawable {
    private static int num_entities = 0;
    private long entity_id = num_entities++;
    protected Coordinate pos;
    protected Polygon poly;
    protected Color color = new Color(0, 0, 0);
    public Room room;

    public Entity(double x, double y, Room room) {
//        this.x = x;
//        this.y = y;
        this.pos = new Coordinate(x, y);
        this.room = room;
    }

    public Coordinate getPos() {
        return new Coordinate(pos);
    }

    public void transpose(double dx, double dy) {
        CoordinateFilter transposer = new CoordinateFilter() {
            @Override
            public void filter(Coordinate coord) {
                coord.x += dx;
                coord.y += dy;
            }
        };
        poly.apply(transposer);
        poly.geometryChanged();
//        x += dx;
//        y += dy;
        pos.setX(pos.x + dx);
        pos.setY(pos.y + dy);
    }

    public HashSet<Terrain> getTerrainCollisions() {
        HashSet<Terrain> ret = new HashSet<>();
        for(Terrain t : room.terrain) {
            if(t.getHitbox().intersects(poly)) {
                ret.add(t);
            }
        }
        return ret;
    }

    public Coordinate[] getRenderInformation() {
        return poly.getCoordinates();
    }

    @Override
    public int hashCode() {
        return (int) entity_id;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Entity) {
            Entity other = (Entity) o;
            return this == other;
        }
        return false;
    }

    public Coordinate[] getOutline() {
        return poly.getCoordinates();
    }

    public Color getColor() {
        return color;
    }

    public Polygon getHitbox() {
        return poly;
    }
}
