import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


public class Main {

    private static boolean searcher(String search,String text)
    {
        if(search.length() > text.length())
        {
            return false;
        }
        else if(search.equals(""))
        {
            return false;
        }
        else
        {
            boolean possible = false;

            
            for(int i = 0; i < text.length()-search.length(); i++)
            {
                boolean match = true;
                
                for(int j = 0; j < search.length(); j++)
                {
                    if(search.charAt(j) == text.charAt(i+j))
                    {
                        match = match && true;

                    }
                    else
                    {
                        match = match && false;
                    }
                }
                possible = possible || match;
            }
            return possible;
        }
    }

    public static void main(String[] args) throws IOException {



        FileReader fileReader = new FileReader("ProgrammingHistory.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);


        ArrayList<String> listOfLines = new ArrayList<>();

        String lineRead;



        while ((lineRead = bufferedReader.readLine()) != null)
        {
            listOfLines.add(lineRead);
        }
        bufferedReader.close();


        FileWriter fileWriter = new FileWriter("out.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for(String s : listOfLines){
            if(s.equals("")) {

            } else{
                for (int i = 0; i < s.length(); i++)
                {

                    if ((s.charAt(i) == '.'))
                    {

                        if (((i + 1) == s.length()))
                        {
                            bufferedWriter.write(s.charAt(i) + "\r");
                        }
                        else if ((s.charAt(i + 1) == ' '))
                        {
                            bufferedWriter.write(s.charAt(i) + "\r");
                        }
                        else if ((s.charAt(i + 1) == '['))
                        {
                            bufferedWriter.write(s.charAt(i));
                        }
                        else
                        {
                            bufferedWriter.write(s.charAt(i));
                        }
                    } else if (s.charAt(i) == ' ')
                    {
                        if (i - 1 > -1) {
                            if (s.charAt(i - 1) == '.')
                            {
                            } else if (i - 4 > -1)
                            {
                                if (s.charAt(i - 4) == '.' && s.charAt(i - 1) == ']' && s.charAt(i) == ' ')
                                {

                                } else
                                {
                                    bufferedWriter.write(s.charAt(i));
                                }
                            } else
                            {
                                bufferedWriter.write(s.charAt(i));
                            }
                        } else
                        {
                            bufferedWriter.write(s.charAt(i));
                        }

                    } else if(s.charAt(i) == ']')
                    {
                        if (i - 3 > -1)
                        {
                            if (s.charAt(i - 3) == '.')
                            {
                                if(!(i+1 >= s.length()))
                                {
                                    bufferedWriter.write(s.charAt(i) + "\r");
                                } else
                                {
                                    bufferedWriter.write(s.charAt(i));
                                }
                            } else
                            {
                                bufferedWriter.write(s.charAt(i));
                            }
                        } else
                        {
                            bufferedWriter.write(s.charAt(i));
                        }
                    } else
                    {
                        bufferedWriter.write(s.charAt(i));
                    }
                }
            }
        }



        bufferedWriter.close();

        ArrayList<String> sentences = new ArrayList<>();

        FileReader fr2 = new FileReader("out.txt");

        BufferedReader br2 = new BufferedReader(fr2);

        while ((lineRead = br2.readLine()) != null){
            sentences.add(lineRead);
        }
        br2.close();

        System.out.println("Word Search:");
        Scanner scanner = new Scanner(System.in);
        String targetWord = scanner.nextLine();
        int foundCount = 0;

        for(String s : sentences)
        {
            if(s.length() < targetWord.length())
            {
                System.out.println("insufficient memory");
            }
            else if(targetWord.equals(""))
            {
                System.out.println("no input");
            }
            else if(searcher(targetWord,s)){

                for(int i = 0; i < s.length()-targetWord.length(); i++)
                {
                    boolean matched = true;


                    for (int j = 0; j < targetWord.length(); j++)
                    {

                        for(int k = 0; k < targetWord.length(); k++)
                        {
                            if(targetWord.charAt(k) == s.charAt(i+k))
                            {
                                matched = matched && true;
                            }
                            else{
                                matched = matched && false;
                            }
                        }

                    }
                    if(matched){
                        System.out.println("Found at "+Math.addExact(sentences.indexOf(s),1)+"th sentence" +
                                " at "+Integer.toString(i+1) + "th word");
                        foundCount++;
                    }
                }
            }
        }
        if(foundCount > 0){

        } else{
            System.out.println("No word exist");
        }

    }

}
