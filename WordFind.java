// Christian Morris
// CSCI 271
// Programming Assignment #2

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordFind
{
    
    public static void WordSearch(String key, char [][] array)
    {
        boolean found = false;
        String orient = "";
        int i = 0;
        int j = 0;
        int length = key.length() - 1;
        int compares = 0;

        //Iterate through each possible starting position
        for (i=0; i < array.length && !found; i++)
        {
            for (j=0; j < array[0].length && !found; j++)
            {
                ++compares;

                //Compare the first character to the key
                if (array[i][j] == key.charAt(0))
                {
                    //Check North
                    if (!found && i-length >= 0)
                    {
                        boolean match = true;
                        int idx = 1;
                        while (match && idx <= length)
                        {
                            if (array[i-idx][j] != key.charAt(idx)) match = false;
                            ++idx;
                            ++compares;
                        }

                        if (match)
                        {
                            found = true;
                            orient = "North";
                        }
                    }

                    //Check Northeast
                    if (!found && i-length >= 0 && j+length < array[i].length)
                    {
                        boolean match = true;
                        int idx = 1;
                        while (match && idx <= length)
                        {
                            if (array[i-idx][j+idx] != key.charAt(idx)) match = false;
                            ++idx;
                            ++compares;
                        }

                        if (match)
                        {
                            found = true;
                            orient = "Northeast";
                        }
                    }

                    //Check East
                    if (!found && j+length < array[i].length)
                    {
                        boolean match = true;
                        int idx = 1;
                        while (match && idx <= length)
                        {
                            if (array[i][j+idx] != key.charAt(idx)) match = false;
                            ++idx;
                            ++compares;
                        }

                        if (match)
                        {
                            found = true;
                            orient = "East";
                        }
                    }

                    //Check Southeast
                    if (!found && i+length < array.length && j+length < array[i].length)
                    {
                        boolean match = true;
                        int idx = 1;
                        while (match && idx <= length)
                        {
                            if (array[i+idx][j+idx] != key.charAt(idx)) match = false;
                            ++idx;
                            ++compares;
                        }

                        if (match)
                        {
                            found = true;
                            orient = "Southeast";
                        }
                    }

                    //Check South
                    if (!found && i+length < array.length)
                    {
                        boolean match = true;
                        int idx = 1;
                        while (match && idx <= length)
                        {
                            if (array[i+idx][j] != key.charAt(idx)) match = false;
                            ++idx;
                            ++compares;
                        }

                        if (match)
                        {
                            found = true;
                            orient = "South";
                        }
                    }

                    //Check Southwest
                    if (!found && i+length < array.length && j-length >= 0)
                    {
                        boolean match = true;
                        int idx = 1;
                        while (match && idx <= length)
                        {
                            if (array[i+idx][j-idx] != key.charAt(idx)) match = false;
                            ++idx;
                            ++compares;
                        }

                        if (match)
                        {
                            found = true;
                            orient = "Southwest";
                        }
                    }

                    //Check West
                    if (!found && j-length >= 0)
                    {
                        boolean match = true;
                        int idx = 1;
                        while (match && idx <= length)
                        {
                            if (array[i][j-idx] != key.charAt(idx)) match = false;
                            ++idx;
                            ++compares;
                        }

                        if (match)
                        {
                            found = true;
                            orient = "West";
                        }
                    }

                    //Check Northwest
                    if (!found && i-length >=0 && j-length >= 0)
                    {
                        boolean match = true;
                        int idx = 1;
                        while (match && idx <= length)
                        {
                            if (array[i-idx][j-idx] != key.charAt(idx)) match = false;
                            ++idx;
                            ++compares;
                        }

                        if (match)
                        {
                            found = true;
                            orient = "Northwest";
                        }
                    }
                }
            }
        }

        if (found)
        {
            System.out.println(key.toUpperCase() + " was found starting at " + (i) + ", " + (j) + " and oriented " + orient + " (" + compares + ")");
        }

        else
        {
            System.out.println(key.toUpperCase() + " was not found (" + compares + ")");
        }
    }

    public static void main(String[] args)
    {
        //Ensure the correct amount of arguments are provided
        if (args.length > 2 || args.length == 0)
        {
            System.out.println("\tERR: WordFind requires at most two command-line arguments: searchfile, keyfile");
            System.exit(1);
        }
    
        //Open the searchfile
        File userFile = new File(args[0]);

        //Declare variables for future use
        int rowCount = 0;
        int colCount = 0;
        char [] [] charGrid = new char [0] [0];
        String [] keyList = new String [0];

        try 
        {
            //Process the searchfile
            Scanner inputFile = new Scanner(userFile);

            boolean colCountDone = false;

            while (inputFile.hasNext())
            {
                String currLine = inputFile.nextLine();
                String charLine = "";

                //Determine if the current line has character content, and increment the number of rows if it does
                for (int i=0; i < currLine.length(); i++)
                {
                    if (Character.isLetterOrDigit(currLine.charAt(i)))
                    {
                        charLine = charLine + currLine.charAt(i);
                    }
                }

                if (!charLine.isEmpty())
                {
                    rowCount++;

                    //Count the number of columns on the first applicable row
                    if (!colCountDone)
                    {
                        colCount = charLine.length();
                    }
                }  
            }

            inputFile.close();
            inputFile = new Scanner(userFile);
            
            //Insert the file contents into an array
            charGrid = new char [rowCount] [colCount];

            int lineCount = 0;
            while (inputFile.hasNext())
            {
                String currLine = inputFile.nextLine();
                String charLine = "";

                //Determine if the current line has character content, and extract that content
                for (int i=0; i < currLine.length(); i++)
                {
                    if (Character.isLetterOrDigit(currLine.charAt(i)))
                    {
                        charLine = charLine + Character.toLowerCase(currLine.charAt(i));
                    }
                }

                //Assign alphabetic content to the array
                if (!charLine.isEmpty())
                {
                    for (int j=0; j < charLine.length(); j++)
                    {
                        charGrid [lineCount] [j] = charLine.charAt(j);
                    } 
                    lineCount++;
                }  
            }

            inputFile.close();

            if (args.length == 2)
            {
                //Process the keyfile
                File userKey = new File(args[1]);
                Scanner inputKey = new Scanner(userKey);
                
                lineCount = 0;
                while (inputKey.hasNext())
                {
                    String currLine = inputKey.nextLine();
                    String charLine = "";

                    //Determine if the current line has character content, and increment the line count if it does
                    for (int i=0; i < currLine.length(); i++)
                    {
                        if (Character.isLetterOrDigit(currLine.charAt(i)))
                        {
                            charLine = charLine + currLine.charAt(i);
                        }
                    }

                    if (!charLine.isEmpty())
                    {
                        lineCount++;
                    }
                }

                inputKey.close();
                inputKey = new Scanner(userKey);

                //Insert the keyfile contents into an array
                keyList = new String [lineCount];

                lineCount = 0;
                while (inputKey.hasNext())
                {
                    String currLine = inputKey.nextLine();
                    String charLine = "";

                    //Determine if the current line has character content, and extract that content
                    for (int i=0; i < currLine.length(); i++)
                    {
                        if (Character.isLetterOrDigit(currLine.charAt(i)))
                        {
                            charLine = charLine + Character.toLowerCase(currLine.charAt(i));
                        }
                    }

                    //Assign string to the array if applicable
                    if (!charLine.isEmpty())
                    {
                        keyList [lineCount] = charLine;
                        lineCount++;
                    }  
                }

                inputKey.close();

            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("\t" + e);
            System.exit(1);
        }

        //Case for searchfile and keyfile
        if (args.length == 2)
        {
            //Apply WordSearch for all given keys in the file
            for (int i=0; i < keyList.length; i++)
            {
                WordSearch(keyList[i], charGrid);
            }
        }

        //Case for only searchfile
        else
        {
            System.out.println("File \"" + args[0] + "\" was succesfully opened.");
            System.out.print("\tEnter the search term [blank to exit]: ");

            Scanner userInput = new Scanner(System.in);
            String key = userInput.nextLine();

            while (!key.isBlank())
            {
                String filterKey = "";
                for (int i=0; i < key.length(); i++)
                {
                    if (Character.isLetterOrDigit(key.charAt(i)))
                    {
                        filterKey = filterKey + Character.toLowerCase(key.charAt(i));
                    }
                }

                WordSearch(filterKey, charGrid);

                System.out.print("\tEnter the search term [blank to exit]: ");
                key = userInput.nextLine();
            }

            userInput.close();
            System.out.println("\tExiting...");
        }

    }
}