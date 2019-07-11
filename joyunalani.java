


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.event.KeyEvent;//klavye tuşları yakalaması için
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.Image;



import javax.sound.sampled.*; // Ses efektleri verebilmek için

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.nio.charset.MalformedInputException;

import javax.swing.*;
@SuppressWarnings("serial")
public class joyunalani extends JFrame {

	oyuncizmepanel oyunpanel;
	public static int oyunalanigenislik=800;
	public static int oyunalaniyukseklik=800;
	public static boolean tus=false,tus2=false;
	public static int tuskodu1,tuskodu2;
	public static ArrayList<jmermi> mermiler =new ArrayList<jmermi>() ;


	String atesetme="file:./src/Purr.aiff";




	public static void main(String[] args) throws IOException {
		new joyunalani();

     PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
     System.setOut(out);

	}


	public joyunalani(){

		this.setSize(oyunalanigenislik,oyunalaniyukseklik);
		this.setTitle("Gulsah Yılmaz- Hale Sahin Oyun");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//key listener tuş takip

		addKeyListener(new KeyListener(){



			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==78){
                                        oyunpanel=new oyuncizmepanel();
				}

				if(e.getKeyCode()==87){

					System.out.println("ileri");
					tuskodu1=e.getKeyCode();
					tus2=true;


				}else if (e.getKeyCode()==83){


					System.out.println("geri");


				}else {
                                    tuskodu1=0;
                                    tus2=false;
                                    }
                                
                            switch (e.getKeyCode()) {
                                case 68:
                                    System.out.println("sağa");
                                    tuskodu2=e.getKeyCode();
                                    tus=true;
                                    break;
                                case 65:
                                    System.out.println("sola");
                                    tuskodu2=e.getKeyCode();
                                    tus=true;
                                    break;
                                case KeyEvent.VK_ENTER:
                                    //efekt(atesetme);
                                    mermiler.add(new jmermi (oyuncizmepanel.gemi.getgemiburunx(),oyuncizmepanel.gemi.getgemiburuny(),
                                            oyuncizmepanel.gemi.getYonacisi()));
                                    break;
                                default:
                                    tuskodu2=0;
                                    tus=false;
                                    break;
                            }
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
                                if(e.getKeyCode()==87){
                                    tus2=false;
				}else
				tus=false;

			}



		});

		oyunpanel = new oyuncizmepanel();

		this.add(oyunpanel,BorderLayout.CENTER);
		ScheduledThreadPoolExecutor calistir = new ScheduledThreadPoolExecutor (5);

		calistir.scheduleAtFixedRate(new ekranitemizle(this), 0L,20L,TimeUnit.MILLISECONDS);
		this.setVisible(true);

	}

	public static void efekt (String ses){

		URL seskonum;
		try{
			seskonum = new URL(ses);
			Clip klib = null;
			klib= AudioSystem.getClip();
			AudioInputStream inputstream;
			inputstream = AudioSystem.getAudioInputStream(seskonum);
			klib.open(inputstream);
			klib.loop(0);
			klib.start();
		}
		catch (MalformedInputException e1){
			e1.printStackTrace();


		}
		catch(UnsupportedAudioFileException e1 ) {
			e1.printStackTrace();


		}
		catch(LineUnavailableException e1){
			e1.printStackTrace();

		}catch(IOException e){
			e.printStackTrace();
		}


	}
}


class ekranitemizle implements Runnable{
	joyunalani oyunalani;

	public ekranitemizle(joyunalani oyunalani){

		this.oyunalani = oyunalani;

	}
	public void run() {

		oyunalani.repaint();

	}



}

@SuppressWarnings("serial")
class oyuncizmepanel extends JComponent{
	public static ArrayList<jgoktasi>goktaslari = new ArrayList<jgoktasi>();
	int[] cokgenax = jgoktasi.hbcokgenxa;
	int[] cokgenay = jgoktasi.hbcokgenya;
	static int updater=0;
        static int score=0;
	static juzaygemisi gemi = new juzaygemisi();
        static int level=0;
	Image[] arkaplanimg=null;
	Image[] gemiimg = null;
        
	int genislik =joyunalani.oyunalanigenislik;
	int yukseklik =joyunalani.oyunalaniyukseklik;

	public oyuncizmepanel() {
                oyuncizmepanel.score=0;
                oyuncizmepanel.level=0;
               	oyuncizmepanel.gemi = new juzaygemisi(); 
                updater=0;
                oyuncizmepanel.goktaslari = new ArrayList<jgoktasi>();
		for(int i=0;i<10;i++){

			int rastbasposx =(int)(Math.random() * (joyunalani.oyunalanigenislik -40)+1);
			int rastbasposy =(int)(Math.random() * (joyunalani.oyunalaniyukseklik -40)+1);


			goktaslari.add(new jgoktasi(jgoktasi.getcokxa(rastbasposx),jgoktasi.getcokya(rastbasposy),
					13, rastbasposx,rastbasposy ));

			jgoktasi.goktaslari= goktaslari;


		}

	}
	public void paint(Graphics g) {
		
		Graphics2D grafikayarlari = (Graphics2D) g;
		if(arkaplanimg == null){
			try {
                            this.arkaplanimg= ImageLoad.loadImage("./11.jpg", 600);
			} catch (IOException ex) {

			}
		}

		grafikayarlari.drawImage(arkaplanimg[0],0,0,getWidth(),getHeight(),null);
		AffineTransform id= new AffineTransform();
		AffineTransform id2= new AffineTransform();

		grafikayarlari.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                grafikayarlari.setPaint(Color.cyan);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
                g.drawString("Score: "+Integer.toString(score), joyunalani.oyunalanigenislik-210, 35);
                g.drawString("Current Level: "+Integer.toString(level), joyunalani.oyunalanigenislik-210, 60);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 10)); 
                g.drawString("Press N to Start New Game", joyunalani.oyunalanigenislik-210, 80);
		grafikayarlari.setPaint(Color.WHITE);

                int ax=0;
		for(jgoktasi tas: goktaslari){
			if(tas.ekranda){   
                                ax++;
				tas.hareketet(gemi , joyunalani.mermiler);
				grafikayarlari.draw(tas);

			}
		}
                if(ax==0){
                    	newgoktaslari();
                }
                 if(joyunalani.tus2==true && joyunalani.tuskodu1==87){
			gemi.setHareketacisi(gemi.getYonacisi());
			gemi.xhizarttir(gemi.gemixhareketacisi(gemi.getHareketacisi()) * 10);
			gemi.yhizarttir(gemi.gemiyhareketacisi(gemi.getHareketacisi()) * 10);
		}               
		if(joyunalani.tus==true && joyunalani.tuskodu2==68){

			gemi.yonacisiarttir();
			System.out.println("gemi açısı" + gemi.getYonacisi());

		}else if(joyunalani.tus==true && joyunalani.tuskodu2==65){

			gemi.yonacisiazalt();
			System.out.println("gemi açısı" + gemi.getYonacisi());


		}


		if(gemiimg == null){
			try {
				this.gemiimg= ImageLoad.loadImage("./1.png", 75);
			} catch (IOException ex) {

			}
		}

		grafikayarlari.setTransform(id);
		gemi.hareketEt();
		id.translate(gemi.getxortala(), gemi.getyortala());
		id.rotate(Math.toRadians(gemi.getYonacisi()));
		grafikayarlari.drawImage(gemiimg[0], id, null);
                if(updater>0){
                oyuncizmepanel.score+=1;
                updater=0;
                }
                
                
		for(jmermi mermim : joyunalani.mermiler){
		
			if(mermim.ekranda){	
				mermim.hareketet();
				grafikayarlari.setTransform(id2);
				grafikayarlari.translate(mermim.getxortala(), mermim.getyortala());
                                id.rotate(Math.toRadians(mermim.gethareketacisi()));                            
				grafikayarlari.draw(mermim);

			}
		}


	}
        public void newgoktaslari(){
            oyuncizmepanel.score+=1000;
            oyuncizmepanel.level+=1;
            		for(int i=0;i<level+10;i++){

			int rastbasposx =(int)(Math.random() * (joyunalani.oyunalanigenislik -40)+1);
			int rastbasposy =(int)(Math.random() * (joyunalani.oyunalaniyukseklik -40)+1);


			goktaslari.add(new jgoktasi(jgoktasi.getcokxa(rastbasposx),jgoktasi.getcokya(rastbasposy),
					13, rastbasposx,rastbasposy ));

			jgoktasi.goktaslari= goktaslari;


		}
        }

}



