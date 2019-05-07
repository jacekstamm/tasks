#!/usr/bin/env bash

runBrowser() {
open http://localhost:8080/crud/v1/task/getTasks
}

fail() {
    echo "There were errors"
}

end() {
  echo "Work is finished"
}

if ~/Programowanie/tasks/runcrud.sh start; then
   echo "Successfully build gradle"
   echo "Successfully Tomcat run"
   runBrowser
else
   echo "Failed build gradle"
   fail
fi
