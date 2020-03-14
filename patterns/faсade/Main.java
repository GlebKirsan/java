public class Main {

    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.startCopyFromDisk();
    }
}

class Computer {
    Power power = new Power();
    DVDrom dvd = new DVDrom();
    HDD hdd = new HDD();

    void startCopyFromDisk() {
        power.on();
        dvd.load();
        hdd.copyDataFromDisk(dvd);
        dvd.unload();
        power.off();
    }
}

class Power {
    void on() {
        System.out.println("Power is turned on");
    }

    void off() {
        System.out.println("Power is turned off");
    }
}

class DVDrom {
    boolean loaded = false;

    void load() {
        System.out.println("Injecting disk");
        loaded = true;
    }

    void unload() {
        System.out.println("Ejecting disk");
        loaded = false;
    }

    boolean isLoaded() {
        return loaded;
    }
}

class HDD {
    void copyDataFromDisk(DVDrom disk) {
        if (disk.loaded) {
            System.out.println("Data from disk were copied");
        } else {
            System.out.println("Please, insert disk");
        }
    }
}