package com.company.model.server;

import java.io.*;
import java.net.*;
import java.util.concurrent.CompletableFuture;

import com.google.gson.*;

public class Server{

    private ServerSocket ss;
    private Socket socket;

    private Gson g = new Gson();

    private BufferedReader br = null;
    private BufferedWriter bw = null;
    private InputStreamReader isr = null;
    private OutputStreamWriter osw = null;
    private String[][] serverScoreList = new String[1][2];

    public Server(){

        try{

          ss = new ServerSocket(8080);

        } catch(IOException e){
            System.out.println(e.getMessage());
        }

        this.runServer();


    }

    private void runServer(){

        CompletableFuture.runAsync(()->{

            while(true){
                try{
                    socket = ss.accept();
                } catch(IOException e){
                    System.out.println(e.getMessage());
                }

                if(socket.isConnected()){

                    this.sendScore();
                    this.receiveScore();
                    this.sendScore();
                    this.closeStream();
                    System.out.println(g.toJson(serverScoreList));

                }
            }

        });

    }



    private void receiveScore(){

        try{

            isr = new InputStreamReader(socket.getInputStream());
            br = new BufferedReader(isr);
            this.serverScoreList = g.fromJson(br.readLine(), serverScoreList.getClass());


        } catch(IOException e){
            System.out.println(e.getMessage());
        }


    }

    private void sendScore(){

        try{

            osw = new OutputStreamWriter(socket.getOutputStream());
            bw = new BufferedWriter(osw);
            bw.write(g.toJson(serverScoreList));
            bw.newLine();
            bw.flush();

        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    private void closeStream(){
        try{
            if(isr != null){
                isr.close();
            }
            if(osw != null){
                osw.close();
            }
            if(br != null){
                br.close();
            }
            if(bw != null){
                bw.close();
            }
            if(socket != null){
                socket.close();
            }

        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }


}
