#!/bin/bash
ps -ef|grep 'com.ever.server.Launcher'|grep -v 'grep'|awk '{print $2}'|xargs kill -9
echo 'SmsMail server stopped'
