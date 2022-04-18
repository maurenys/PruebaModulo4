package Modulo4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class EjemploServlet
 */
@WebServlet("/EjemploServlet")
public class EjemploServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public EjemploServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        Connection conn;
        String vret = null;

        Title_types title_types;
		
        try {
			// Inicializa Conexion
			conn = DBConnection.initializeDatabase();
			
			// Implementa el Crud
			switch(request.getParameter("table")) {
			case "title_types":				
				switch(request.getParameter("operation")) {
					case "select":
						title_types = DTOTitle_types.select(conn,request.getParameter("title_no"));
						vret = title_types.getTitle_no() + ";" + title_types.getTitle();
						break;
					case "insert":
						if(DTOTitle_types.insert(conn,new Title_types(request.getParameter("title_no"),request.getParameter("title")))) {
							vret = "Exito";
						};
						break;
					case "update":
						if(DTOTitle_types.update(conn,new Title_types(request.getParameter("title_no"),request.getParameter("title")))) {
							vret = "Exito";
						};
						break;
					case "delete":
						if(DTOTitle_types.delete(conn,request.getParameter("title_no"))) {
							vret = "Exito";
						};
						break;					
				};
				break;
			};
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		response.getWriter().append(vret);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
