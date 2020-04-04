#!/bin/sh

##项目服务名称

SERVICE_NAME=web-folder-upload

# java虚拟机启动参数

JAVA_OPTS="-ms512m -mx512m -Xmn256m -Djava.awt.headless=true -XX:MaxPermSize=128m"

## Other

JAR_NAME=$SERVICE_NAME\.jar
JAR_HOME=jar/

##pid监控文件 内容是程序的pid
PID=$SERVICE_NAME\.pid


## 日志文件名称
LOG_NAME=catalina.log
##服务目录
SERVICE_LOGS=logs



## 使用说明,用来提示输入参数

usage(){
   echo "Usage: sh 执行脚本.sh [start|stop|restart|status]"
   exit 1
}


## 检查程序是否在运行

is_exist(){
  pid=`ps -ef |grep $JAR_NAME |grep -v grep|awk '{print $2}' `
  # 如果不存在返回1 存在返回0
  if [[ -z "$pid" ]]; then
    return 1
  else
    return 0
   fi
}

#启动方法

start(){
  is_exist
  if [[ $? -eq "0" ]]; then
    echo ">>> ${JAR_NAME} is already running PID=${pid} <<<"
  else
    nohup java $JAVA_OPTS -jar $JAR_HOME$JAR_NAME >>$SERVICE_LOGS/$LOG_NAME 2>&1 &
    echo $1 > $PID
    ## 根据传参 判断 是自动构建工具还是直接运行  构建工具 需要睡眠5s,然后 打印出最后100行
    if [[ $1 == "ci" ]]; then
    sleep 5s
     tail  -n 100 $SERVICE_LOGS/$LOG_NAME
    else
    tail -f $SERVICE_LOGS/$LOG_NAME
    fi
    echo ">>> start $JAR_NAME successed PID=$! <<<"
  fi
}

#停止方法
stop(){
  #is_exist
  pidf=$(cat $PID)
  #echo "$pidf"
  echo ">>> api PID = $pidf begin kill $pidf <<<"
  kill -15 $pidf
  rm -rf $PID
  sleep 2
  is_exist
  if [ $? -eq "0" ]; then
    echo ">>> api 2 PID = $pid begin kill -9 $pid  <<<"
    kill -9  $pid
    sleep 2
    echo ">>> $JAR_NAME process stopped <<<"
  else
    echo ">>> ${JAR_NAME} is not running <<<"
  fi
}

#输出运行状态
status(){
  is_exist
  if [ $? -eq "0" ]; then
    echo ">>> ${JAR_NAME} is running PID is ${pid} <<<"
  else
    echo ">>> ${JAR_NAME} is not running <<<"
  fi
}

#重启
restart(){
  stop
  start $1
}

#根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$1" in
  "start")
    start "$2"
    ;;
  "stop")
    stop
    ;;
  "status")
    status
    ;;
  "restart")
    restart "$2"
    ;;
  *)
    usage
    ;;
esac
exit 0