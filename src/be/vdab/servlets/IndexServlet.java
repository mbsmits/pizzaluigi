package be.vdab.servlets;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Adres;
import be.vdab.entities.Begroeting;
import be.vdab.entities.Persoon;

@WebServlet(urlPatterns = "/index.htm", name = "indexservlet")
public class IndexServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/index.jsp";
	private static final String INDEX_REQUESTS = "indexRequests";

	private final AtomicInteger aantalKeerBekeken = new AtomicInteger();

	@Override
	public void init() throws ServletException {
		getServletContext().setAttribute(INDEX_REQUESTS, new AtomicInteger());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		((AtomicInteger) getServletContext().getAttribute(INDEX_REQUESTS)).incrementAndGet();
		request.setAttribute("aantalKeerBekeken", aantalKeerBekeken.incrementAndGet());
		request.setAttribute("begroeting", new Begroeting());
		request.setAttribute("zaakvoerder",
				new Persoon("Luigi", "Peperone", 7, true, new Adres("Grote markt", "3", 9700, "Oudenaarde")));
		request.setAttribute("emailAdresWebMaster", getServletContext().getInitParameter("emailAdresWebMaster"));
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
