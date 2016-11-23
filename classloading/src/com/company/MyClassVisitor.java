package com.company;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.EmptyVisitor;

/**
 * Created by ivan on 06.11.16.
 */
public class MyClassVisitor extends EmptyVisitor {
    private String className;
//

    @Override

    public void visit(int version, int access, String name, String signature,
                      String superName, String[] interfaces) {
        className = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
