const int vermelho1 = 10;
const int amarelo1 = 9;
const int verde1 = 8;
const int vermelho2 = 7;
const int amarelo2 = 6;
const int verde2 = 5;
const int pedestreVermelho = 12;
const int pedestreVerde = 11;
const int botao1 = 3;
const int botao2 = 2;
const int esperaDeSeguranca = 2000;;
const int tempoTotal = 6000;
const int tempoPedestre = 4000;
bool botaoApertado = false;

void setup(){
  pinMode(A0, INPUT);
  pinMode(vermelho1, OUTPUT);
  pinMode(amarelo1, OUTPUT);
  pinMode(verde1, OUTPUT);
  pinMode(vermelho2, OUTPUT);
  pinMode(amarelo2, OUTPUT);
  pinMode(verde2, OUTPUT);
  pinMode(pedestreVermelho, OUTPUT);
  pinMode(pedestreVerde, OUTPUT);
  pinMode(botao2,INPUT);
  
  attachInterrupt(botao1 - 2, apertarBotaoPedestre, RISING);
  attachInterrupt(botao2 - 2, apertarBotaoPedestre, RISING);
  //attachInterrupt(0,interrupcaoVermelho, RISING);
  //attachInterrupt(0,interrupcaoVerde, RISING);
  
  
  Serial.begin(9600);
  
  liga(vermelho2);
  liga(pedestreVermelho);
  
}


void loop(){
  int qtCarros = analogRead(A0);
  int tempoA = calcula(qtCarros);
  int tempoB = tempoTotal -  tempoA;
  double tempoASeconds = (double)tempoA / (double)1000;
  double tempoBSeconds = (double)tempoB / (double)1000;
  
  imprimirDetalhes(qtCarros, tempoASeconds, tempoBSeconds);
  
  verificaPedestre();
  ligaPorTempo(verde1, tempoA);
  ligaPorTempo(amarelo1, esperaDeSeguranca);
  
  liga(vermelho1);
  delay(esperaDeSeguranca);
  desligar(vermelho2);
  
  ligaPorTempo(verde2, tempoB);
  ligaPorTempo(amarelo2, esperaDeSeguranca);
  
  liga(vermelho2);
  delay(esperaDeSeguranca);
  
  digitalWrite(pedestreVermelho, HIGH);
  digitalWrite(pedestreVerde, HIGH);
  delay(600);
  digitalWrite(pedestreVermelho, LOW);
  digitalWrite(pedestreVerde, LOW);
  delay(600);
  //verificaPedestre();
  desligar(vermelho1);
}

void imprimirDetalhes(int qtCarros, int tempoASeconds, int tempoBSeconds){
  Serial.print("Quantidade de veiculos: ");
    Serial.print(qtCarros);
    Serial.print("Tempo do sinal verde A: ");
    Serial.print(tempoASeconds);
    Serial.println("s");
    Serial.print("Tempo do sinal verde B: ");
    Serial.print(tempoBSeconds);
    Serial.println("s");
}
  
void ligaPorTempo(int porta, int tempo){
  liga(porta);
  delay(tempo);
  desligar(porta);
}

void liga(int porta){
  digitalWrite(porta, HIGH);
}

void desligar(int porta){
  digitalWrite(porta, LOW);
}

void pisca(int porta){
  for(int i = 0; i < 5; i ++){
    liga(porta);
      delay(600);
      desligar(porta);
      delay(600);
  }
}

float calcula(int qtCarros){
  if(qtCarros < 341)
      return((qtCarros / 341.0 * 0.3) + 0.2) * tempoTotal;
      return(((qtCarros - 341)/682.0 * ((2.0/3)- 0.5)) + 0.5) * tempoTotal;
}

void verificaPedestre(){
  if(botaoApertado == true){
    desligar(pedestreVermelho);
    liga(pedestreVerde);
    liga(vermelho1);
    liga(vermelho2);
    delay(tempoPedestre);
    desligar(pedestreVerde);
    pisca(pedestreVermelho);
    liga(pedestreVermelho);
    desligar(vermelho1);
    liga(verde1);
    delay(600);
  }
}
void interrupcaoVerde(){
  digitalWrite(pedestreVermelho, LOW);
  digitalWrite(pedestreVerde, HIGH);
  delay(600);
}

void interrupcaoVermelho(){
  digitalWrite(pedestreVerde, LOW);
  digitalWrite(pedestreVermelho, HIGH);
  delay(600);
}
void apertarBotaoPedestre(){
  botaoApertado = true;
  Serial.println("Botão foi apertado");
}
  
  
  
    
  
