package Control;

import Model.Exemplar;
import Model.Historico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoricoDao {
    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet rs;
    String sql;

    public HistoricoDao() throws ClassNotFoundException {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void insereHistorico(Historico historico) {
        sql = "insert into historico "
                + "(codigo, isbn, numero, data_emprestimo, data_devolucao)"
                + " values (?, ?, ?, ?, ?)";
        try {
            pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, historico.getCodigo());
            pstmt.setInt(2, historico.getIsbn());
            pstmt.setInt(3, historico.getNumero());
            pstmt.setString(4, historico.getData_emprestimo());
            pstmt.setString(5, historico.getData_devolucao());

            pstmt.execute();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
        public List<Historico> getHistorico(int cod) {
        sql = "select * from historico where codigo = ? ";
        try {
            List<Historico> historicos = new ArrayList<Historico>();
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, cod);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Historico historico = new Historico();
                historico.setIsbn(rs.getInt("isbn"));
                historico.setNumero(rs.getInt("numero"));
                historico.setData_emprestimo(rs.getString("data_emprestimo"));
                historico.setData_devolucao(rs.getString("data_devolucao"));

                historicos.add(historico);
            }
            rs.close();
            pstmt.close();
            return historicos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
