package fr.aleclerc.gpio.led.ws.service;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@Path("led")
public class LedRessource {
	
	private GpioController gpio ;
	private GpioPinDigitalOutput led1 ;
	
	@PostConstruct
	public void init() {
		 gpio = GpioFactory.getInstance();
		 led1 = gpio.provisionDigitalOutputPin (
	                RaspiPin.GPIO_01,
	                "LED1",
	                PinState.LOW);
	}
	
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
       
        led1.blink(500, 10000);
        return "CLIGNOTE !!";
    }
}
