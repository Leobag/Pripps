package com.company.model.server;

import java.io.*;
import java.net.*;

import com.google.gson.*;

public class Client {

    private Socket socket = null;
    private String[][] scoreList;
    private Gson g = new Gson();
    private BufferedReader br = null;
    private BufferedWriter bw = null;
    private InputStreamReader isr = null;
    private OutputStreamWriter osw = null;

    public Client(){

        scoreList = new String[0][2];

    }

    /**
     * updateServerScore creates a socket in port 8080, connects to the server for the current
     * highscore list. The list is then updated with the new score, and the list is sorted according to best
     * time first. The updated highscore list is then sent to the server, and the connection is closed.
     *
     * @param name - entered name of player
     * @param s - the time of completion
     *
     * - Leonard Bagiu
     */

    public void updateServerScore(String name, int s){
            this.startServer();
            this.getServerScore();
            this.setNewScore(name, s);
            this.sortScores();
            this.sendScore();
            this.closeStream();

    }

    /**
     * Returns the current highscore list from the server. First starts the socket in port 8080,
     * receives the list, closes the stream and returns the current list.
     *
     * @return - returns the current highscore list on the server
     *
     * - Leonard Bagiu
     */

    public String[][] getTotalHighscore(){

        this.startServer();
        this.getServerScore();
        this.closeStream();

        return this.scoreList;

    }

    /**
     * Creates a socket in port 8080.
     *
     * - Leonard Bagiu
     */

    private void startServer(){
        try{
            this.socket = new Socket("142.93.106.21", 6000);

        } catch(IOException e){
            System.out.println("fail1");
        }
    }

    /**
     * Creates an output stream through a BufferedReader, allowing the String array
     * (which is first converted to Json) to be sent in groups of packets rather than the data
     * being transmitted packet by packet. The function then writes the data, finishing it with
     * a new line and flushes the buffer, making sure it is empty.
     *
     * - Leonard Bagiu
     */

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

    /**
     * Reads the input port of the socket with the help of a BufferedReader, significantly
     * speeding up the process. The read data is then converted from Json to a nested String array
     * uúsing the GSON opensource library.
     *
     * - Leonard Bagiu
     */

    private void getServerScore(){

        try{
            isr = new InputStreamReader(socket.getInputStream());
            br = new BufferedReader(isr);

            this.scoreList = g.fromJson(br.readLine(), scoreList.getClass());


        } catch(IOException e){
            System.out.println("fail3");
        }


    }

    /**
     * This function takes the new values and add them to the local String[][] scoreList.
     * The integer value is first converted to String. If the scoreList array is empty, the
     * first position is filled. Otherwise a new array with one extra spot is created, copying
     * all values of scoreList before adding the last value, effectively incrementing the array size
     * by one per function call.
     *
     * @param name - Name of player
     * @param s - Integer value of player time
     *
     * - Leonard Bagiu
     */


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

    /**
     * If array length is more than one, a bubble sort is done for the array.
     * Instead of running the length of the array multiple times, the for-loop index is reset every time
     * two values are switched, reassuring that the entire array is sorted when i reaches its max value
     *
     * - Leonard Bagiu
     */

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

    /**
     * Closes all open streams and closes the socket, allowing new values to be sent.
     *
     * - Leonard Bagiu
     */
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
