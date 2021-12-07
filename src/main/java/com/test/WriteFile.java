package com.test;

import sun.misc.Unsafe;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

public class WriteFile {


    public static void maintest(String fileName) throws Exception{
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        FileOutputStream fileOutputStream = (FileOutputStream) unsafe.allocateInstance(FileOutputStream.class);
        File file = new File(fileName);
        String name = (file != null ? file.getPath() : null);
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkWrite(name);
        }
        if (name == null) {
            throw new NullPointerException();
        }
        boolean append = true;
//        if (file.isInvalid()) {
//            throw new FileNotFoundException("Invalid file path");
//        }
        Field fdField = FileOutputStream.class.getDeclaredField("fd");
        fdField.setAccessible(true);
        FileDescriptor fd = new FileDescriptor();
        fdField.set(fileOutputStream, fd);

        Method attachMethod = FileDescriptor.class.getDeclaredMethod("attach", Closeable.class);
        attachMethod.setAccessible(true);
        attachMethod.invoke(fd,fileOutputStream);
        //fd.attach(this);
        Field appendFiled = FileOutputStream.class.getDeclaredField("append");
        appendFiled.setAccessible(true);
        appendFiled.set(fileOutputStream,append);
//        this.append = append;
        Field pathField = FileOutputStream.class.getDeclaredField("path");
        pathField.setAccessible(true);
        pathField.set(fileOutputStream,name);
//        this.path = name;
        Method openMethod = FileOutputStream.class.getDeclaredMethod("open",String.class,boolean.class);
        openMethod.setAccessible(true);
        openMethod.invoke(fileOutputStream,name,append);
//        open(name, append);
        System.out.println("success");
        fileOutputStream.write("test".getBytes(StandardCharsets.UTF_8));
    }
}
