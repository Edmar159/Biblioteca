package Control;

import Model.Associado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AssociadoDao {

    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private String sql;

    public AssociadoDao() throws ClassNotFoundException {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void adiciona(Associado associado) {
        sql = "insert into associado "
                + "(codigo, nome, endereco, email, senha, tipo)"
                + " values (?, ?, ?, ?, ?, ?)";
        try {
            pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, associado.getCodigo());
            pstmt.setString(2, associado.getNome());
            pstmt.setString(3, associado.getEndereco());
            pstmt.setString(4, associado.getEmail());
            pstmt.setString(5, associado.getSenha());
            pstmt.setString(6, associado.getTipo());

            pstmt.execute();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Associado login(int codigo, String senha) {
        sql = "select * from associado"
                + " where codigo = ? and senha = ? ";
        try {
            pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, codigo);
            pstmt.setString(2, senha);
            ResultSet rs = pstmt.executeQuery();
            Associado associado = new Associado();
            while (rs.next()) {
                associado.setCodigo(codigo);
                associado.setNome(rs.getString("nome"));
                associado.setEndereco(rs.getString("endereco"));
                associado.setEmail(rs.getString("email"));
                associado.setSenha(senha);
                associado.setTipo(rs.getString("tipo"));
            }
            rs.close();
            pstmt.close();
            return associado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Associado getAssociado(int codigo){
        sql = "select * from associado where codigo = ? ";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, codigo);
            rs = pstmt.executeQuery();
            Associado associado = new Associado();
            while (rs.next()) {
                associado.setCodigo(codigo);
                associado.setNome(rs.getString("nome"));
                associado.setEndereco(rs.getString("endereco"));
                associado.setEmail(rs.getString("email"));
                associado.setTipo(rs.getString("tipo"));
            }
            rs.close();
            pstmt.close();
            return associado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
