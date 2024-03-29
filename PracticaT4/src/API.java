import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.InputStreamReader;
import java.io.BufferedReader;
public class API {
    private final String API_KEY = "33687832-d32da473205562989dbf542e6";
    private final String API_URL ="https://pixabay.com/api/?key=KEY&q=QUERY&image_type=photo&order=lastest";
    public String searchImage(String busqueda) {
        try {

            URL url = new URL(API_URL.replace("QUERY", busqueda).replace("KEY", API_KEY));

            // Conexión y descarga del JSON
            System.out.println("Obteniendo información de la API...");

            InputStream is = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuilder builder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            String json = builder.toString();
            is.close();

            System.out.println("Información obtenida con éxito.");

            return json;

        } catch (IOException e) {
            System.out.println("Se ha producido un error al buscar la imagen.");
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
