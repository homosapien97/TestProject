package world;

import entity.Entity;
import geometry.RectangleFactory;
import render.Color;

public class Terrain extends Entity {
    public static final double DEFAULT_TERRAIN_SIZE = 80;
    //type property to decide what to render;
    String type;

    public Terrain(double x, double y, Room room){
        this(x,y, room, "Rock");
    };

    public Terrain(double x, double y, Room room, String settype){
        super(x, y, room);
        RectangleFactory rf = new RectangleFactory();
        if(!settype.equals("wallv") && !settype.equals("wallh")) {
            this.poly = rf.createRectangle(x, y, Rand.room_next_double() * DEFAULT_TERRAIN_SIZE, Rand.room_next_double() * DEFAULT_TERRAIN_SIZE);
        } else if(settype == "wallv"){
            this.poly = rf.createRectangle(x, y, 20, 800);
        } else if(settype == "wallh"){
            this.poly = rf.createRectangle(x, y, 800, 20);
        }
        //type can be used when rendering to decide what color this terrain is, e.g.  rocks are brown
        //type can be used when rendering to decide what color this terrain is, e.g.  rocks are brown
        type = settype;

        switch(type) {
            case "Rock":
                this.color = new Color(165, 100, 42);
                break;
            case "snow_mound":
                this.color = new Color(200, 200, 255);
                break;
        }
    }

    public boolean hasTransparency() {
        return false;
    }


}
