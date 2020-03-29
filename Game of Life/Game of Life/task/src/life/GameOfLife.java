package life;

import javax.swing.*;
import java.awt.*;


public class GameOfLife extends JFrame {
    private int size = 150;
    Universe universe = new Universe(size);

    JPanel[][] cells = new JPanel[size][size];

    JPanel grid = new JPanel();
    JPanel labels = new JPanel();

    JLabel generationLabel = new JLabel();
    JLabel aliveLabel = new JLabel();

    private void initCells() {
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                cells[i][j] = new JPanel();
                cells[i][j].setBackground(Color.BLACK);
            }
        }
    }

    private void initGrid() {
        grid.setLayout(new GridLayout(size, size, 1, 1));
        grid.setBackground(Color.DARK_GRAY);
        add(grid, BorderLayout.CENTER);
        grid.setPreferredSize(new Dimension(getWidth(), getHeight()));

        for (JPanel[] row : cells) {
            for (JPanel cell : row) {
                grid.add(cell);
            }
        }
    }

    private void initLabels() {
        labels.setLayout(new BoxLayout(labels, BoxLayout.Y_AXIS));
        generationLabel.setName("GenerationLabel");
        aliveLabel.setName("AliveLabel");
        labels.add(generationLabel);
        labels.add(aliveLabel);

        labels.setBounds(10, 10, getWidth(), 25);

        add(labels, BorderLayout.NORTH);
    }

    private void init() {
        UniverseGenerator.createUniverse(universe);
        initCells();
        initGrid();
        initLabels();
    }

    public GameOfLife() {
        super("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setSize(1000, 1000);
        setLayout(new BorderLayout());
        init();
        setVisible(true);

        for (int generation = 1; generation <= 100000; ++generation) {
            recalcLabels(generation);
            repaintGrid();

            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
            UniverseGenerator.evolve(universe);
        }
    }

    private void recalcLabels(int generation) {
        String generation1 = "Generation #";
        generationLabel.setText(generation1 + generation);
        String alive = "Alive: ";
        aliveLabel.setText(alive + universe.getAlive());
    }

    private void repaintGrid() {
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                Cell cell = universe.at(i, j);
                Color color = cell.isAlive() ? Color.BLACK : Color.WHITE;
                cells[i][j].setBackground(color);
            }
        }
    }
}
