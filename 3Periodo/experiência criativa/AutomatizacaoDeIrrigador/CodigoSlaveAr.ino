//CODIGO SLAVE AR
#include <Wire.h>
#include <LiquidCrystal.h>
const int ID_MASTER = 1;
const int ID_SLAVE_TEMP = 2;
const int PORTA_SENSOR_TEMP = A0;
bool leitura = false;
LiquidCrystal LCD(12,11,5,4,3,2);
void setup(){
	Wire.begin(ID_SLAVE_TEMP);
	Wire.onReceive(pedidoLeitura);
	Serial.begin(9600);
}
void loop(){
	if(leitura){
		byte temperatura = lerTemperatura();
		transmitirTemperatura(temperatura);
	}
	aguardaPedido();
}
float lerTemperatura(){
	int SensorTempTensao=analogRead(PORTA_SENSOR_TEMP);
	// Converte a tensao lida
	float Tensao = SensorTempTensao * 5;
	Tensao /= 1024;
	// Converte a tensao lida em Graus Celsius
	float temperatura = (Tensao - 0.5) * 100;
	return temperatura;
}
void transmitirTemperatura(byte temperatura){
	Serial.println("Enviando Temperatura");
	Wire.beginTransmission(ID_MASTER);
	Wire.write(temperatura);
	Wire.endTransmission();
	leitura = false;
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