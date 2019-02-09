package world;

import geometry.IntCoordinate;

import java.util.ArrayList;
import java.util.Stack;

public class Level {
    public Room[][] rooms;
    public int width;
    public int height;

    public Level previous;
    public Level next;

    public double difficulty;

    public Level(Level previous) {
        if(previous != null) {
            difficulty = scaleDifficulty(previous.difficulty);
        } else {
            difficulty = 1.0;
        }
        width = (int)(previous.width * difficulty);
        height = (int)(previous.height * difficulty);
        rooms = new Room[width][height];
        generateRooms();
    }

    private void generateRooms() {
        int startx = (int) (Rand.world_rand.nextDouble() * width);
        int starty = (int) (Rand.world_rand.nextDouble() * height);
        ArrayList<Integer> remainingDirections = new ArrayList<>();
        rooms[startx][starty] = new Room(startx, starty);
        int currentx = startx;
        int currenty = starty;
        Stack<IntCoordinate> roomStack = new Stack<>();
        do {
            for(int i = 0; i < 4; i++) {
                remainingDirections.add(i);
            }
            boolean unfound = true;
            while(remainingDirections.size() != 0 && unfound) {
                int r = Rand.world_next_int(0, remainingDirections.size());
                if(rooms[currentx][currenty].connections[remainingDirections.get(r)] == null) {
                    //TODO
                    switch(remainingDirections.get(r)) {
                        case 0: //EAST
                            if(currentx == width - 1) {
                                remainingDirections.remove(r);
                            } else {
                                rooms[currentx + 1][currenty] = (new Room(currentx + 1, currenty)).init();
                                rooms[currentx][currenty].setConnection(rooms[currentx + 1][currenty], 0);
                                roomStack.push(new IntCoordinate(currentx, currenty));
                                currentx = currentx + 1;
                                unfound = false;
                            }
                            break;
                        case 1: //NORTH
                            if(currenty == height - 1) {
                                remainingDirections.remove(r);
                            } else {
                                rooms[currentx][currenty + 1] = (new Room(currentx + 1, currenty)).init();
                                rooms[currentx][currenty].setConnection(rooms[currentx][currenty + 1], 1);
                                roomStack.push(new IntCoordinate(currentx, currenty));
                                currenty = currenty + 1;
                                unfound = false;
                            }
                            break;
                        case 2: //WEST
                            if(currentx == 0) {
                                remainingDirections.remove(r);
                            } else {
                                rooms[currentx - 1][currenty] = (new Room(currentx + 1, currenty)).init();
                                rooms[currentx][currenty].setConnection(rooms[currentx - 1][currenty], 2);
                                roomStack.push(new IntCoordinate(currentx, currenty));
                                currentx = currentx - 1;
                                unfound = false;
                            }
                            break;
                        case 3: //SOUTH
                            if(currenty == 0) {
                                remainingDirections.remove(r);
                            } else {
                                rooms[currentx][currenty - 1] = (new Room(currentx + 1, currenty)).init();
                                rooms[currentx][currenty].setConnection(rooms[currentx][currenty - 1], 3);
                                roomStack.push(new IntCoordinate(currentx, currenty));
                                currenty = currenty - 1;
                                unfound = false;
                            }
                            break;
                    }
                } else {
                    remainingDirections.remove(r);
                }
            }
            if(unfound) {
                IntCoordinate c = roomStack.pop();
                currentx = c.x;
                currenty = c.y;
            }
        } while(!roomStack.empty());
    }

    private static double scaleDifficulty(double previousDifficulty) {
        return previousDifficulty * 1.5;
    }
}
