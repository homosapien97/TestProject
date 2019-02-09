package gameEngine;

import org.locationtech.jts.geom.Coordinate;
import processing.core.PApplet;
import processing.core.PFont;
import render.Drawable;
import render.Effect;
import world.Level;
import world.Rand;
import world.Room;

import java.util.Random;

public class MainGameLoop extends PApplet {
    public static final double HUD_WIDTH = 50;
//    private static Level level;
    private static Room room;
    private static long tick = 0;
    private static int timeIncrement = 50;
    private static long deltaTime;
    private static long beginTime;

    /**
     * handling event
     *
     */
    public void eventHandler() {
        deltaTime +=  System.currentTimeMillis() - beginTime;
        beginTime = System.currentTimeMillis();
        if(timeIncrement < deltaTime ) {
            //We should go on to the next tick
            deltaTime -= timeIncrement;
            tick++;
        }
    }
    PFont font;
    /**
     *
     */
    public void settings() {
        size((int) (Room.width + 2 * HUD_WIDTH),(int) (Room.height + 2 * HUD_WIDTH));
    }
//    PImage img;
    public void setup() {
        smooth();
        noStroke();
        font = createFont("Arial",16,true); // STEP 2 Create Font
    }
    //rendering
    public void draw() {
            background(255);
            getUserInput();
            eventHandler();
            for(Drawable d : room.terrain) {
                fill(d.getColor().r, d.getColor().g, d.getColor().b);
                if(d.hasTransparency()) {
                    tint(255, d.getColor().a);
                }
                Coordinate[] info = d.getOutline();
                beginShape();
                for(int i = 0; i < info.length; i++) {
                    vertex((float) (info[i].x + HUD_WIDTH), (float) (info[i].y + HUD_WIDTH));
                }
                endShape();
            }
            for(Effect d : Effect.effects) {
                fill(d.getColor().r, d.getColor().g, d.getColor().b);
                if(d.hasTransparency()) {
                    tint(255, d.getColor().a);
                }
                Coordinate[] info = d.getOutline();
                beginShape();
                for(int i = 0; i < info.length; i++) {
                    vertex((float) (info[i].x + HUD_WIDTH), (float) (info[i].y + HUD_WIDTH));
                }
                endShape();
                d.tick();
            }
            //TODO make effects last longer than a tick
            Effect.effects.clear();
            //HUD

            textFont(font, 16);
            fill(0);
            String hudString = "HUD STRING";
            text(hudString, 20, 20);

            ///HUD

            eventHandler();
    }

    public void getUserInput() {
        if (keyCode == LEFT) {
            //move left
//            player.storeMovement(-5, 0);
//            player.direction = 2;
        }
        if (keyCode == RIGHT) {
            //move right
//            player.storeMovement(+5, 0);
//            player.direction = 0;
        }
        if (keyCode == DOWN) {
            //move up
//            player.storeMovement(0, +5);
//            player.direction = 1;
        }
        if (keyCode == UP) {
            //move down
//            player.storeMovement(0, -5);
//            player.direction = 3;
        }
        if (key == ' '){
//            player.doAttack();
////            System.out.println("good");
        }
        keyCode = 0;
        key = '\0';
    }

    public static void main(String args[]){
        Rand.init((new Random()).nextLong());
//        level = new Level(null);
        room = new Room(0,0);
//        room = level.rooms[0][0];
        room.init();
//        player.setlocation(0, 0);
//        room.mobs.add(player);
//        player = new Player(400, 400, room);
//        new SpawnEvent(player);
        //grab current time
        beginTime = System.currentTimeMillis();
        PApplet.main("gameEngine.MainGameLoop");
    }

}
