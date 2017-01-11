package button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ButtonModel;
import javax.swing.JButton;


public class GradientCircularButton extends JButton {
	
	private Color color;

	private Color pressedColor;


	public GradientCircularButton() {
		super();
		color = Color.red;
		pressedColor = new Color(178,34,34);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusable(false);
		setPreferredSize(new Dimension(68, 65));
		setRolloverEnabled(true);
		setForeground(Color.BLACK);

	}

	public GradientCircularButton(String s) {
		super();
	
		color = Color.red;
		pressedColor = new Color(178,34,34);
		setFont(new Font("Thoma", Font.BOLD, 52));
		
		setText(s);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusable(false);
		setPreferredSize(new Dimension(68, 65));
		setRolloverEnabled(true);
		setForeground(Color.BLACK);

	}

	public GradientCircularButton(String s, Color startColor, Color pressedColor) {
		super();
	
		color = Color.red;
		pressedColor = new Color(178,34,34);
		setText(s);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusable(false);
		setPreferredSize(new Dimension(68, 65));
		setRolloverEnabled(true);
		setForeground(Color.BLACK);

	}


	public void paintComponent(Graphics g) {
		int size = Math.min(getWidth(), getHeight()) - 4;
		int totalSize = size + 4;
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		Color baseColor = color;
		ButtonModel model = getModel();
		if (model.isPressed()) {
			baseColor = pressedColor;
			setForeground(new Color(0,0,0));
		}
		GradientPaint outerBevel = new GradientPaint(0.0F, 0.0F, new Color(
				0.0F, 0.0F, 0.0F, 0.15F), 0.0F, totalSize, new Color(1.0F,
				1.0F, 1.0F, 0.25F));
		graphics.setPaint(outerBevel);
		paintInsetCircle(graphics, totalSize, 0);
		
		float colorComponents[] = baseColor.getRGBComponents(new float[4]);
		Color shadowColor = new Color(colorComponents[0] * 0.52F,
				colorComponents[1] * 0.63F, colorComponents[2] * 0.69F);
		
		graphics.setPaint(shadowColor);
		graphics.setPaint(baseColor);
		paintInsetCircle(graphics, totalSize, 3);
		
		GradientPaint highlight = new GradientPaint(0.0F, 0.0F, new Color(1.0F,
				1.0F, 1.0F, 1.0F), 0.0F, (float) totalSize * .45F, new Color(
				1.0F, 1.0F, 1.0F, 0.0F));
		
		graphics.setPaint(highlight);
		paintInsetCircle(graphics, totalSize, 4);
		paintInsetCircle(graphics, totalSize, 8);
		
		super.paintComponent(graphics);

	}

	private void paintInsetCircle(Graphics2D graphics, int size, int inset) {
		graphics.fillOval(inset, inset, size - inset * 2, size - inset * 2);
	}
}

	