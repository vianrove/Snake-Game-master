package src;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Score {
    private int score;

    // Constructor
    public Score(){
        this.score=0;
    }

    // Aumenta el puntaje
    public void increaseScore(){
        this.score++;
    }

    // Reinicia el puntaje
    public void resetScore(){
        this.score=0;
    }

    // Devuelve el valor del puntaje para mostrarlo en Gameplay
    public int getScore(){
        return this.score;
    }

    // Función para obtener el puntaje más alto
    public String getHighScore() {
        FileReader readFile = null;
        BufferedReader reader = null;
        try {
            // Leer el archivo highscore.dat
            readFile = new FileReader("highscore.dat");
            reader = new BufferedReader(readFile);

            String line = reader.readLine();
            String allLines = line;

            while (line != null) {
                // Leer línea por línea
                line = reader.readLine();
                // Manejo de errores
                if (line == null)
                    break;
                // Concatenar las líneas
                allLines = allLines.concat("\n" + line);
            }

            // Devolver una cadena exactamente igual al contenido de highscore.dat
            return allLines;
        }
        // Si highscore.dat no existe
        catch (Exception e) {
            return "0\n0\n0\n0\n0\n0\n0\n0\n0\n0";
        } finally {
            try {
                // Cerrar el lector
                if (reader != null)
                    reader.close();
            } // Manejo de excepciones
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Función para ordenar el puntaje más alto
    public void sortHighScore() {
        FileReader readFile = null;
        BufferedReader reader = null;
        FileWriter writeFile = null;
        BufferedWriter writer = null;
        List<Integer> list = new ArrayList<Integer>();
        try {
            readFile = new FileReader("highscore.dat");
            reader = new BufferedReader(readFile);

            String line = reader.readLine();

            // Mover el contenido de highscore.dat a una lista
            while (line != null) {
                list.add(Integer.parseInt(line));
                line = reader.readLine();
            }

            // Ordenar la lista
            Collections.sort(list);

            // Invertir para que sea descendente
            Collections.reverse(list);

            // Escribir en highscore.dat, el puntaje ordenado
            writeFile = new FileWriter("highscore.dat");
            writer = new BufferedWriter(writeFile);

            int size = list.size();

            // Solo se escribirán los 10 puntajes más altos
            for (int i = 0; i < 10; i++) {
                // Para llenar con 0 si no hay suficientes puntajes
                if (i > size - 1) {
                    String def = "0";
                    writer.write(def);
                } else { // Tomar uno de la lista
                    String str = String.valueOf(list.get(i));
                    writer.write(str);
                }
                writer.newLine();
            }
        } catch (Exception e) {
            return;
        } finally {
            try {
                // Cerrar el lector
                if (reader != null)
                    reader.close();
                // Cerrar el escritor
                if (writer != null)
                    writer.close();
            } // Manejo de excepciones
            catch (IOException e) {
                return;
            }
        }

    }

    // Función para escribir un nuevo puntaje en el archivo
    public void saveNewScore() {
        String newScore = String.valueOf(this.getScore());
        System.out.println(newScore);

        // Crear un archivo para guardar el puntaje más alto
        File scoreFile = new File("highscore.dat");

        // Si el archivo highscore.dat no existe
        if (!scoreFile.exists()) {
            try {
                // Crear un nuevo archivo
                scoreFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileWriter writeFile = null;
        BufferedWriter writer = null;

        try {
            // Escribir el nuevo puntaje en el archivo
            writeFile = new FileWriter(scoreFile, true);
            writer = new BufferedWriter(writeFile);
            writer.write(newScore);
        } catch (Exception e) {
            return;
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (Exception e) {
                return;
            }
        }
    }
}
