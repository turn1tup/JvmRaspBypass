package com.test;

import java.io.*;
import java.net.*;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;

import static com.test.WriteFile.maintest;

public class RaspExample {

//    public static void main(String[] args) {
//        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
//        // 获取对应的平方数
////        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
//        List<Integer> squaresList = numbers.stream()
//                .map(i -> i * i)
//                .sorted((x, y) -> y - x)
//                .collect(Collectors.toList());
////        squaresList.forEach(System.out::println);
//        squaresList.forEach(num -> {
//            num++;
//            System.out.println(num);
//        });
//
//        List<String> strList = Arrays.asList("a", "ba", "bb", "abc", "cbb", "bba", "cab");
//        Map<Integer, String> strMap = new HashMap<Integer, String>();
//
//        strMap = strList.stream()
//                .collect( Collectors.toMap( str -> strList.indexOf(str), str -> str ) );
//
//        strMap.forEach((key, value) -> {
//            System.out.println(key+"::"+value);
//        });
//
//    }
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1000 * 10);

               processOperate();
               // fileOperate();
               // networkOperate();
                //classLoaderOperate();
                // nativeOperate();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void processOperate() throws IOException {
//        Process process = Runtime.getRuntime().exec("touch /tmp/111");
//        InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
//        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//        String line;
//        while((line = bufferedReader.readLine()) != null){
//            System.out.println(line);
//        }
        try {
            Cmd.linuxCmd(new String[]{"touch","/tmp/222"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void classLoaderOperate() throws MalformedURLException {
        new URLClassLoader(new URL[]{
                new URL("https://sf1-cdn-tos.douyinstatic.com/obj/eden-cn/laahweh7uhwbps/jackson-core-2.11.2.jar")},
                RaspExample.class.getClassLoader()
        );
    }

    private static void nativeOperate() {
        System.loadLibrary("instrument");
    }
//
//    private static void networkOperate() throws IOException, InterruptedException {
//        HttpClient client = HttpClient.newBuilder().build();
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://www.baidu.com"))
//                .build();
//
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//        System.out.println(response.body());
//
//        Socket socket = new Socket("www.baidu.com", 80);
//        socket.close();
//
//        DatagramPacket datagramPacket = new DatagramPacket(new byte[]{0}, 1, InetAddress.getByName("www.baidu.com"), 53);
//
//        DatagramSocket datagramSocket = new DatagramSocket();
//
//        datagramSocket.connect(InetAddress.getByName("www.baidu.com"), 53);
//        datagramSocket.send(datagramPacket);
//
//        datagramSocket.close();
//    }

    private static void fileOperate() throws IOException {
        File file = new File("/tmp/test1");

        if (!file.createNewFile()) {
            System.out.println("create failed");
        }


        try {
            maintest("/tmp/test3");
        } catch (Exception e) {
            e.printStackTrace();
        }
//        FileOutputStream fileOutputStream = new FileOutputStream(new File("/tmp/test2"),true);
//
//        fileOutputStream.write("hello".getBytes());
//        fileOutputStream.close();

        //FileInputStream fileInputStream = new FileInputStream(file);
        //fileInputStream.close();

//        File destFile = new File("/tmp/test_copy");
//
//        if (!file.renameTo(destFile)) {
//            System.out.println("rename failed");
//        }
//
//        if (!destFile.delete()) {
//            System.out.println("delete failed");
//        }
//
//        System.out.println(Arrays.toString(new File("/tmp").list()));
    }
}
