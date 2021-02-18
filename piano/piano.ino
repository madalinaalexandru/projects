#include <Servo.h>
#include "notes.h"

//tempourile melodiilor 1, 2 si 3
int tempo_hedwig = 144, tempo_take_on_me = 140, tempo_fur_elise = 85;

//array-ul de note si durate ale fiecarei note din prima melodie
int hedwig[] = {
   
  REST, 2, NOTE_D4, 4,
  NOTE_G4, -4, NOTE_AS4, 8, NOTE_A4, 4,
  NOTE_G4, 2, NOTE_D5, 4,
  NOTE_C5, -2, 
  NOTE_A4, -2,
  NOTE_G4, -4, NOTE_AS4, 8, NOTE_A4, 4,
  NOTE_F4, 2, NOTE_GS4, 4,
  NOTE_D4, -1, 
  NOTE_D4, 4,

  NOTE_G4, -4, NOTE_AS4, 8, NOTE_A4, 4,
  NOTE_G4, 2, NOTE_D5, 4,
  NOTE_F5, 2, NOTE_E5, 4,
  NOTE_DS5, 2, NOTE_B4, 4,
  NOTE_DS5, -4, NOTE_D5, 8, NOTE_CS5, 4,
  NOTE_CS4, 2, NOTE_B4, 4,
  NOTE_G4, -1,
  NOTE_AS4, 4,
  
};

//array-ul de note si durate ale fiecarei note din a doua melodie
int take_on_me[] = {

  NOTE_FS5,8, NOTE_FS5,8,NOTE_D5,8, NOTE_B4,8, REST,8, NOTE_B4,8, REST,8, NOTE_E5,8, 
  REST,8, NOTE_E5,8, REST,8, NOTE_E5,8, NOTE_GS5,8, NOTE_GS5,8, NOTE_A5,8, NOTE_B5,8,
  NOTE_A5,8, NOTE_A5,8, NOTE_A5,8, NOTE_E5,8, REST,8, NOTE_D5,8, REST,8, NOTE_FS5,8, 
  REST,8, NOTE_FS5,8, REST,8, NOTE_FS5,8, NOTE_E5,8, NOTE_E5,8, NOTE_FS5,8, NOTE_E5,8,
  NOTE_FS5,8, NOTE_FS5,8,NOTE_D5,8, NOTE_B4,8, REST,8, NOTE_B4,8, REST,8, NOTE_E5,8, 
  
  REST,8, NOTE_E5,8, REST,8, NOTE_E5,8, NOTE_GS5,8, NOTE_GS5,8, NOTE_A5,8, NOTE_B5,8,
  NOTE_A5,8, NOTE_A5,8, NOTE_A5,8, NOTE_E5,8, REST,8, NOTE_D5,8, REST,8, NOTE_FS5,8, 
  REST,8, NOTE_FS5,8, REST,8, NOTE_FS5,8, NOTE_E5,8, NOTE_E5,8, NOTE_FS5,8, NOTE_E5,8,
  NOTE_FS5,8, NOTE_FS5,8,NOTE_D5,8, NOTE_B4,8, REST,8, NOTE_B4,8, REST,8, NOTE_E5,8, 
  REST,8, NOTE_E5,8, REST,8, NOTE_E5,8, NOTE_GS5,8, NOTE_GS5,8, NOTE_A5,8, NOTE_B5,8,
  
};

//array-ul de note si durate ale fiecarei note din prima melodie
const int fur_elise[] = {

  // Fur Elise - Ludwig van Beethovem
  // Score available at https://musescore.com/user/28149610/scores/5281944

  //starts from 1 ending on 9
  NOTE_E5, 16, NOTE_DS5, 16,
  NOTE_E5, 16, NOTE_DS5, 16, NOTE_E5, 16, NOTE_B4, 16, NOTE_D5, 16, NOTE_C5, 16,
  NOTE_A4, -8, NOTE_C4, 16, NOTE_E4, 16, NOTE_A4, 16,
  NOTE_B4, -8, NOTE_E4, 16, NOTE_GS4, 16, NOTE_B4, 16,
  NOTE_C5, 8,  REST, 16, NOTE_E4, 16, NOTE_E5, 16,  NOTE_DS5, 16,
  
  NOTE_E5, 16, NOTE_DS5, 16, NOTE_E5, 16, NOTE_B4, 16, NOTE_D5, 16, NOTE_C5, 16,
  NOTE_A4, -8, NOTE_C4, 16, NOTE_E4, 16, NOTE_A4, 16, 
  NOTE_B4, -8, NOTE_E4, 16, NOTE_C5, 16, NOTE_B4, 16, 
  NOTE_A4 , 4, REST, 8, 
  
};

volatile int recordedNotes[300]; //array pentru notele care pot fi inregistrare
volatile int nbOfNotesInSong; //numarul de note dintr-o melodie

volatile int durations[10]; //duratele notelor dintr-o melodie
volatile int song[10]; //o melodie

volatile int startButton; //butonul de pe care se incepe inregistrarea
volatile int stopButton; //butonul de pe care se opreste intregistrarea si se reda mai apoi melodia 
//de cate ori se doreste pana la apasarea butonului de start din nou

volatile int cnt = 0; //variabila folosita pentru a contoriza array-uri

Servo srv; //servomotorul

void setup()
{
  Serial.begin(9600);
  
  //note buttons
  pinMode(7, INPUT); //button 1
  pinMode(8, INPUT); //button 2
  pinMode(4, INPUT); //button 3
  pinMode(5, INPUT); //button 4
  pinMode(6, INPUT); //button 5
  
  //reset and start button
  pinMode(2, INPUT); //button 6 - interrupt
  
  //stop and replay recording button
  pinMode(3, INPUT); //button 7 - interrupt
  
  //change song in play mode
  pinMode(15, INPUT);
  
  //rgb led
  pinMode(13, OUTPUT); //red
  pinMode(12, OUTPUT); //blue
  pinMode(11, OUTPUT); //green
  
  //piezo
  pinMode(9, OUTPUT); //piezo1
  
  //switch - se poate seta daca se doreste inregistrarea unei melodii sau folosirea notelor sau redarea celor 3 melodii deja existente
  pinMode(10, INPUT_PULLUP);
  
  //se ataseaza intreruperile pentru pinii 2 si 3 (butoanele start si stop rrecording)
  attachInterrupt(digitalPinToInterrupt(2), startRecording, RISING);
  attachInterrupt(digitalPinToInterrupt(3), stopRecording, RISING);
  
  startButton = 0;
  stopButton = 0;
  nbOfNotesInSong = 0;
}

void loop()
{
  //cazul in care switch-ul este pornit -> se inregistreaza melodie cu propriile note
  if(digitalRead(10) == 1){ //daca pinul 10, butonul 1, este apasat
    
    ledOut();
    
    //se va reda nota a si, in cazul in care este pornita inregistrarea va fi adaugata la melodie; se va aprinde led rosu;
    //aceeasi situatie este si pentru celelalte 4 butoane corespunzatoare notelor
    if(digitalRead(7) == 1){
       tone(9, NOTE_A, 100);
       displayRed();
       if(startButton == 1 && nbOfNotesInSong <= 500){
         recordedNotes[nbOfNotesInSong] = 220;
         nbOfNotesInSong++;
       }
     }
  
    if(digitalRead(8) == 1){
       tone(9, NOTE_B, 100);
       displayBlue();
    
       if(startButton == 1 && nbOfNotesInSong <= 500){
          recordedNotes[nbOfNotesInSong] = 247;
          nbOfNotesInSong++;
       }
    }
  
    if(digitalRead(4) == 1){
       tone(9, NOTE_C, 100);
       displayGreen();
    
       if(startButton == 1 && nbOfNotesInSong <= 500){
         recordedNotes[nbOfNotesInSong] = 139;
         nbOfNotesInSong++;
       }
    }
  
    if(digitalRead(5) == 1){
       tone(9, NOTE_D, 100);
       displayYellow();
    
       if(startButton == 1 && nbOfNotesInSong <= 500){
         recordedNotes[nbOfNotesInSong] = 147;
         nbOfNotesInSong++;
       }
    }
  
    if(digitalRead(6) == 1){
       tone(9, NOTE_E, 100);
       displayPurple();
    
       if(startButton == 1 && nbOfNotesInSong <= 500){
         recordedNotes[nbOfNotesInSong] = 165;
         nbOfNotesInSong++;
       }
    }
  
    if(stopButton == 1){ //daca se va mai apasa buttonul de stop dupa intrerupere, se va reda melodia curenta
      replay();
    }
  }
  
  //cazul in care switch-ul nu este pornit -> se redau melodiile deja memorate
  if(digitalRead(10) == 0){
    ledOut();
    
    if(digitalRead(15) == 0){
      song1();
      noTone(9);
      delay(1000);
      song2();
      noTone(9);
      delay(1000);
      song3();
      noTone(9);
    } 
  }
}

//prima melodie
void song1()
{
  srv.attach(14);
  nbOfNotesInSong = sizeof(hedwig) / sizeof(hedwig[0]) / 2;
  
  int note = (60000 * 4) / tempo_hedwig;
  
  int divider = 0, duration = 0;
  
  //se redau toate sunetele timp de durata lor corespunzatoare si se misca servomotorul;
  for(int i = 0; i < nbOfNotesInSong * 2; i = i + 2){
    
    if(digitalRead(10) == 1 || digitalRead(15) == 1) {
      break;
    }
    
    divider = hedwig[i + 1];
    
    if(divider > 0){
      duration = note / divider;
    }
    else if (divider < 0){
      duration = note / abs(divider);
      duration *= 1.5;
    }
    
    tone(9, hedwig[i], duration * 0.9);
    
    if(divider != 0){
      srv.write(note / abs(divider));
    }
   
    displayPurple();
    delay(duration);
    noTone(9);
  }
  srv.detach();
}

//a doua melodie
void song2()
{
  srv.attach(14);
  nbOfNotesInSong = sizeof(take_on_me) / sizeof(take_on_me[0]) / 2;
  
  int note = (60000 * 4) / tempo_take_on_me;
  
  int divider = 0, duration = 0;
  
  for(int i = 0; i < nbOfNotesInSong * 2; i = i + 2){
    
    if(digitalRead(10) == 1 || digitalRead(15) == 1) break;
    
    divider = take_on_me[i + 1];
    
    if(divider > 0){
      duration = note / divider;
    }
    else if (divider < 0){
      duration = note / abs(divider);
      duration *= 1.5;
    }
    
    tone(9, take_on_me[i], duration * 0.9);
    
    srv.write(note / 100);
    
    displayYellow();
    delay(duration);
    noTone(9);
  }
  srv.detach();
}

// a treia melodie
void song3()
{
  srv.attach(14);
  nbOfNotesInSong = sizeof(fur_elise) / sizeof(fur_elise[0]) / 2;
  
  int note = (60000 * 4) / tempo_fur_elise;
  
  int divider = 0, duration = 0;
  
  for(int i = 0; i < nbOfNotesInSong * 2; i = i + 2){
    
    if(digitalRead(10) == 1 || digitalRead(15) == 1) break;
    
    divider = fur_elise[i+1];
    
    if(divider > 0){
      duration = note / divider;
    }
    else if (divider < 0){
      duration = note / abs(divider);
      duration *= 1.5;
    }
    
    tone(9, fur_elise[i], duration * 0.9);
    
    srv.write(note / 100);
    
    displayGreen();
    delay(duration);
    noTone(9);
  }
  srv.detach();
}

//metoda ce va reda melodia inregistrata la intrerupere
void replay(){
  
  srv.attach(14);
  
  int duration = 0;
  for(int i = 0; i < nbOfNotesInSong - 1; i++){
    duration = 0;
    while(recordedNotes[i] == recordedNotes[i+1]){
      i++;
      duration++;
    }
    
    song[cnt] = recordedNotes[i];
    durations[cnt] = duration;
    cnt++;
  }
  
  for(int i = 0; i < cnt; i++){
    
    if(song[i] == NOTE_A){
      
      srv.write(NOTE_A / 3.14);
      displayRed();
    }
    
    if(song[i] == NOTE_B){
      srv.write(NOTE_B / 3.14);
      displayBlue();
    }
    
    if(song[i] == NOTE_C){
      srv.write(NOTE_C / 3.14);
      displayGreen();
    }
    
    if(song[i] == NOTE_D){
      srv.write(NOTE_D / 3.14);
      displayYellow();
    }
    
    if(song[i] == NOTE_E){
      srv.write(NOTE_E / 3.14);
      displayPurple();
    }
    
    tone(9, song[i], durations[i] * 10);
    int pauseBetweenNotes = durations[i] * 13;
  delay(pauseBetweenNotes);
  }
  
  ledOut();
  
  noTone(9);
  stopButton = 0;
}

//ISR pentru butonul de start 
void startRecording(){
  startButton = 1;
  stopButton = 0;
  nbOfNotesInSong = 0;
  
  ledOut();
  
  Serial.println("Recording!");
}

//ISR pentru butonul de stop
void stopRecording(){
  startButton = 0;
  stopButton = 1;
  cnt = 0;
  
  ledOut();
  
  Serial.println("Recording stopped!");
}

//metode pentru setarea culorilor ledului RGB
void ledOut(){
  digitalWrite(13, LOW);
  digitalWrite(12, LOW);
  digitalWrite(11, LOW);
}

void displayRed(){
  digitalWrite(13, HIGH);
  digitalWrite(12, LOW);
  digitalWrite(11, LOW);
}

void displayBlue(){
  digitalWrite(13, LOW);
  digitalWrite(12, HIGH);
  digitalWrite(11, LOW);
}

void displayGreen(){
  digitalWrite(13, LOW);
  digitalWrite(12, LOW);
  digitalWrite(11, HIGH);
}

void displayYellow(){
  digitalWrite(13, HIGH);
  digitalWrite(12, LOW);
  digitalWrite(11, HIGH);
}

void displayPurple(){
  digitalWrite(13, HIGH);
  digitalWrite(12, HIGH);
  digitalWrite(11, LOW);
}
