package world;

import java.util.Random;

public class Rand {
    public static long seed;
    public static Random world_rand;
    public static Random room_rand;
    public static Random mob_rand;

    public static int world_next_int(int lowbound_inclusive, int highbound_exclusive) {
        return lowbound_inclusive + (int) (world_rand.nextDouble() * (highbound_exclusive - lowbound_inclusive));
    }
    public static double room_next_double() {
        return room_rand.nextDouble();
    }

    public static void init(long seed) {
        Rand.seed = seed;
        world_rand = new Random(seed);
        room_rand = new Random(world_rand.nextLong());
        mob_rand = new Random(world_rand.nextLong());
    }

    public static double mob_next_double(double lowbound_inclusive, double highbound_exclusive) {
        return lowbound_inclusive + (highbound_exclusive - lowbound_inclusive) * mob_rand.nextDouble();
    }
}