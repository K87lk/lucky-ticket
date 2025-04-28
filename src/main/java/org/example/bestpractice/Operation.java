package org.example.bestpractice;

public enum Operation {

    ADD {
        @Override
        public double calculate(double a, double b) {
            return a + b;
        }
    },
    SUBTRACT {
        @Override
        public double calculate(double a, double b) {
            return a - b;
        }
    },
    MULTIPLY {
        @Override
        public double calculate(double a, double b) {
            return a * b;
        }
    },
    DIVIDE {
        @Override
        public double calculate(double a, double b) {
            return a / b;
        }
    };

    public abstract double calculate(double a, double b);
}