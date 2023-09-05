socat tcp-listen:81,fork,reuseaddr tcp:192.168.1.10:80
# where:
# 81 is port number on local machine
# 192.168.1.10:80 is remote host
