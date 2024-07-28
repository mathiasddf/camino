package caminito;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;


public class Question {
    private int id;
    private String questionText;
    private String correctAnswer;

    public Question(int id, String questionText, String correctAnswer) {
        this.id = id;
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
    }

    public int getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    private static Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/caminito";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }

    public static Question getQuestionForTile(int tileNumber) {
        int difficultyLevel = getDifficultyLevel(tileNumber);
        String query = "SELECT id, pregunta, respuesta_correcta FROM preguntas WHERE nivel = ? ORDER BY RAND() LIMIT 1";
        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, difficultyLevel);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Question(rs.getInt("id"), rs.getString("pregunta"), rs.getString("respuesta_correcta"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int getDifficultyLevel(int tileNumber) {
        if (tileNumber >= 2 && tileNumber <= 10) {
            return 1; // Fácil
        } else if (tileNumber >= 11 && tileNumber <= 19) {
            return 2; // Medio
        } else if (tileNumber >= 20 && tileNumber <= 27) {
            return 3; // Difícil
        }
        return 0; // Sin dificultad
    }

    public static String[] getOptionsForQuestion(int questionId) {
        String query = "SELECT alternativa FROM alternativas WHERE pregunta_id = ?";
        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, questionId);
            ResultSet rs = pstmt.executeQuery();
            List<String> options = new ArrayList<>();
            while (rs.next()) {
                options.add(rs.getString("alternativa"));
            }
            Collections.shuffle(options); // Desordenar las alternativas
            return options.toArray(new String[0]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new String[0];
    }

    public static boolean showQuestion(Question question) {
        if (question != null) {
            String[] options = getOptionsForQuestion(question.getId());
            String answer = (String) JOptionPane.showInputDialog(
                    null,
                    question.getQuestionText(),
                    "Pregunta",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );
            return answer != null && answer.equals(question.getCorrectAnswer());
        }
        return false;
    }
}
