import java.awt.image.BufferedImage;

import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Main extends JComponent implements ActionListener{

	public static void main(String[] args) 
	{
		new Main();
		// TODO Auto-generated method stub

	}
	public static final int WIDTH = 800;
	public static final int HEIGHT= 600;
	public static final int ITERATION =100;
	public static final float SCALE =250;
	
	private float hueoffset=0;
	private BufferedImage buffer;
	private Timer timer;
	
	public Main()
	{
		buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		timer = new Timer(100,this);
		renderMandelbrotSet();
		JFrame frame= new JFrame("Mandelbrot Set");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
		
		
		
	}
	public void addNotify()
	{
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		timer.start();
		
	}
	public void renderMandelbrotSet()
	{
		for (int x =0; x< WIDTH;x++)
			for (int y=0;y<HEIGHT;y++)
			{
				int color =calculatePoint((x-WIDTH/2f)/SCALE, (y-HEIGHT/2f)/SCALE);
				buffer.setRGB(x, y, color);
			}
		
	}
	public int calculatePoint(float x, float y) {
		float cx=x;
		float cy=y;
		int i=0;
		for(;i<ITERATION;i++)
		{
			float nx=x*x-y*y+cx;
			float ny=2*x*y+cy;
			x=nx;
			y=ny;
			
			if(x*x+y*y>4) break;
			
		}
		if( i== ITERATION) return 0x00000000;
		return Color.HSBtoRGB(((float)i/ITERATION+hueoffset)%1, 0.5f, 1);
		// TODO Auto-generated method stub
		
	}
	public void paint(Graphics g)
	{
		g.drawImage(buffer, 0, 0, null);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Tick");
		hueoffset+=0.01f;
		renderMandelbrotSet();
		repaint();
	}

}
