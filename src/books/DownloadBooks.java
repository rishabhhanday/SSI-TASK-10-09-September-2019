package books;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadBooks
 */
@WebServlet("/DownloadBooks")
public class DownloadBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownloadBooks() {
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
			File f = new File("F:/JOB/worldpay/Documents/Extra");
			PrintWriter out = response.getWriter();
			out.println("<br>");
			out.println("<h1>Books available to download</h1>");
			out.println("<hr>");
			if (f.isDirectory()) {
				String[] str = f.list();
				for (String s : str) {
					out.println("<a href='GetBooks?fname=" + s + "'>" + s + "</a>");
					out.println("<br>");

				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
