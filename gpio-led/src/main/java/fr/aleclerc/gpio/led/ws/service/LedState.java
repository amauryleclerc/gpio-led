package fr.aleclerc.gpio.led.ws.service;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/myendpoint")
public class LedState {
  private static  Set<Session> sessions = 
          Collections.synchronizedSet(new HashSet<Session>());

  @OnOpen
  public void onOpen(final Session session) {
    sessions.add(session);
 
  }

  @OnClose
  public void onClose(final Session session) {
    sessions.remove(session);
  }

  public void broadcastArticle(String msg) {
    synchronized(sessions) {
      for (Session s : sessions) {
        if (s.isOpen()) {
          try {
            s.getBasicRemote().sendText("Message:" + msg);
          } catch (IOException ex) { 
        	  
          }
        }
      }
    }
  }
}
