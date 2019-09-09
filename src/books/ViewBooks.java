package books;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewBooks
 */
@WebServlet("/ViewBooks")
public class ViewBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewBooks() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Loaded");

			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "rishabh", "abcd1234");
			String sql = "select bsub from books";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			PrintWriter out = response.getWriter();
			out.println("<form action='ViewSubBooks'>");
			out.println("Select Book's subject to search");
			out.println("<select name='bsub'>");
			while(rs.next()){
				out.println("<option>"+rs.getString(1)+"</option>");
			}
			out.println("</select>");
			out.println("<input type='submit' value='View Books'>");
			out.println("</form>");
			}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

}
