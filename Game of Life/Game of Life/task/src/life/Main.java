package life;

import java.io.IOException;
import java.util.Scanner;
import java.util.Random;
import java.lang.Thread;

class Cell {
    private boolean state;

    public Cell() {
        this.state = false;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void Die() {
        this.state = false;
    }

    public void Alive() {
        this.state = true;
    }

    public boolean isAlive() {
        return state;
    }
}

class Universe {
    static private int[] shift = {-1, 0, 1};

    int size;
    Cell[][] universe;

    public Universe(int size) {
        this.size = size;
        universe = new Cell[this.size][this.size];

        for (int i = 0; i < this.size; ++i) {
            for (int j = 0; j < this.size; ++j) {
                universe[i][j] = new Cell();
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void printUniverse() {
        for (Cell[] row : universe) {
            for (Cell cell : row) {
                System.out.print(cell.isAlive() ? 'O' : ' ');
            }
            System.out.println();
        }
    }

    public Cell[] at(int i) {
        return universe[i];
    }

    public Cell at(int i, int j) {
        return universe[i][j];
    }

    public byte countAliveNeighbours(int i, int j) {
        byte alive = 0;

        for (int i_idx : shift) {
            for (int j_idx : shift) {
                if (i_idx == j_idx && i_idx == 0) {
                    continue;
                }

                int x = (i + i_idx) % size;
                if (x < 0) {
                    x = size - 1;
                }

                int y = (j + j_idx) % size;
                if (y < 0) {
                    y = size - 1;
                }
                if (universe[x][y].isAlive()) {
                    ++alive;
                }
            }
        }

        return alive;
    }

    static void copy(Universe from, Universe to) {
        int size = from.getSize();
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                Cell cell = to.at(i, j);
                cell.setState(from.at(i, j).isAlive());
            }
        }
    }
}

class UniverseGenerator {
    static long seed;

    static void setSeed(long seed) {
        UniverseGenerator.seed = seed;
    }

    static boolean fillCell(Random randomizer) {
        return randomizer.nextBoolean();
    }

    static void createUniverse(Universe universe) {
        Random randomize = new Random(seed);
        int size = universe.getSize();
        for (int i = 0; i < size; ++i) {
            Cell[] cells = universe.at(i);
            for (Cell cell : cells) {
                cell.setState(fillCell(randomize));
            }
        }
    }

    static void evolve(Universe universe) {
        int size = universe.getSize();
        Universe tmp = new Universe(size);
        Universe.copy(universe, tmp);

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                Cell cell = universe.at(i, j);
                byte aliveNeighbours = universe.countAliveNeighbours(i, j);

                if (cell.isAlive() && (aliveNeighbours < 2 || aliveNeighbours > 3)) {
                    tmp.at(i, j).Die();
                } else if (!cell.isAlive() && aliveNeighbours == 3) {
                    tmp.at(i, j).Alive();
                }
            }
        }

        Universe.copy(tmp, universe);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
//        long seed = in.nextLong();
//        int generations = in.nextInt();

        Universe universe = new Universe(size);
//        UniverseGenerator.setSeed(seed);
        UniverseGenerator.createUniverse(universe);
        for (int i = 0; i < 10; ++i) {
            UniverseGenerator.evolve(universe);
            universe.printUniverse();
            try {
                Thread.sleep(100);
                if (System.getProperty("os.name").contains("Windows")) {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } else {
                    Runtime.getRuntime().exec("clear");
                }
            } catch (IOException | InterruptedException e) {

            }
        }
    }


}
