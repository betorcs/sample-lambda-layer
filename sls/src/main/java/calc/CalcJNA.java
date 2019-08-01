package calc;

import com.sun.jna.Library;

public interface CalcJNA extends Library {

    int sum(int a, int b);

    int mult(int a, int b);

}
