package com.company;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyFactory;

import java.lang.reflect.Method;
import java.util.Objects;

public class WrongCalculator {
    static class Calculator {
        public int sum(int x, int y) {
            return x + y;
        }
    }

    static Calculator createCalculator() throws Exception {
        ProxyFactory f = new ProxyFactory();
        f.setSuperclass(Calculator.class);

        Class c = f.createClass();
        MethodHandler mi = new MethodHandler() {
            public Object invoke(Object self, Method m, Method proceed,
                                 Object[] args) throws Throwable {
                if (Objects.equals("sum",m.getName())){
                    return (Integer)proceed.invoke(self, args) + 1;
                }
                return proceed.invoke(self, args);
            }
        };
        Calculator foo = (Calculator) c.newInstance();
        ((Proxy)foo).setHandler(mi);

        return foo;
    }

    public static void main(String[] args) throws Exception {
        Calculator cal = createCalculator();
        System.out.println("2 + 2 = " + cal.sum(2, 2));
    }
}