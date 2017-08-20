package com.cf.sectest;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sap.xs2.security.container.SecurityContext;
import com.sap.xs2.security.container.UserInfoException;

/**
 * Servlet implementation class DisplayServlet
 */
public class DisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DisplayServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("<!DOCTYPE HTML>"
			+ "<html><head>"
			+ "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />"
			+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"
			+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/static.css\">"
			+ "<title>XSA Security Demo Backend</title></head>");
		
		response.getWriter().append("<body><div class=\"content\">");
		
		response.getWriter().append("<h2>Display Role</h2>");
		response.getWriter().append("<table>");
       
		try {
			response.getWriter().append("<tr><td>").append("Identiy Zone").append("</td><td>").append(SecurityContext.getUserInfo().getIdentityZone()).append("</td></tr>");
			response.getWriter().append("<tr><td>").append("EMail").append("</td><td>").append(SecurityContext.getUserInfo().getEmail()).append("</td></tr>");
			response.getWriter().append("<tr><td>").append("UserId").append("</td><td>").append(SecurityContext.getUserInfo().getLogonName()).append("</td></tr>");
		}  catch (UserInfoException e) {
			// TODO Auto-generated catch block
			response.getWriter().append("<tr><td>").append("Error").append("</td><td>").append(e.getLocalizedMessage()).append("</td></tr>");
		}
				
        @SuppressWarnings("unchecked")
		Enumeration<String> headerNames = request.getHeaderNames();

        if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                	String header = headerNames.nextElement();
					if (header.equals("authorization")) {
						response.getWriter().append("<tr><td>JWT</td><td><textarea readonly>")
							.append(request.getHeader(header))
							.append("</textarea></td></tr>");
					}
                }
        }

        response.getWriter().append("</table>");
		response.getWriter().append("</div class=\"content\"></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
