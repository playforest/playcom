#include <math.h> // for fmod function
#define LED_PIN 13 // LED connected to digital pin 13

void setup() {
  Serial.begin(9600); // Initialize serial communication at 9600 baud
  randomSeed(analogRead(0)); // Initialize random seed with a somewhat random input
}

void loop() {
  // Generate dummy accelerometer data
  float x = (float)random(-1800, 1800) / 10.0;
  float y = (float)random(-1800, 1800) / 10.0;
  float z = (float)random(-1800, 1800) / 10.0;

  // Generate dummy quaternion data
  float qw = (float)random(-1000, 1000) / 1000.0;
  float qx = (float)random(-1000, 1000) / 1000.0;
  float qy = (float)random(-1000, 1000) / 1000.0;
  float qz = (float)random(-1000, 1000) / 1000.0;

  // Print data to serial monitor
  Serial.print("X:"); Serial.print(x); Serial.print(" ");
  Serial.print("Y:"); Serial.print(y); Serial.print(" ");
  Serial.print("Z:"); Serial.print(z); Serial.print(" ");
  Serial.print("qw:"); Serial.print(qw); Serial.print(" ");
  Serial.print("qx:"); Serial.print(qx); Serial.print(" ");
  Serial.print("qy:"); Serial.print(qy); Serial.print(" ");
  Serial.print("qz:"); Serial.print(qz); Serial.println();

  delay(100);  // Wait for 100 milliseconds

    // Blink the LED
  digitalWrite(LED_PIN, HIGH); // Turn the LED on
  delay(50); // Wait for 50 milliseconds
  digitalWrite(LED_PIN, LOW); // Turn the LED off

  delay(50);  // Wait for additional 50 milliseconds before sending the next data
}