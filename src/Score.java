package src;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalDate;
import java.util.List;

public class Score {
    private int score;
    private String name;
    private int time;
    private Timestamp playedDate;
    //konstruktor
    public Score(){
        this.score=0;
        this.name = "";
        this.playedDate = new Timestamp(System.currentTimeMillis());
        this.time = 0;
    }
    public Score(String name, int time, int score, Timestamp playedtime){
        this.score=score;
        this.name = name;
        this.playedDate = playedtime;
        this.time = time;
    }

    //menambah score
    public void increaseScore(){
        this.score++;
    }

    //reset Score
    public void resetScore(){
        this.score=0;
    }


    public int getScore(){
        return this.score;
    }

    public void putName(String name){
        this.name = name;
    }

    public void putTime(int time){
        this.time = time;
    }

    public void putDate(Timestamp date){
        this.playedDate = date;
    }
    public String readScore(){
        return  ""+this.name+" - "+this.score+" - "+this.time;
    }

    public String getName() {
        return this.name;
    }

    public int getTime() {
        return this.time;
    }
    // Fungsi buat ambil HighScore
    public List<Score> getHighScore() {
        // SELECT TOP 10 * FROM scores
        String query =  "SELECT *\n" +
                        "FROM scores\n" +
                        "ORDER BY score DESC, totalTime ASC, playedDate ASC\n" +
                        "LIMIT 10;";
        Statement stmt;
        ResultSet rs;
        int id, score;
        int totaltime;
        String name;
        Timestamp playedDate;
        List<Score> list = new ArrayList<Score>();
        try {
            // ReadFile highscore.dat
            Connection conn = conexionToMYSQL();
            //String allLines = "";

            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Score newScore;
                id = rs.getInt("id");
                name = rs.getString("name");
                score = rs.getInt("score");
                totaltime = rs.getInt("totaltime");
                playedDate = rs.getTimestamp("playedDate");
                newScore = new Score(name, totaltime, score, playedDate);
                list.add(newScore);
                // System.out.println("id: "+id+", name: "+name+", score: "+score+", time: "+totaltime+", date: "+playedDate);
                //allLines += "name: "+name+", score: "+score+", time: "+totaltime+", date: "+playedDate;
            }

            // return String yang persis seperti isi dari highscore.dat
            return list;
        }
        // Kalau highscore.dat nya gaada
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return list;
            //throw new RuntimeException(ex);
        }
    }

    //fungsi untuk mengurutkan high score
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

            // Pindahkan isi dari highscore.dat ke sebuah array List
            while (line != null) {
                list.add(Integer.parseInt(line));
                line = reader.readLine();
            }

            // Sort array listnya
            Collections.sort(list);

            // Balikin biar jadi descending
            Collections.reverse(list);

            // Tulis ke highscore.dat, score yang udah diurutin
            writeFile = new FileWriter("highscore.dat");
            writer = new BufferedWriter(writeFile);

            int size = list.size();

            // Nantinya akan hanya 10 skor teratas yang ditulis kembali
            for (int i = 0; i < 10; i++) {
                // Ini untuk mengisi nilai lainnya 0
                if (i > size - 1) {
                    String def = "0";
                    writer.write(def);
                } else { // Ambil satu satu dari list
                    String str = String.valueOf(list.get(i));
                    writer.write(str);
                }
                if (i < 9) {// This prevent creating a blank like at the end of the file**
                    writer.newLine();
                }
            }
        } catch (Exception e) {
            return;
        } finally {
            try {
                // Tutup readernya
                if (reader != null)
                    reader.close();
                // Tutup writer
                if (writer != null)
                    writer.close();
            } // Kalau terjadi exception
            catch (IOException e) {
                return;
            }
        }

    }


    public void saveNewScore() {
        //
        String query ="INSERT INTO scores(name, score, totaltime, playedDate) VALUES ('"+this.name+"','"+this.score+"','"+this.time+"','"+this.playedDate+"')";
        Statement stmt;
        int result;
        try{
            Connection conn = conexionToMYSQL();
            stmt = conn.createStatement();
            result = stmt.executeUpdate(query);
            if(result == 1) System.out.println("Resultados guardados");
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }

    }
    public static Connection conexionToMYSQL(){
        Connection connection;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/snakedb","root","3xi5dzA*");
            System.out.println("Conexion con MySQL!!!");
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
        return connection;
    }

    public static void Desconeccion(Connection db){
        try{
            db.close();
            System.out.println("connection close to mysql");
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
}
