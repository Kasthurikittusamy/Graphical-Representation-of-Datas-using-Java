package graphic_design;

import java.awt.*;
import javax.swing.*;

public class graph_design_3d extends JFrame {
    private ThreeDBarchart chartPanel;

    public graph_design_3d() {
        setTitle("3D Bar Chart");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        chartPanel = new ThreeDBarchart();
        getContentPane().add(chartPanel, BorderLayout.CENTER);

        setSize(800, 600);

        // Center the frame on the screen
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            graph_design_3d frame = new graph_design_3d();
            frame.setVisible(true);
        });
    }
}

class ThreeDBarchart extends JPanel {
    private Color side = new Color(248, 111, 121); // RGB colors
    private Color top = new Color(250, 194, 24);
    private Color front = new Color(222, 179, 173);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Get panel dimensions
        int width = getWidth();
        int height = getHeight();

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // Bar chart settings
        int numBars = 5;
        int barWidth = Math.max(40, width / (numBars * 3)); // Adjust bar width dynamically
        int barSpacing = Math.max(10, barWidth / 2); // Adjust spacing dynamically
        int baseX = (width - (numBars * (barWidth + barSpacing) - barSpacing)) / 2;
        int baseY = height - 100; // Base height

        for (int i = 0; i < numBars; i++) {
            int x = baseX + i * (barWidth + barSpacing);
            int barHeight = (int) (Math.random() * (height / 2)) + 50; // Dynamic height
            int z = 10;

            // Draw front face
            g2d.setColor(front);
            g2d.fillRect(x, baseY - barHeight, barWidth, barHeight);

            // Draw top face
            Polygon topFace = new Polygon();
            topFace.addPoint(x, baseY - barHeight);
            topFace.addPoint(x + barWidth, baseY - barHeight);
            topFace.addPoint(x + barWidth + z, baseY - barHeight - z);
            topFace.addPoint(x + z, baseY - barHeight - z);
            g2d.setColor(top);
            g2d.fillPolygon(topFace);

            // Draw side face
            Polygon sideFace = new Polygon();
            sideFace.addPoint(x + barWidth, baseY - barHeight);
            sideFace.addPoint(x + barWidth + z, baseY - barHeight - z);
            sideFace.addPoint(x + barWidth + z, baseY - z);
            sideFace.addPoint(x + barWidth, baseY);
            g2d.setColor(side);
            g2d.fillPolygon(sideFace);
        }

        // Draw axis labels
        int axisLabelX = baseX - 5;
        int axisLabelY = baseY + 20;
        g2d.setColor(Color.BLACK);

        for (int i = 0; i < numBars; i++) {
            g2d.drawString("Label " + (i + 1), axisLabelX + i * (barWidth + barSpacing), axisLabelY);
        }

        // Draw y-axis ticks
        int numTicks = 5;
        int tickSpacing = Math.max(20, (baseY - 50) / numTicks);
        g2d.drawString("0", axisLabelX - 20, baseY);

        for (int i = 1; i <= numTicks; i++) {
            int tickValue = i * 40;
            g2d.drawString(Integer.toString(tickValue), axisLabelX - 30, baseY - i * tickSpacing);
        }
    }
}
