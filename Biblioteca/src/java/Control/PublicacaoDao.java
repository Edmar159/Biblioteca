package Control;

import Model.Publicacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublicacaoDao {

    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private String sql;

    public PublicacaoDao() throws ClassNotFoundException {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void adiciona(Publicacao publicacao) {
        sql = "insert into publicacao "
                + "(isbn, titulo, autor, editora, ano)"
                + " values (?, ?, ?, ?, ?)";
        try {
            pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, publicacao.getIsbn());
            pstmt.setString(2, publicacao.getTitulo());
            pstmt.setString(3, publicacao.getAutor());
            pstmt.setString(4, publicacao.getEditora());
            pstmt.setInt(5, publicacao.getAno());

            pstmt.execute();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Publicacao> getPublicacao(int isbn){
        sql = "select * from publicacao where isbn = ? ";
        try {
            List<Publicacao> publicacoes = new ArrayList<Publicacao>();
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, isbn);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Publicacao publicacao = new Publicacao();
                publicacao.setIsbn(isbn);
                publicacao.setTitulo(rs.getString("titulo"));
                publicacao.setAutor(rs.getString("autor"));
                publicacao.setEditora(rs.getString("editora"));
                publicacao.setAno(rs.getInt("ano"));
                
                publicacoes.add(publicacao);
            }
            rs.close();
            pstmt.close();
            return publicacoes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Publicacao getPubli(int isbn){
        sql = "select * from publicacao where isbn = ? ";
        try {
            Publicacao publicacao = new Publicacao();
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, isbn);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                publicacao.setIsbn(isbn);
                publicacao.setTitulo(rs.getString("titulo"));
                publicacao.setAutor(rs.getString("autor"));
                publicacao.setEditora(rs.getString("editora"));
                publicacao.setAno(rs.getInt("ano"));
            }
            rs.close();
            pstmt.close();
            return publicacao;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
