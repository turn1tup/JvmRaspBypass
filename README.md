# JvmRaspBypass


代码执行情况下，bypass Elkeid/OpenRASP 的检测

原理请看代码

Elkeid测试

java -javaagent:JVMProbeModified-1.0.jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar rasptest-1.0-SNAPSHOT.jar

socat UNIX-LISTEN:"/var/run/smith_agent.sock" -

