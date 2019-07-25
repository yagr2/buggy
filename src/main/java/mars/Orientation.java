package mars;

import java.util.function.Function;
import java.util.function.Supplier;

public class Orientation {

    public static final Orientation NORTH = new NorthOrientation();
    public static final Orientation SOUTH = new SouthOrientation();
    public static final Orientation EAST = new EastOrientation();
    public static final Orientation WEST = new WestOrientation();

    private Orientation current;
    private Function<Point, Point> forwards;
    private Function<Point, Point> backwards;
    private Supplier<Orientation> left;
    private Supplier<Orientation> right;

    public Orientation(Orientation initialOrientation) {
        this.current = initialOrientation;
    }

    private Orientation(Function<Point, Point> forwards, Function<Point, Point> backwards, Supplier<Orientation> left, Supplier<Orientation> right) {
        this.forwards = forwards;
        this.backwards = backwards;
        this.left = left;
        this.right = right;
    }

    public void turnRight() {
        current = current.right.get();
    }

    public void turnLeft() {
        current = current.left.get();
    }

    public Point driveForwards(Point from) {
        return current.forwards.apply(from);
    }

    public Point driveBackwards(Point from) {
        return current.backwards.apply(from);
    }

    public Orientation getCurrent() {
        return current;
    }

    private static final class NorthOrientation extends Orientation {
        private NorthOrientation() {
            super(
                    from -> new Point(from.getX(), from.getY() + 1),
                    from -> new Point(from.getX(), from.getY() - 1),
                    () -> WEST,
                    () -> EAST
            );
        }
        public String toString() {
            return "NORTH";
        }
    }

    private static final class SouthOrientation extends Orientation {
        private SouthOrientation() {
            super(
                    from -> new Point(from.getX(), from.getY() - 1),
                    from -> new Point(from.getX(), from.getY() + 1),
                    () -> EAST,
                    () -> WEST);
        }
        public String toString() {
            return "SOUTH";
        }
    }

    private static final class EastOrientation extends Orientation {
        private EastOrientation() {
            super(
                    from -> new Point(from.getX() + 1, from.getY()),
                    from -> new Point(from.getX() - 1, from.getY()),
                    () -> NORTH,
                    () -> SOUTH);
        }
        public String toString() {
            return "EAST";
        }
    }

    private static final class WestOrientation extends Orientation {
        private WestOrientation() {
            super(
                    from -> new Point(from.getX() - 1, from.getY()),
                    from -> new Point(from.getX() + 1, from.getY()),
                    () -> SOUTH,
                    () -> NORTH);
        }
        public String toString() {
            return "WEST";
        }
    }

}
