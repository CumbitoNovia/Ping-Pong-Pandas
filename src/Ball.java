import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
// The Ball class inherits from Rectangle and represents a moving ball in the game
public class Ball extends Rectangle {
    Random random; // Random number generator for directions  
    int xVelocity; // Ball's velocity in the x direction
    int yVelocity; // Ball's velocity in the y direction
    int initialSpeed = 2; // Initial speed of the ball
    boolean isOnFire; // Flag to check if the ball is 'on fire' (generates more particles)

    // Constructor to initialize the ball with position and size
    public Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        random = new Random();
        
        // Randomly set the x direction (-1 or 1) for the ball's velocity
        int randomXDirection = random.nextInt(2);
        if (randomXDirection == 0) {
            randomXDirection--;
        }
        setXDirection(randomXDirection * initialSpeed);
        
        // Randomly set the y direction (-1 or 1) for the ball's velocity
        int randomYDirection = random.nextInt(2);
        if (randomYDirection == 0) {
            randomYDirection--;
        }
        setYDirection(randomYDirection * initialSpeed);
        
        // Initially, the ball is not 'on fire'
        isOnFire = false;
    }

    // Method to set the x direction velocity
    public void setXDirection(int randomXDirection) {
        xVelocity = randomXDirection;
    }

    // Method to set the y direction velocity
    public void setYDirection(int randomYDirection) {
        yVelocity = randomYDirection;
    }

    // Method to move the ball and handle collision with game boundaries
    public void move() {
/*   // Reverse direction if the ball hits the left or right boundary
        if (x + xVelocity < 0 || x + width + xVelocity > GamePanel.GAME_WIDTH) {
            xVelocity *= -1;
        }
        
        // Reverse direction if the ball hits the top or bottom boundary
        if (y + yVelocity < 0 || y + height + yVelocity > GamePanel.GAME_HEIGHT) {
            yVelocity *= -1;
        }
*/
        // Update the ball's position
        x += xVelocity;
        y += yVelocity;
        
        // Update each particle's position and life
        for (Particle p : particles) {
            p.update();
        }
        
        // Generate new particles based on the ball's state
        generateParticles();
    }

    // Method to draw the ball and its particles
    public void draw(Graphics g) {
        g.setColor(Color.red); // Set the ball color to red
        g.fillOval(x, y, height, width); // Draw the ball as an oval
        
        // Draw each particle
        for (Particle p : particles) {
            p.draw(g);
        }
    }

    // List to store particles generated by the ball
    ArrayList<Particle> particles = new ArrayList<>();

    // Method to generate particles from the ball
    private void generateParticles() {
        int numberOfParticles = 5; // Default number of particles
//        
       // Increase the number of particles if the ball is 'on fire'
        if (isOnFire) {
            numberOfParticles = 1 + random.nextInt(10);
        }
//        
        // Create the specified number of particles and add to the list
        for (int i = 0; i < numberOfParticles; i++) {
            particles.add(new Particle(x + width / 2, y + height / 2, xVelocity, yVelocity));
        }
    }   
}

// The Particle class represents a single particle generated by the ball
class Particle {
    int x, y; // Particle's position
    double vx, vy; // Particle's velocity
    double life; // Particle's life span
    int size; // Particle's size
    Color color; // Particle's color
    
    Random random = new Random(); // Random number generator for particle properties

    // Constructor to initialize the particle with position, velocity, and random properties
    public Particle(int startX, int startY, int ballXVelocity, int ballYVelocity) {
        x = startX;
        y = startY;
        
        // Randomly adjust particle velocity based on ball's velocity
        vx = ballXVelocity + random.nextDouble() * 2 - 1; 
        vy = ballYVelocity + random.nextDouble() * -2 - 1; 
        
        // Initialize particle life
        life = 1.0;
        
        // Randomly set particle size
        size = random.nextInt(3) + 1;
        
        // Randomly set particle color with random transparency
        color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    // Method to update the particle's position and life
    public void update() {
        x += vy;
        y += vx;
        life = 1.01; //0.01 Decrease particle life
    }

    // Method to draw the particle
    public void draw(Graphics g) { 
        float hue = (float) (.0 - life); // Calculate hue based on particle life
        int alpha = (int) (50 + life); // Calculate transparency based on particle life
        g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha)); // Set particle color with transparency
        g.fillOval(x, y, size, size); // Draw particle as an oval
    }
}