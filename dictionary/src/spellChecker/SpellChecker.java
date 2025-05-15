package src.spellChecker;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import src.structures.MyArrayList;
import src.structures.GTUHashSet;

public class SpellChecker {
    private GTUHashSet<String> dictionary;
    private static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final int MAX_OPERATIONS = 8000;

    public SpellChecker(String f_name) throws IOException{
        dictionary= new GTUHashSet<String>();
        readDictionary(f_name);
    }

    public void readDictionary(String f_name) throws IOException{
        try{
            File file=new File(f_name);
            Scanner scan=new Scanner(file);
            while(scan.hasNextLine()){
                String word=scan.nextLine().trim();
                if(!word.isEmpty()){
                    dictionary.add(word.toLowerCase());
                }
            }
            scan.close();
            System.out.println("Disctionary loaded succesfully with "+ dictionary.size()+ " words.\n");

        }catch(Exception e){
            System.err.println("Error reading dictionary file: " + e.getMessage()+"\n");
        }
    }

    public boolean check(String word){
        if(word==null || word.isEmpty()){
            throw new IllegalArgumentException("Word cannot be null or empty");
        }
        return dictionary.contains(word.toLowerCase());
    } 

    public MyArrayList<String> getSuggestions(String word){
        word=word.toLowerCase();
        MyArrayList<String> suggestions=new MyArrayList<String>();
        MyArrayList<String> edit1=generateEdits(word);
        int operation_count=0;
        for(int i=0;i<edit1.size() && operation_count<MAX_OPERATIONS;i++){
            operation_count++;
            String edit=edit1.get(i);
            boolean isDuplicate1=false;
            for(int n=0;n<suggestions.size();n++){
                if(suggestions.get(n).equals(edit)){
                    isDuplicate1=true;
                    break;
                }
            }
            if(!isDuplicate1 && dictionary.contains(edit)){
                suggestions.add(edit);
            }
        }
        for(int i=0;i<edit1.size() && operation_count<MAX_OPERATIONS;i++){
            String edit=edit1.get(i);
            MyArrayList<String> edit2=generateEdits(edit);
            for(int j=0;j<edit2.size() && operation_count<MAX_OPERATIONS;j++){
                operation_count++;
                String edit2Word=edit2.get(j);
                boolean isDuplicate2=false;
                for(int k=0;k<suggestions.size();k++){
                    if(suggestions.get(k).equals(edit2Word)){
                        isDuplicate2=true;
                        break;
                    }
                }
                if(!isDuplicate2 && dictionary.contains(edit2Word)){
                    suggestions.add(edit2Word);
                }
            }
        }
        return suggestions;
    }

    private MyArrayList<String> generateEdits(String word){
        int len=word.length();
        MyArrayList<String> edits=new MyArrayList<String>();
        // Deletion
        for(int i=0;i<len;i++){
            String deletion=word.substring(0,i)+word.substring(i+1);
            edits.add(deletion);
        }
        //Replacement
        for(int i=0;i<len;i++){
            for(char c:ALPHABET){
                String replacement=word.substring(0,i)+c+word.substring(i+1);
                edits.add(replacement);
            }
        }
        //Insertion
        for(int i=0;i<=len;i++){
            for(char c:ALPHABET){
                String insertion=word.substring(0,i)+c+word.substring(i);
                edits.add(insertion);
            }
        }
        //Transposition
        for(int i=0;i<len-1;i++){
            String transposition=word.substring(0,i)+word.charAt(i+1)+word.charAt(i)+word.substring(i+2);
            edits.add(transposition);
        }

        return edits;
    }
}
