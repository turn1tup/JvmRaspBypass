package com.test;

import sun.misc.Unsafe;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class Cmd {
    static byte[] helperpath;
    public static void linuxCmd(String[] var0,Method forkAndExec) throws Exception{
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);

        Unsafe unsafe = (Unsafe) theUnsafe.get(null);
        Class<?> UNIXProcessClass = Class.forName("java.lang.UNIXProcess");
        Class<?> ProcessImplClass = Class.forName("java.lang.ProcessImpl");
        Object unixProc = unsafe.allocateInstance(UNIXProcessClass);

        if (forkAndExec == null) {
            forkAndExec = UNIXProcessClass.getDeclaredMethod("forkAndExec",
            int.class, byte[].class, byte[].class, byte[].class, int.class, byte[].class, int.class, byte[].class, int[].class,
            boolean.class);
            forkAndExec.setAccessible(true);
        }


        byte[][] var5 = new byte[var0.length - 1][];
        int var6 = var5.length;

        for(int var7 = 0; var7 < var5.length; ++var7) {
            var5[var7] = var0[var7 + 1].getBytes();
            var6 += var5[var7].length;
        }

        byte[] var70 = new byte[var6];
        int var8 = 0;
        byte[][] var9 = var5;
        int var10 = var5.length;

        for(int var11 = 0; var11 < var10; ++var11) {
            byte[] var12 = var9[var11];
            System.arraycopy(var12, 0, var70, var8, var12.length);
            var8 += var12.length + 1;
        }
        int[] var71 = new int[1];
        Map<String, String> var1 = null;
        //byte[] var72 = ProcessEnvironment.toEnvironmentBlock(var1, var71);
        byte[] var72 = null;
        String var2 = null;

        boolean var4 = false;
        int[] var73 = new int[] { -1, -1, -1 };
        Method toCString = ProcessImplClass.getDeclaredMethod("toCString", String.class);
        toCString.setAccessible(true);

        Field helperpathField = UNIXProcessClass.getDeclaredField("helperpath");
        helperpathField.setAccessible(true);
        helperpath = (byte[]) helperpathField.get(null);
        ForkAndExec(forkAndExec, unixProc,
                (byte[])toCString.invoke(null, var0[0]), var70, var5.length,
                var72, var71[0],  (byte[])toCString.invoke(null, var2), var73, var4);

    }

    public static void ForkAndExec(
            Method forkAndExec,
            Object unixProc,
            byte[] var1, byte[] var2, int var3, byte[] var4, int var5, byte[] var6, int[] var7, boolean var8
    ) throws Exception {

//private static final UNIXProcess.LaunchMechanism launchMechanism;
        Class UNIXProcessClass = Class.forName("java.lang.UNIXProcess");
        Field launchMechanismField = UNIXProcessClass.getDeclaredField("launchMechanism");
        launchMechanismField.setAccessible(true);
        Object launchMechanism = launchMechanismField.get(unixProc);

        int firstPara = (int) Enum.class.getDeclaredMethod("ordinal").invoke(launchMechanism) + 1;
        forkAndExec.invoke(unixProc, firstPara , helperpath, var1, var2, var3, var4, var5, var6, var7, var8);

    }
    public static void winCmd(String cmdstr) throws Exception{

        //java.lang.Runtime.getRuntime().exec("calc");

        //String cmdstr = "calc";
        String envblock = null;
        String path = null;
        boolean redirectErrorStream = false;
        ProcessBuilder.Redirect[] redirects = null;
        long[] stdHandles;
        if (redirects == null) {
            stdHandles = new long[] { -1L, -1L, -1L };
        }else{
            stdHandles = new long[] { -1L, -1L, -1L };
        }

        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);

        Unsafe unsafe = (Unsafe) theUnsafe.get(null);
        Class ProcImplClass = Class.forName("java.lang.ProcessImpl");
        Object procImpl = unsafe.allocateInstance(ProcImplClass);
        Method create = ProcImplClass.getDeclaredMethod("create", String.class, String.class, String.class,
                long[].class, boolean.class);
        create.setAccessible(true);
        create.invoke(procImpl, cmdstr, envblock, path,
                stdHandles, redirectErrorStream);
//        handle = create(cmdstr, envblock, path,
//                stdHandles, redirectErrorStream);
    }
}
