#include <Keypad.h>
#include <Wire.h>
#include <LiquidCrystal.h>
LiquidCrystal lcd(13, 12, 11, 10, 9, 8);
long first = 0;
long second = 0;
long total = 0;
char key;

const byte ROWS = 4;
const byte COLS = 4;
char keys[ROWS][COLS] = {
    {'1','2','3','+'},
  {'4','5','6','-'},
  {'7','8','9','*'},
  {'C','0','=','/'}
};

byte rowPins[ROWS] = {7,6,5,4}; 
byte colPins[COLS] = {3,2,A4,A5}; 
const int MAX_DEC_LENGTH = 3;
const int MAX_BIN_LENGTH = 3;
//const int MAX_OCT_LENGTH = 3;
enum {DECIMAL_CALCULO,
  BINARIO_CALCULO,
    OCTAL_CALCULO,
  DECIMAL_PARA_BINARIO,
  BINARIO_PARA_DECIMAL,
    OCTAL_PARA_DECIMAL,
    DECIMAL_PARA_OCTAL} mode = DECIMAL_CALCULO;


Keypad keypad = Keypad( makeKeymap(keys), rowPins, colPins, ROWS, COLS);
void setup()
{
  Serial.begin(9600);
  lcd.begin(16, 2); 
  lcd.setCursor(0,0);
  lcd.print("AS - 3 ");
  lcd.setCursor(0,1);
  lcd.print("CALCULADORA");
  delay(2000);
  lcd.clear();
  lcd.setCursor(0, 0);
}
void loop(){
  switch(mode){
    case DECIMAL_CALCULO: decimal_calculadora(); break;
    case BINARIO_CALCULO: binario_calculadora(); break;
    case DECIMAL_PARA_BINARIO: decimal_para_binario(); break;
        case BINARIO_PARA_DECIMAL: binario_para_decimal(); break;  
        case DECIMAL_PARA_OCTAL: decimal_para_octal(); break;
        case OCTAL_PARA_DECIMAL: octal_para_decimal();
  }
}
void change_mode(){
  switch(mode){
    case DECIMAL_CALCULO: mode = BINARIO_CALCULO; break;
    case BINARIO_CALCULO: mode = DECIMAL_PARA_BINARIO; break;
        case DECIMAL_PARA_BINARIO: mode = BINARIO_PARA_DECIMAL; break;
        case BINARIO_PARA_DECIMAL: mode = DECIMAL_PARA_OCTAL; break;
        case DECIMAL_PARA_OCTAL: mode = OCTAL_PARA_DECIMAL; break;
        case OCTAL_PARA_DECIMAL: mode = DECIMAL_CALCULO;
  }
}
void decimal_calculadora(){
  char stop = 'C';
  while (stop == 'C'){
    lcd.clear();
      lcd.setCursor(0, 0);
    lcd.print("Calculadora dec");
    decimal_expression(stop);
  }
  change_mode();
}

void binario_calculadora(){
  char stop = 'C';
  while (stop == 'C'){
    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print("Calculadora bin");
    lcd.setCursor(0,1);
    binary_expression(stop);
  }
  change_mode();
}
void decimal_para_binario(){
  char stop = 'C';
  while (stop == 'C'){
    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print("dec para bin");
        lcd.setCursor(0,1);
    dec_to_bin(stop);
  }
  change_mode();
}

void binario_para_decimal(){
  char stop = 'C';
  while (stop == 'C'){
    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print("bin para dec");
    lcd.setCursor(0,1);
    bin_to_dec(stop);
  }
  change_mode();
}

void octal_para_decimal(){
  char stop = 'C';
  while (stop == 'C'){
    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print("oct para dec");
    lcd.setCursor(0,1);
    oct_to_dec(stop);
  }
  change_mode();
}
void decimal_para_octal(){
  char stop = 'C';
  while (stop == 'C'){
    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print("dec para oct");
    lcd.setCursor(0,1);
    dec_to_oct(stop);
  }
  change_mode();
}

void dec_to_oct(char& stop){
  while (stop == 'C'){
    key = keypad.getKey();
    switch(key){
      case '0' ... '9':
        first = first * 10 + (key - '0');
        lcd.print(key);
        break;
      case '+':
        total = converterDec2Oct(first);
        lcd.print("->");
        lcd.print(total);
        reset_values();
        break;
      case '-':
        total = converterDec2Oct(first);
        lcd.print("->");
        lcd.print(total);
        reset_values();
        break;
      case '*':
        total = converterDec2Oct(first);
        lcd.print("->");
        lcd.print(total);
        reset_values();
        break;
      case '/':
        total = converterDec2Oct(first);
        lcd.print("->");
        lcd.print(total);
        reset_values();
        break;
      case 'C':
        lcd.clear();
        lcd.setCursor(0,0);
        //lcd.print("dec para bin");
        lcd.setCursor(0,1);
        reset_values();
        break;
      case '=':
        stop = 'D';
        reset_values();
    }
  }
}


void oct_to_dec(char& stop){
  while (stop == 'C'){
    key = keypad.getKey();
    switch(key){
      case '0' ... '9':
        first = first * 10 + (key - '0');
        lcd.print(key);
        break;
      case '+':
        total = converterOct2Dec(first);
        lcd.print("->");
        lcd.print(total);
        reset_values();
        break;
      case '-':
        total = converterOct2Dec(first);
        lcd.print("->");
        lcd.print(total);
        reset_values();
        break;
      case '*':
        total = converterOct2Dec(first);
        lcd.print("->");
        lcd.print(total);
        reset_values();
        break;
      case '/':
        total = converterOct2Dec(first);
        lcd.print("->");
        lcd.print(total);
        reset_values();
        break;
      case 'C':
        lcd.clear();
        lcd.setCursor(0,0);
        //lcd.print("dec para bin");
        lcd.setCursor(0,1);
        reset_values();
        break;
      case '=':
        stop = 'D';
        reset_values();
    }
  }
}
void binary_expression(char& stop){
  long firstInDecimal = 0;
  long secondInDecimal = 0;
  while (stop == 'C'){
      key = keypad.getKey();
    switch(key){
      case '0' ... '1':
        if(boolean_length_is_valid(first)){
          first = first * 10 + (key - '0');
          lcd.print(key);
        }
        break;
      case '+':
        first = (total != 0 ? total : first);
        lcd.print("+");
        second = second_number_binary();
        total = convert_to_binary(convert_to_decimal(first) + convert_to_decimal(second));
        lcd.print("=");
        lcd.print(total);
        reset_values();
        break;
      case '-':
        first = (total != 0 ? total : first);
        lcd.print("-");
        second = second_number_binary();
        total = convert_to_binary(convert_to_decimal(first) - convert_to_decimal(second));
        lcd.print("=");
        lcd.print(total);
        reset_values();
        break;
      case '*':
        first = (total != 0 ? total : first);
        lcd.print("*");
        second = second_number_binary();
        total = convert_to_binary(convert_to_decimal(first) * convert_to_decimal(second));
        lcd.print("=");
        lcd.print(total);
        reset_values();
        break;
      case '/':
        first = (total != 0 ? total : first);
        lcd.print("/");
        second = second_number_binary();
        second == 0 ? lcd.print("Invalido") : total = convert_to_binary(convert_to_decimal(first) -
        convert_to_decimal(second));
        lcd.print("=");
        lcd.print(total);
        reset_values();
        break;
      case 'C':
        lcd.clear();
        lcd.setCursor(0,0);
        lcd.print("Calculadora bin");
        lcd.setCursor(0,1);
        reset_values();
        break;
            case '=':
        stop = 'D';
        reset_values();
    }
  }
}

void decimal_expression(char& stop){
  while (stop == 'C'){
    key = keypad.getKey();
    switch(key){
      case '0' ... '9':
        if(decimal_length_is_valid(first)){
          lcd.setCursor(0,1);
          first = first * 10 + (key - '0');
          lcd.print(first);
        }
          break;
      case '+':
        first = (total != 0 ? total : first);
        lcd.print("+");
        second = second_number();
        total = first + second;
        lcd.print("=");
        lcd.print(total);
        reset_values();
        break;
      case '-':
        first = (total != 0 ? total : first);
        lcd.print("-");
        second = second_number();
        total = first - second;
        lcd.print("=");
        lcd.print(total);
        reset_values();
        break;
      case '*':
        first = (total != 0 ? total : first);
        lcd.print("*");
        second = second_number();
        total = first * second;
        lcd.print("=");
        lcd.print(total);
        reset_values();
        break;
      case '/':
        first = (total != 0 ? total : first);
        lcd.print("/");
        second = second_number();
        second == 0 ? lcd.print("Invalid") : total = (float)first / (float)second;
        lcd.print("=");
        lcd.print(total);
        reset_values();
        break;
      case 'C':
        lcd.clear();
        reset_values();
        break;
      case '=':
        stop = 'D';
        reset_values();
    }
  }
}



void dec_to_bin(char& stop){
  while (stop == 'C'){
    key = keypad.getKey();
    switch(key){
      case '0' ... '9':
        first = first * 10 + (key - '0');
        lcd.print(key);
        break;
      case '+':
        total = convert_to_binary(first);
        lcd.print("->");
        lcd.print(total);
        reset_values();
        break;
      case '-':
        total = convert_to_binary(first);
        lcd.print("->");
        lcd.print(total);
        reset_values();
        break;
      case '*':
        total = convert_to_binary(first);
        lcd.print("->");
        lcd.print(total);
        reset_values();
        break;
      case '/':
        total = convert_to_binary(first);
        lcd.print("->");
        lcd.print(total);
        reset_values();
        break;
      case 'C':
        lcd.clear();
        lcd.setCursor(0,0);
        //lcd.print("dec para bin");
        lcd.setCursor(0,1);
        reset_values();
        break;
      case '=':
        stop = 'D';
        reset_values();
    }
  }
}
void bin_to_dec(char& stop) {
  while (stop == 'C') {
    key = keypad.getKey();
      switch(key){
      case '0' ... '1':
        first = first * 10 + (key - '0');
        lcd.print(key);
        break;
      case '+':
        total = convert_to_decimal(first);
        lcd.print("->");
        lcd.print(total);
        reset_values();
        break;
      case '-':
        total = convert_to_decimal(first);
        lcd.print("->");
        lcd.print(total);
        reset_values();
        break;
      case '*':
        total = convert_to_decimal(first);
        lcd.print("=>");
        lcd.print(total);
        reset_values();
        break;
      case '/':
        total = convert_to_decimal(first);
        lcd.print("->");
        lcd.print(total);
        reset_values();
        break;
      case 'C':
        lcd.clear();
        lcd.setCursor(0,0);
        //lcd.print("bin para dec");
        lcd.setCursor(0,1);
        reset_values();
        break;
      case '=':
        stop = 'D';
        reset_values();
    }
  }
}
long convert_to_binary(int decimal_number){
  Serial.print("Binario: ");
  Serial.print(decimal_number);
  String binary_number = "";
  for(int i = decimal_number; i > 0; i /= 2){
    binary_number = String(i%2) + binary_number;
  }
        Serial.print("-> ");
                Serial.println(binary_number);
  return binary_number.toInt();
}
int get_binary_lenght(int binary_number){
  if(int(binary_number/pow(10,2)) == 1)return 3;
  if(int(binary_number/pow(10,1)) == 1)return 2;
  return 1;
}
long convert_to_decimal(int binary_number){
  Serial.print("Decimal: ");
  Serial.print(binary_number);
  long decimal_number = 0;
  int lenght = get_binary_lenght(binary_number);
  for(int i = 0; i < lenght; i++){
    decimal_number += (binary_number/(long)pow(10,i))%10*powint(2,i);
}
  Serial.print("-> ");
  Serial.println(decimal_number);
  return decimal_number;
}
int converterOct2Dec(int valor_octal){
    int decimal_number = 0, sequencia = 0;
    while(valor_octal != 0){
        decimal_number += (valor_octal % 10) * pow(8, sequencia);
        ++sequencia;
        valor_octal /= 10;
    }
    return decimal_number + 1;
}

int converterDec2Oct(int valor_decimal ){
    int valor_octal = 0, sequencia = 1;
    while (valor_decimal != 0){
        valor_octal += (valor_decimal % 8) * sequencia;
        valor_decimal /= 8;
        sequencia *= 10 ;
    }
    return valor_octal;
}


long powint(int base, unsigned int expoent){
  long i = 1;
  while (expoent--) i *= base;
  return i;
}
long second_number(){
  while( 1 ){
  key = keypad.getKey();
  if(key >= '0' && key <= '9'){
    if(decimal_length_is_valid(second)) {
      second = second * 10 + (key - '0');
      lcd.print(key);
    }
  }
  if(key == '=') break; 
  }
  return second;
}
long second_number_binary(){
  while( 1 ){
    key = keypad.getKey();
    if(key >= '0' && key <= '1'){
      if(boolean_length_is_valid(second)){
        second = second * 10 + (key - '0');
        lcd.print(key);
      }
    }
    if(key == '=') break;
  }
  return second;
}
bool decimal_length_is_valid(long decimal_value){
  return (decimal_value < powint(10, MAX_DEC_LENGTH-1)-1);
}
bool boolean_length_is_valid(long boolean_value){
  return (boolean_value < powint(10, MAX_DEC_LENGTH-1)-1);
}


void reset_values() {
  first = 0, second = 0, total = 0;
}
