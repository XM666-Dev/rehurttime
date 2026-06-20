package com.xm666.rehurttime.handler;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.runtime.JavaMethodReflectionFunctionMissing;
import com.googlecode.aviator.runtime.function.ClassMethodFunction;
import com.googlecode.aviator.utils.Reflector;

public class EvaluatorHandler {
    static {
        AviatorEvaluator.setFunctionMissing(JavaMethodReflectionFunctionMissing.getInstance());
        try {
            addMethodFunctions();
        } catch (IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addMethodFunctions() throws IllegalAccessException, NoSuchMethodException {
        var instance = AviatorEvaluator.getInstance();
        var methodMap = Reflector.findMethodsFromClass(ExpressionHandler.class, true);
        for (var entry : methodMap.entrySet()) {
            var methodName = entry.getKey();
            var methods = entry.getValue();
            methods.add(null);
            instance.addFunction(new ClassMethodFunction((Class<?>) ExpressionHandler.class, true, methodName, methodName, methods));
            methods.removeLast();
        }
    }

    public static Object execute(String expression, Object... values) {
        return AviatorEvaluator.execute(expression, AviatorEvaluator.newEnv(values), true);
    }
}
