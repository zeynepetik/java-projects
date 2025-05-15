package Main;

import java.io.IOException;
import java.util.Scanner;
import src.spellChecker.SpellChecker;
import src.structures.MyArrayList;


public class Main {
    public static final void main(String[] args)throws IOException{
        try {
            SpellChecker spell_checker=new SpellChecker("dictionary.txt");
        Scanner scan=new Scanner(System.in);
        boolean running=true;
        while(running){
            System.out.println("Enter a word: ");
            String word=scan.nextLine().trim();
            if(word.equals("^")){
                System.out.println("Exiting...");
                running=false;
                break;
            }
            long startTime=System.nanoTime();
            if(spell_checker.check(word)){
                System.out.println("Correct.\n");
            }
            else{
                System.out.println("Incorrect.");
                MyArrayList<String> suggestions=spell_checker.getSuggestions(word);
                System.out.println("Suggestions: ");
                for(int i=0;i<suggestions.size();i++){
                    System.out.println(suggestions.get(i));
                }
            }
            long endTime=System.nanoTime();
            System.out.printf("Lookup and suggestion time: %.2f ms\n", (endTime-startTime)/1000000.0);
        }
        scan.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }   
    } 
}
