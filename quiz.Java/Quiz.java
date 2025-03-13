import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

// Abstract parent class: Base Layer
abstract class LayeredImage extends JPanel {
    private BufferedImage baseImage;

    public LayeredImage() {
        loadImage();
    }

    protected abstract void loadImage();

    protected void setBaseImage(String filePath) {
        try {
            baseImage = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected BufferedImage getBaseImage() {
        return baseImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (baseImage != null) {
            g.drawImage(baseImage, 0, 0, this);
        }
    }
}

// Layer 1
class Layer1 extends LayeredImage {
    private BufferedImage layer1Image;

    public Layer1() {
        super();
    }

    @Override
    protected void loadImage() {
        setBaseImage("quiz+half+8.3-8.4+per8+2025-1.png");
        try {
            layer1Image = ImageIO.read(new File("quiz+half+8.3-8.4+per8+2025-2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (layer1Image != null) {
            g.drawImage(layer1Image, 0, 0, this);
        }
    }
}

// Layer 2
class Layer2 extends Layer1 {
    private BufferedImage layer2Image;

    public Layer2() {
        super();
    }

    @Override
    protected void loadImage() {
        super.loadImage();
        try {
            layer2Image = ImageIO.read(new File("quiz+half+8.3-8.4+per8+2025-3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (layer2Image != null) {
            g.drawImage(layer2Image, 0, 0, this);
        }
    }
}

// Layer 3
class Layer3 extends Layer2 {
    private BufferedImage layer3Image;

    public Layer3() {
        super();
    }

    @Override
    protected void loadImage() {
        super.loadImage();
        try {
            layer3Image = ImageIO.read(new File("quiz+half+8.3-8.4+per8+2025-4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (layer3Image != null) {
            g.drawImage(layer3Image, 0, 0, this);
        }
    }
}

// Random Layer 4
class RandomLayer extends Layer3 {
    private BufferedImage randomImage;
    private static final String[] RANDOM_FILES = {
        "quiz+half+8.3-8.4+per8+2025-5.png",
        "quiz+half+8.3-8.4+per8+2025-6.png"
    };

    public RandomLayer() {
        super();
    }

    @Override
    protected void loadImage() {
        super.loadImage();
        Random rand = new Random();
        String selectedFile = RANDOM_FILES[rand.nextInt(RANDOM_FILES.length)];
        try {
            randomImage = ImageIO.read(new File(selectedFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (randomImage != null) {
            g.drawImage(randomImage, 0, 0, this);
        }
    }
}

// Main class to display GUI
public class Quiz {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Quiz GUI");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 700);

            // Demonstrating polymorphism
            LayeredImage quizImage = new RandomLayer();

            frame.add(quizImage);
            frame.setVisible(true);
        });
    }
}
