/**
 * Author - Luke Walters
 * This program maps the home category to other categories and can get the images and text necessary for the AAC
 * Acknowledgements: Worked with Kevin Johanson on this file
 */
package Structures;

import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AACMappings {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

    AACCategory AACHome;

    AACCategory currentCategory;

    AssociativeArray<String, AACCategory> allCategories;



  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+
    /**
     * Constructor for AACMappings
     * @param filename - takes in a filename in the fromat of a string
     */
    public AACMappings (String filename){
        this.AACHome = new AACCategory("home");
        this.allCategories = new AssociativeArray<>();
        String[] holder = new String[10];

        try{
            File myFile = new File(filename);
            Scanner myScanner = new Scanner(myFile);
            while (myScanner.hasNextLine()){
                String data = myScanner.nextLine();
                if(!(data.charAt(0) == '>')){
                    holder = data.split(" ");
                    this.AACHome.addItem(holder[0], holder[1]);
                    this.allCategories.set(holder[0], new AACCategory(holder[1]));
                    try{
                    this.currentCategory = this.allCategories.get(holder[0]);
                    } catch(KeyNotFoundException e){
                        System.err.println("This category does not exist");
                    }//catch()   
                }//if()
                else {
                    
                    holder = data.split(" ");
                    holder[0] = holder[0].substring(1, holder[0].length());
                    System.out.println(holder[0]);
                    this.currentCategory.addItem(holder[0], holder[1]);
                    
                    
                }//else()

            }//while()
            myScanner.close();
            this.currentCategory = this.AACHome;
        } catch (FileNotFoundException e){
            System.out.println("File does not exist");
        }//catch()
    }//AACMappings()



  // +----------------+----------------------------------------------
  // |    Methods     |
  // +----------------+

    /**
     * Given the image location selected, it determines the associated text with the image. 
     * If the image provided is a category, it also updates the AAC's current category to be the category associated with that image
     * @param imageLoc - the location where the image is stored
     * @return - String
     */
    public String getText (String imageLoc){
        if (this.currentCategory.getCategory().equals(this.AACHome.getCategory())){
            try{
            this.currentCategory = this.allCategories.get(imageLoc);
            return this.AACHome.getText(imageLoc);
            } catch (KeyNotFoundException e){
                return "That image does not exist within the current category";
            }//catch(KeyNotFoundException)
        } //if()
        else {
            return this.currentCategory.getText(imageLoc);
        } //else()
    }//getText()


    /**
     * Provides an array of all the images in the current category
     * @return - String[]
     */
    public String[] getImageLocs(){
        return currentCategory.getImages();
    }//getImageLocs()


    /**
     * Resets the current category of the AAC back to the default category
     */
    public void reset(){
        this.currentCategory = this.AACHome;
    }//reset()

    /**
     * returns the current category or the empty string if on the default category
     * @return - String
     */
    public String getCurrentCategory(){
        if (currentCategory.getCategory().equals(this.AACHome.getCategory())){
            return "";
        }//if()
        return currentCategory.getCategory();
    }


    /**
     * Determines if the image represents a category or text to speak
     * @param imageLoc - the location where the image is stored
     * @return - true if the image represents a category, false if the image represents text to speak
     */
    public boolean isCategory (String imageLoc){
        return false;
    }


    /**
     * Writes the ACC mappings stored to a file. 
     * @param filename - the name of the file to write the AAC mapping to
     */
    public void writeToFile (String filename){
        File saveFile = new File(filename);
        try{
        PrintWriter pen = new PrintWriter(saveFile);

        String[] keys = this.allCategories.getKeys();
        for (int i = 0; i < this.allCategories.size(); i++){
            try{
                pen.println(keys[i] + " " + this.allCategories.get(keys[i]).getCategory());
                try{
                    this.currentCategory = this.allCategories.get(keys[i]);
                    String[] catKeys = this.currentCategory.getImages();
                    String[] catText = this.currentCategory.getAllText();
                    for (int j = 0; j < this.currentCategory.size(); j++){
                        pen.println(">" + catKeys[j] + " " + catText[j]);
                    }//for(currentCategory)
                } catch (KeyNotFoundException e){
                    System.err.println("That key does not exist");
                }//catch(KeynotFound)
            } catch(KeyNotFoundException e){
                System.err.println("That key does not exist");
            }//catch(KeyNotFound)

        }//for(allCategories)
        pen.println();




        pen.close();
        } catch (FileNotFoundException e) {
            System.err.println("The file does not exist");
        }//catch()
        
    }//writeToFile

    /**
     * Adds the mapping to the current category (or the default category if that is the current category)
     * @param imageLoc - the location of the image
     * @param text - - the text associated with the image
     */
    public void add (String imageLoc, String text){
        if(getCurrentCategory().equals("")){
            this.AACHome.addItem(imageLoc, text);
            this.allCategories.set(imageLoc, new AACCategory(text));
        }//if()
        else{
        currentCategory.addItem(imageLoc, text);
        }//else()
    }






    
}
