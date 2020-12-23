#!/bin/bash

set -o errexit
set -o nounset

readonly APP_NAME="custom_admin"
readonly SELF_DIR=$(cd $(dirname $0) && pwd)
readonly MAIN_MODULE="com.upc.lw.AppRun"
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

function stop
{
    local -i timeout=60
    local -i interval=1
    local -r service_pid=$(get_pid) || true # ignore error
    local -i PID
    [[ -n $service_pid ]] || {
        echo "WARNING: process not found, nothing to stop" >&2
        exit 0
    }
    echo "*******1******* $service_pid"
    kill $service_pid
    while (( timeout > 0 )) && get_pid > $LOGDIR/$APP_NAME.log; do
        PID=$(get_pid);
            if [ $PID -eq 0 ]; then
                    echo "no process";
                    break;
            else
                    sleep $interval;
                    timeout=$(( timeout - interval ));
            fi
    done
    PID=$(get_pid);
    echo "*******2******* $PID"
    if [ $PID -gt 0 ]; then
        echo "WARNING: process still alive, sending SIGKILL ..." >&2
        kill -9 "$service_pid"
    fi
}

function main
{
    get_pid > $LOGDIR/$APP_NAME.log || {
        echo "WARNING: process not found, nothing to stop" >&2
        exit 0  # Ignore error
    }
    stop
}
main "$@"
