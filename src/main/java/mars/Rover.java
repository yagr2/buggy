package mars;

import java.util.Arrays;
import java.util.Objects;

public class Rover {

    private Point position;
    private String direction;

    public Rover(Point position, String direction) {
        this.position = position;
        this.direction = direction;
    }

    public Point getPosition() {
        return position;
    }

    public String getDirection() {
        return direction;
    }

    private void turnLeft() {
        switch (direction) {
            case "N":
                direction = "W";
                break;
            case "S":
                direction = "E";
                break;
            case "E":
                direction = "N";
                break;
            case "W":
                direction = "S";
                break;
        }
    }

    private void turnRight() {
        switch (direction) {
            case "N":
                direction = "E";
                break;
            case "S":
                direction = "W";
                break;
            case "E":
                direction = "S";
                break;
            case "W":
                direction = "N";
                break;
        }
    }

    public void driveForwards() {
        switch (direction) {
            case "N":
                position = new Point(position.getX(), position.getY() + 1);
                break;
            case "S":
                position = new Point(position.getX(), position.getY() - 1);
                break;
            case "E":
                position = new Point(position.getX() + 1, position.getY());
                break;
            case "W":
                position = new Point(position.getX() -1, position.getY());
                break;
        }
    }

    public void drive(String command) {
        switch(command) {
            case "l":
                turnLeft();
                break;
            case "r":
                turnRight();
                break;
            case "f":
                driveForwards();
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
                Objects.equals(direction, rover.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, direction);
    }

    @Override
    public String toString() {
        return "Rover{" +
                "position=" + position +
                ", direction='" + direction + '\'' +
                '}';
    }
}
