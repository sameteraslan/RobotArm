#****************** Server ********************
import socket
import sys
import RPi.GPIO as GPIO
import time

GPIO.setmode(GPIO.BCM)
GPIO.setup(26, GPIO.OUT)
GPIO.setup(19, GPIO.OUT)
GPIO.setup(13, GPIO.OUT)
GPIO.setup(6, GPIO.OUT)
GPIO.setup(5, GPIO.OUT)
GPIO.setup(0, GPIO.OUT)


pwm0 = GPIO.PWM(0, 100)
pwm5 = GPIO.PWM(5, 100)
pwm6 = GPIO.PWM(6, 100)
pwm13 = GPIO.PWM(13, 100)
pwm19 = GPIO.PWM(19, 100)
pwm26 = GPIO.PWM(26, 100)
pwm0.start(5)
pwm5.start(5)
pwm6.start(5)
pwm13.start(5)
pwm19.start(5)
pwm26.start(5)

GPIO.setwarnings(False)
 
HOST = '192.168.43.188' #this is your localhost
PORT = 4900
 
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
#socket.socket: must use to create a socket.
#socket.AF_INET: Address Format, Internet = IP Addresses.
#socket.SOCK_STREAM: two-way, connection-based byte streams.
print 'socket created'
 
#Bind socket to Host and Port
try:
    s.bind((HOST, PORT))
except socket.error as err:
    print 'Bind Failed, Error Code: ' + str(err[0]) + ', Message: ' + err[1]
    sys.exit()
 
print 'Socket Bind Success!'
 

#listen(): This method sets up and start TCP listener.
s.listen(10)
print 'Socket is now listening'
 
def updateServo(a, b):
    
    duty = float(b) / 10.0 + 2.5
    if a == "0":
        pwm0.ChangeDutyCycle(duty)
    elif a == "1":
        pwm5.ChangeDutyCycle(duty)
    elif a == "2":
        pwm6.ChangeDutyCycle(duty)
    elif a == "3":
        pwm13.ChangeDutyCycle(duty)
    elif a == "4":
        pwm19.ChangeDutyCycle(duty)
    elif a == "5":
        pwm26.ChangeDutyCycle(duty)
    else:
        print 'hata'

while 1:
    conn, addr = s.accept()
    print 'Connect with ' + addr[0] + ':' + str(addr[1])
    buf = conn.recv(64)
    print buf
    #mFormat = "0$100/1$100/2$100/3$100/4$100/5$100"
    mListe = buf.split("/")
    for i in mListe:
        a = i.split("$")
        updateServo(a[0],a[1])
        
s.close()


 
#**************Client**************************
import socket
import sys
 
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect(('192.168.142.160', 8888)) #IP is the server IP
 
for args in sys.argv:
    if args == "":
        args = 'no args'
    else:
        s.send(args + ' ')
 
print 'goodbye!'
