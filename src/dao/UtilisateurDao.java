package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Utilisateur;

public class UtilisateurDao
{
	private static final String	AJOUT_UTILISATEUR_SQL	= "INSERT INTO user VALUES(0, ?, ?, ?, ?)";
	private static final String	SELECT_UTILISATEUR_SQL	= "SELECT * FROM user";

	public static void ajouter(Utilisateur utilisateur) throws DaoException
	{
		Connection connexion = DatabaseManager.getConnection();
		try
		{
			PreparedStatement statement = connexion.prepareStatement(AJOUT_UTILISATEUR_SQL);
			statement.setString(1, utilisateur.getNom());
			statement.setString(2, utilisateur.getPrenom());
			statement.setString(3, utilisateur.getLogin());
			statement.setString(4, utilisateur.getPassword());
			statement.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new DaoException("Utilisateur non ajouté");
		}
	}

	public static List<Utilisateur> getList() throws DaoException
	{
		Connection connexion = DatabaseManager.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		ArrayList<Utilisateur> utilisateurs = null;
		try
		{
			int id;
			utilisateurs = new ArrayList<Utilisateur>();
			String nom, prenom, login, password;
			statement = connexion.createStatement();
			resultSet = statement.executeQuery(SELECT_UTILISATEUR_SQL);
			while (resultSet.next())
			{
				// id = resultSet.getInt(1);
				nom = resultSet.getString(2);
				prenom = resultSet.getString(3);
				login = resultSet.getString(4);
				password = resultSet.getString(5);
				utilisateurs.add(new Utilisateur(nom, prenom, login, password));
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return utilisateurs;
	}
	
	public boolean deleteBook(Utilisateur utilisateur) throws SQLException, DaoException {
        String sql = "DELETE FROM client where login = ?";
         
        Connection connexion = DatabaseManager.getConnection();
         
        PreparedStatement statement = connexion.prepareStatement(sql);
        statement.setString(1, utilisateur.getLogin());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        connexion.close();
        return rowDeleted;     
    }
     
    public boolean updateBook(Utilisateur utilisateur) throws SQLException, DaoException {
        String sql = "UPDATE client SET prenom = ?, nom = ?, login = ?, password = ?";
        sql += " WHERE book_id = ?";
        Connection connexion = DatabaseManager.getConnection();
         
        PreparedStatement statement = connexion.prepareStatement(sql);
        statement.setString(1, utilisateur.getPrenom());
        statement.setString(2, utilisateur.getNom());
        statement.setString(3, utilisateur.getLogin());
        statement.setString(4, utilisateur.getPassword());
        
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        connexion.close();
        return rowUpdated;     
    }
}
