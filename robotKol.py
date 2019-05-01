import pigpio
import time
from threading import Thread
import socket
import sys

HOST = '192.168.43.188' #this is your localhost
PORT = 4400 
 
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


 
class Servo():
    def __init__(self,name,pin,pi,f,angle,time=0.003):
        try:
            self.name =name
            self.pi=pi
            self.pin=pin
            self.f=f
            self.time=time
            self.pi.set_mode(pin,pigpio.OUTPUT)
            self.pi.set_PWM_frequency(self.pin,self.f)
            self.angle=angle
            self.angle=self.pi.get_servo_pulsewidth(self.pin)
            self.set_angle(angle)
        except pigpio.error:
            print("initilize hatasi")
            self.pi.set_servo_pulsewidth(self.pin,self.angle)
    
    def set_angle(self, angle):
        print ("setangle" , angle,self.angle)
        if angle>self.angle:
            while(angle>self.angle):
                
                self.pi.set_servo_pulsewidth(self.pin,self.angle)
                self.angle+=1
                time.sleep(self.time)
        elif angle<self.angle:
            while(angle<self.angle):
            
                self.pi.set_servo_pulsewidth(self.pin,self.angle)
                self.angle-=1
                time.sleep(self.time)
       
        self.pi.set_servo_pulsewidth(self.pin,self.angle)
        print(self.name,self.pin,self.angle)
        
class Arm():
    def __init__(self):
        self.pi = pigpio.pi('localhost',8888)
        self.f=50
        self.servo_0 = Servo("0",21,self.pi,self.f,1200)
        self.servo_1 = Servo("1",20,self.pi,self.f,1250)
        self.servo_2 = Servo("2",16,self.pi,self.f,1150)
        self.servo_3 = Servo("3",26,self.pi,self.f,900)
        self.servo_4 = Servo("4",19,self.pi,self.f,1700) #1700 orta nokta
        self.servo_5 = Servo("5",13,self.pi,self.f,1400)
    
    def move4(self):
        self.servo_4.set_angle(1000)
        self.servo_4.set_angle(2400)
        self.servo_4.set_angle(1700)
    def move5(self):
        print("sam")
        self.servo_5.set_angle(1200)
        self.servo_5.set_angle(1800)
        self.servo_5.set_angle(1400)
    def some(self):
        print("sam")
    def start(self):
        print("Starting robot arm")
##        self.servo_5.set_angle(1900)
##        self.servo_1.set_angle(900)
##        self.servo_2.set_angle(1300)
##        self.servo_3.set_angle(1700)
##        self.servo_4.set_angle(1500)
##        self.servo_5.set_angle(1700)
        '''
        self.servo_1.set_angle(1390)
        self.servo_2.set_angle(1600)
        self.servo_1.set_angle(1600)
        self.servo_3.set_angle(1800)
        self.servo_2.set_angle(2000)
        self.servo_3.set_angle(1500)
        self.servo_2.set_angle(1800)
        self.servo_1.set_angle(1250)
        '''
        #self.servo_2.set_angle(1350)
        #self.servo_3.set_angle(1100)
        #self.servo_5.set_angle(2000)
        #self.servo_5.set_angle(1200)
        #self.servo_5.set_angle(1800)
        #self.servo_5.set_angle(1400)
        #self.servo_4.set_angle(1000)
        #self.servo_4.set_angle(2400)
        #self.servo_4.set_angle(1700)
        
        #self.servo_1.set_angle(1350)
        #self.servo_1.set_angle(1250)
        #self.servo_2.set_angle(900)
        #self.servo_3.set_angle(500)
        #self.servo_2.set_angle(1500)
        #self.servo_2.set_angle(1150)
        #self.servo_3.set_angle(1800)
        #self.servo_4.set_angle(1000)
        #self.servo_3.set_angle(900)
        #self.servo_4.set_angle(2400)
        #self.servo_4.set_angle(1700)
    def updateServo(self, a, b):
        
    ##    duty = float(b) / 10.0 + 2.5
        print b
        b = int(b)
        if a == "0":
            self.servo_0.set_angle(b)
        elif a == "1":
            self.servo_1.set_angle(b)
        elif a == "2":
            self.servo_2.set_angle(b)
        elif a == "3":
            self.servo_3.set_angle(b)
        elif a == "4":
            self.servo_4.set_angle(b)
        elif a == "5":
            self.servo_5.set_angle(b)
        else:
            print 'hata'        

        
 
myarm = Arm()
#myarm.start()

#servo4Thread = Thread(target = myarm.move4())
#servo5Thread = Thread(target = myarm.move5())
#servo4Thread.start()
#print("mustafa")
#servo5Thread.start()


conn, addr = s.accept()
while 1:
    print 'Connect with ' + addr[0] + ':' + str(addr[1])
    buf = conn.recv(64)
    print buf
    #mFormat = "0$100/1$100/2$100/3$100/4$100/5$100
    a = buf.split("$")
    myarm.updateServo(a[0],a[1])
        
s.close()

myarm.pi.stop()
##
