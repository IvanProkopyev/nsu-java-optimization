package com.company;

import org.objectweb.asm.*;

import java.io.*;

import static org.objectweb.asm.Opcodes.*;

public class Main {
    static File folder;
    public static void searchInFolder(File folder)  {
        File[] files = folder.listFiles();
     //   Class[] classes = null;

        for (File f: files) {
            InputStream binary = null;
            String fullClassName;// = null;
            try {
                binary = new FileInputStream(folder+ "/"+ f.getName());
                ClassReader reader = new ClassReader(binary);
                MyClassVisitor classVisitor = new MyClassVisitor();
                reader.accept(classVisitor, 0);
                fullClassName = classVisitor.getClassName();
                byte[] b = new byte[(int) f.length()];
                FileInputStream fileInputStream = new FileInputStream(f);
                fileInputStream.read(b);
                ByteArrayClassLoader byteArrayClassLoader = new ByteArrayClassLoader();
                Class c = byteArrayClassLoader.findClass(fullClassName, b);

               Adapter a = new Adapter();
                a.setC(c);
               reader.accept(a, 0);
/*
                ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
                classWriter.visit(Opcodes.V1_7, ACC_PUBLIC, c.getName(), null,
                        "java/lang/Object", null);
                MethodVisitor mv=classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
                mv.visitCode();
                mv.visitVarInsn(ALOAD, 0);
                mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");

                mv.visitInsn(RETURN);
                mv.visitMaxs(1,1);
                mv.visitEnd();
                */
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    binary.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    public static void main(String[] args) {
        folder = new File(args[0]);
        searchInFolder(folder);
    }
}
