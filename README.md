# Home Automation

## Application Details
- Enables us to turn on/off appliances present in a room.
- Add firebase database to your app and then you can configure it.
- Updates the values in firebase database which is consumed at hardware level.

## Hardware Code
Defining header files and constants
```
#include <WiFi.h>
#include <FirebaseESP32.h>

#define light_pin 27
#define fan_pin 26
#define night_bulb_pin 25
#define socket_pin 33

const char* ssid = "YOUR_SSID";
const char* password = "YOUR_PWD";
IPAddress ip;
const char* database_url = "YOUR_DB_URL";
const char* database_secret = "YOUR_DB_SECRET";
FirebaseData databaseObject;
```

Connecting to Firebase and Wifi
```
void connectToFirebase() {
	Serial.printf("Firebase Client v%s\n\n", FIREBASE_CLIENT_VERSION);
	Firebase.begin(database_url, database_secret);
}

void connectToWifi() {
	Serial.println();
	Serial.println();
	Serial.print("Connecting to WIFI network");
	WiFi.begin(ssid, password);
	while (WiFi.status() != WL_CONNECTED)
	{
		delay(500);
		Serial.print(".");
	}
	Serial.println("WiFi connected");
	ip = WiFi.localIP();
	Serial.println(ip);
}
```

Main Code
```
void setPinModes() {
	pinMode(light_pin, OUTPUT);
	pinMode(fan_pin, OUTPUT);
	pinMode(night_bulb_pin, OUTPUT);
	pinMode(socket_pin, OUTPUT);
}

void setup() {
	Serial.begin(115200);
	setPinModes();
	connectToWifi();
	connectToFirebase();
}

void loop() {
	delay(500);
	if (Firebase.getJSON(databaseObject, "/HomeAutomation/Room2/Light")) {
		Serial.println("Light:");
		Serial.println(databaseObject.boolData() ? "On" : "Off");
		if (databaseObject.boolData()) {
			digitalWrite(light_pin, LOW);
		} else {
		digitalWrite(light_pin, HIGH);
		}
	}
	Serial.println();
	Serial.println();
	if (Firebase.getJSON(databaseObject, "/HomeAutomation/Room2/Fan") {
		Serial.println("Fan:");
		Serial.println(databaseObject.boolData() ? "On" : "Off");
		if (databaseObject.boolData()) {
			digitalWrite(fan_pin, LOW);
		} else {
			digitalWrite(fan_pin, HIGH);
		}
	}
	Serial.println();
	Serial.println();
	if (Firebase.getJSON(databaseObject, "/HomeAutomation/Room2/NightBulb")) {
		Serial.println("NightBulb:");
		Serial.println(databaseObject.boolData() ? "On" : "Off");
		if (databaseObject.boolData()) {
			digitalWrite(night_bulb_pin, LOW);
		} else {
			digitalWrite(night_bulb_pin, HIGH);
		}
	}
	Serial.println();
	Serial.println();
	if (Firebase.getJSON(databaseObject, "/HomeAutomation/Room2/Socket")) {
		Serial.println("Socket:");
		Serial.println(databaseObject.boolData() ? "On" : "Off");
		if (databaseObject.boolData()) {
			digitalWrite(socket_pin, LOW);
		} else {
			digitalWrite(socket_pin, HIGH);
		}
	}
	Serial.println();
	Serial.println();
}
```
## Hardware PIN Setup
- Hardware required - Arduino UNO, ESP32/ESP8266, Four channel relay, Jumper Wires. (Note: I will be considering a ESP32 for my setup but you can use ESP8266 as well)
- Connect PINS 23,25,26,27 from ESP32 to IN4, IN3, IN2, IN1 from Relay sensor respectively.
- Connect Vin and Gnd from Arduino to Vcc and Gnd from Relay sensor.
- Connect 5V and Gnd from Arduino to Vin and Gnd from ESP 32
