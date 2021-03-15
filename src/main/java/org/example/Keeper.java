package org.example;

import java.io.*;
import java.util.Scanner;

public class Keeper {

    private String filename;
    private File file;
    private int[][] template;

    public Keeper(String filename) {
        this.filename = filename;
    }

    public void createFile(){
        try{
            file = new File(filename);
            if(file.createNewFile()){
                System.out.println("File created: "+this.filename);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(MainView mainView){
        StringBuffer stringBuffer = new StringBuffer();
        for (int y = 0; y < mainView.getSimulation().height; y++) {
            for (int x = 0; x < mainView.getSimulation().width; x++) {
                stringBuffer.append(mainView.getSimulation().board[x][y]);
            }
        }
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
            bufferedWriter.write(String.valueOf(stringBuffer));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public int[][] readFromFile(MainView mainView){
        try{
            Scanner scanner = new Scanner(new File(filename));
            this.template = new int[mainView.getSimulation().height][mainView.getSimulation().width];
            String string = scanner.nextLine();
            int i = 0;
            for (int y = 0; y < mainView.getSimulation().height; y++) {
                for (int x = 0; x < mainView.getSimulation().width; x++) {
                        template[x][y] = Integer.parseInt(String.valueOf(string.charAt(i)));
                        i++;
                }
            }

            //testing printing out the saved state of board in console
            System.out.println("-Read from file-");
            for (int y = 0; y < mainView.getSimulation().height; y++) {
                String line = "|";
                for (int x = 0; x < mainView.getSimulation().width; x++) {
                    if (this.template[x][y] == Simulation.DEAD) {
                        line += ".";
                    } else line += "*";
                }
                line += "|";
                System.out.println(line);
            }
            System.out.println("---\n");
        } catch (FileNotFoundException e) {
            System.out.println("The file is not found!");;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return template;
    }



}
