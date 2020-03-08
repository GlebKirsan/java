import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Objects;

public class Main {
    class Animal implements Serializable {
        private final String name;

        public Animal(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Animal) {
                return Objects.equals(name, ((Animal) obj).name);
            }
            return false;
        }
    }

    public static Animal[] deserializeAnimalArray(byte[] data) {
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
            int size = ois.readInt();
            Animal[] animals = new Animal[size];
            for (int i = 0; i < size; ++i) {
                animals[i] = (Animal) ois.readObject();
            }
            return animals;
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            throw new IllegalArgumentException();
        }

    }

    public static void main(String[] args) {

    }
}
