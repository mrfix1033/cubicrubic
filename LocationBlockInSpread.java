import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public enum LocationBlockInSpread {
    CORE(0, 0, 0),
    UP(0, 1, 0),
    DOWN(0, -1, 0),
    RIGHT(1, 0, 0),
    LEFT(-1, 0, 0),
    FORWARD(0, 0, 1),
    BACKWARD(0, 0, -1),
    EDGE_UP_RIGHT(1, 1, 0),
    EDGE_UP_LEFT(-1, 1, 0),
    EDGE_DOWN_RIGHT(1, -1, 0),
    EDGE_DOWN_LEFT(-1, -1, 0),
    EDGE_RIGHT_FORWARD(1, 0, 1),
    EDGE_LEFT_FORWARD(-1, 0, 1),
    EDGE_RIGHT_BACKWARD(1, 0, -1),
    EDGE_LEFT_BACKWARD(-1, 0, -1),
    EDGE_UP_FORWARD(0, 1, 1),
    EDGE_UP_BACKWARD(0, 1, -1),
    EDGE_DOWN_FORWARD(0, -1, 1),
    EDGE_DOWN_BACKWARD(0, -1, -1),
    CORNER_UP_RIGHT_FORWARD(1, 1, 1),
    CORNER_UP_RIGHT_BACKWARD(1, 1, -1),
    CORNER_UP_LEFT_FORWARD(-1, 1, 1),
    CORNER_UP_LEFT_BACKWARD(-1, 1, -1),
    CORNER_DOWN_RIGHT_FORWARD(1, -1, 1),
    CORNER_DOWN_RIGHT_BACKWARD(1, -1, -1),
    CORNER_DOWN_LEFT_FORWARD(-1, -1, 1),
    CORNER_DOWN_LEFT_BACKWARD(-1, -1, -1);

    private final int x;
    private final int y;
    private final int z;

    LocationBlockInSpread(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public boolean isUp() {
        return y == 1;
    }

    public boolean isRight() {
        return x == 1;
    }

    public boolean isForward() {
        return z == 1;
    }

    public boolean isDown() {
        return y == -1;
    }

    public boolean isLeft() {
        return x == -1;
    }

    public boolean isBackward() {
        return z == -1;
    }

    public boolean isXZero() {
        return x == 0;
    }

    public boolean isYZero() {
        return y == 0;
    }

    public boolean isZZero() {
        return z == 0;
    }

    // Utilities: defining constants
    private final static List<LocationBlockInSpread> centers = Arrays.asList(UP, DOWN, RIGHT, LEFT, FORWARD, BACKWARD);
    private final static List<LocationBlockInSpread> corners = Arrays.asList(CORNER_UP_RIGHT_FORWARD,
            CORNER_UP_RIGHT_BACKWARD,
            CORNER_UP_LEFT_FORWARD,
            CORNER_UP_LEFT_BACKWARD,
            CORNER_DOWN_RIGHT_FORWARD,
            CORNER_DOWN_RIGHT_BACKWARD,
            CORNER_DOWN_LEFT_FORWARD,
            CORNER_DOWN_LEFT_BACKWARD);
    private final static List<LocationBlockInSpread> edges = Arrays.asList(EDGE_UP_RIGHT,
            EDGE_UP_LEFT,
            EDGE_DOWN_RIGHT,
            EDGE_DOWN_LEFT,
            EDGE_RIGHT_FORWARD,
            EDGE_LEFT_FORWARD,
            EDGE_RIGHT_BACKWARD,
            EDGE_LEFT_BACKWARD,
            EDGE_UP_FORWARD,
            EDGE_UP_BACKWARD,
            EDGE_DOWN_FORWARD,
            EDGE_DOWN_BACKWARD);
    private final static List<LocationBlockInSpread> noCore = Stream.concat(Stream.concat(centers.stream(), edges.stream()), corners.stream()).toList();
    private final static List<LocationBlockInSpread> ups = Arrays.asList(UP,
            EDGE_UP_RIGHT,
            EDGE_UP_LEFT,
            EDGE_UP_FORWARD,
            EDGE_UP_BACKWARD,
            CORNER_UP_RIGHT_FORWARD,
            CORNER_UP_RIGHT_BACKWARD,
            CORNER_UP_LEFT_FORWARD,
            CORNER_UP_LEFT_BACKWARD);
    private final static List<LocationBlockInSpread> downs = Arrays.asList(DOWN,
            EDGE_DOWN_RIGHT,
            EDGE_DOWN_LEFT,
            EDGE_DOWN_FORWARD,
            EDGE_DOWN_BACKWARD,
            CORNER_DOWN_RIGHT_FORWARD,
            CORNER_DOWN_RIGHT_BACKWARD,
            CORNER_DOWN_LEFT_FORWARD,
            CORNER_DOWN_LEFT_BACKWARD);
    private final static List<LocationBlockInSpread> lefts = Arrays.asList(LEFT,
            EDGE_UP_LEFT,
            EDGE_DOWN_LEFT,
            EDGE_LEFT_FORWARD,
            EDGE_LEFT_BACKWARD,
            CORNER_UP_LEFT_FORWARD,
            CORNER_UP_LEFT_BACKWARD,
            CORNER_DOWN_LEFT_FORWARD,
            CORNER_DOWN_LEFT_BACKWARD);
    private final static List<LocationBlockInSpread> rights = Arrays.asList(RIGHT,
            EDGE_UP_RIGHT,
            EDGE_DOWN_RIGHT,
            EDGE_RIGHT_FORWARD,
            EDGE_RIGHT_BACKWARD,
            CORNER_UP_RIGHT_FORWARD,
            CORNER_UP_RIGHT_BACKWARD,
            CORNER_DOWN_RIGHT_FORWARD,
            CORNER_DOWN_RIGHT_BACKWARD);
    private final static List<LocationBlockInSpread> forwards = Arrays.asList(FORWARD,
            EDGE_RIGHT_FORWARD,
            EDGE_LEFT_FORWARD,
            EDGE_UP_FORWARD,
            EDGE_DOWN_FORWARD,
            CORNER_UP_RIGHT_FORWARD,
            CORNER_UP_LEFT_FORWARD,
            CORNER_DOWN_RIGHT_FORWARD,
            CORNER_DOWN_LEFT_FORWARD);
    private final static List<LocationBlockInSpread> backwards = Arrays.asList(BACKWARD,
            EDGE_RIGHT_BACKWARD,
            EDGE_LEFT_BACKWARD,
            EDGE_UP_BACKWARD,
            EDGE_DOWN_BACKWARD,
            CORNER_UP_RIGHT_BACKWARD,
            CORNER_UP_LEFT_BACKWARD,
            CORNER_DOWN_RIGHT_BACKWARD,
            CORNER_DOWN_LEFT_BACKWARD);

    // Utilities: functions
    public static <T> List<T> copyCollection(List<T> collection) {
        ArrayList<T> dest = new ArrayList<>();
        Collections.copy(dest, collection);
        return dest;
    }

    public List<LocationBlockInSpread> getSpreadsForBlock() {
        if (this == CORE) return noCore;
        List<LocationBlockInSpread> spreads = new ArrayList<>();
        spreads.add(this);
        if (this.isRight()) spreads.add(RIGHT);
        else if (this.isLeft()) spreads.add(LEFT);
        if (this.isUp()) spreads.add(UP);
        else if (this.isDown()) spreads.add(DOWN);
        if (this.isForward()) spreads.add(FORWARD);
        else if (this.isBackward()) spreads.add(BACKWARD);
        return spreads;
    }

    public static LocationBlockInSpread parse(double x, double y, double z) {
        if (x > 0)
            if (y > 0)
                if (z > 0)
                    return CORNER_UP_RIGHT_FORWARD;
                else if (z == 0) return EDGE_UP_RIGHT;
                else return CORNER_UP_RIGHT_BACKWARD;
            else if (y == 0)
                if (z > 0) return EDGE_RIGHT_FORWARD;
                else if (z == 0) return RIGHT;
                else return EDGE_RIGHT_BACKWARD;
            else if (z > 0) return CORNER_DOWN_RIGHT_FORWARD;
            else if (z == 0) return EDGE_DOWN_RIGHT;
            else return CORNER_DOWN_RIGHT_BACKWARD;
        else if (x == 0)
            if (y > 0)
                if (z > 0)
                    return EDGE_UP_FORWARD;
                else if (z == 0) return UP;
                else return EDGE_UP_BACKWARD;
            else if (y == 0)
                if (z > 0) return FORWARD;
                else if (z == 0) return CORE;
                else return BACKWARD;
            else if (z > 0) return EDGE_DOWN_FORWARD;
            else if (z == 0) return DOWN;
            else return EDGE_DOWN_BACKWARD;
        else if (y > 0)
            if (z > 0)
                return CORNER_UP_LEFT_FORWARD;
            else if (z == 0) return EDGE_UP_LEFT;
            else return CORNER_UP_LEFT_BACKWARD;
        else if (y == 0)
            if (z > 0) return EDGE_LEFT_FORWARD;
            else if (z == 0) return LEFT;
            else return EDGE_LEFT_BACKWARD;
        else if (z > 0) return CORNER_DOWN_LEFT_FORWARD;
        else if (z == 0) return EDGE_DOWN_LEFT;
        else return CORNER_DOWN_LEFT_BACKWARD;
    }

    // Constants getters
    public static List<LocationBlockInSpread> getCenters() {
        return copyCollection(centers);
    }

    public static List<LocationBlockInSpread> getEdges() {
        return copyCollection(edges);
    }

    public static List<LocationBlockInSpread> getCorners() {
        return copyCollection(corners);
    }

    public static List<LocationBlockInSpread> getUps() {
        return copyCollection(ups);
    }

    public static List<LocationBlockInSpread> getDowns() {
        return copyCollection(downs);
    }

    public static List<LocationBlockInSpread> getLefts() {
        return copyCollection(lefts);
    }

    public static List<LocationBlockInSpread> getRights() {
        return copyCollection(rights);
    }

    public static List<LocationBlockInSpread> getForwards() {
        return copyCollection(forwards);
    }

    public static List<LocationBlockInSpread> getBackwards() {
        return copyCollection(backwards);
    }

    public static List<LocationBlockInSpread> getNoCore() {
        return copyCollection(noCore);
    }
}
