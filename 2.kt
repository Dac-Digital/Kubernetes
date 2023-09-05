apiVersion: v1
kind: Pod
metadata:
  name: port-forward
spec:
  containers:
 - name: port-forward
   image: alpine/socat:1.7.4.4
   command:
       - "/bin/sh"
       - "-c"
       - |
          socat tcp-listen:$LOCAL_PORT,reuseaddr,fork  tcp:$REMOTE_HOST:$REMOTE_PORT & pid=$! && trap "kill $pid" SIGINT && \
          echo "Socat started listening on $LOCAL_PORT: Redirecting traffic to $REMOTE_HOST:$REMOTE_PORT ($pid)" && wait $pid
   env:
     - name: REMOTE_HOST
         value: postgresql.database.cloud.net
     - name: REMOTE_PORT
         value: "5432"
     - name: LOCAL_PORT
         value: "5432"
