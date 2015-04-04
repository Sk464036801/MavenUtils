#!/bin/bash
# startup smsMail server shell.

if [ -z "$JAVA_HOME" ];then
	echo "Usage: enviromental JAVA_HOME is not exists,please set it !"
	exit
fi

SERVER_HOME=`cd .. && pwd`
export SERVER_HOME

PATH=$SERVER_HOME/bin:$PATH
export PATH

CLASSPATH=$SERVER_HOME/lib:$CLASSPATH
export CLASSPATH

LIBPATH=$SERVER_HOME/lib
for file in ${LIBPATH}/*.jar;
do
CLASSPATH=$CLASSPATH:$file
done
export CLASSPATH

cd $SERVER_HOME
nohup ${JAVA_HOME}/bin/java -Xmx400m com.ever.server.Launcher > /dev/null 2>&1 &
echo 'SmsMail server started'
