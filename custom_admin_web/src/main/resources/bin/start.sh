#!/bin/bash
set -o errexit

BASEDIR=$(cd $(dirname $0) && pwd)/..            # 获取运行脚本的上级目录

readonly APP_NAME="custom_admin"                        #定义当前应用的名称
readonly JAVA_HOME="/opt/appuser/jdk1.8/jdk1.8.0_251" # java home
readonly JAVA="$JAVA_HOME/bin/java"
readonly MAIN_MODULE="com.upc.lw.AppRun"       # main 函数所在的类名称。
readonly LOGDIR="/opt/application/logs"
# 应用启动日志
readonly LOGFILE="$LOGDIR/$APP_NAME.log"

# 创建应用日志目录
if [ ! -d "$LOGDIR" ] ;then
  mkdir "$LOGDIR"
  if [ $? -ne 0 ] ;then
    echo "Cannot create $LOGDIR" >&2
    exit 1
  fi
fi

# 获取当前应用的进程 id
function get_pid
{
    #pgrep -f "java .*$MAIN_MODULE"
    ps -ef | grep $MAIN_MODULE | grep -v 'grep'| awk '{print $2}'
}


# FIXME: make this configurable
OPTS_MEMORY="-Xms2G -Xmx2G -server -XX:MaxPermSize=256M -Xss256K"       #定义启动 jvm 的参数信息。
CLASSPATH="$BASEDIR/conf/:$BASEDIR/lib/*"

[[ -z $(get_pid) ]] || {
    echo "ERROR:  $APP_NAME already running" >&2
    exit 1
}

echo "Starting $APP_NAME ...."
[[ -x $JAVA ]] || {
    echo "ERROR: no executable java found at $JAVA" >&2
    exit 1
}


echo "************** $JAVA ...."
echo "************** $OPTS_MEMORY ...."
echo "************** $CLASSPATH ...."
echo "************** $BASEDIR ...."
echo "************** $MAIN_MODULE ...."
echo "************** $@ ...."


cd $BASEDIR
setsid "$JAVA" $OPTS_MEMORY \
    -classpath "$CLASSPATH" \
    -Dbasedir="$BASEDIR" \
    -Dfile.encoding="UTF-8" \
    $MAIN_MODULE \
    "$@" > $LOGDIR/$APP_NAME.log 2>&1 &

sleep 10.5
[[ -n $(get_pid) ]] || {
    echo "ERROR: $APP_NAME failed to start" >&2
    exit 1
}
echo "$APP_NAME is up runnig :)"