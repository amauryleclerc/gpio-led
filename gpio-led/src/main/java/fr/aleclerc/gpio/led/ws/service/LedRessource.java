package fr.aleclerc.gpio.led.ws.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Singleton;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

import fr.aleclerc.gpio.led.infra.service.LedService;

@Singleton
@Path("led")
public class LedRessource {
	
	private  GpioController gpio ;
	private  LedService ledService;
	
	@PostConstruct
	public void init() {
		gpio = GpioFactory.getInstance();
		ledService = new LedService(gpio);
	}
	@PreDestroy
	public void detroy(){
		gpio.shutdown();
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String blink(@DefaultValue("10") @QueryParam("sec") int sec, @DefaultValue("10") @QueryParam("nb") int nb) {

		ledService.clignote(sec, nb);
		
		return "CLIGNOTE !! ";
	}
	
}
