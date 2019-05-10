import pigpio
import time
from threading import Thread
import socket
import sys
 
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
		#Servolar baslangic konumuna gore tanimlandi
        self.servo_0 = Servo("0",21,self.pi,self.f,1200)
        self.servo_1 = Servo("1",20,self.pi,self.f,1250)
        self.servo_2 = Servo("2",16,self.pi,self.f,1150)
        self.servo_3 = Servo("3",26,self.pi,self.f,900)
        self.servo_4 = Servo("4",19,self.pi,self.f,1700)
        self.servo_5 = Servo("5",13,self.pi,self.f,1400)
    
    def start(self):
        print("Starting robot arm")
	#Gelen servo numarasina(a), gelen aci degerini veriyor(b)	
    def updateServo(self, a, b):
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

HOST = '192.168.43.188' #this is your localhost
PORT = 4400 


#socket.socket: Socket olusturmak icin kullanilir.
#socket.AF_INET: Address Format, Internet = IP Addresses.
#socket.SOCK_STREAM: 2 yonlu (two way) connection based
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
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

# s.accept() : Client gelmesini bekler
conn, addr = s.accept()
while 1:
    print 'Connect with ' + addr[0] + ':' + str(addr[1])
    #buf : Client tarafindan gelen mesaj(text)
	buf = conn.recv(64)
    print buf
    #mFormat = 0$100$ : 0. servo 100 pwm
    a = buf.split("$")
    myarm.updateServo(a[0],a[1])
s.close()
myarm.pi.stop()
