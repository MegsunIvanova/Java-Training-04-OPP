package theme_03_Inheritance.Lab.P06_StackOfStrings;

public class Main {
    public static void main(String[] args) {
        StackOfStrings sos = new StackOfStrings();
        sos.push("one");
        sos.push("two");
        sos.push("three");

        System.out.println(sos.isEmpty());


        System.out.println(sos.pop());
        System.out.println(sos.pop());
        System.out.println(sos.pop());

    }
}
