# Mensajes-en-LCD
Proyecto para mandar mensajes desde java a pantalla LCD con arduino.

Programa que muestra la hora, la temperatura, la fecha e imprime algun mensaje que el usuario manda desde una interfaz realizada en java, en una pantalla LCD y segun la temperatura registrada, se enciende un led ( si es calor y mucho calor se enciende el led naranja, si es agradable se encianede el led verde, si es frio y mucho frio se enciende el led azul). El texto se mueve a la izquierda y regresa a la derecha. Al enviarse el mensaje desde java, se limpia el JTextField(cuadro de texto) automaticamente, cuenta el numero de caracteres, no debe ser mayor a 32, de preferencia deben de ser mensajes menores o iguales a 16, al darle clic al boton de Fecha/Hora se muestra en la pantalla LCD la fecha y hora, y al darle clic al boton de temperatura, se muestra en la pantalla LCD la temperatura y un mensaje de si hace mucho calor, o calor o confort o frio o mucho frio segun sea la temperatura registrada y enciende los correspondientes leds. Ademas si la temperatura llega a ser mayor de 40 grados, suena una alarma, esto como un tipo de emergencia por si llega a haber un incendio.

MATERIALES:
- 3 leds
- 3 resistencias de 220 ohms
- 2 resistencias de 1 kilohms
- 1 TinyRTC I2C
- 1 Arduino UNO
- 1 Pantalla LCD 16x2
- 1 Potenciometro de 10 kilohms
- cables de conexion macho-macho
- 1 LM35
- 1 Buzzer

SOFTWARE UTILIZADO:
- Java NetBeans : Para hacer la interfaz. (Se puede descargar de este link: 
*Primero se descarga este link el jdk de java (se instala primero): https://www.java.com/es/download/
*Despues se descarcarga el IDE de netbeans (se instala segundo): https://netbeans.org/downloads/)
NOTA: se recomienda que descargues la primera opcion de netbeans para esta practica...
- Arduino (IDE de arduino) : Para programar codigo arduino. (Se puede descargar de este link: https://www.arduino.cc/en/Main/software)
- Fritzing : Para realizar los diagramas de los circuitos. (Se puede descargar de este link: http://fritzing.org/download/)

ARCHIVOS:
- Window.java : es el codigo java, al ejecutar muestra una ventana.
- THFM2.ino : es el codigo de arduino.
- PanamaHitek_Arduino-3.0.0.jar : es la libreria necesaria para poder hacer la coneccion con arduino, se pone en el proyecto de java Netbeans.

Conexiones por separado de cada componente:
![Esquema de la conexion con la pantalla LCD](https://github.com/Sarahi-Perez/Display-serial/blob/master/LCD-LM35.jpg "Esquema de la conexion con la pantalla LCD y el LM35")

![Esquema de Leds con RTC](https://github.com/Sarahi-Perez/Display-serial/blob/master/LEDS-RTC.jpg
 "Esquema de leds con RTC")
 
![Esquema del buzzer](https://github.com/Sarahi-Perez/Display-serial/blob/master/Buzzer.jpg "Esquema de la conexion del buzzer")
 
 ![Interfaz en Java](https://github.com/Sarahi-Perez/Display-serial/blob/master/java-arduino.jpg "Interfaz en Java")
 
 ![Imagen del circuito](https://github.com/Sarahi-Perez/Display-serial/blob/master/34347177_1702742139807887_8401201084630040576_n.jpg "Circuito armado")
 
 ![Imagen de mensajes](https://github.com/Sarahi-Perez/Display-serial/blob/master/30657331_1654206121328156_1496173654520627200_n.jpg "Mensajes")
 
![Imagen de la temperatura](https://github.com/Sarahi-Perez/Display-serial/blob/master/30689308_1654206111328157_4690709984994918400_n.jpg "Temperatura")

![Imagen de la fecha y hora](https://github.com/Sarahi-Perez/Display-serial/blob/master/30697784_1654206104661491_2917003674179862528_n.jpg "Fecha y hora")
