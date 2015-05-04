package fr.aleclerc.gpio.led.webservice;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import fr.aleclerc.gpio.led.infra.service.LedService;


@Singleton
@Path("led")
public class LedRessource {
	
	
	private  LedService ledService;
	
	@PostConstruct
	public void init() {
	
		ledService = LedService.getInstance();
	}
//	@PreDestroy
//	public void detroy(){
//		gpio.shutdown();
//	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String blink(@DefaultValue("10") @QueryParam("sec") int sec, @DefaultValue("10") @QueryParam("nb") int nb) {

		ledService.clignote(sec, nb);
		
		return "CLIGNOTE !! ";
	}
	@GET
	@Path("on")
	@Produces(MediaType.TEXT_PLAIN)
	public String on() {
		ledService.on();
		return "on";
	}
	@GET
	@Path("off")
	@Produces(MediaType.TEXT_PLAIN)
	public String off() {
		ledService.off();
		return "off";
	}
}
