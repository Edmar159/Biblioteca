package Control;

import Model.Exemplar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExemplarDao {

    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private String sql;

    public ExemplarDao() throws ClassNotFoundException {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void adiciona(Exemplar exemplar) {
        sql = "insert into exemplar "
                + "(isbn, numero, preco, situacao)"
                + " values (?, ?, ?, ?)";
        try {
            pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, exemplar.getIsbn());
            pstmt.setInt(2, exemplar.getNumero());
            pstmt.setDouble(3, exemplar.getPreco());
            pstmt.setString(4, exemplar.getSituacao());

            pstmt.execute();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Exemplar> getExemplar(int isbn) {
        sql = "select * from exemplar where isbn = ? ";
        try {
            List<Exemplar> exemplares = new ArrayList<Exemplar>();
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, isbn);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Exemplar exemplar = new Exemplar();
                exemplar.setIsbn(isbn);
                exemplar.setNumero(rs.getInt("numero"));
                exemplar.setPreco(rs.getDouble("preco"));
                exemplar.setSituacao(rs.getString("situacao"));

                exemplares.add(exemplar);
            }
            rs.close();
            pstmt.close();
            return exemplares;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Exemplar> selectByTitulo(String titulo) {
        sql = "select publicacao.isbn, numero, preco, situacao"
                + " from publicacao right join exemplar "
                + " on publicacao.isbn = exemplar.isbn"
                + " where titulo like concat(\"%\", ?, \"%\")"
                + " order by numero";
        try {
            List<Exemplar> exemplares = new ArrayList<Exemplar>();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, titulo);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Exemplar exemplar = new Exemplar();
                exemplar.setIsbn(rs.getInt(1));
                exemplar.setNumero(rs.getInt(2));
                exemplar.setPreco(rs.getDouble(3));
                exemplar.setSituacao(rs.getString(4));

                exemplares.add(exemplar);
            }
            rs.close();
            pstmt.close();
            return exemplares;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Exemplar selectExemplar(int isbn, int numero) {
        sql = "select * from exemplar where isbn = ? and numero = ? ";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, isbn);
            pstmt.setInt(2, numero);
            rs = pstmt.executeQuery();
            Exemplar exemplar = new Exemplar();
            while (rs.next()) {
                exemplar.setIsbn(isbn);
                exemplar.setNumero(numero);
                exemplar.setPreco(rs.getDouble("preco"));
                exemplar.setSituacao(rs.getString("situacao"));
            }
            rs.close();
            pstmt.close();
            return exemplar;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSituacao(Exemplar exemplar, String situacao) {
        sql = "update exemplar set situacao = ?"
                + " where isbn = ? and numero = ? ";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, situacao);
            pstmt.setInt(2, exemplar.getIsbn());
            pstmt.setInt(3, exemplar.getNumero());

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
