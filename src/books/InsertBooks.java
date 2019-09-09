package books;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertBooks
 */
@WebServlet("/InsertBooks")
public class InsertBooks extends HttpServlet {
	Connection con;
	PreparedStatement st;
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public InsertBooks() {
		// TODO Auto-generated constructor stub
	}

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
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Loaded");

			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "rishabh", "abcd1234");
			String sql = "insert into books values(?,?,?,?)";
			st = con.prepareStatement(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			st.setInt(1, Integer.parseInt(request.getParameter("bid")));
			st.setString(2, request.getParameter("bname"));
			st.setString(3, request.getParameter("bauth"));
			st.setString(4, request.getParameter("bsub"));
			st.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();

		}

	}

}
