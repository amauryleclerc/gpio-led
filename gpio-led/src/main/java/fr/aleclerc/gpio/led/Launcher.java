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
	        boolean BLOCKING = true;
	        boolean NONBLOCKING = false;
	 
	        System.out.println("===== Starting Tutorial 2 =====");
	        
	        final GpioPinDigitalOutput led1 = gpio.provisionDigitalOutputPin (
	                RaspiPin.GPIO_01,
	                "LED1",
	                PinState.LOW);
	        /*
	         * Add a shutdown hook so that the application can trap a Ctrl-C and
	         * handle it gracefully by ensuring that all the LEDs are turned off
	         * prior to exiting.
	         */
	        Runtime.getRuntime().addShutdownHook(new Thread() {
	            @Override
	            public void run() {
	                System.out.println("\n\nPROGRAM WAS INTERRUPTED. SHUTTING " +
	                        "DOWN!");
	                led1.low();
	                gpio.shutdown();
	            }
	        });
	 
	        System.out.println("Pins initialized. All LEDs should be off.");
	 
	        /*
	         * Starting the main loop of our program. We put it in a try-catch
	         * since the various sleep's throw an exception and we need to catch
	          * those exceptions.
	         */
	        try {
	            System.out.println("Blocking Call: Each LED will be on for 1 sec" +
	                    " one after another.");
	            Thread.sleep(1000);
	 
	            /*
	             * Blocking call: Execution proceeds to next line only after current
	             * line is finished. LEDs will blink one after the other.
	             */
	            led1.blink(500, 10000);
	 
	         
	            Thread.sleep(10000);
	 
	          
	        } catch (InterruptedException ie) {
	            System.out.println("Execution Interrupted.");
	            led1.low();
	            gpio.shutdown();
	        }
	    }
	    
}
