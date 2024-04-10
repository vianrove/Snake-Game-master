package src;

public class Snake {
    // Determina la posición de la serpiente
    int[] snakexLength = new int[750];
    int[] snakeyLength = new int[750];

    // Longitud de la serpiente y si la serpiente se ha movido o no
    int lengthOfSnake;
    int moves;

    // Dirección de la serpiente
    boolean left;
    boolean right;
    boolean up;
    boolean down;

    // Si la serpiente ha muerto o no
    boolean death;

    // Constructor
    public Snake(){
        this.left=false;
        this.right=false;
        this.up=false;
        this.down=false;
        this.death=false;
        this.lengthOfSnake=5;
        this.moves=0;
    }

    // Movimiento hacia la derecha
    public void moveRight(){
        if (this.moves != 0 && !this.death) {
            this.moves++;
            if (!this.left) {
                this.right = true;
            } else {
                this.right = false;
                this.left = true;
            }
            this.up = false;
            this.down = false;
        }
    }

    // Movimiento hacia la izquierda
    public void moveLeft(){
        if (this.moves != 0 && !this.death) {
            this.moves++;
            if (!this.right) {
                this.left = true;
            } else {
                this.left = false;
                this.right = true;
            }
            this.up = false;
            this.down = false;
        }
    }

    // Movimiento hacia arriba
    public void moveUp(){
        if (this.moves != 0 && !this.death) {
            this.moves++;
            if (!this.down) {
                this.up = true;
            } else {
                this.up = false;
                this.down = true;
            }
            this.left = false;
            this.right = false;
        }
    }

    // Movimiento hacia abajo
    public void moveDown(){
        if (this.moves != 0 && !this.death) {
            this.moves++;
            if (!this.up) {
                this.down = true;
            } else {
                this.down = false;
                this.up = true;
            }
            this.left = false;
            this.right = false;
        }
    }

    // Función para matar la serpiente
    public void dead() {
        // Hace que la serpiente no pueda moverse
        this.right = false;
        this.left = false;
        this.up = false;
        this.down = false;
        this.death = true;
    }

    // Movimiento de la serpiente hacia la derecha
    public void movementRight(){
        // Mover la posición de la cabeza al siguiente índice
        for (int i = this.lengthOfSnake - 1; i >= 0; i--) {
            // Mover la posición de snakeyLength
            this.snakeyLength[i + 1] = this.snakeyLength[i];
        }
        for (int i = this.lengthOfSnake - 1; i >= 0; i--) {
            // Mover la posición de snakexLength
            if (i == 0) {
                this.snakexLength[i] = this.snakexLength[i] + 6;
            } else {
                this.snakexLength[i] = this.snakexLength[i - 1];
            }
            // Si pasa el extremo derecho
            if (this.snakexLength[0] > 637) {
                // Mover la cabeza de nuevo al interior del tablero
                this.snakexLength[0] -= 6;
                // Morir
                dead();
            }
        }
    }

    // Movimiento de la serpiente hacia la izquierda
    public void movementLeft(){
        // Mover la posición de la cabeza al siguiente índice
        for (int i = this.lengthOfSnake - 1; i >= 0; i--) {
            // Mover la posición de snakeyLength
            this.snakeyLength[i + 1] = this.snakeyLength[i];
        }
        for (int i = this.lengthOfSnake - 1; i >= 0; i--) {
            // Mover la posición de snakexLength
            if (i == 0) {
                this.snakexLength[i] = this.snakexLength[i] - 6;
            } else {
                this.snakexLength[i] = this.snakexLength[i - 1];
            }
            // Si pasa el extremo izquierdo
            if (this.snakexLength[0] < 25) {
                // Mover la cabeza de nuevo al interior del tablero
                this.snakexLength[0] += 6;
                // Morir
                dead();
            }
        }
    }

    // Movimiento de la serpiente hacia arriba
    public void movementUp(){
        // Mover la posición de la cabeza al siguiente índice
        for (int i = this.lengthOfSnake - 1; i >= 0; i--) {
            // Mover la posición de snakexLength
            this.snakexLength[i + 1] = this.snakexLength[i];
        }
        for (int i = this.lengthOfSnake - 1; i >= 0; i--) {
            // Mover la posición de snakeyLength
            if (i == 0) {
                this.snakeyLength[i] = this.snakeyLength[i] - 6;
            } else {
                this.snakeyLength[i] = this.snakeyLength[i - 1];
            }
            // Si pasa el extremo superior
            if (this.snakeyLength[0] < 73) {
                // Mover la cabeza de nuevo al interior del tablero
                this.snakeyLength[0] += 6;
                // Morir
                dead();
            }
        }
    }

    // Movimiento de la serpiente hacia abajo
    public void movementDown(){
        // Mover la posición de la cabeza al siguiente índice
        for (int i = this.lengthOfSnake - 1; i >= 0; i--) {
            // Mover la posición de snakexLength
            this.snakexLength[i + 1] = this.snakexLength[i];
        }
        for (int i = this.lengthOfSnake - 1; i >= 0; i--) {
            // Mover la posición de snakeyLength
            if (i == 0) {
                this.snakeyLength[i] = this.snakeyLength[i] + 6;
            } else {
                this.snakeyLength[i] = this.snakeyLength[i - 1];
            }
            // Si pasa el extremo inferior
            if (this.snakeyLength[0] > 679) {
                // Mover la cabeza de nuevo al interior del tablero
                this.snakeyLength[0] -= 6;
                // Morir
                dead();
            }
        }
    }
}
