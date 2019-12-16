package histogram;

import functions.MathFunctions;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import speedtest.SpeedTest;

@SuppressWarnings("serial")
public class DrawGraph extends JPanel {
   private int MAX_SCORE = 100;
   private static final int PREF_W = 2000;
   private static final int PREF_H = 600;
   private static final int BORDER_GAP = 60;
   private static final Color GRAPH_COLOR = Color.blue;
   private static final Color GRAPH_COLOR_BLACK = Color.black;
   private static final Color GRAPH_COLOR_GREY = new Color(211,211,211);
   private static final Color GRAPH_POINT_COLOR = new Color(150, 50, 50, 180);
   private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
   private static final int GRAPH_POINT_WIDTH = 3;
   private static final int Y_HATCH_CNT = 20;
   private List<Integer> scores;

   public DrawGraph(List<Integer> scores, int MAX_SCORE) {
      this.scores = scores;
      this.MAX_SCORE = MAX_SCORE;
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (scores.size() - 1);
      double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (MAX_SCORE - 1);

      List<Point> graphPoints = new ArrayList<Point>();
      for (int i = 0; i < scores.size(); i++) {
         int x1 = (int) (i * xScale + BORDER_GAP);
         int y1 = (int) ((MAX_SCORE - scores.get(i)) * yScale + BORDER_GAP);
         graphPoints.add(new Point(x1, y1));
      }

      // create x and y axes 
      g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
      g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);

      // create hatch marks for y axis
      for (int i = 0; i < Y_HATCH_CNT; i++) {
         
         int x0 = BORDER_GAP + 1;
         int x1 = PREF_W - BORDER_GAP;
         int y0 = getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP);
         int y1 = y0;
         g2.setColor(GRAPH_COLOR_GREY);
         g2.drawLine(x0, y0, x1, y1);
         g2.setColor(GRAPH_COLOR_BLACK);
         g2.drawString(Float.toString((MAX_SCORE / Y_HATCH_CNT)/100000 * (i + 1)) + "ms", 10, y0);
      }

      // and for x axis
      for (int i = 0; i < scores.size() - 1; i++) {
         int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (scores.size() - 1) + BORDER_GAP;
         int x1 = x0;
         int y0 = getHeight() - BORDER_GAP;
         int y1 = y0 - GRAPH_POINT_WIDTH;
         g2.drawLine(x0, y0, x1, y1);
      }

      Stroke oldStroke = g2.getStroke();
      g2.setColor(GRAPH_COLOR);
      g2.setStroke(GRAPH_STROKE);
      for (int i = 0; i < graphPoints.size() - 1; i++) {
         int x1 = graphPoints.get(i).x;
         int y1 = graphPoints.get(i).y;
         int x2 = graphPoints.get(i + 1).x;
         int y2 = graphPoints.get(i + 1).y;
         g2.drawLine(x1, y1, x2, y2);         
      }

      g2.setStroke(oldStroke);      
      g2.setColor(GRAPH_POINT_COLOR);
      for (int i = 0; i < graphPoints.size(); i++) {
         int x = graphPoints.get(i).x - GRAPH_POINT_WIDTH / 2;
         int y = graphPoints.get(i).y - GRAPH_POINT_WIDTH / 2;;
         int ovalW = GRAPH_POINT_WIDTH;
         int ovalH = GRAPH_POINT_WIDTH;
         g2.fillOval(x, y, ovalW, ovalH);
      }
   }

   @Override
   public Dimension getPreferredSize() {
      return new Dimension(PREF_W, PREF_H);
   }

   public static void createAndShowGui() {
      JPanel container = new JPanel();
      DrawGraph firstPanel = new DrawGraph(SpeedTest.run1, SpeedTest.MAX_SCORE);
      DrawGraph secondPanel = new DrawGraph(SpeedTest.run2, SpeedTest.MAX_SCORE2);
      JFrame frame = new JFrame("Graph");
      JLabel medianspeed = new JLabel("First Method, median execution speed: " + SpeedTest.run1MedianDouble/100000 + "ms.");
      JLabel medianspeed2 = new JLabel("Second Method, median execution speed: " + SpeedTest.run2MedianDouble/100000 + "ms.");
      container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

      container.add(medianspeed);
      if (SpeedTest.run1MedianDouble < SpeedTest.run2MedianDouble)
      {
         JLabel percentage = new JLabel("method1 is " + MathFunctions.getPercentage((int)SpeedTest.run2MedianDouble, (int)SpeedTest.run1MedianDouble, SpeedTest.count) + " percent faster.");
         container.add(percentage);
         container.add(firstPanel);
         container.add(medianspeed2);
      } 
      else
      {
         JLabel percentage = new JLabel("method2 is " + MathFunctions.getPercentage((int)SpeedTest.run1MedianDouble, (int)SpeedTest.run2MedianDouble, SpeedTest.count) + " percent faster.");
         container.add(firstPanel);
         container.add(medianspeed2);
         container.add(percentage);
         
      }
      container.add(secondPanel);
      frame.add(container);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
   }
}