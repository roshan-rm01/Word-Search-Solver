package com.solver.wordsearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    findWords();
    }

    public static String[][] getGrid(){
        String line;
        ArrayList<String> grid = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader("src\\com\\solver\\wordsearch\\WordSearch.txt"))){
            line = reader.readLine();
            while (line != null){
                grid.add(line.replaceAll("\\s+", ","));
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String[][] dimensions = new String[grid.toArray().length][];
        for (int i=0; i<grid.toArray().length; i++){
            String[] format = grid.get(i).split(",");
            dimensions[i] = format;
        }
        return  dimensions;
    }

    public static void displayGrid(){
        String[][] x = getGrid();
        for (String[] y: x){
            System.out.println(Arrays.toString(y));
        }
    }

    public static void checkArray(String[] list, String word, int row, int col) {
        String[] word_array = word.split("");
        int counter = 0;
        boolean finished = false;
        for (int i=0; i<list.length; i++){
            if (list[i].equals(word_array[0])){
                if (i == list.length-1){
                    break;
                }
                for (int j=0; j<word_array.length; j++){
                    if (counter > list.length-1){
                        break;
                    }
                    if (list[counter].equals(word_array[j])){
                        counter++;
                        if (j == word_array.length-1){
                            if (col == 0){
                                System.out.println("(" + (row + 1) + ", " + (counter-j) + ")" + " " + "(" + (row + 1) + ", " + counter + ")");
                                finished = true;
                            }else{
                                System.out.println("(" + (counter-j) + ", " + (col + 1) + ")" + " " + "(" + counter + ", " + (col + 1) + ")");
                                finished = true;
                            }
                        }
                    }else{
                        counter = i;
                        break;
                    }
                }
            }
            counter++;
            if (finished){
                break;
            }
        }
    }

    public static void checkDiagonalArray(String[] list, String word, int row, int col, boolean right) {
        String[] word_array = word.split("");
        int counter = 0;
        int word_start = 0;
        boolean finished = false;
        for (int i=0; i<list.length; i++){
            word_start++;
            if (list[i].equals(word_array[0])){
                if (i == list.length-1){
                    break;
                }
                for (int j=0; j<word_array.length; j++){
                    if (counter >= list.length){
                        break;
                    }
                    if (list[counter].equals(word_array[j])){
                        int begin = word_start;
                        if (right){
                            if (list[counter].equals(word_array[0])){
                                begin = word_start;
                            }
                            counter++;
                            if (j == word_array.length-1){
                                int word_end = begin + (word.length()-1);
                                int begin_x = row;
                                int begin_y = col;
                                for (int l=0; l<list.length-(begin); l++){
                                    begin_x--;
                                    begin_y--;
                                }
                                int end_x = row;
                                int end_y = col;
                                for (int m=0; m<list.length-(word_end); m++){
                                    end_x--;
                                    end_y--;
                                }
                                System.out.println("(" + (begin_x+1) + ", " + (begin_y+1) + ")" + " " + "(" + (end_x+1) + ", " + (end_y+1) + ")");
                                finished = true;
                            }
                        }else{
                            if (list[counter].equals(word_array[0])){
                                begin = word_start;
                            }
                            counter++;
                            if (j == word_array.length-1){
                                int word_end = begin + (word.length()-1);
                                int begin_x = row;
                                int begin_y = col;
                                for (int l=0; l<list.length-begin; l++){
                                    begin_x--;
                                    begin_y++;
                                }
                                int end_x = row;
                                int end_y = col;
                                for (int m=0; m<list.length-word_end; m++){
                                    end_x--;
                                    end_y++;
                                }
                                System.out.println("(" + (begin_x+1) + ", " + (begin_y+1) + ")" + " " + "(" + (end_x+1) + ", " + (end_y+1) + ")");
                                finished = true;
                            }
                        }
                    }else{
                        counter = i;
                        break;
                    }
                }
            }
            counter++;
            if (finished){
                break;
            }
        }
    }

    public static void checkDiagonalLeft(String word){
        String[][] grid = getGrid();
        int horizontal = grid[0].length;
        int x_point = 0;
        int y_point;
        for (int i=0; i<horizontal; i++){
            ArrayList<String> temp = new ArrayList<>();
            y_point = i;
            while (y_point >= 0 && x_point < grid.length){
                temp.add(grid[x_point][y_point]);
                x_point++;
                y_point--;
            }
            String[] list = temp.toArray(new String[0]);
            checkDiagonalArray(list, word, (x_point-1), (y_point+1), false);
            temp.clear();
            x_point = 0;
            if (i == horizontal-1){
                y_point = i;
                for (int j=0; j<grid.length; j++){
                    x_point = j;
                    while (y_point >= 0 && x_point < grid.length){
                        temp.add(grid[x_point][y_point]);
                        x_point++;
                        y_point--;
                    }
                    String[] last_list = temp.toArray(new String[0]);
                    checkDiagonalArray(last_list, word, x_point-1, (y_point+1), false);
                    temp.clear();
                    y_point = i;
                }
            }
        }
    }

    public static void checkDiagonalRight(String word){
        String[][] grid = getGrid();
        int horizontal = grid[0].length;
        int x_point = 0;
        int y_point = 0;
        for (int i=horizontal-1; i>=0; i--){
            ArrayList<String> temp = new ArrayList<>();
            y_point = i;
            while (y_point <= horizontal-1 && x_point < grid.length){
                temp.add(grid[x_point][y_point]);
                x_point++;
                y_point++;
            }
            String[] list = temp.toArray(new String[0]);
            checkDiagonalArray(list, word, x_point-1, y_point-1, true);
            temp.clear();
            x_point = 0;
            if (i == 0){
                y_point = i;
                for (int j=0; j<grid.length; j++){
                    x_point = j;
                    while (y_point <= horizontal-1 && x_point < grid.length){
                        temp.add(grid[x_point][y_point]);
                        x_point++;
                        y_point++;
                    }
                    String[] last_list = temp.toArray(new String[0]);
                    checkDiagonalArray(last_list, word, x_point-1, y_point-1, true);
                    temp.clear();
                    y_point = i;
                }
            }
        }
    }

    public static void checkHorizontal(String word) {
        String[][] grid = getGrid();
        for (int i=0; i<grid.length; i++){
            String[] horizontal = grid[i];
            checkArray(horizontal, word, i, 0);
        }
    }

    public static void checkVertical(String word){
        String[][] grid = getGrid();
        int horizontal = grid[0].length;
        String[] temp = new String[grid.length];
        for (int i=0; i<horizontal; i++){
            for (int j=0; j<grid.length; j++){
                temp[j] = grid[j][i];
            }
            checkArray(temp, word, 0, i);
        }
    }

    public static int getInteger(String message){
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextInt();
    }

    public static String getString(String message){
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine();
    }

    public static String reverseWord(String word){
        String[] arr = word.split("(?!^)");
        int counter = word.length()-1;
        String[] reverse = new String[word.length()];
        for (int i=0; i<arr.length; i++){
            String last = arr[counter];
            reverse[i] = last;
            counter--;
        }
        StringBuilder reversed_word = new StringBuilder();
        for (String x: reverse){
            reversed_word.append(x);
        }
        return reversed_word.toString();
    }

    public static void findWords(){
        displayGrid();
        int words = getInteger("How many words to search");
        String[] word_list = new String[words];
        for (int i=0; i<words; i++){
            String word = getString("Enter Word to Search");
            word_list[i] = word.toUpperCase();
        }
        for (String x: word_list){
            System.out.println(x + ": ");
            checkVertical(x);
            checkHorizontal(x);
            checkDiagonalLeft(x);
            checkDiagonalRight(x);
            String reversed = reverseWord(x);
            checkVertical(reversed);
            checkHorizontal(reversed);
            checkDiagonalLeft(reversed);
            checkDiagonalRight(reversed);
        }
    }

}
