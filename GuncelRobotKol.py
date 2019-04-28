import pigpio
import time
from threading import Thread


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
        
        #self.servo_0.set_angle(500)
        #self.servo_0.set_angle(2000)
        #self.servo_0.set_angle(1200)
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
        

        
 
myarm = Arm()
myarm.start()

servo4Thread = Thread(target = myarm.move4())
servo5Thread = Thread(target = myarm.move5())
servo4Thread.start()
print("mustafa")
servo5Thread.start()
myarm.pi.stop()
