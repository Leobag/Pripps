package com.company.model.server;

import java.io.*;
import java.net.*;

import com.google.gson.*;

public class server {

    private ServerSocket ss = null;
    private Socket socket = null;

    private Gson g;

    private BufferedReader br = null;
    private BufferedWriter bw = null;
    private InputStreamReader isr = null;
    private OutputStreamWriter osw = null;
    private String[][] scoreList;

    public server(){


    }



    public void receiveScore(){

        try{
            ss = new ServerSocket(8080);
            socket = ss.accept();

            isr = new InputStreamReader(socket.getInputStream());
            br = new BufferedReader(isr);

            g = new Gson();

            this.scoreList = g.fromJson(br.readLine(), scoreList.getClass());

            this.closeStream();


        } catch(IOException e){
            System.out.println(e.getMessage());
        }


    }

    public void sendScore(){


        try{
            ss = new ServerSocket(8080);
            socket = ss.accept();

            osw = new OutputStreamWriter(socket.getOutputStream());
            bw = new BufferedWriter(osw);
            g = new Gson();
            bw.write(g.toJson(scoreList));
            bw.newLine();
            bw.flush();

        } catch(IOException e){
            System.out.println(e.getMessage());
        }

         this.closeStream();
    }

    private void closeStream(){
        try{
            if(socket != null){
                socket.close();
            }
            if(ss != null){
                ss.close();
            }
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
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }


}
