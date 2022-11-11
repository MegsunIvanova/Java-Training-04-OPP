package theme_05_Polymorphism.Exercises.P05_Calculator.calculator;

public interface Operation {
    void addOperand(int operand);
    int getResult();
    boolean isCompleted();
}
