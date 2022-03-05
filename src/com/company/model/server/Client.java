package com.company.model.server;

import java.io.*;
import java.net.*;

import com.google.gson.*;

public class Client {

    private Socket socket = null;
    private String[][] scoreList;
    private int arraySize;
    private Gson g = new Gson();
    private BufferedReader br = null;
    private BufferedWriter bw = null;
    private InputStreamReader isr = null;
    private OutputStreamWriter osw = null;

    public Client(){

        scoreList = new String[0][2];

    }

    public void updateServerScore(String name, int s){
            this.startServer();
            this.getServerScore();
            this.setNewScore(name, s);
            this.sortScores();
            this.sendScore();
            this.closeStream();

    }

    public String[][] getTotalHighscore(){

        this.startServer();
        this.getServerScore();
        this.closeStream();

        return this.scoreList;

    }

    private void startServer(){
        try{
            this.socket = new Socket("localhost", 8080);

        } catch(IOException e){
            System.out.println("fail1");
        }
    }

    private void sendScore() {
        try{

            String currentList = g.toJson(scoreList);

            osw = new OutputStreamWriter(socket.getOutputStream());
            bw = new BufferedWriter(osw);
            bw.write(currentList);
            bw.newLine();
            bw.flush();


        } catch(IOException e){
            System.out.println("fail2");
        }

    }


    private void getServerScore(){

        try{
            isr = new InputStreamReader(socket.getInputStream());
            br = new BufferedReader(isr);

            this.scoreList = g.fromJson(br.readLine(), scoreList.getClass());


        } catch(IOException e){
            System.out.println("fail3");
        }


    }


    public void setNewScore(String name, int s){
        String score = Integer.toString(s);
        String[][] tempArray;

        if(scoreList[0][0] != null){

            tempArray = new String[scoreList.length + 1][2];


            int i;
            for (i = 0; i < scoreList.length; i++){


                tempArray[i][0] = scoreList[i][0];
                tempArray[i][1] = scoreList[i][1];
            }
            tempArray[i][0] = name;
            tempArray[i][1] = score;


        }else{
            tempArray = new String[1][2];
            tempArray[0][0] = name;
            tempArray[0][1] = score;

        }

        scoreList = tempArray;


    }

    public void sortScores(){

        if(scoreList.length > 1){

        for (int i = 1; i < scoreList.length; i++){

            if( Integer.parseInt(scoreList[i-1][1]) > Integer.parseInt(scoreList[i][1])){
                String tempName, tempScore;
                tempName = scoreList[i-1][0];
                tempScore = scoreList[i-1][1];

                scoreList[i-1][0] = scoreList[i][0];
                scoreList[i-1][1] = scoreList[i][1];

                scoreList[i][0] = tempName;
                scoreList[i][1] = tempScore;

                i=0;
            }
          }
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
            System.out.println("fail4");
        }
    }



}
