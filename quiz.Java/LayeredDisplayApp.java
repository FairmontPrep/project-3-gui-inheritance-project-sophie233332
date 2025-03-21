import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

class BackgroundLayer extends JPanel {
    protected BufferedImage bgImage;
    protected String description = ".";
    protected final Dimension CANVAS_SIZE = new Dimension(640, 1080);

    public BackgroundLayer() {
        try {
            bgImage = ImageIO.read(new File("bg.png"));
            if (bgImage == null) System.err.println("Background image missing!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, CANVAS_SIZE.width, CANVAS_SIZE.height, null);
        // Removed drawDescription() here
    }

    protected void drawDescription(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        // Optional: background box
        g2d.setColor(new Color(0, 0, 0, 150)); // Semi-transparent black
        g2d.fillRect(0, 1030, CANVAS_SIZE.width, 50);

        // Draw description text
        g2d.setColor(new Color(50, 255, 50));
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString(description, 20, 500);
    }

    @Override
    public Dimension getPreferredSize() {
        return CANVAS_SIZE;
    }
}

class AnswerLayer extends BackgroundLayer {
    private BufferedImage answerImage;

    public AnswerLayer() {
        super();
        try {
            answerImage = ImageIO.read(new File("answer.png"));
            description += " with your Answer";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(answerImage, 0, 0, CANVAS_SIZE.width, CANVAS_SIZE.height, null);
    }
}

class ChapterLayer extends AnswerLayer {
    private BufferedImage chapterImage;

    public ChapterLayer() {
        super();
        try {
            chapterImage = ImageIO.read(new File("chapter.png"));
            description += " 14";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(chapterImage, 0, 0, CANVAS_SIZE.width, CANVAS_SIZE.height, null);
    }
}

class QuestionLayer extends ChapterLayer {
    private BufferedImage questionImage;

    public QuestionLayer() {
        super();
        try {
            questionImage = ImageIO.read(new File("question.png"));
            description += " + the questions,";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(questionImage, 0, 0, CANVAS_SIZE.width, CANVAS_SIZE.height, null);
    }
}

class RandomLayer extends QuestionLayer {
    private BufferedImage randomImage;
    private String layerName;

    public RandomLayer() {
        super();
        try {
            if (Math.random() < 0.5) {
                randomImage = ImageIO.read(new File("66.png"));
                layerName = "Layer 66";
                description += " your final grade is 66";
            } else {
                randomImage = ImageIO.read(new File("99.png"));
                layerName = "Layer 99";
                description += "your final grade is 99";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(randomImage, 0, 0, CANVAS_SIZE.width, CANVAS_SIZE.height, null);

        // Draw description at the END so it stays on top
        drawDescription(g);
    }
}

public class LayeredDisplayApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Layered Image Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(655, 1120); // Slightly larger to fit canvas + padding

        RandomLayer scene = new RandomLayer();
        frame.add(scene);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
