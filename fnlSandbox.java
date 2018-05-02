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
  
  
  static HashMap<String, JButton> memoButtons = new HashMap<String, JButton>();
  static HashMap<String, BufferedImage> memoBufferedImages = new HashMap<String, BufferedImage>();
  static HashMap<String, Integer> memoColorValues = new HashMap<String, Integer>();
  
  static Scanner scan = new Scanner(System.in);
  
  public static void main(String[] args) throws IOException, InterruptedException{
    
    JFrame mainJFrame = new JFrame("My Collage");
    JFrame fStore = mainJFrame;
    JPanel panel = new JPanel(new GridLayout(4, 4, 3, 3));
    mainJFrame.setLayout(new BorderLayout());
    mainJFrame.add(panel, BorderLayout.CENTER);
   
    
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
    
    
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(3, 2));
    
    
    
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
    
    
    buttonPanel.add(redButton);
    buttonPanel.add(greenButton);
    buttonPanel.add(blueButton);
    mainJFrame.add(buttonPanel, BorderLayout.EAST);
    
    for(int j = 0; j < 16; j++){
      panel.add(memoButtons.get("image"+j));
    }
    
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
      redVal = redTotal-(greenTotal+blueTotal)/2;
      blueVal = blueTotal-(redTotal+greenTotal)/2;
      greenVal = greenTotal-(redTotal+blueTotal)/2;
      
      
      String redKey = "red"+m;
      String blueKey = "blue"+m;
      String greenKey = "green"+m;
      
      memoColorValues.put(redKey, redVal);
      memoColorValues.put(blueKey, blueVal);
      memoColorValues.put(greenKey, greenVal);
      
      
      
      System.out.println("red is "+redVal+", blue is "+blueVal+", green is "+greenVal);
      
      
    }
    
    
//       sortRed();
//       mainJFramesetVisiblefalse);
//       panelDraw();
    
    
    
//        for(int o = 0; o < 16; o++)
//          panel.add(memoButtons.get("image"+o));
//        
//        mainJFrameadd(panel, BorderLayout.CENTER);
//        
//        panel.updateUI();
    
    
    System.out.println(memoColorValues.get("red0")+", "+memoColorValues.get("red1")+ "," + memoColorValues.get("red15"));
    
    
    }
  
  public static void swapHash(String key1, String key2){
    JButton tempKey = memoButtons.get(key1);
    memoButtons.put(key1, memoButtons.get(key2));
    memoButtons.put(key2, tempKey);
  }
  
  public static void swapHash1(String key1, String key2){
    BufferedImage tempKey = memoBufferedImages.get(key1);
    memoBufferedImages.put(key1, memoBufferedImages.get(key2));
    memoBufferedImages.put(key2, tempKey);
  }
  
  public static void swapHash2(String key1, String key2){
    Integer tempKey = memoColorValues.get(key1);
    memoColorValues.put(key1, memoColorValues.get(key2));
    memoColorValues.put(key2, tempKey);
  }
  
  public static void swapAll(String key, String key1){
    int keyInt = Integer.parseInt(key.substring(key.lastIndexOf('e') + 1));
    int keyInt1 = Integer.parseInt(key1.substring(key1.lastIndexOf('e') + 1));
    // swapHash("image"+keyInt, "image"+keyInt1);
    swapHash1("image"+keyInt, "image"+keyInt1);
    swapHash2("red"+keyInt, "red"+keyInt1);
    swapHash2("green"+keyInt, "green"+keyInt1);
    swapHash2("blue"+keyInt, "blue"+keyInt1);
  }
  
//    public static void sortRed(){
//      int count = 0;
//      do{
//        count = 0;
//        for(int n = 0; n < 15; n++)
//          if(memoColorValues.get("red"+n) < memoColorValues.get("red"+(n+1))){
//            swapAll("image"+n, "image"+(n+1));
//            count += 1; 
//          
//        }
//      }while(count != 0);
//    } 
//    
//         public static void sortGreen(){
//      int count = 0;
//      do{
//        count = 0;
//        for(int n = 0; n < 15; n++)
//          if(memoColorValues.get("green"+n) < memoColorValues.get("green"+(n+1))){
//            swapAll("image"+n, "image"+(n+1));
//            count += 1; 
//        
//    }
//      }while(count != 0);
//    } 
  
//public static void sortBlue(){
//      int count = 0;
//      do{
//        count = 0;
//        for(int n = 0; n < 15; n++)
//          if(memoColorValues.get("blue"+n) < memoColorValues.get("blue"+(n+1))){
//            swapAll("image"+n, "image"+(n+1));
//            count += 1;
//          
//        }
//      }while(count != 0);
//    }
  
  
  
  
  public static void panelDraw(JFrame frame, JPanel panel){
    
//        JPanel panel = new JPanel(new GridLayout(4, 4, 3, 3));
//        mainJFramesetLayout(new BorderLayout());
//        mainJFrameadd(panel, BorderLayout.CENTER);
//        
//        
//        JPanel buttonPanel = new JPanel();
//       buttonPanel.setLayout(new GridLayout(3, 2));
//        JButton redButton = new JButton("Red Sort");
//        JButton greenButton = new JButton("Blue Sort");
//        JButton blueButton = new JButton("Green Sort");
//         buttonPanel.add(redButton);
//         buttonPanel.add(greenButton);
//         buttonPanel.add(blueButton);
//         mainJFrameadd(buttonPanel, BorderLayout.EAST);
    
    for(int j = 0; j < 16; j++){
      //panel.add(memoButtons.get("image"+j));
      String key = "image" + j;
      JButton theButton = memoButtons.get(key);
      BufferedImage theImage = memoBufferedImages.get(key);
      theButton.setIcon(new ImageIcon(theImage));
    }
    
//    
//    mainJFramesetSize(800, 800);
//    mainJFramesetDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    mainJFramesetVisible(true);
    panel.repaint();
    
  }
  }