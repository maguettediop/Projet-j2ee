package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Client;

public class ClientDao {
	
	private static final String	AJOUT_UTILISATEUR_SQL	= "INSERT INTO client VALUES(0, ?, ?, ?, ?)";
	private static final String	SELECT_CLIENT_SQL	= "SELECT * FROM client";
	private static final String SELECT_CLIENT_BY_ID = "select id, nom, prenom, login, password from client where id =?";
    private static final String DELETE_CLIENT_SQL = "delete from client where id = ?;";
    private static final String UPDATE_CLIENT_SQL = "update client set nom = ?,prenom= ?, login =?, password =? where id = ?;";

	public static void ajouter(Client client) throws DaoException
	{
		Connection connexion = DatabaseManager.getConnection();
		try
		{
			PreparedStatement statement = connexion.prepareStatement(AJOUT_UTILISATEUR_SQL);
			statement.setString(1, client.getPrenom());
			statement.setString(2, client.getNom());
			statement.setString(3, client.getLogin());
			statement.setString(4, client.getPassword());
			statement.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new DaoException("Utilisateur non ajouté");
		}
	}

	public static List<Client> getList() throws DaoException
	{
		Connection connexion = DatabaseManager.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		ArrayList<Client> clients = null;
		try
		{
			int id;
			clients = new ArrayList<Client>();
			String nom, prenom, login, password;
			statement = connexion.createStatement();
			resultSet = statement.executeQuery(SELECT_CLIENT_SQL);
			while (resultSet.next())
			{
				id = resultSet.getInt(1);
				prenom = resultSet.getString(2);
				nom = resultSet.getString(3);
				login = resultSet.getString(4);
				password = resultSet.getString(5);
				clients.add(new Client(id, nom, prenom, login, password));
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clients;
	}
	 // Selectionner client avec id
	public static Client selectClient(int id) throws DaoException
	{
		Connection connexion = DatabaseManager.getConnection();
		Client client = null;
		try
		{
			String nom, prenom, login, password;
			PreparedStatement statement = connexion.prepareStatement(SELECT_CLIENT_BY_ID);
			statement.setInt(1,id);
			ResultSet resultSet= statement.executeQuery();
			while (resultSet.next())
			{
				nom = resultSet.getString("nom");
				prenom = resultSet.getString("prenom");
				login = resultSet.getString("login");
				password = resultSet.getString("password");
				client=new Client(id, nom, prenom, login, password);
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return client;
	}
	
	public static boolean deleteClient(int id) throws SQLException, DaoException {
        
         
        Connection connexion = DatabaseManager.getConnection();
         
        PreparedStatement statement = connexion.prepareStatement(DELETE_CLIENT_SQL);
        statement.setInt(1, id);
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        connexion.close();
        return rowDeleted;     
    }
     
    public static boolean updateClient(Client client) throws SQLException, DaoException {
      
        Connection connexion = DatabaseManager.getConnection();
         
        PreparedStatement statement = connexion.prepareStatement(UPDATE_CLIENT_SQL);
        statement.setString(2, client.getPrenom());
        statement.setString(1, client.getNom());
        statement.setString(3, client.getLogin());
        statement.setString(4, client.getPassword());
        statement.setInt(5, client.getId());
        
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        connexion.close();
        return rowUpdated;     
    }
}
