package com.example.demo1;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Author: yin7331
 * Date: 2023/11/2 22:35
 * Describe: 弹幕 scrolling
 */
@ServerEndpoint("/scrolling")
@Slf4j
@Component

public class ScrollingWebsocketServiceImpl {

   private Session session;

    private static AtomicInteger onlineCount = new AtomicInteger(0);

    private static ConcurrentHashMap<String,ScrollingWebsocketServiceImpl> sessionMap =
            new ConcurrentHashMap();

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        String sessionId = session.getId();
        if (sessionMap.containsKey(sessionId)){
            sessionMap.remove(sessionId);
            sessionMap.put(sessionId, this);
        }else {
            sessionMap.put(sessionId,this);
            onlineCount.getAndIncrement();
        }
        System.out.println("scrolling on open");
        log.debug("onOpen {}",onlineCount.get());

    }

    @OnClose
    public void onClose(){
        System.out.println("scrolling on close");
        log.debug("onClose");

    }

    @OnMessage
    public void onMessage(String message){
        System.out.println("scrolling on message:"+message);
        log.debug("message  {}",message);

    }

    @OnError
    public void onError(Throwable error){
        error.printStackTrace();
    }

    //或直接指定时间间隔，例如：5秒
    @Scheduled(fixedRate=1000)
    private void noticeOnlineCount() throws IOException {
        for(Map.Entry<String, ScrollingWebsocketServiceImpl> entry : ScrollingWebsocketServiceImpl.sessionMap.entrySet()){
            ScrollingWebsocketServiceImpl webSocketService = entry.getValue();
            if(webSocketService.session.isOpen()){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("scrolling onlineCount", onlineCount.get());
                jsonObject.put("scrolling msg", "当前在线人数为" + onlineCount.get());
                webSocketService.sendMessage(jsonObject.toJSONString());
            }
        }

        log.debug("scrolling scheduled");
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

}


