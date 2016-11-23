package com.company;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.EmptyVisitor;

import java.lang.reflect.InvocationTargetException;


public class Adapter extends EmptyVisitor{

    private static final String NAME = "getSecurityMessage";

    private Class c;

    public Class getC() {
        return c;
    }

    public void setC(Class c) {
        this.c = c;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv;
        mv = super.visitMethod(access, name, desc, signature, exceptions);
        if (mv != null && name.equals(NAME)) {
            try {
                Object o = c.newInstance();
                System.out.println(o.getClass().getMethod(NAME).invoke(o));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mv;
    }
}