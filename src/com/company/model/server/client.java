package com.company.model.server;

import java.io.*;
import java.net.*;

import com.google.gson.*;


public class client {

    private Socket socket = null;
    private String[][] scoreList;
    private int arraySize;
    private Gson g;
    private BufferedReader br = null;
    private BufferedWriter bw = null;
    private InputStreamReader isr = null;
    private OutputStreamWriter osw = null;

    public client(){

    }

    public void sendScore() {
        try{
            socket = new Socket("localhost", 8080);
            String currentList = g.toJson(scoreList);

            osw = new OutputStreamWriter(socket.getOutputStream());
            bw = new BufferedWriter(osw);
            bw.write(currentList);
            bw.newLine();
            bw.flush();



        } catch(IOException e){
            System.out.println(e.getMessage());
        }

        this.closeStream();

    }

    public String[][] getServerScore(){


        try{
            socket = new Socket("localhost", 8080);
            isr = new InputStreamReader(socket.getInputStream());
            br = new BufferedReader(isr);

            g = new Gson();
            this.scoreList = g.fromJson(br.readLine(), scoreList.getClass());




        } catch(IOException e){
            System.out.println(e.getMessage());
        }

            this.closeStream();

            return scoreList;
    }


    private void closeStream(){
        try{
            if(socket != null){
                socket.close();
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




    public void setNewScore(String score, String name){
        String[][] tempArray = new String[scoreList.length + 1][];
        int i;
        for (i = 0; i < scoreList.length; i++){

            tempArray[i][0] = scoreList[i][0];
            tempArray[i][1] = scoreList[i][1];
        }
        tempArray[i][0] = name;
        tempArray[i][1] = score;

        scoreList = tempArray;

    }

    public void sortScores(){

        for (int i = 0; i < scoreList.length; i++){

            if( Integer.parseInt(scoreList[i][1]) > Integer.parseInt(scoreList[i+1][1])){
                String tempName, tempScore;
                tempName = scoreList[i][0];
                tempScore = scoreList[i][1];

                scoreList[i][0] = scoreList[i+1][0];
                scoreList[i][1] = scoreList[i+1][1];

                scoreList[i+1][0] = tempName;
                scoreList[i+1][1] = tempScore;

                i=0;
            }

        }


    }

    public void updateScoreList(){

    }



}
