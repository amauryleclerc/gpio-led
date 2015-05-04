package fr.aleclerc.gpio.led.infra.service;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import fr.aleclerc.gpio.led.websocket.LedStatus;

public class LedService {
	
	private  GpioPinDigitalOutput led1 ;
	private static LedService instance = null;
	
	   public static LedService getInstance() {
		      if(instance == null) {
		         instance = new LedService(	GpioFactory.getInstance());
		      }
		      return instance;
		   }
	
	public LedService(GpioController gpio) {
		super();
		this.led1  = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "LED1", PinState.LOW);
		led1.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
	}

	public void clignote(int sec,int  nb){
		int secON = (int) (sec * 1000) /( nb * 2);
		LedStatus.broadcast("LED BLINK");
		led1.blink(secON, sec * 1000);
	}

	public void on() {
		LedStatus.broadcast("LED ON");
		led1.setState(true);
		
	}
	public void off() {
		LedStatus.broadcast("LED OFF");
		led1.setState(false);
		
	}
}
