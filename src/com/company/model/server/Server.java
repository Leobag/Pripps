package com.company.model.server;

import java.io.*;
import java.net.*;

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

   public static void main(String[] args){
        Server s = new Server();
   }

    /**
     * Creating a server makes a ServerSocket connecting to port 8080 and starts a loop
     * waiting for a connection from a client.
     *
     * - Leonard Bagiu
     */

    public Server(){

        try{

          ss = new ServerSocket(8080,1,InetAddress.getByName("127.0.0.1"));

        } catch(IOException e){
            System.out.println(e.getMessage());
        }

        this.runServer();

    }

    /**
     * runServer continuously creates a socket waiting for a connection from the ServerSocket
     * connected to port 8080. When a connection is established, the server first sends data
     * for processing, receives it back and proceeds to send it once again if an updated list
     * would be desired. The streams are then closed, and the socket is put on hold until another
     * connection is made.
     *
     * - Leonard Bagiu
     */

    private void runServer(){


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

                }
            }

    }

    /**
     * receiveScore checks the socket input stream. The function takes the stream and reads it with
     * a BufferedReader to be able to read packets of data instead of reading packet by packet.
     * The data is then converted to JSON using the GSON open source library and stored in the
     * local variable "serverScoreList"
     *
     * - Leonard Bagiu
     */

    private void receiveScore(){

        try{

            isr = new InputStreamReader(socket.getInputStream());
            br = new BufferedReader(isr);
            this.serverScoreList = g.fromJson(br.readLine(), serverScoreList.getClass());


        } catch(IOException e){
            System.out.println(e.getMessage());
        }


    }

    /**
     * Sendscore takes whichever data is within the socket output stream and converts it
     * through a BufferedWriter, allowing packets of data to be sent through a buffer instead of the data
     * being read packet by packet. The bufferedwriter then takes the local variable serverScoreList
     * and writes it with help of the GSON opensource library. The writer then adds a new line
     * to help the reader from another socket to know when to stop reading.
     *
     * - Leonard Bagiu
     */

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

    /**
     * closeStream closes all open streams, allowing new data to be channeled through sendScore and
     * receiveScore.
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
            System.out.println(e.getMessage());
        }
    }


}
