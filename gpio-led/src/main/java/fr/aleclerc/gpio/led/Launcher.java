package fr.aleclerc.gpio.led;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Launcher {
	   public static void main(String[] args) {
	        System.out.println("Hello, Raspberry Pi!");
	 
	        /* Initialize pi4j */
	        final GpioController gpio = GpioFactory.getInstance();
	         
	        
	        final GpioPinDigitalOutput led1 = gpio.provisionDigitalOutputPin (
	                RaspiPin.GPIO_01,
	                "LED1",
	                PinState.LOW);
	        led1.pulse(1000, true);
	      
	 
	        /* Close all open connections. */
	        gpio.shutdown();
	    }
}
