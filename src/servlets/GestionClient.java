
package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Client;
import beans.Utilisateur;
import dao.ClientDao;
import dao.DaoException;
import metier.forms.AjoutUtilisateurForm;
import metier.forms.AuthenticationForm;

/**
 * Servlet implementation class AjoutUtilisateur
 */
@WebServlet("/clients/*")
public class GestionClient extends HttpServlet
{
	private static final long				serialVersionUID		= 1L;
	private static final String				VUE_AJOUT_CLIENT	= "/WEB-INF/ajoutClient.jsp";
	private static final String				VUE_LIST_CLIENT	= "/WEB-INF/listeClient.jsp";

	public static final List<Client>	clients			= new ArrayList<Client>();
	private static final String				VUE_UPDATE_UTILISATEUR	= "/WEB-INF/modifierUtilisateur.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String requestedUrl = request.getRequestURI();
		if (requestedUrl.endsWith("/clients/add"))
		{
			getServletContext().getRequestDispatcher(VUE_AJOUT_CLIENT)
					.forward(request, response);
		}
		else if (requestedUrl.endsWith("/clients/list"))
		{
			HttpSession session = request.getSession();
			try {
				request.setAttribute("clients", ClientDao.getList());
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getServletContext().getRequestDispatcher(VUE_LIST_CLIENT)
					.forward(request, response);
			
		}
		else if (requestedUrl.endsWith("/clients/update"))
		{
			try {
				afficherFormModif(request, response);
			}catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (requestedUrl.endsWith("/clients/delete"))
		{
			try {
				deleteUser(request, response);
			}  catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			response.sendRedirect(request.getContextPath());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("utf-8");
		String requestedUrl = request.getRequestURI();

		if (requestedUrl.endsWith("/clients/add"))
		{
			AjoutUtilisateurForm form = new AjoutUtilisateurForm(request);
			Client client = form.getUtilisateur();

			if (form.isValid())
			{
				try {
					ClientDao.ajouter(client);
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			request.setAttribute("client", client);
			request.setAttribute("messageErreurs", form.getMessageErreurs());
			request.setAttribute("statusMessage", form.getStatusMessage());

			getServletContext().getRequestDispatcher(VUE_AJOUT_CLIENT)
					.forward(request, response);
		}
		else if(requestedUrl.endsWith("/clients/update"))
		{
			try {
				updateClient(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void updateClient(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, DaoException {
		//AjoutUtilisateurForm form = new AjoutUtilisateurForm(request);
		int id= Integer.parseInt(request.getParameter("id"));
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String login = request.getParameter("login");
		String password = request.getParameter("password");

		Client client = new Client(id, nom, prenom, login, password);	
		//request.setAttribute("messageErreurs", form.getMessageErreurs());
		//request.setAttribute("statusMessage", form.getStatusMessage());
		/*
		 * int id = Integer.parseInt(request.getParameter("id"));
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		*/
	
		ClientDao.updateClient(client);
		response.sendRedirect("list");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response)
		    throws IOException, DaoException {
		 int id = Integer.parseInt(request.getParameter("id"));
		 try {
			ClientDao.deleteClient(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 response.sendRedirect("list");

	}
	
	private void afficherFormModif(HttpServletRequest request, HttpServletResponse response)
		    throws  ServletException, IOException, DaoException {
		int id = Integer.parseInt(request.getParameter("id"));
		Client client = ClientDao.selectClient(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher(VUE_UPDATE_UTILISATEUR);
		request.setAttribute("client", client);
		/*getServletContext().getRequestDispatcher(VUE_UPDATE_UTILISATEUR)
		.forward(request, response);*/
		dispatcher.forward(request, response);

	}
	public static List<Client> getUtilisateurs()
	{
		return clients;
	}
}

