package com.hy.Chat.handle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@ServerEndpoint("/BoardChat/{id}")
public class BoardChat {

	private static Map<Session,String> clients = Collections.synchronizedMap(new HashMap<Session,String>());
	
	@OnOpen
	@RequestMapping("/")
	public void onOpen(Session session,@PathParam("id") String id){
		clients.put(session,id);
		
		System.out.println(session.getId());
		
	}
	public void sendNotice(String message,String id){
		String userName = "server";
		System.out.println(userName + " : " + message);
		
		synchronized (clients) {
			Iterator<Map.Entry<Session, String>> it = clients.entrySet().iterator();
			
			while(it.hasNext()){
				Map.Entry<Session, String> currentSession = (Map.Entry<Session, String>) it.next();
				try {
					if(currentSession.getValue().equals(id)){
						currentSession.getKey().getBasicRemote().sendText(userName + " : " + message);
						}
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	
	@OnMessage
	public void onMessage(String msg,Session session,@PathParam("id") String id)throws IOException{
		System.out.println(msg);
		synchronized (clients) {
			Iterator<Map.Entry<Session, String>> it = clients.entrySet().iterator();
			
			while(it.hasNext()){
				Map.Entry<Session, String> currentSession = (Map.Entry<Session, String>) it.next();
				if(currentSession.getValue().equals(id)){
					currentSession.getKey().getBasicRemote().sendText(msg);
				}
			}
		}
		
	}
	@OnError
	public void onError(Throwable e){
		e.printStackTrace();
	}
	@OnClose
	public void onClose(Session session){
		clients.remove(session);
	}
}
