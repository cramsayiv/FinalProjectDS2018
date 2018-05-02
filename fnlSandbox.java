import java.util.Scanner;
import java.awt.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class fnlSandbox{
  
  // three hashmaps that contain the memoized buttons, buffered images, and ColorValues of each image
  static HashMap<String, JButton> memoButtons = new HashMap<String, JButton>();
  static HashMap<String, BufferedImage> memoBufferedImages = new HashMap<String, BufferedImage>();
  static HashMap<String, Integer> memoColorValues = new HashMap<String, Integer>();
  
  static Scanner scan = new Scanner(System.in);
  
  public static void main(String[] args) throws IOException, InterruptedException{
    
    //creates the JFrame and the panel for the image collage
    JFrame mainJFrame = new JFrame("My Collage");
    JFrame fStore = mainJFrame;
    JPanel panel = new JPanel(new GridLayout(4, 4, 3, 3));
    mainJFrame.setLayout(new BorderLayout());
    mainJFrame.add(panel, BorderLayout.CENTER);
    
    //places the saved images onto the buttons and places the buttons into the button hashmap
    //also places the Bufferedimages into file and memoizes them into the BufferedImage hashmap
    for (int i = 0; i < 16; i++){
      String key = "image"+i;
      JButton imgButton = new JButton();
      imgButton.setIcon(new ImageIcon("image"+i+".jpg"));
      imgButton.setBorder(BorderFactory.createEmptyBorder());
      memoButtons.put(key, imgButton);
   
      
      File photoFile= new File("image"+i+".jpg");
      BufferedImage photo = ImageIO.read(photoFile);
      memoBufferedImages.put(key, photo);
    }
    
    // this is the panel of buttons for the colored sorts
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(3, 2));
    
    
   // button that a method for sorting the images from the most red to the least
   // also creates a new thread, allowing the panel to be redrawn each time the images are swapped
    JButton redButton = new JButton("Red Sort");
    redButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new Thread(new Runnable() {
          public void run(){
            
            int count = 0;
            do{
              count = 0;
              for(int n = 0; n < 15; n++)
                if(memoColorValues.get("red"+n) < memoColorValues.get("red"+(n+1))){
                swapAll("image"+n, "image"+(n+1));
                count += 1;
                panelDraw(mainJFrame, panel);
                try{
                  Thread.sleep(50);
                }catch(InterruptedException q){
                }
              }
            }while(count != 0);
          }
        }).start(); 
      } 
    } );
    
    // button that a method for sorting the images from the most green to the least
   // also creates a new thread, allowing the panel to be redrawn each time the images are swapped
    JButton greenButton = new JButton("Green Sort");
    greenButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new Thread(new Runnable() {
          public void run(){
            int count = 0;
            do{
              count = 0;
              for(int n = 0; n < 15; n++)
                if(memoColorValues.get("green"+n) < memoColorValues.get("green"+(n+1))){
                swapAll("image"+n, "image"+(n+1));
                count += 1;
                panelDraw(mainJFrame, panel);
                try{
                  Thread.sleep(50);
                }catch(InterruptedException q){
                }         
              }
            }while(count != 0);
          }
        }).start();
      }
    } );
   
    // button that a method for sorting the images from the most blue to the least
   // also creates a new thread, allowing the panel to be redrawn each time the images are swapped
    JButton blueButton = new JButton("Blue Sort");
    blueButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new Thread(new Runnable() {
          public void run(){
            int count = 0;
            do{
              count = 0;
              for(int n = 0; n < 15; n++)
                if(memoColorValues.get("blue"+n) < memoColorValues.get("blue"+(n+1))){
                swapAll("image"+n, "image"+(n+1));
                count += 1;
                panelDraw(mainJFrame, panel);
                try{
                  Thread.sleep(50);
                }catch(InterruptedException q){
                }
              }
            }while(count != 0);
          }
        }).start();
      }
    } );
    
    //adds the buttons on the buttonPanel and places the panel onto the JFrame
    buttonPanel.add(redButton);
    buttonPanel.add(greenButton);
    buttonPanel.add(blueButton);
    mainJFrame.add(buttonPanel, BorderLayout.EAST);
    
    //adds the created image buttons onto the main panel through a for loop
    for(int j = 0; j < 16; j++){
      panel.add(memoButtons.get("image"+j));
    }
    
    // declares the values of the needed variables for obtaining the colors 
    // in the images.
    
    int redVal = 0;
    int blueVal = 0;
    int greenVal = 0;
    int redTotal = 0;
    int blueTotal = 0;
    int greenTotal = 0;
    int clrTotal = 0;
    
    mainJFrame.setSize(800, 800);
    mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainJFrame.setVisible(true);
    
    // randomly grabs pixels in the 200x200 image and adds its color value to the 
    // given color total (i.e. the amount of red in the pixel added to the RedTotal
    // These are used to obtain the Color values to compare for the sorting functions
    for(int m = 0; m < 16; m++){
      
      redTotal = 0;
      blueTotal = 0;
      greenTotal = 0;
      
      for(int k = 1; k < 200; k = k+(int)(10*Math.random())){
        for(int l = 1; l < 200; l = l+(int)(10*Math.random())){
          int clr =  memoBufferedImages.get("image"+m).getRGB(l,k);
          redTotal += (clr & 0x00f00000) >> 20;
          greenTotal += (clr & 0x0000f000) >> 12;
          blueTotal += (clr & 0x000000f0) >> 4;
        }
            
      }
      
      // These are the values that will be used for comparing color content of 
      // the images.
      redVal = redTotal-(greenTotal+blueTotal)/2;
      blueVal = blueTotal-(redTotal+greenTotal)/2;
      greenVal = greenTotal-(redTotal+blueTotal)/2;
     
      
     // sets the keys to place these values into the color values hashmap 
      String redKey = "red"+m;
      String blueKey = "blue"+m;
      String greenKey = "green"+m;
      
      memoColorValues.put(redKey, redVal);
      memoColorValues.put(blueKey, blueVal);
      memoColorValues.put(greenKey, greenVal);
      
      
      
      System.out.println("red is "+redVal+", blue is "+blueVal+", green is "+greenVal);
      
    }
    
    
    
//    System.out.println(memoColorValues.get("red0")+", "+memoColorValues.get("red1")+ "," + memoColorValues.get("red15"));
    
    
  }
  
  public static void swapHash(String key1, String key2){
    JButton tempKey = memoButtons.get(key1);
    memoButtons.put(key1, memoButtons.get(key2));
    memoButtons.put(key2, tempKey);
  }
  
  //swap function switching the order of the images in the BufferedImage hashmap
  public static void swapHash1(String key1, String key2){
    BufferedImage tempKey = memoBufferedImages.get(key1);
    memoBufferedImages.put(key1, memoBufferedImages.get(key2));
    memoBufferedImages.put(key2, tempKey);
  }
  
  //swap function switching the order of the values in the Color Value hashmap
  // This runs concurrently alongside the swapHash1 function
  
  public static void swapHash2(String key1, String key2){
    Integer tempKey = memoColorValues.get(key1);
    memoColorValues.put(key1, memoColorValues.get(key2));
    memoColorValues.put(key2, tempKey);
  }
  
  //combines the SwapHash1 and SwapHash2 functions to help with the Color Sort functions above 
  public static void swapAll(String key, String key1){
    int keyInt = Integer.parseInt(key.substring(key.lastIndexOf('e') + 1));
    int keyInt1 = Integer.parseInt(key1.substring(key1.lastIndexOf('e') + 1));
  // swapHash("image"+keyInt, "image"+keyInt1);
    swapHash1("image"+keyInt, "image"+keyInt1);
    swapHash2("red"+keyInt, "red"+keyInt1);
    swapHash2("green"+keyInt, "green"+keyInt1);
    swapHash2("blue"+keyInt, "blue"+keyInt1);
  }
  
  //redraws the panel of images by getting them from the hashmap and replacing
  // them onto the buttons. 
  public static void panelDraw(JFrame frame, JPanel panel){
    
    for(int j = 0; j < 16; j++){
      String key = "image" + j;
      JButton theButton = memoButtons.get(key);
      BufferedImage theImage = memoBufferedImages.get(key);
      theButton.setIcon(new ImageIcon(theImage));
    }
    
    panel.repaint();
    
  }
}