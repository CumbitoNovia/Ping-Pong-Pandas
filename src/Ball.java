import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Ball extends Rectangle {
        Random random; 
       int xVelocity;
       int yVelocity;
       int initialSpeed = 2;
       int colorCounter;

          Ball(int x, int y, int width, int heigth) {
           super(x,y,width,heigth);
           random = new Random();
        int randomXDirection = random.nextInt(2);
           if(randomXDirection == 0)
                   randomXDirection--;
           setXDirection(randomXDirection*initialSpeed);
        int randomYDirection = random.nextInt(2);
           if (randomYDirection == 0)
                   randomYDirection--;
           setYDirection(randomYDirection*initialSpeed);
    }

       public void setXDirection(int randomXDirection) {
           xVelocity = randomXDirection;
    }

       public void setYDirection(int randomYDirection) {
           yVelocity = randomYDirection;
    }

       public void move() {
        x += xVelocity;
        y += yVelocity;
        for (Particle p : particles) {
            p.update();
        }
        generateParticles();
    }
       public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(x, y, height, width);
        
        for (Particle p : particles) {
            p.draw(g);
        }
    }

   ArrayList<Particle> particles = new ArrayList<>();


    private void generateParticles() {
        int numberOfParticles = 5; 
        for (int i = 0; i < numberOfParticles; i++) {
            particles.add(new Particle(x + width / 2, y + height / 2));
        }
    }   
}
class Particle {

    int x, y;
    double xVelocity, yVelocity;
    
    Random random = new Random();

    public Particle(int startX, int startY) {
       x = startX;
       y = startY;

       // Випадкова швидкість, що представляє вогняні іскри
       xVelocity = random.nextDouble() * 2 - 1; 
       yVelocity = random.nextDouble() *-2 - 1; 
   }

   public void update() {
      x += xVelocity;
      y += yVelocity;
   }

   public void draw(Graphics g) { 
      g.setColor(Color.orange); 
      g.fillRect(x, y, 1, 3); 
   } 
}