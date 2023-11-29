#include <Wire.h>
#include <Keypad.h>
#include <Adafruit_NeoPixel.h>
#define MEU_ENDERECO 1
#define OUTRO_ENDERECO 2
#define PIN1 12 // input pin Neopixel is attached to
#define NUMPIXELS 12 // number of neopixels in Ring
Adafruit_NeoPixel pixels1 = Adafruit_NeoPixel(NUMPIXELS, PIN1, NEO_GRB + NEO_KHZ800);

bool vitoriaX = false;
bool vitoriaO = false;
bool erro = false;
char y;
char customKey;
const byte ROWS = 4;
const byte COLS = 4;
const byte linhas = 3;
const byte colunas = 3;
char keys[ROWS][COLS] = {
 {'1','2','3','+'},
 {'4','5','6','-'},
 {'7','8','9','*'},
 {'C','0','=','/'}
};
char tabuleiro[linhas][colunas] = {
 {'-', '-', '-'},
 {'-', '-', '-'},
 {'-', '-', '-'}
}; 
byte rowPins[ROWS] = {7,6,5,4}; //connect to the row pinouts of the keypad
byte colPins[COLS] = {3,2,A2,A3}; //connect to the column pinouts of the keypad
Keypad customKeypad = Keypad( makeKeymap(keys), rowPins, colPins, ROWS, COLS);
void AcenderLed(int posicao, bool ligado, int color1, int color2, int color3) {
 if (ligado){
 pixels1.setPixelColor(posicao, pixels1.Color(color1, color2, color3));
 pixels1.show();
 } else{
 pixels1.setPixelColor(posicao, pixels1.Color(0,0,0));
 pixels1.show();
 }
}
void checaLinhas(){
 if (tabuleiro[0][0] == 'X' && tabuleiro[0][1] == 'X' && tabuleiro[0][2] == 'X'){
 vitoriaX = true;
 }
 if (tabuleiro[1][0] == 'X' && tabuleiro[1][1] == 'X' && tabuleiro[1][2] == 'X'){
 vitoriaX = true;
 }
 if (tabuleiro[2][0] == 'X' && tabuleiro[2][1] == 'X' && tabuleiro[2][2] == 'X'){
 vitoriaX = true;
 }
 if (tabuleiro[0][0] == 'O' && tabuleiro[0][1] == 'O' && tabuleiro[0][2] == 'O'){
 vitoriaO = true;
 }
 if (tabuleiro[1][0] == 'O' && tabuleiro[1][1] == 'O' && tabuleiro[1][2] == 'O'){
 vitoriaO = true; 
 }
 if (tabuleiro[2][0] == 'O' && tabuleiro[2][1] == 'O' && tabuleiro[2][2] == 'O'){
 vitoriaO = true;
 }
}
void checaColunas(){
 if (tabuleiro[0][0] == 'X' && tabuleiro[1][0] == 'X' && tabuleiro[2][0] == 'X'){
 vitoriaX = true;
 }
 if (tabuleiro[0][1] == 'X' && tabuleiro[1][1] == 'X' && tabuleiro[2][1] == 'X'){
 vitoriaX = true;
 }
 if (tabuleiro[0][2] == 'X' && tabuleiro[1][2] == 'X' && tabuleiro[2][2] == 'X'){
 vitoriaX = true;
 }
 if (tabuleiro[0][0] == 'O' && tabuleiro[1][0] == 'O' && tabuleiro[2][0] == 'O'){
 vitoriaO = true;
 }
 if (tabuleiro[0][1] == 'O' && tabuleiro[1][1] == 'O' && tabuleiro[2][1] == 'O'){
 vitoriaO = true;
 }
 if (tabuleiro[0][2] == 'O' && tabuleiro[1][2] == 'O' && tabuleiro[2][2] == 'O'){
 vitoriaO = true;
 }
}
void checaDiagonais(){
 if (tabuleiro[0][0] == 'X' && tabuleiro[1][1] == 'X' && tabuleiro[2][2] == 'X'){
 vitoriaX = true;
 } 
 if (tabuleiro[0][2] == 'X' && tabuleiro[1][1] == 'X' && tabuleiro[2][0] == 'X'){
 vitoriaX = true;
 }
 if (tabuleiro[0][0] == 'O' && tabuleiro[1][1] == 'O' && tabuleiro[2][2] == 'O'){
 vitoriaO = true;
 }
 if (tabuleiro[0][2] == 'O' && tabuleiro[1][1] == 'O' && tabuleiro[2][0] == 'O'){
 vitoriaO = true;
 }
}
void setup()
{
 Wire.begin(MEU_ENDERECO);
 Wire.onReceive(receiveEvent);
 Wire.begin();
 Serial.begin(9600);
}
char x;
bool aguardando_mensagem;
void espera_mensagem()
{
 aguardando_mensagem = true;
 while (aguardando_mensagem) { delay(100); }
 Serial.println(y);
 switch(y){
 case '1':
 AcenderLed(0, true, 255, 255, 0);
 tabuleiro[0][0] = 'X'; 
 break;

 case '2':
 AcenderLed(1, true, 255, 255, 0);
 tabuleiro[0][1] = 'X';
 break;

 case '3':
 AcenderLed(2, true, 255, 255, 0);
 tabuleiro[0][2] = 'X';
 break;

 case '4':
 AcenderLed(4, true, 255, 255, 0);
 tabuleiro[1][0] = 'X';
 break;

 case '5':
 AcenderLed(5, true, 255, 255, 0);
 tabuleiro[1][1] = 'X';
 break;

 case '6':
 AcenderLed(6, true, 255, 255, 0);
 tabuleiro[1][2] = 'X';
 break;

 case '7':
 AcenderLed(8, true, 255, 255, 0);
 tabuleiro[2][0] = 'X';
 break; 

 case '8':
 AcenderLed(9, true, 255, 255, 0);
 tabuleiro[2][1] = 'X';
 break;

 case '9':
 AcenderLed(10, true, 255, 255, 0);
 tabuleiro[2][2] = 'X';
 break;
 }
}
void envia_mensagem()
{
 delay(random(100, 900));
 Serial.println("Digite algo: ");
 AcenderLed(3, true, 0, 255, 0);
 AcenderLed(7, true, 0, 255, 0);
 AcenderLed(11, true, 0, 255, 0);
 x = customKeypad.getKey();
 erro = false;
 while (erro == false) {
 x = NO_KEY;
 while(x == NO_KEY){
 delay(100);
 x = customKeypad.getKey();
 }
 switch(x){
 case '1':
 if (tabuleiro[0][0] == '-') { 
 Serial.println('1');
 AcenderLed(0, true, 255, 0, 255);
 tabuleiro[0][0] = 'O';
 erro = true;
 }
 break;
 case '2':
 if (tabuleiro[0][1] == '-') {
 AcenderLed(1, true, 255, 0, 255);
 tabuleiro[0][1] = 'O';
 erro = true;
 }
 break;
 case '3':
 if (tabuleiro[0][2] == '-') {
 AcenderLed(2, true, 255, 0, 255);
 tabuleiro[0][2] = 'O';
 erro = true;
 }
 break;
 case '4':
 if (tabuleiro[1][0] == '-') {
 AcenderLed(4, true, 255, 0, 255);
 tabuleiro[1][0] = 'O';
 erro = true;
 }
 break; 
 case '5':
 if (tabuleiro[1][1] == '-') {
 AcenderLed(5, true, 255, 0, 255);
 tabuleiro[1][1] = 'O';
 erro = true;
 }
 break;
 case '6':
 if (tabuleiro[1][2] == '-') {
 AcenderLed(6, true, 255, 0, 255);
 tabuleiro[1][2] = 'O';
 erro = true;
 }
 break;
 case '7':
 if (tabuleiro[2][0] == '-') {
 AcenderLed(8, true, 255, 0, 255);
 tabuleiro[2][0] = 'O';
 erro = true;
 }
 break;
 case '8':
 if (tabuleiro[2][1] == '-') {
 AcenderLed(9, true, 255, 0, 255);
 tabuleiro[2][1] = 'O';
 erro = true;
 }
 break; 
 case '9':
 if (tabuleiro[2][2] == '-') {
 AcenderLed(10, true, 255, 0, 255);
 tabuleiro[2][2] = 'O';
 erro = true;
 }
 break;
 }
 }
 checaLinhas();
 checaColunas();
 checaDiagonais();
 if (vitoriaX == true || vitoriaO == true) {
 Serial.println("Vitória.");
 while (1 == 1) {
	  AcenderLed(0, true, 0, 255, 0);
      AcenderLed(1, true, 0, 255, 0);
      AcenderLed(2, true, 0, 255, 0);
      AcenderLed(3, true, 0, 255, 0);
      AcenderLed(4, true, 0, 255, 0);
      AcenderLed(5, true, 0, 255, 0);
      AcenderLed(6, true, 0, 255, 0);
      AcenderLed(7, true, 0, 255, 0);
      AcenderLed(8, true, 0, 255, 0);
      AcenderLed(9, true, 0, 255, 0);
      AcenderLed(10, true, 0, 255, 0);
      AcenderLed(11, true, 0, 255, 0);
      delay(500);
      AcenderLed(0, false, 0, 255, 0);
      AcenderLed(1, false, 0, 255, 0);
      AcenderLed(2, false, 0, 255, 0);
      AcenderLed(3, false, 0, 255, 0);
      AcenderLed(4, false, 0, 255, 0);
      AcenderLed(5, false, 0, 255, 0);
      AcenderLed(6, false, 0, 255, 0);
      AcenderLed(7, false, 0, 255, 0);
      AcenderLed(8, false, 0, 255, 0);
      AcenderLed(9, false, 0, 255, 0);
      AcenderLed(10, false, 0, 255, 0);
      AcenderLed(11, false, 0, 255, 0);
 }
 }
 Wire.beginTransmission(OUTRO_ENDERECO); 
 
 Wire.write(x);
 Wire.endTransmission(); 
 AcenderLed(3, false, 0, 255, 0);
 AcenderLed(7, false, 0, 255, 0); 
 AcenderLed(11, false, 0, 255, 0);
}
void loop()
{
 espera_mensagem();
 envia_mensagem();
}
/
void receiveEvent(int howMany)
{
 y = Wire.read(); 
 aguardando_mensagem = false;
}