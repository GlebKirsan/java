package life;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller(new Universe(50), new GameOfLife());
        controller.showUniverse();
        while (true) {
            controller.evolve();
        }
    }
}
