package drawer;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import clock.Clock;

public class Drawer {

	private String m_title;
	private final int m_size;
	
	private JFrame clockWindow;
	private ClockPanel windowPanel;
	
	public Drawer(String title, int size) {
		setTitle(title);
		m_size = size;
		
		initDraw();
	}

	public String getTitle() {
		return m_title;
	}

	public void setTitle(String m_title) {
		this.m_title = m_title;
	}

	public int getSize() {
		return m_size;
	}

	public void initDraw() {
		
		//Clock clock = new Clock(); //on cr�er la clock que l'on va donner au JPanel
		windowPanel =  new ClockPanel(); //on cr�er notre panel o� on va dessiner l'horloge et que l'on va lier a notre clockWindow
		clockWindow = new JFrame(); //on cr�er la fenetre qui va contenir le panel
		
		clockWindow.setTitle(getTitle());//D�finit un titre pour notre fen�tre
		clockWindow.setSize(getSize(), getSize());//D�finit sa taille, elle sera carr�e car deux fois size en W et H
		clockWindow.setLocationRelativeTo(null);//Nous demandons maintenant � notre objet de se positionner au centre
		clockWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Termine le processus lorsqu'on clique sur la croix rouge
		clockWindow.setResizable(false);//L'utilisateur ne peux pas redimensionner la fenetre
		clockWindow.setVisible(true);//Et enfin, la rendre visible
		
		
		clockWindow.setContentPane(windowPanel); //on d�finit notre contenu comme �tant celui du panel
		updateDrawing();//on commence a rafraichir le contenu
		
	}
	
	public void updateDrawing() {
		Timer t = new Timer(); //on cr�er un timer
		t.schedule(new TimerTask() { //toute les secondes
		    @Override
		    public void run() {
				windowPanel.updateHour(); //on demande au panel de refresh l'heure
		    }
		}, 0, 1000);
	}
	
}
