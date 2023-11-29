// MASTER
#include <LiquidCrystal.h>
#include <Wire.h>
#include <Servo.h>
const int ID_MASTER = 1;
const int ID_SLAVE_TEMP = 2;
const int ID_SLAVE_PIR = 3;
const int PORTA_MOTOR = 3;
float potencia = 0;
const int POTENCIA_MAXIMA = 1023;
const int POTENCIA_NORMAL = 400;
const int POTENCIA_BAIXA = 100;
Servo janela;
int posicao = 0;
byte temperatura;
byte presenca;
const int LCD_RS = 12;
const int LCD_E = 13;
const int LCD_DB4 = A0;
const int LCD_DB5 = A1;
const int LCD_DB6 = A2;
const int LCD_DB7 = A3;
LiquidCrystal lcd(LCD_RS, LCD_E, LCD_DB4, LCD_DB5, LCD_DB6, LCD_DB7);
byte infotemp, infopir;
boolean status = false;
boolean recebeu_msg = false;
enum{
	OCIOSO,
	ATUALIZANDO_AR, PEDINDO_SLAVE1,PEDINDO_SLAVE2,
	ESPERANDO_SLAVE1, ESPERANDO_SLAVE2,
	ATUALIZANDO_JANELA} estado = OCIOSO;
void setup(){
	janela.attach(7);
	pinMode(2,INPUT);
	attachInterrupt(0, statusAr, RISING);
	Wire.begin(ID_MASTER);
	Wire.onReceive(receberMensagem);
	lcd.begin(16, 2);
	lcd.setCursor(0,1);
	Serial.begin(9600);
}
void statusAr(){
	status = !status;
	if(status){
		estado = PEDINDO_SLAVE1;
	}	
  	else{
		estado = OCIOSO;
	}
	Serial.println(status);
}
void loop(){
	switch(estado){
		case OCIOSO:
			lcd.clear();
			lcd.setCursor(0,0);
			lcd.print("AR - Desligado");
			while(status == false){
				delay(1000);
			}
			lcd.clear();
			lcd.setCursor(0,0);
			lcd.print("Temperatura");
			break;
		case PEDINDO_SLAVE1:
			if(status){
				slaveTemp();
				estado = ESPERANDO_SLAVE1;
			}
      		else{
				estado = OCIOSO;
			}
			break;
		case PEDINDO_SLAVE2:
			slavePIR();
			estado = ESPERANDO_SLAVE2;
			break;
		case ESPERANDO_SLAVE1:
 			while(!recebeu_msg);
			Serial.print("Temp recebida: ");
			Serial.println(infotemp);
			temperatura = infotemp;
			recebeu_msg = false;
			estado = PEDINDO_SLAVE2;
			break;
		case ESPERANDO_SLAVE2:
			while(!recebeu_msg);
			Serial.print("Presenca recebida: ");
			Serial.println(infopir);
			presenca = infopir;
			recebeu_msg = false;
			estado = ATUALIZANDO_AR;
			break;
		case ATUALIZANDO_AR:
			lcd.setCursor(0,1);
			lcd.print(temperatura);
			lcd.print(" C");
			if(infopir == 1){
				lcd.setCursor(6,1);
				lcd.print("P:SIM");
				if(temperatura > 0 && temperatura < 25){
					potencia = POTENCIA_BAIXA;
					lcd.setCursor(13,1);
					lcd.print("MIN");
					Serial.println("TEMPERATURA baixa utilizando pouca potencia");
				}
              	else if(temperatura >= 25 && temperatura <= 39){
					potencia = POTENCIA_NORMAL;
					lcd.setCursor(13,1);
					lcd.print("NOR");
					Serial.println("TEMPERATURA RAZOAVEL utilizando potencia normal");
				}
              	else if(temperatura >= 40){
					estado_janela(false);
					potencia = POTENCIA_MAXIMA;
					lcd.setCursor(13,1);
					lcd.print("MAX");
					Serial.println("TEMPERATURA ALTA utilizando potencia maxima");
				}
			}
      		else{
 				potencia = POTENCIA_BAIXA;
				lcd.setCursor(6,1);
				lcd.print("P:NAO");
				lcd.setCursor(13,1);
				lcd.print("MIN");
				Serial.println("SEM PRESENCA utilizando pouca potencia");
            }
			analogWrite(PORTA_MOTOR, potencia);
			estado = PEDINDO_SLAVE1;
			break;
	}
}
void slaveTemp(){
	Serial.println("Chamando slave Temperatura");
	Wire.beginTransmission(ID_SLAVE_TEMP);
	Wire.write('Master Requisitando Temperatura');
	Wire.endTransmission();
}
void slavePIR(){
	Serial.println("Chamando slave PIR");
	Wire.beginTransmission(ID_SLAVE_PIR);
	Wire.write('Master Requisitando PRESENCA');
	Wire.endTransmission();
}
void receberMensagem(int numBytesRecebidos){
	byte info = Wire.read();
	if (estado == ESPERANDO_SLAVE2){
		infopir = info;
	}	
 	else{
	infotemp = info;
	}
	recebeu_msg = true;
}
void estado_janela(bool abrir){
	if(abrir){
		while(posicao <=180){
			posicao++;
			janela.write(posicao);
			delay(10);
		}
	}
  else{
	while(posicao >=0){
		janela.write(posicao);
		posicao--;
		delay(10);
	}
}
}