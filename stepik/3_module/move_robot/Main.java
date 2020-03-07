public class Main {

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public abstract class Robot {

        abstract public Direction getDirection(); // текущее направление взгляда

        abstract public int getX();               // текущая координата X

        abstract public int getY();               // текущая координата Y

        public void turnLeft() {
            // повернуться на 90 градусов против часовой стрелки
        }

        public void turnRight() {
            // повернуться на 90 градусов по часовой стрелке
        }

        public void stepForward() {
            // шаг в направлении взгляда
            // за один шаг робот изменяет одну свою координату на единицу
        }
    }

    public static void moveRobot(Robot robot, int toX, int toY) {
        int dx = robot.getX() - toX;
        if (dx < 0) {
            while (robot.getDirection() != Direction.RIGHT) {
                robot.turnRight();
            }
        } else if (dx > 0) {
            while (robot.getDirection() != Direction.LEFT) {
                robot.turnLeft();
            }
        }
    
        while (robot.getX() != toX) {
            robot.stepForward();
        }
    
        int dy = robot.getY() - toY;
        if (dy < 0) {
            while (robot.getDirection() != Direction.UP) {
                robot.turnRight();
            }
        } else if (dy > 0) {
            while (robot.getDirection() != Direction.DOWN) {
                robot.turnLeft();
            }
        }
        while (robot.getY() != toY) {
            robot.stepForward();
        }
    }
}