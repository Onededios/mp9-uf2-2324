package a1;

import java.util.concurrent.Callable;

public class Multiplicacio {
    private int op1, op2;
    public Multiplicacio(int op1, int op2) {
        this.op1=op1;
        this.op2=op2;
    }
    public int multiply() {
        return op1*op2;
    }
}