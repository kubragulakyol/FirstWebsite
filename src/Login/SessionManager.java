package Login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Servlet")
public class SessionManager extends HttpServlet {
    private UserManager _userManager = new UserManager();
    private HttpServletResponse _response;
    private HttpServletRequest _request;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        _request = request;
        _response = response;

        this.InOutSwitch("post");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        _request = request;
        _response = response;

        this.InOutSwitch("get");
    }

    private void LogInGet() {
        try {
            HttpSession session = _request.getSession(true);

            User user = (User) session.getAttribute("currentUser");

            if (user != null && user.isValid()) {
                _request.setAttribute("FirstName", user.getFirstName());
                _request.getRequestDispatcher("member.jsp").forward(_request, _response);
            } else {
                _request.getRequestDispatcher("login.jsp").forward(_request, _response);
            }
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }

    private void LogInPost() {
        try {
            HttpSession session = _request.getSession(true);

            User user = new User();
            user.setUsername(_request.getParameter("username"));
            user.setPassword(_request.getParameter("password"));

            _userManager.login(user);

            if (user.isValid()) {
                session.setAttribute("currentUser", user);
                _response.sendRedirect("/");
            } else {
                session.setAttribute("error", "Sorry, you are not a registered user! Please sign up first");
                _request.getRequestDispatcher("login.jsp").forward(_request, _response);
            }
        } catch (Throwable e) {
            System.out.println(e);
        }
    }

    private void LogOutGet() {
        try {
            HttpSession session = _request.getSession(true);
            if(session.getAttribute("currentUser") != null) {
                session.removeAttribute("currentUser");
                session.setAttribute("info", "You're succesfully logged out! Please sign back in!");
            }
            _response.sendRedirect("/");
        } catch (Throwable e) {
            System.out.println(e);
        }
    }

     private void InOutSwitch(String method) {
        String uri = _request.getRequestURI();
        String shortUri = uri.substring(1, uri.length());

        switch (shortUri) {
            case "login":

                if (method == "get")
                    this.LogInGet();
                else if (method == "post")
                    this.LogInPost();

                break;
            case "logout":

                this.LogOutGet();

                break;
        }
    }
}

