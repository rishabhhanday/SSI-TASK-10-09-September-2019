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
 * Servlet implementation class ViewSubBooks
 */
@WebServlet("/ViewSubBooks")
public class ViewSubBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	PreparedStatement st;

	@Override
	public void destroy() {
		try {
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void init() throws ServletException {
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "rishabh", "abcd1234");
			String sql = "select *  from books where bsub=?";
			st = con.prepareStatement(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewSubBooks() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Loaded");

			st.setString(1, request.getParameter("bsub"));
			ResultSet rs = st.executeQuery();
			PrintWriter out = response.getWriter();

			out.println("<table>");
			out.println("<thead>");
			out.println("<td>");
			out.println("Book ID");
			out.println("</td>");
			out.println("<td>");
			out.println("Book Name");
			out.println("</td>");
			out.println("<td>");
			out.println("Book Author");
			out.println("</td>");
			out.println("<td>");
			out.println("Book Subject");
			out.println("</td>");
			out.println("</thead>");

			while (rs.next()) {
				out.println("<tr>");

				out.println("<td>");
				out.println(rs.getString(1));
				out.println("</td>");
				out.println("<td>");
				out.println(rs.getString(2));
				out.println("</td>");
				out.println("<td>");
				out.println(rs.getString(3));
				out.println("</td>");
				out.println("<td>");
				out.println(rs.getString(4));
				out.println("</td>");
				out.println("</tr>");
			}
			out.println("</table>");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
