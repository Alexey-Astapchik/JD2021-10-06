package by.it.serkutsyev.jd01_09;

public abstract class Var implements Operation {
    @Override
    public String toString() {
        return "unknown variable (abstract stub)";
    }

    @Override
    public Var add(Var other) {
        System.out.printf("Operation %s + %s unsupported\n",this,other);
        return null;
    }

    @Override
    public Var sub(Var other) {
        System.out.printf("Operation %s - %s unsupported\n",this,other);
        return null;
    }

    @Override
    public Var mul(Var other) {
        System.out.printf("Operation %s * %s unsupported\n",this,other);
        return null;
    }

    @Override
    public Var div(Var other) {
        System.out.printf("Operation %s / %s unsupported\n",this,other);
        return null;
    }
}
