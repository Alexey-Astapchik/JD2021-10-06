package by.it.chumak.calc.service;

import by.it.chumak.calc.constant.Patterns;
import by.it.chumak.calc.exception.CalcException;
import by.it.chumak.calc.model.ResourceManager;
import by.it.chumak.calc.model.Var;
import by.it.chumak.calc.repository.VarRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private final Map<String, Integer> map = Map.of(
            "=", 0,
            "+", 1,
            "-", 1,
            "*", 2,
            "/", 2
    );

    private VarRepository varRepository;
    private VarCreator varCreator;

    public Parser() {
    }

    public Var evaluate(ResourceManager resourceManager, String expression, VarRepository varRepository) throws CalcException {
        this.varRepository = varRepository;
        this.varCreator = new VarCreator(varRepository);
        StringBuilder stringBuilder = new StringBuilder(expression);
        Matcher matcher = Pattern.compile(Patterns.MATH_EXPRESSION_IN_PARENTHESES).matcher(stringBuilder.toString());

        while (matcher.find()) {
            stringBuilder.replace(matcher.start(), matcher.end(), processOperands(resourceManager, matcher.group()));
            matcher.reset(stringBuilder.toString());
        }

        return varCreator.create(processOperands(resourceManager, stringBuilder.toString()).replaceAll(" ", ""));
    }

    private String processOperands(ResourceManager resourceManager, String expression) throws CalcException {
        expression = expression.replaceAll("\\(", "").replaceAll("\\)", "");
        List<String> operands = new ArrayList<>(List.of(expression.split(Patterns.OPERATION)));
        List<String> operations = new ArrayList<>();

        Matcher matcher = Pattern.compile(Patterns.OPERATION).matcher(expression);
        while (matcher.find()) {
            operations.add(matcher.group());
        }

        while (operations.size() > 0) {
            int index = getIndex(operations);
            String operation = operations.remove(index);
            String left = operands.remove(index).replaceAll(" ", "");
            String right = operands.remove(index).replaceAll(" ", "");
            Var var = oneOperation(resourceManager, left, operation, right);
            operands.add(index, var.toString());
        }

        return operands.get(0);
    }

    private Var oneOperation(ResourceManager resourceManager, String stingLeftVar, String operation, String stingRightVar) throws CalcException {
        CalcProcessor calcProcessor = new CalcProcessor();
        Var right = varCreator.create(stingRightVar);
        if (operation.equals("=")) {
            varRepository.save(stingLeftVar, right);
            return right;
        }

        Var left = varCreator.create(stingLeftVar);
        return calcProcessor.calc(operation, left, right, resourceManager);
    }

    private int getIndex(List<String> operation) {
        int index = -1;
        int pr = -1;
        for (int i = 0; i < operation.size(); i++) {
            String currentOperation = operation.get(i);
            Integer currentPriority = map.get(currentOperation);
            if (pr < currentPriority) {
                index = i;
                pr = currentPriority;
            }
        }
        return index;
    }
}
