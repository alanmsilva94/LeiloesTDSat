
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 *
 * @author Adm
 */
public class conectaDAO {

    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;

    public String url = "jdbc:mysql://localhost:3306/uc11";
    public String usuario = "root";
    public String senha = "alanmoura1994";

    public boolean connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão realizada com sucesso");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + ex.getMessage());
            return false;
        }
    }

    public void desconectar() {
        try {
            conn.close();
        } catch (SQLException ex) {

        }
    }
}
