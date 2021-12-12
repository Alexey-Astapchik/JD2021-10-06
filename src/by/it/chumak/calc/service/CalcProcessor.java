package by.it.chumak.calc.service;

import by.it.chumak.calc.exception.CalcException;
import by.it.chumak.calc.model.Var;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CalcProcessor {

    public CalcProcessor() {
    }

    public Var calc(String operation, Var varLeftPartExpression, Var varRightPartExpression) throws CalcException {
        return switch (operation) {
            case "+" -> performCalculations("add", varLeftPartExpression, varRightPartExpression);
            case "-" -> performCalculations("sub", varLeftPartExpression, varRightPartExpression);
            case "*" -> performCalculations("mul", varLeftPartExpression, varRightPartExpression);
            case "/" -> performCalculations("div", varLeftPartExpression, varRightPartExpression);
            default -> null;
        };
    }

    @SuppressWarnings("deprecation")
    private Var performCalculations(String operation, Var varLeftPartExpression, Var varRightPartExpression) throws CalcException {
        String fullClassName = varLeftPartExpression.getClass().getPackageName() + "." + "MathExpressions";
        fullClassName = fullClassName.replace("model", "service");
        try {
            Class<?> desiredClass = Class.forName(fullClassName);
            Object instanceClass = desiredClass.newInstance();
            Class<?>[] arrayParameters = new Class[]{Var.class};
            Method method = desiredClass.getDeclaredMethod(operation, arrayParameters[0], arrayParameters[0]);
            Object abc = method.invoke(instanceClass, varLeftPartExpression, varRightPartExpression);
            return (Var) abc;
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException exception) {
            throw new CalcException(exception);
        }
    }

}
