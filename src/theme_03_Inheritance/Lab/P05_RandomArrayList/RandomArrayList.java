package theme_03_Inheritance.Lab.P05_RandomArrayList;

import java.util.ArrayList;

import java.util.concurrent.ThreadLocalRandom;

public class RandomArrayList<T> extends ArrayList<T> {

    public T getRandomElement() {
        int index = ThreadLocalRandom.current().nextInt(0, size());
        return this.remove(index);
    }
}
