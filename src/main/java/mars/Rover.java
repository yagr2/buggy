package mars;

import java.util.Arrays;
import java.util.Objects;

public class Rover {

    private Point position;
    private Orientation orientation;

    public Rover(Point position, Orientation orientation) {
        this.position = position;
        this.orientation = new Orientation(orientation);
    }

    public Point getPosition() {
        return position;
    }

    public Orientation getOrientation() {
        return orientation.getCurrent();
    }


    public void drive(String command) {
        switch(command) {
            case "l":
                orientation.turnLeft();
                break;
            case "r":
                orientation.turnRight();
                break;
            case "f":
                position = orientation.driveForwards(position);
                break;
            case "b":
                position = orientation.driveBackwards(position);
                break;
        }
    }

    public void command(String commands) {
        Arrays.stream(commands.split(",")).forEach(command -> drive(command));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rover rover = (Rover) o;
        return Objects.equals(position, rover.position) &&
                Objects.equals(orientation.getCurrent(), rover.orientation.getCurrent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, orientation.getCurrent());
    }

    @Override
    public String toString() {
        return "Rover{" +
                "position=" + position +
                ", orientation='" + orientation.getCurrent() + '\'' +
                '}';
    }
}
