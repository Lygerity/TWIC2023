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

@WebServlet(name = "CalculDistance", value = "/CalculDistance")
public class CalculDistance extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("villes", getVilles());
        this.getServletContext().getRequestDispatcher("/WEB-INF" +
                "/CalculDistance" +
                ".jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String villeDepart = request.getParameter("villeDepart");
        String villeArrivee = request.getParameter("villeArrivee");
        String distance = "" + calculDistance(villeDepart, villeArrivee);
        request.setAttribute("distance", distance);
        request.setAttribute("villes", getVilles());
        request.setAttribute("villeDepart", getVille(villeDepart));
        request.setAttribute("villeArrivee", getVille(villeArrivee));

        this.getServletContext().getRequestDispatcher("/WEB-INF" +
                "/CalculDistance" +
                ".jsp").forward(request, response);
    }

    private List<Ville> getVilles() {

        List<Ville> villes;
        RestTemplate restTemplate = new RestTemplate();
        villes = restTemplate.exchange("http://localhost:15900/villes", HttpMethod.GET, null, new ParameterizedTypeReference<List<Ville>>() {
        }).getBody();
        return villes;
    }

    private Ville getVille(String codeCommune) {

        Ville ville;
        RestTemplate restTemplate = new RestTemplate();
        ville = restTemplate.exchange("http://localhost:15900/ville" +
                "?code_commune=" + codeCommune, HttpMethod.GET, null,
                new ParameterizedTypeReference<Ville>() {
        }).getBody();
        return ville;
    }

    private double calculDistance(String villeDepart, String villeArrivee){
        Ville villeD = getVille(villeDepart);
        Ville villeA = getVille(villeArrivee);

        double x =
                (villeA.getLatitude() - villeD.getLatitude()) * Math.cos((villeA.getLongitude() + villeD.getLongitude()) / 2);
        double y = villeA.getLongitude() - villeD.getLongitude();
        double z = Math.sqrt(x * x + y * y);
        return 1.852 * 60 * z;
    }
}
