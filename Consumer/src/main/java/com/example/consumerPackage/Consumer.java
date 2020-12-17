package com.example.consumerPackage;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;

@Component
public class Consumer {

        @RabbitListener(queues = "link1")
        public void receive1(String in) throws InterruptedException {
            receive(in, 1);
        }


        public void receive(String in, int receiver) {
            System.out.println("instance " + receiver + " [x] Received '" + in + "'");
            Long inLinkId = Long.parseLong(in.substring(in.indexOf("linkId")+8, in.indexOf("url")-2));
            String inUrl = in.substring(in.indexOf("url")+6, in.length()-2).replace("\\", "");
            System.out.println(inLinkId+ "aaa"+ inUrl);
            try {
                URL url = new URL(inUrl);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                int code = con.getResponseCode();
                System.out.println(code);
                try {
                    URL appUrl = new URL("http://192.168.99.100:8080/status/link");
                    HttpURLConnection appConnection = (HttpURLConnection) appUrl.openConnection();
                    appConnection.setRequestMethod("PUT");
                    appConnection.setDoOutput(true);
                    appConnection.setRequestProperty("Content-Type", "application/json");
                    appConnection.setRequestProperty("Accept", "application/json");
                    OutputStreamWriter osw = new OutputStreamWriter(appConnection.getOutputStream());
                    String str = "{\"id\":"+inLinkId+",\"status\":"+code+",\"url\":\""+inUrl+"\"}";
                    System.out.println(str);
                    osw.write(str);
                    osw.flush();
                    osw.close();
                    System.out.println("appCode "+appConnection.getResponseCode());
                } catch (IOException e) {
                    System.out.println(Arrays.toString(e.getStackTrace()));
                }
            } catch (Exception e) {
                System.out.println(Arrays.toString(e.getStackTrace()));
            }
        }
}
