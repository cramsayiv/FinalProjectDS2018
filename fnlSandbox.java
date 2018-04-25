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

  static Scanner scan = new Scanner(System.in);
  
    public static void main(String[] args) throws IOException{ //I have no idea what the throws part means but
                                                               //some guy on stackoverflow used it to save files
        final JFrame f = new JFrame("My Collage");

        JPanel panel = new JPanel(new GridLayout(4, 4, 3, 3));
        
 //       int scanner1 = scan.nextInt();
 //       int scanner2 = scan.nextInt();
        
        for (int i = 0; i < 16; i++){
          String key = "image"+i;
          JButton imgButton = new JButton();
          imgButton.setIcon(new ImageIcon("image"+i+".jpg"));
            imgButton.setBorder(BorderFactory.createEmptyBorder());
            imgButton.setFont(imgButton.getFont().deriveFont(20f));
            memo.put(key, imgButton);
            
            File photoFile= new File("image"+i+".jpg");
            BufferedImage photo = ImageIO.read(photoFile);
            memo1.put(key, photo);
        }
        
        
 //       swapHash("image"+scanner1, "image"+scanner2);
        
        for(int j = 0; j < 16; j++){
          panel.add(memo.get("image"+j));
        }

        f.setContentPane(panel);
        f.setSize(800, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        int clrTotal = 0;
        for(int m = 0; m < 16; m++){
          clrTotal = 0;
        for(int k = 1; k < 200; k = k++){
          for(int l = 1; l < 200; l = l++){
            int clr =  memo1.get("image"+m).getRGB(l,k);
            clrTotal += (clr & 0x00f00000) >> 20;
          }
        }
          System.out.println("Red in picture "+m+": "+clrTotal);
    }
    }
    
    public static void swapHash(String key1, String key2){
      JButton tempKey = memo.get(key1);
      memo.put(key1, memo.get(key2));
      memo.put(key2, tempKey);
    }
}