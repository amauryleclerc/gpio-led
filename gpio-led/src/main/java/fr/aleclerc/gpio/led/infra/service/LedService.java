package fr.aleclerc.gpio.led.infra.service;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class LedService {
	
	private  GpioPinDigitalOutput led1 ;
	
	
	public LedService(GpioController gpio) {
		super();
		this.led1  = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "LED1", PinState.LOW);
		led1.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
	}

	public void clignote(int sec,int  nb){
		int secON = (int) (sec * 1000) /( nb * 2);
		led1.blink(secON, sec * 1000);
	}

	public void on() {
		led1.setState(true);
		
	}
	public void off() {
		led1.setState(false);
		
	}
}
