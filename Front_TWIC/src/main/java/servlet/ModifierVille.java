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

import static java.lang.Double.parseDouble;

@WebServlet(name = "ModifierVille", value = "/ModifierVille")
public class ModifierVille extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
            IOException {
        String codeCommune = request.getParameter("codeCommune");
        Ville ville = getVille(codeCommune);

        request.setAttribute("codeCommune", ville.getCodeCommune());
        request.setAttribute("nomCommune", ville.getNom());
        request.setAttribute("codePostal", ville.getCodePostal());
        request.setAttribute("latitude", ville.getLatitude());
        request.setAttribute("longitude", ville.getLongitude());

        this.getServletContext().getRequestDispatcher("/WEB-INF" +
                "/ModifierVille" +
                ".jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
            IOException {
        Ville ville = new Ville();
        ville.setCodeCommune(request.getParameter("codeCommune"));
        ville.setNom(request.getParameter("nomCommune"));
        ville.setCodePostal(request.getParameter("codePostal"));
        ville.setLatitude(parseDouble(request.getParameter("latitude")));
        ville.setLongitude(parseDouble(request.getParameter("longitude")));

        updateVille(ville);

        request.setAttribute("codeCommune", ville.getCodeCommune());
        request.setAttribute("nomCommune", ville.getNom());
        request.setAttribute("codePostal", ville.getCodePostal());
        request.setAttribute("latitude", ville.getLatitude());
        request.setAttribute("longitude", ville.getLongitude());

        this.getServletContext().getRequestDispatcher("/WEB-INF" +
                "/ModifierVille" +
                ".jsp").forward(request, response);
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

    private void updateVille(Ville ville) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put("http://localhost:15900/ville/"+ville.getCodePostal(), ville);
    }
}
