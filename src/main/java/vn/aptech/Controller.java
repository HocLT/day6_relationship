package vn.aptech;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.aptech.dao.BookDao;
import vn.aptech.dao.CategoryDao;
import vn.aptech.entity.Book;
import vn.aptech.entity.Category;

/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BookDao bDao = new BookDao();
	private CategoryDao cDao = new CategoryDao();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String a = request.getParameter("a");
		if (a == null) {
			request.setAttribute("cates", cDao.findAll());
			request.setAttribute("books", bDao.findAll());
			request.getRequestDispatcher("book/index.jsp").forward(request, response);
		} else {
			switch (a) {
			case "Filter": {
				String cateId = request.getParameter("category");
				int nCateId = Integer.parseInt(cateId);
				List<Book> books = new ArrayList<>();
				if (nCateId == 0) {
					books.addAll(bDao.findAll());
				} else {
					Category cate = cDao.findById(nCateId);
					books.addAll(cate.getBooks());
				}

				request.setAttribute("cates", cDao.findAll());
				request.setAttribute("books", books);
				request.getRequestDispatcher("book/index.jsp").forward(request, response);
				break;
			}
			case "DisplayCreate": {
				request.setAttribute("cates", cDao.findAll());
				request.getRequestDispatcher("book/create.jsp").forward(request, response);
				break;
			}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String a = request.getParameter("a");
		if (a != null) {
			switch (a) {
			case "Create": {
				String title = request.getParameter("title");
				String price = request.getParameter("price");
				int nPrice = Integer.parseInt(price);
				String cateId = request.getParameter("category");
				int nCateId = Integer.parseInt(cateId);
				Category cate = cDao.findById(nCateId);
				Book b = new Book();
				b.setTitle(title);
				b.setPrice(nPrice);
				b.setCategory(cate);
				bDao.create(b);
				response.sendRedirect("Controller");
				break;
			}
			}
		}
	}

}
