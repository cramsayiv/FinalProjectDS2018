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

  
  static HashMap<String, JButton> memo = new HashMap<String, JButton>();
  static HashMap<String, BufferedImage> memo1 = new HashMap<String, BufferedImage>();
  static HashMap<String, Integer> memo2 = new HashMap<String, Integer>();

  static Scanner scan = new Scanner(System.in);
  
    public static void main(String[] args) throws IOException, InterruptedException{
      
        JFrame f = new JFrame("My Collage");
       JFrame fStore = f;
        JPanel panel = new JPanel(new GridLayout(4, 4, 3, 3));
        f.setLayout(new BorderLayout());
        f.add(panel, BorderLayout.CENTER);
        
//        int scanner1 = scan.nextInt();
//        int scanner2 = scan.nextInt();
        
        
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
        
        
       JPanel buttonPanel = new JPanel();
       buttonPanel.setLayout(new GridLayout(3, 2));
        
       
       
       JButton b1 = new JButton("Red Sort");
       b1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
           
           int count = 0;
           do{
             count = 0;
             for(int n = 0; n < 15; n++)
               if(memo2.get("red"+n) < memo2.get("red"+(n+1))){
               swapAll("image"+n, "image"+(n+1));
               count += 1;
               
//                  f.setVisible(false);
//                  panelDraw();
               
               panelDraw(f, panel);
//                  try{
//                  Thread.sleep(100);
//                  }catch(InterruptedException q){
//                  }
             }
           }while(count != 0);
//           f.setVisible(false);
//           f = new JFrame("My Collage");
         }
         
         
       } );
       
       JButton b2 = new JButton("Green Sort");
       b2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
           int count = 0;
           do{
             count = 0;
             for(int n = 0; n < 15; n++)
               if(memo2.get("green"+n) < memo2.get("green"+(n+1))){
               swapAll("image"+n, "image"+(n+1));
               count += 1; 
               
             }
           }while(count != 0);
//           f.setVisible(false);
//           f = new JFrame("My Collage");
           panelDraw(f, panel);
         }
       } );
           
        JButton b3 = new JButton("Blue Sort");
        b3.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            int count = 0;
      do{
        count = 0;
        for(int n = 0; n < 15; n++)
          if(memo2.get("blue"+n) < memo2.get("blue"+(n+1))){
            swapAll("image"+n, "image"+(n+1));
            count += 1;
          
        }
      }while(count != 0);
      
//           f.setVisible(false);
//           f = new JFrame("My Collage");
           panelDraw(f, panel);
    }
        } );

        
         buttonPanel.add(b1);
         buttonPanel.add(b2);
         buttonPanel.add(b3);
         f.add(buttonPanel, BorderLayout.EAST);
        
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
         redVal = redTotal-(greenTotal+blueTotal)/2;
        blueVal = blueTotal-(redTotal+greenTotal)/2;
        greenVal = greenTotal-(redTotal+blueTotal)/2;
        
        
        String redKey = "red"+m;
        String blueKey = "blue"+m;
        String greenKey = "green"+m;
        
        memo2.put(redKey, redVal);
        memo2.put(blueKey, blueVal);
        memo2.put(greenKey, greenVal);
        
        
        
        System.out.println("red is "+redVal+", blue is "+blueVal+", green is "+greenVal);
     
          
    }
        

//       sortRed();
//       f.setVisible(false);
//       panelDraw();
        
       
       
//        for(int o = 0; o < 16; o++)
//          panel.add(memo.get("image"+o));
//        
//        f.add(panel, BorderLayout.CENTER);
//        
//        panel.updateUI();
        
      
        System.out.println(memo2.get("red0")+", "+memo2.get("red1")+ "," + memo2.get("red15"));
        
        
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
    
    public static void swapHash2(String key1, String key2){
      Integer tempKey = memo2.get(key1);
      memo2.put(key1, memo2.get(key2));
      memo2.put(key2, tempKey);
    }
    
    public static void swapAll(String key, String key1){
      int keyInt = Integer.parseInt(key.substring(key.lastIndexOf('e') + 1));
      int keyInt1 = Integer.parseInt(key1.substring(key1.lastIndexOf('e') + 1));
        swapHash("image"+keyInt, "image"+keyInt1);
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
//          if(memo2.get("red"+n) < memo2.get("red"+(n+1))){
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
//          if(memo2.get("green"+n) < memo2.get("green"+(n+1))){
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
//          if(memo2.get("blue"+n) < memo2.get("blue"+(n+1))){
//            swapAll("image"+n, "image"+(n+1));
//            count += 1;
//          
//        }
//      }while(count != 0);
//    }

        
   
    
    public static void panelDraw(JFrame f, JPanel panel){
       
//        JPanel panel = new JPanel(new GridLayout(4, 4, 3, 3));
//        f.setLayout(new BorderLayout());
//        f.add(panel, BorderLayout.CENTER);
//        
//        
//        JPanel buttonPanel = new JPanel();
//       buttonPanel.setLayout(new GridLayout(3, 2));
//        JButton b1 = new JButton("Red Sort");
//        JButton b2 = new JButton("Blue Sort");
//        JButton b3 = new JButton("Green Sort");
//         buttonPanel.add(b1);
//         buttonPanel.add(b2);
//         buttonPanel.add(b3);
//         f.add(buttonPanel, BorderLayout.EAST);
        
        for(int j = 0; j < 16; j++){
          panel.add(memo.get("image"+j));
        }
          

        f.setSize(800, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        
        
    }
}