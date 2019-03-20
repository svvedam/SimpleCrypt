import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;

public class ROT13  {
    private String originalFileText;

    public ROT13(String fileName) {

        this.originalFileText = FileReader.readFile(fileName);
    }

    ROT13(Character cs, Character cf) {
    }

    ROT13() {
    }


    public String crypt(String text) throws UnsupportedOperationException {
        StringBuilder result= new StringBuilder();
        int n = 13;

        for (int i=0; i<text.length(); i++)
        {
            if(text.charAt(i) == ' ')
            {
                result.append(' ');
                continue;
            }
            else if(text.charAt(i) == '?')
            {
                result.append("?");
                continue;
            }
            else {
                if (Character.isUpperCase(text.charAt(i)))
                {
                    char ch = (char)(((int)text.charAt(i) +
                            n - 65) % 26 + 65);
                    result.append(ch);
                }
                else
                {
                    char ch = (char)(((int)text.charAt(i) +
                            n - 97) % 26 + 97);
                    result.append(ch);
                }
            }
        }
        String returnString = result.toString();
        return returnString;
    }

    public String encrypt(String text) {
        String str = crypt(text);
        return str;
    }

    public String decrypt(String text) {
        int shift =13;
        String cipherText="";
        int length = text.length();
        for (int i = 0; i < text.length(); i++) {
             char ch = text.charAt(i);
             if(Character.isLetter(ch)){
                 if(Character.isLowerCase(ch)){
                     char c =(char)(ch-shift);
                     if(c<'a'){
                         cipherText +=(char)(ch+(26-shift));
                     }
                     else{
                         cipherText+=c;
                     }
                 }
                 else if(Character.isUpperCase(ch)){
                     char c = (char) (ch-shift);
                     if(c<'A'){
                         cipherText +=(char)(ch+(26-shift));
                     }
                     else{
                         cipherText+=c;
                     }
                 }
             }
             else{
                 cipherText+=ch;
             }
        }
        return cipherText;
    }

    public static String rotate(String s, Character c) {
        if((s.charAt(0)=='A') && (c=='A'))
            return s;
        StringBuilder result = new StringBuilder();
        for (int i = (s.length()/2); i < s.length(); i++) {

            result.append(s.charAt(i));
        }
        for (int j = 0; j<s.length()/2 ;j++) {
            result.append(s.charAt(j));
        }
        return result.toString();
    }
    public void processFile(){
        try{
            File file = new File("sonnet");
            String encryptedString = crypt(originalFileText);
            System.out.println(encryptedString);
            org.apache.commons.io.FileUtils.writeStringToFile(file, encryptedString,  "utf8");


        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

