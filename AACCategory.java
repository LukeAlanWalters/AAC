/**
 * Author: Luke Walters
 * The AACCategory class which creates an assosiative array which stores image locations and the text associated with it
 *  * Acknowledgements: Worked with Kevin Johanson on this file
 */

package Structures;

public class AACCategory {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

    String categoryName;

    AssociativeArray<String,String> imageAndText;


  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

    /** 
     * Creates a new empty category with the given name
    * @param name - the name of the category
    */
    public AACCategory (String name){
        this.categoryName = name;
        this.imageAndText = new AssociativeArray<>();
    }//AACCategory()


  // +----------------+----------------------------------------------
  // |    Methods     |
  // +----------------+

    /**
     * Adds the mapping of the imageLoc to the text to the category.
     * @param imageLoc - the location of the image to add
     * @param text - the text that image maps to
    */
    public void addItem (String imageLoc, String text){
        imageAndText.set(imageLoc, text);
    }// addItem()


    /**
     * Returns the name of the category
     * @return - String
     */
    public String getCategory(){
        return this.categoryName;
    }//getCategory()


    /**
     * Returns the text associated with the given image loc in this category
     * @param imageLoc - the location of the image
     * @return - String
     */
    public String getText (String imageLoc){
        try{
            return imageAndText.get(imageLoc);
        }
        catch(KeyNotFoundException e){
            System.err.println("The image does not currently exist within the category");
        }
        return "";
    }//getText()


    /**
     * Determines if the provided images is stored in the category
     * @param imageLoc - the location of the category
     * @return - true if it is in the category, false otherwise
     */
    public boolean hasImage (String imageLoc){
        return (imageAndText.hasKey(imageLoc));
    }//hasImage()


    /**
     * Returns an array of all the images in the category
     * @return - String[]
     */
    public String[] getImages() {
        String[] images = new String[imageAndText.size()];
        images = imageAndText.getKeys();
        return images;
    }//getImage


    /**
     * Returns an array of all the text in the category
     * @return - String[]
     */
    public String[] getAllText() {
        String[] Texts = new String[imageAndText.size()];
        Texts = imageAndText.getValues();
        return Texts;
    }//getImage




   /**
   * Determine how many values are in the associative array.
   */
  public int size() {
    return imageAndText.size();
  } // size()




}
