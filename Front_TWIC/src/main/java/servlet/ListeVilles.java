package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Ville;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListeVilles", value = "/ListeVilles")
public class ListeVilles extends HttpServlet {

    public ListeVilles() {
        super();
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
            IOException {
        List<Ville> villes = getVilles();
        int nbVillesMaxPage = 50;
        int totalVilles = villes.size();
        int nbPages = totalVilles / nbVillesMaxPage;
        if (totalVilles % nbVillesMaxPage == 0) nbPages++;

        List<Ville> page = villes.subList(0, 49);
        request.setAttribute("villesPage", page);
        request.setAttribute("nbPages", nbPages);
        request.setAttribute("totalVilles", totalVilles);
        request.setAttribute("page", 1);

        this.getServletContext().getRequestDispatcher("/WEB-INF" +
                "/ListeVilles" +
                ".jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
            IOException {
        List<Ville> villes = getVilles();
        int nbVillesMaxPage = 50;
        int totalVilles = villes.size();
        int nbPages = totalVilles / nbVillesMaxPage;
        if (totalVilles % nbVillesMaxPage == 0) nbPages++;

        String pageString = request.getParameter("page");
        int currentPage = (pageString != null) ?
                Integer.parseInt(pageString) : 1;

        int start = (currentPage - 1) * nbVillesMaxPage;
        int end = Math.min(start + nbVillesMaxPage, totalVilles);
        List<Ville> page = villes.subList(start, end);

        request.setAttribute("villesPage", page);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("nbPages", nbPages);
        request.setAttribute("page", currentPage);

        this.getServletContext().getRequestDispatcher("/WEB-INF" +
                "/ListeVilles" +
                ".jsp").forward(request, response);
    }


    private List<Ville> getVilles() {

        List<Ville> villes;
        RestTemplate restTemplate = new RestTemplate();
        villes = restTemplate.exchange("http://localhost:15900/villes", HttpMethod.GET, null, new ParameterizedTypeReference<List<Ville>>() {
        }).getBody();
        return villes;
    }
}