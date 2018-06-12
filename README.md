# MazkekaV2
Java application using Pi4J, to controll distillery

The app uses java and Pi4J library to control distillery using RaspberryPi.
You need to connect DS18b20 sensor to GPIO_4, and the output (Heater) pin is GPIO_21.
To use the source code, you will need to aquire firebase acount and json file.

for released version: 
1) Download Mazkeka.sh
2) Run chmod +x Mazkeka.sh
3) Run: sudo modprobe w1-gpio
4) Run: sudo modprobe w1-therm
5) (Optional) to run program at startup:   
6) run nano ~/.config/lxsession/LXDE-pi/autostart
7) @/path/Mazkeka.sh (Replace path with the path to Mazkeka.sh)

8) For OLED screen support, please enable I2C on raspbian
