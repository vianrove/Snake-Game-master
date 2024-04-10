package src;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Font;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    // Instancia el objeto snake
    Snake snake = new Snake();

    // Instancia el objeto apple
    Apple apple = new Apple();

    // Dibuja la cabeza de la serpiente
    private ImageIcon snakeHead;

    private Timer timer;
    private int delay = 250;
    private ImageIcon snakeBody;

    AtomicBoolean speedUp = new AtomicBoolean(true);

    // Coordenadas de la cabeza de la serpiente
    private int snakeHeadXPos = 379;

    // Dibuja la manzana
    private ImageIcon appleImage;

    // Para generar números aleatorios
    private Random random = new Random();

    private int xPos = random.nextInt(100);
    private int yPos = random.nextInt(100);

    // Dibuja el título del juego
    private ImageIcon titleImage;

    // Dibuja el puntaje del juego
    Score score = new Score();

    // Dibuja el puntaje más alto
    private String highScore;

    // Para mostrar el controlador
    private ImageIcon arrowImage;
    private ImageIcon shiftImage;

    public Gameplay() {
        // Cuando el juego comienza
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        // Borde del título
        g.setColor(Color.WHITE);
        g.drawRect(24, 10, 852, 55);

        // Título
        titleImage = new ImageIcon("images/title.png");
        titleImage.paintIcon(this, g, 25, 11);

        // Borde para el juego
        g.setColor(Color.WHITE);
        g.drawRect(24, 71, 620, 614);

        // Fondo del juego
        g.setColor(Color.black);
        g.fillRect(25, 72, 619, 613);

        // Borde para el marcador
        g.setColor(Color.WHITE);
        g.drawRect(653, 71, 223, 614);

        // Fondo del marcador
        g.setColor(Color.black);
        g.fillRect(654, 72, 221, 613);

        // Muestra el puntaje
        g.setColor(Color.white);
        g.setFont(new Font("Helvetica", Font.BOLD, 20));
        g.drawString("PUNTAJE : " + score.getScore(), 720, 110);
        g.drawRect(653, 130, 221, 1);

        // Comprueba si el juego ha comenzado
        if (snake.moves == 0) {
            // Establece las posiciones iniciales de la serpiente
            for (int i = 0; i < 5; i++) {
                snake.snakexLength[i] = snakeHeadXPos;
                snakeHeadXPos -= 6;
                snake.snakeyLength[i] = 355;
            }
            // Muestra el puntaje más alto
            score.sortHighScore();
            highScore = score.getHighScore();
        }

        g.drawString("HIGH SCORES", 705, 180);
        drawString(g, highScore, 705, 200);

        // Muestra el controlador
        g.drawRect(653, 490, 221, 1);
        g.setFont(new Font("Helvetica", Font.BOLD, 25));
        g.drawString("CONTROLES", 690, 530);

        arrowImage = new ImageIcon("images/keyboardArrow.png");
        arrowImage.paintIcon(this, g, 670, 560);
        g.setFont(new Font("Helvetica", Font.PLAIN, 16));
        g.drawString("Movimiento", 770, 590);

        shiftImage = new ImageIcon("images/shift.png");
        shiftImage.paintIcon(this, g, 695, 625);
        g.drawString("Acelerar", 770, 640);

        // Instancia la imagen de la cabeza de la serpiente
        snakeHead = new ImageIcon("images/snakeHead4.png");
        snakeHead.paintIcon(this, g, snake.snakexLength[0], snake.snakeyLength[0]);

        for (int i = 0; i < snake.lengthOfSnake; i++) {
            if (i == 0 && (snake.right || snake.left || snake.up || snake.down)) {
                snakeHead = new ImageIcon("images/snakeHead4.png");
                snakeHead.paintIcon(this, g, snake.snakexLength[i], snake.snakeyLength[i]);
            }
            if (i != 0) {
                snakeBody = new ImageIcon("images/snakeimage4.png");
                snakeBody.paintIcon(this, g, snake.snakexLength[i], snake.snakeyLength[i]);
            }
        }

        appleImage = new ImageIcon("images/apple4.png");

        // Si la serpiente come la manzana
        if ((apple.applexPos[xPos]) == snake.snakexLength[0] && (apple.appleyPos[yPos] == snake.snakeyLength[0])) {
            snake.lengthOfSnake++;
            score.increaseScore();
            xPos = random.nextInt(100);
            yPos = random.nextInt(100);

            // Acelera el movimiento de la serpiente cada vez que el puntaje alcanza un múltiplo de 10
            if (score.getScore() % 5 == 0 && score.getScore()!= 0){
                if(delay > 100){
                    delay = delay - 100;
                }
                else if (delay == 100){
                    delay = delay - 50;
                }
                else if (delay <= 50 && delay > 20){
                    delay = delay - 10;
                }
                else {
                    delay = 20;
                }
                timer.setDelay(delay);
            }
        }

        // La manzana no se muestra antes de que el usuario presione la barra espaciadora
        if (snake.moves != 0) {
            appleImage.paintIcon(this, g, apple.applexPos[xPos], apple.appleyPos[yPos]);
        }

        // Muestra el texto "Presiona barra espaciadora para comenzar el juego"
        if (snake.moves == 0) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Courier New", Font.BOLD, 24));
            g.drawString("Presiona barra espaciadora para jugar", 70, 300);
        }

        // Verifica si la cabeza choca con el cuerpo
        for (int i = 1; i < snake.lengthOfSnake; i++) {
            // Si hay una colisión
            if (snake.snakexLength[i] == snake.snakexLength[0] && snake.snakeyLength[i] == snake.snakeyLength[0]) {
                // Llama a la función dead
                snake.dead();
            }
        }

        // Verifica si el jugador ha muerto
        if (snake.death) {
            // Guarda el puntaje en el archivo highscore.dat
            InputDataPanel frame = new InputDataPanel(score);
            score.saveNewScore();

            // Muestra el texto "¡Juego terminado!"
            g.setColor(Color.RED);
            g.setFont(new Font("Courier New", Font.BOLD, 50));
            g.drawString("¡Juego terminado!", 85, 340);

            // Muestra el puntaje
            g.setColor(Color.GREEN);
            g.setFont(new Font("Courier New", Font.BOLD, 18));
            g.drawString("Tu puntaje : " + score.getScore(), 250, 370);

            // Muestra el texto "Presiona barra espaciadora para reiniciar"
            g.setColor(Color.WHITE);
            g.setFont(new Font("Courier New", Font.BOLD, 20));
            g.drawString("Presiona barra espaciadora para reiniciar", 82, 400);

            // Volvemos a la posición inicial
            snakeHeadXPos = 379;

            // Retornamos a la velocidad inicial
            delay = 250;
            timer.setDelay(delay);
        }
        g.dispose();
    }

    // Método para mostrar una cadena con \n en ella
    public void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        timer.start();

        // Para el movimiento de la serpiente
        // Mueve la serpiente hacia la derecha
        if (snake.right) {
            // Llama al método en la clase Snake para mover la serpiente hacia la derecha
            snake.movementRight();
            // Vuelve a llamar al método paint automáticamente
            repaint();
        }
        // Mueve la serpiente hacia la izquierda
        if (snake.left) {
            // Llama al método en la clase Snake para mover la serpiente hacia la izquierda
            snake.movementLeft();
            // Vuelve a llamar al método paint automáticamente
            repaint();
        }
        // Mueve la serpiente hacia arriba
        if (snake.up) {
            // Llama al método en la clase Snake para mover la serpiente hacia arriba
            snake.movementUp();
            // Vuelve a llamar al método paint automáticamente
            repaint();
        }
        // Mueve la serpiente hacia abajo
        if (snake.down) {
            // Llama al método en la clase Snake para mover la serpiente hacia abajo
            snake.movementDown();
            // Vuelve a llamar al método paint automáticamente
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Condición para la pulsación de teclas
        switch (e.getKeyCode()) {
            // Si el usuario presiona Shift
            case KeyEvent.VK_SHIFT:
                if (speedUp.compareAndSet(true, false)) {
                    if (delay > 100) {
                        timer.setDelay(delay/10);
                    }
                    else {
                        timer.setDelay(10);
                    }
                }
                break;
            // Si el usuario presiona espacio
            case KeyEvent.VK_SPACE:
                // Para iniciar el juego
                if (snake.moves == 0) {
                    snake.moves++;
                    snake.right = true;
                }
                // Para reiniciar el juego después de morir
                if (snake.death) {
                    snake.moves = 0;
                    snake.lengthOfSnake = 5;
                    score.resetScore();
                    repaint();
                    snake.death = false;
                }
                break;
            // Si el usuario presiona flecha derecha
            case KeyEvent.VK_RIGHT:
                // Llama al método en la clase Snake para mover hacia la derecha
                snake.moveRight();
                break;
            // Si el usuario presiona flecha izquierda
            case KeyEvent.VK_LEFT:
                // Llama al método en la clase Snake para mover hacia la izquierda
                snake.moveLeft();
                break;
            // Si el usuario presiona flecha arriba
            case KeyEvent.VK_UP:
                // Llama al método en la clase Snake para mover hacia arriba
                snake.moveUp();
                break;
            // Si el usuario presiona flecha abajo
            case KeyEvent.VK_DOWN:
                // Llama al método en la clase Snake para mover hacia abajo
                snake.moveDown();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Si el usuario suelta Shift
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            speedUp.set(true);
            timer.setDelay(delay);
        }
    }

}
