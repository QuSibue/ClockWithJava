package drawer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import clock.Clock;

public class ClockPanel extends JPanel {

	public static double INVARIABLE_ANGLE_SECMIN = ((Math.PI*2)/60);
	public static double INVARIABLE_ANGLE_HOUR = ((Math.PI*2)/12);
	public static double INVARIABLE_DECALAGE = Math.PI/2;
	
	private int m_centerX;
	private int m_centerY;
	private int m_radiusWhiteCircle; //seul rayon qui nous interesse, celui qu'on utilisera pour dessigner les nombres de l'heure et l'aiguilli des minutes

    private Clock m_clock;
	
	public ClockPanel() {
		super();
		this.m_clock = new Clock();
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		
		m_radiusWhiteCircle = (getWidth()/2)-40; //on prend un rayon qui va donner un cercle qui prend quasiment toute la fenetre
		m_centerX = m_centerY = getWidth()/2; //dans le contexte actuel, la fenetre est carrée, donc qu'on prenne widh ou height c'est pareil (a separe en deux opération si on change)
	    //System.out.println(getHeight());
		
		this.setBackground(new Color(160, 196, 235)); //on défini le fond du JPanel
		g2d.setColor(Color.BLACK);// le prochaine cercle sera noir
		int[] blackCircleSquare = convertCircleCoordsToSquareCoords(m_centerX, m_centerY, m_radiusWhiteCircle + 10); //on convertit les coordonées de notre cercle afin d'obtenir ceux du coin gauche du carré qui va contenri notre cerle
		g2d.fillOval(blackCircleSquare[0],blackCircleSquare[1],blackCircleSquare[2],blackCircleSquare[2]);//On dessine le contour de l'horlorge en prenant le centre de la fenetre et un rayon un peu supérieur au cercle "principal"
	    
		g2d.setColor(Color.WHITE);//le prochain cercle sera blanc
	    int[] whiteCircleSquare = convertCircleCoordsToSquareCoords(m_centerX, m_centerY, m_radiusWhiteCircle);
	    g2d.fillOval(whiteCircleSquare[0],whiteCircleSquare[1],whiteCircleSquare[2],whiteCircleSquare[2]);
	    
	    g2d.setColor(Color.GREEN);//le cercle du centre pour les aiguilles
	    int[] greenCircleSquare = convertCircleCoordsToSquareCoords(m_centerX, m_centerY, 10);
	    g2d.fillOval(greenCircleSquare[0],greenCircleSquare[1],greenCircleSquare[2],greenCircleSquare[2]);
	    
	    g2d.setColor(Color.RED);
	    g2d.setFont(new Font("TimesRoman", Font.PLAIN,20));
	    for(int i=1;i<=12;i++) { //pour chaque heure
	    	int xHour = (int)(m_centerX+(m_radiusWhiteCircle-15)*Math.cos(i*INVARIABLE_ANGLE_HOUR - INVARIABLE_DECALAGE)); //on calcul les coordonées du text en X par rapport a l'angle des heures (invariable)
	    	int yHour = (int)(m_centerY+(m_radiusWhiteCircle-20)*Math.sin(i*INVARIABLE_ANGLE_HOUR - INVARIABLE_DECALAGE)); //on calcul les coordonées du text en Y par rapport a l'angle des heures (invariable)
	    	g2d.drawString(String.valueOf(i),xHour,yHour); //on dessine le chiffre de l'heure correspondante aux coordonnées calculer
	    }
	    
	    //g.setColor(Color.BLACK);
	    g2d.setStroke(new BasicStroke(2f)); //on rend l'aiguillies en gras
	    int xSeconds = (int)(m_centerX+(m_radiusWhiteCircle * 0.75)*Math.cos(m_clock.getSeconds()*INVARIABLE_ANGLE_SECMIN - INVARIABLE_DECALAGE)); //on calcul les coordonnées de l'aiguillis des secondes en X suivant les valeur de l'object clock
	    int ySeconds = (int)(m_centerY+(m_radiusWhiteCircle * 0.75)*Math.sin(m_clock.getSeconds()*INVARIABLE_ANGLE_SECMIN - INVARIABLE_DECALAGE)); //on calcul les coordonnées de l'aiguillis des secondes en Y suivant les valeur de l'object clock
	    g2d.drawLine(m_centerX, m_centerY, xSeconds, ySeconds); //on dessine l'aiguillis des secondes
	    
	    g2d.setColor(Color.BLACK);
	    int xMinutes = (int)(m_centerX+(m_radiusWhiteCircle * 0.75)*Math.cos(m_clock.getMinutes()*INVARIABLE_ANGLE_SECMIN - INVARIABLE_DECALAGE)); //on calcul les coordonnées de l'aiguillis des secondes en X suivant les valeur de l'object clock
	    int yMinutes = (int)(m_centerY+(m_radiusWhiteCircle * 0.75)*Math.sin(m_clock.getMinutes()*INVARIABLE_ANGLE_SECMIN - INVARIABLE_DECALAGE)); //on calcul les coordonnées de l'aiguillis des secondes en Y suivant les valeur de l'object clock
	    g2d.drawLine(m_centerX, m_centerY, xMinutes, yMinutes);//on dessine l'aiguillis des minutes
	    
	    //g.setColor(Color.BLACK);
	    int xHour = (int)(m_centerX+(m_radiusWhiteCircle * 0.5)*Math.cos(m_clock.getHour()*INVARIABLE_ANGLE_HOUR - INVARIABLE_DECALAGE)); //on calcul les coordonnées de l'aiguillis des secondes en X suivant les valeur de l'object clock
	    int yHour = (int)(m_centerY+(m_radiusWhiteCircle * 0.5)*Math.sin(m_clock.getHour()*INVARIABLE_ANGLE_HOUR - INVARIABLE_DECALAGE)); //on calcul les coordonnées de l'aiguillis des secondes en Y suivant les valeur de l'object clock
	    g2d.drawLine(m_centerX, m_centerY, xHour, yHour);//on dessine l'aiguillis des heures
	    
	    
		
	}
	
	public int[] convertCircleCoordsToSquareCoords(int xCircle, int yCircle, int radius) {
		int[] squareCoords = new int[3];
		squareCoords[0] = xCircle - radius; //on calcul le x du carré qui va contenir notre cercle
		squareCoords[1] = yCircle - radius; //on calcul le y du carré qui va contenir notre cercle
		squareCoords[2] = radius * 2; //on calcul la hauter et la largeur du carré qui va contenir notre cercle
		 
		return squareCoords; //on retourne les coordonnées de notre carré pour le cercle courant
	}
	
	public void updateHour() { //fonction qui permet de mettre a jour l'affichage
		System.out.println(m_clock.getHour() + " : " + m_clock.getMinutes() + " : " + m_clock.getSeconds());
		this.m_clock.increment(); //on met a jour notre horloge
		this.repaint(); //on redessine l'horloge
	}
	
	
}
