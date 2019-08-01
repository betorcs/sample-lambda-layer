package com.serverless;

import java.util.Objects;

public class Data {

    public static enum Operation { sum, mult}

    private Operation operation;
    private int a;
    private int b;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return a == data.a &&
                b == data.b &&
                operation == data.operation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(operation, a, b);
    }

    @Override
    public String toString() {
        return "Data{" +
                "operation=" + operation +
                ", a=" + a +
                ", b=" + b +
                '}';
    }

}
