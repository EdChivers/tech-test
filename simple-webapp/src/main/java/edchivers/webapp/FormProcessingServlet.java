package edchivers.webapp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edchivers.webapp.validators.StringArrayDataValidator;

import java.io.IOException;

import javax.servlet.ServletException;

public class FormProcessingServlet extends HttpServlet {
	
	private StringArrayDataValidator dataValidator = new StringArrayDataValidator();
	
	public static final String FIRST_NAME_PARAM = "people[][firstname]";
	public static final String LAST_NAME_PARAM = "people[][surname]";
	
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
        {
    		if (validateFormData(req))
    		{
    			//TODO write data
    			resp.getWriter().print("Thank you for your data");
    			resp.getWriter().close();
    		}
    		else
    		{
    			resp.getWriter().print("Data was not valid");
    			resp.getWriter().close();
    		}
        }

	private boolean validateFormData(HttpServletRequest req) {

		String[] firstNames = req.getParameterValues(FIRST_NAME_PARAM);
		String[] lastNames = req.getParameterValues(LAST_NAME_PARAM);
		
		return dataValidator.validate(firstNames, lastNames);

	}
}
