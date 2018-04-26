import java.util.Scanner;
import java.awt.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


public class fnlSandbox{

  
  static HashMap<String, JButton> memo = new HashMap<String, JButton>();
  static HashMap<String, BufferedImage> memo1 = new HashMap<String, BufferedImage>();
  static HashMap<String, Integer> memo2 = new HashMap<String, Integer>();

  static Scanner scan = new Scanner(System.in);
  
    public static void main(String[] args) throws IOException{ //I have no idea what the throws part means but
                                                               //some guy on stackoverflow used it to save files
        final JFrame f = new JFrame("My Collage");

        JPanel panel = new JPanel(new GridLayout(4, 4, 3, 3));
        
//        int scanner1 = scan.nextInt();
//       int scanner2 = scan.nextInt();
        
        for (int i = 0; i < 16; i++){
          String key = "image"+i;
          JButton imgButton = new JButton();
          imgButton.setIcon(new ImageIcon("image"+i+".jpg"));
            imgButton.setBorder(BorderFactory.createEmptyBorder());
            memo.put(key, imgButton);
            
            File photoFile= new File("image"+i+".jpg");
            BufferedImage photo = ImageIO.read(photoFile);
            memo1.put(key, photo);
        }
        
        
//        swapHash("image"+scanner1, "image"+scanner2);
//        swapHash1("image"+scanner1, "image"+scanner2);
//        swapHash2("red"+scanner1, "red"+scanner2);
//        swapHash2("green"+scanner1, "green"+scanner2);
//        swapHash2("blue"+scanner1, "blue"+scanner2);
        
        for(int j = 0; j < 16; j++){
          panel.add(memo.get("image"+j));
        }
        
        int redVal = 0;
        int blueVal = 0;
        int greenVal = 0;
        int redTotal = 0;
        int blueTotal = 0;
        int greenTotal = 0;
        int clrTotal = 0;

        f.setContentPane(panel);
        f.setSize(800, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        
        
        for(int m = 0; m < 16; m++){
          
        redTotal = 0;
        blueTotal = 0;
        greenTotal = 0;
          
        for(int k = 1; k < 200; k = k+(int)(10*Math.random())){
          for(int l = 1; l < 200; l = l+(int)(10*Math.random())){
            int clr =  memo1.get("image"+m).getRGB(l,k);
            redTotal += (clr & 0x00f00000) >> 20;
            greenTotal += (clr & 0x0000f000) >> 12;
            blueTotal += (clr & 0x000000f0) >> 4;
            
            
          }
          
        }    
        redVal = redTotal-greenTotal-blueTotal;
        blueVal = blueTotal-redTotal-greenTotal;
        greenVal = greenTotal-redTotal-blueTotal;
        
        
        String redKey = "red"+m;
        String blueKey = "blue"+m;
        String greenKey = "green"+m;
        
        memo2.put(redKey, redVal);
        memo2.put(blueKey, blueVal);
        memo2.put(greenKey, greenVal);
        
        System.out.println("red is "+redVal+", blue is "+blueVal+", green is "+greenVal);
     
          
    }
    }
    
    public static void swapHash(String key1, String key2){
      JButton tempKey = memo.get(key1);
      memo.put(key1, memo.get(key2));
      memo.put(key2, tempKey);
    }
    
    public static void swapHash1(String key1, String key2){
      BufferedImage tempKey = memo1.get(key1);
      memo1.put(key1, memo1.get(key2));
      memo1.put(key2, tempKey);
    }
    
//    public static void swapHash2(String key1, String key2){
//      int tempKey = memo2.get(key1);
//      memo2.put(key1, memo2.get(key2));
//      memo2.put(key2, tempKey);
//    }
}