trap "kill 0" EXIT
MASTER_PORT=./tty_master
socat PTY,link=$MASTER_PORT,raw,echo=0 PTY,link=./tty_slave,raw,echo=0,crnl &
MASTER_PORT_ABS=$(readlink -f $MASTER_PORT)
echo $MASTER_PORT_ABS
java -jar AppButton.jar $MASTER_PORT_ABS
wait