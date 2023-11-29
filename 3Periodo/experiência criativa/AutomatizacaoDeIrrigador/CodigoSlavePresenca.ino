// PRESENCA
#include <LiquidCrystal.h>
#include <Wire.h>
const int lampada = 11;
const int ID_MASTER = 1;
const int ID_SLAVE_PIR = 3;
const int pinoPIR = 2;
bool leitura = false;
const int LCD_RS = 12;
const int LCD_E = 13;
const int LCD_DB4 = 9;
const int LCD_DB5 = A1;
const int LCD_DB6 = A2;
const int LCD_DB7 = A3;
LiquidCrystal lcd(LCD_RS, LCD_E, LCD_DB4, LCD_DB5, LCD_DB6, LCD_DB7);
void setup(){
	Wire.begin(ID_SLAVE_PIR);
	Wire.onReceive(pedidoLeitura);
	lcd.begin(16, 2);
	Serial.begin(9600);
	pinMode(pinoPIR, INPUT);
	pinMode(lampada, OUTPUT);
}
void loop(){
	if(leitura){
		transmitirPresenca();
		verificaLuz();
	}
	aguardaPedido();
}
void verificaLuz(){
	int luz = analogRead(0);
	if(luz <= 788){
		lcd.setCursor(0,1);
		lcd.print("DIA");
		digitalWrite(lampada,0);
	}
  	else{
		lcd.setCursor(0,1);
		lcd.print("NOITE");
		digitalWrite(lampada,1);
	}
	Serial.println(luz);
}
bool lerPresenca(){
	if(digitalRead(pinoPIR) == HIGH){
		lcd.clear();
		lcd.setCursor(0,0);
		lcd.print("HA PRESENCA");
		return true;
	}	
  	else{
		lcd.clear();
		lcd.setCursor(0,0);
		lcd.print("SEM PRESENCA");
		return false;
	}
}
void transmitirPresenca(){
	Wire.beginTransmission(ID_MASTER);
	Wire.write(lerPresenca());
	Wire.endTransmission();
}
void pedidoLeitura(int numBytesRecebidos){
	for (int count = 1; count < numBytesRecebidos; count++){
		char c = Wire.read();
		Serial.print(c);
	}
	char x = Wire.read();
	leitura = true;
}
void aguardaPedido(){
	leitura = false;
	Serial.println("Sem pedidos!");
	while (!leitura)delay(100);
}