package by.it.konon.jd01_08;

import java.util.Arrays;

class Vector extends Var {
    private final double[] values;


    Vector(double[] values) {
        this.values = Arrays.copyOf(values, values.length);


    }

    @Override
    public Var add(Var other) {
        if (other instanceof Scalar) {
            double[] res = Arrays.copyOf(values, values.length);
            for (int i = 0; i < res.length; i++) {
                res[i] = res[i] + ((Scalar) other).getValue();

            }
            return new Vector(res);
        } else if (other instanceof Vector) {
            double[] res = Arrays.copyOf(values, values.length);
            for (int i = 0; i < res.length; i++) {
                res[i] = res[i] + ((Vector) other).values[i];
            }
            return new Vector(res);
        } else {
            return super.add(other);
        }
    }

    @Override
    public Var sub(Var other) {
        if (other instanceof Scalar) {
            double[] res = Arrays.copyOf(values, values.length);
            for (int i = 0; i < res.length; i++) {
                res[i] = res[i] - ((Scalar) other).getValue();

            }
            return new Vector(res);
        } else if (other instanceof Vector) {
            double[] res = Arrays.copyOf(values, values.length);
            for (int i = 0; i < res.length; i++) {
                res[i] = res[i] - ((Vector) other).values[i];
            }
            return new Vector(res);
        } else {
            return super.sub(other);
        }

    }

    @Override
    public Var mul(Var other) {
        if (other instanceof Scalar) {
            double[] res = Arrays.copyOf(values, values.length);
            for (int i = 0; i < res.length; i++) {
                res[i] = res[i] * ((Scalar) other).getValue();
            }
            return new Vector(res);
        } else if (other instanceof Vector) {
            double[] res = Arrays.copyOf(values, values.length);
            if (((Vector) other).values.length != this.values.length) {
                System.out.println("Incorrect operation");
            }
            double result = 0.0;
            for (int i = 0; i < res.length; i++) {
                res[i] = +res[i] * ((Vector) other).values[i];
                result += res[i];
            }

            return new Scalar(result);
        } else {
            return super.mul(other);

        }
    }

    @Override
    public Var div(Var other) {

        if (other instanceof Scalar) {
            if (((Scalar) other).getValue() != 0) {
                double[] res = Arrays.copyOf(values, values.length);
                for (int i = 0; i < res.length; i++) {
                    res[i] = res[i] / ((Scalar) other).getValue();
                }
                return new Vector(res);
            }

        }
        return super.div(other);
    }


    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("{");
        String delimiter = "";
        for (double value : values) {
            out.append(delimiter).append(value);
            delimiter = ", ";

        }
        out.append("}");
        return out.toString();
    }

    public Vector(Vector vector) {
        this.values = vector.values;
    }


}






