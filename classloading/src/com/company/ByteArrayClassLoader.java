package com.company;

/**
 * Created by ivan on 06.11.16.
 */
public class ByteArrayClassLoader extends ClassLoader {
    public Class findClass(String name, byte[] b) {
       name= name.replace("/", ".");
        return (defineClass(name,b,0,b.length));
    }
}
