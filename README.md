# JvmRaspBypass


代码执行情况下，bypass Elkeid/OpenRASP/jrasp(native-hook) 的检测

原理请看代码

Elkeid测试

java -javaagent:JVMProbe.jar -jar rasptest-1.0-SNAPSHOT.jar

socat UNIX-LISTEN:"/var/run/smith_agent.sock" -

https://tech.meituan.com/2019/02/14/talk-about-java-magic-class-unsafe.html
