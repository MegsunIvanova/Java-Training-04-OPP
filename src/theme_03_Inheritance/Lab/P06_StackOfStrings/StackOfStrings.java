package theme_03_Inheritance.Lab.P06_StackOfStrings;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class StackOfStrings {
    private ArrayList <String> data;

    public StackOfStrings() {
        this.data = new ArrayList<>();
    }

    public void push(String element) {
        data.add(element);
    }

    public String pop() {
        return data.remove(data.size()-1);
    }

    public String peek() {
        return data.get(data.size()-1);
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }
}
