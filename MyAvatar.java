import java.awt.*;
import java.applet.Applet;

public class MyAvatar extends Applet{

	public void paint(Graphics g){
				
		int xPointsHair[]={60,80,120,130,140,150,180,280,420,440,450,460,470,485,430,440,350,400,300,320,220,
				260,160,175,100,105, 80,55};
		int yPointsHair[]={100,60,37, 36, 35, 34, 30, 20, 40, 60, 80,100,145,240,130,200,130,195,140,190,145,
				185,140,190,160,215,190,220};
		int nPointsHair = xPointsHair.length;
		
		int xPointsBack[]={ 80, 70, 90, 80,100,100,140,150,180,200,230,250,280,310,340,370,390,420,430,450,460};
		int yPointsBack[]={200,300,250,350,300,400,350,440,400,460,420,470,400,470,400,450,380,410,340,360,280};
		int nPointsBack = xPointsBack.length;
		
		int xPointsLEar[]={ 60, 30, 20, 30, 55, 80,100, 80};
		int yPointsLEar[]={200,210,230,275,295,320,330,210};
		int nPointsLEar = xPointsLEar.length;

		int xPointsREar[]={480,510,520,510,485,460,440,460};
		int yPointsREar[]={195,205,225,270,290,315,325,205};
		int nPointsREar = xPointsREar.length;

		int xLeftGL[]={75,135,135,75};
		int yLeftGL[]={205,210,250,215};
		int nLeftGL = xLeftGL.length;
		
		int xRightGL[]={440,480,480,440};
		int yRightGL[]={210,200,210,250};
		int nRightGL = xRightGL.length;
		
		int xER[]={465,475,465,455};
		int yER[]={295,305,315,305};
		int nER = xER.length;
		
		Color skin = new Color(255,230,180);
		Color glasses = new Color(85,85,85);
		Color glassesLeg = new Color(185,5,5);
		Color earring = new Color(150,150,150);
				
		//back hair
		g.setColor(Color.BLACK);
		g.fillPolygon(xPointsBack, yPointsBack, nPointsBack);	

		//ears
		g.setColor(skin);
		g.fillPolygon(xPointsLEar,yPointsLEar,nPointsLEar);
		g.setColor(skin);
		g.fillPolygon(xPointsREar,yPointsREar,nPointsREar);		
		
		//face
		g.setColor(Color.BLACK);
		g.fillOval(75, 20, 391, 421);
		g.setColor(skin);
		g.fillOval(76, 20, 390, 420);
					
		//eyes
		g.setColor(Color.WHITE);
		g.fillOval(150, 200, 100, 70);
		g.fillOval(320, 200, 100, 70);
		g.setColor(Color.BLACK);
		g.fillOval(180,210,40,40);
		g.fillOval(350,210,40,40);
		
		//eyebrows
		g.drawArc(150, 180, 100, 20, 0,180);
		g.drawArc(320, 180, 100, 20, 0,180);
		
		//nose
		g.drawArc(270,200,30,120,-90,180);
		
		//mouth
		g.drawArc(205,335,170,40,190,170);
		
		//glasses
		g.setColor(glasses);
		g.fillRect(135, 210, 300, 5);
		g.fillRect(135, 210, 5, 50);
		g.fillRect(265, 210, 5, 50);
		g.fillRect(305, 210, 5, 50);
		g.fillRect(435, 210, 5, 50);
		g.fillRect(265, 210, 40, 20);
		g.fillRect(135, 260, 135, 5);
		g.fillRect(305, 260, 135, 5);
		g.setColor(glassesLeg);
		g.fillPolygon(xLeftGL, yLeftGL, nLeftGL);
		g.fillPolygon(xRightGL, yRightGL, nRightGL);
		
		//front hair
		g.setColor(Color.BLACK);
		g.fillPolygon(xPointsHair, yPointsHair, nPointsHair);
		
		//earring
		g.setColor(earring);
		g.fillPolygon(xER, yER, nER);
		
		//name
		g.setColor(Color.BLACK);
		g.drawString("JoeZ", 460, 460);
	}
}
