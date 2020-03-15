import java.util.Date;

public class Main {
    public static void main(String[] args) {
        WatchCreator creator = getWatchCreator("Rome");
        creator.createWatch().showTime();

        creator = getWatchCreator("Digital");
        creator.createWatch().showTime();

        creator = getWatchCreator("abacaba");
    }

    public static WatchCreator getWatchCreator(String watchCreatorType) {
        if (watchCreatorType.equals("Digital")) {
            return new DigitalWatchCreator();
        } else if (watchCreatorType.equals("Rome")) {
            return new RomeWatchCreator();
        }

        throw new RuntimeException("Unacceptable watch creator type = " + watchCreatorType);
    }
}

interface Watch {
    void showTime();
}

class DigitalWatch implements Watch {
    @Override
    public void showTime() {
        System.out.println(new Date());
    }
}

class RomeWatch implements Watch {
    @Override
    public void showTime() {
        System.out.println("XV--II");
    }
}

interface WatchCreator {
    Watch createWatch();
}

class DigitalWatchCreator implements WatchCreator {
    @Override
    public Watch createWatch() {
        return new DigitalWatch();
    }
}

class RomeWatchCreator implements WatchCreator {
    @Override
    public Watch createWatch() {
        return new RomeWatch();
    }
}