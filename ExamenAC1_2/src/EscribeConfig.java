import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class EscribeConfig {

    public static void main(String[] args) {
        String miUsuario = "userIESBelen";
        String miContrasena = "I3SB3l3n";
        String elServidor = "localhost";
        String elPuerto = "3306";
        String laBBDD = "iesbelen";

        Properties configuracion = new Properties();

        configuracion.setProperty("user", miUsuario);
        configuracion.setProperty("password", miContrasena);
        configuracion.setProperty("server", elServidor);
        configuracion.setProperty("port", elPuerto);
        configuracion.setProperty("database", laBBDD);
        try {
            configuracion.store(new FileOutputStream("configuracion.props"), "Fichero de configuracion");
        } catch ( FileNotFoundException fnfe ) {
            fnfe.printStackTrace();
        } catch ( IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
