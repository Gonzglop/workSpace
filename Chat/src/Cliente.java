
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.spec.ECField;
import java.util.ArrayList;

public class Cliente {
    public static void main(String[] args) {
        MarcoCliente mimarco = new MarcoCliente();
        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class MarcoCliente extends JFrame {
    public MarcoCliente() {
        setBounds(600, 300, 280, 350);
        LaminaMarcoCliente milamina = new LaminaMarcoCliente();
        add(milamina);
        setVisible(true);
        addWindowListener(new EnvioOnline());
    }
}
//Envío señal online
class EnvioOnline extends WindowAdapter{
    public void windowOpened(WindowEvent e){
        try {
            Socket misocket = new Socket("192.168.0.101",9999);
            PaqueteEnvio datos = new PaqueteEnvio();
            datos.setMensaje(" online");
            ObjectOutputStream paquete_datos = new ObjectOutputStream(misocket.getOutputStream());
            paquete_datos.writeObject(datos);
            misocket.close();

        }catch (Exception e2){
            System.err.println(e2.getMessage());
        }
    }
}

class LaminaMarcoCliente extends JPanel implements Runnable {
    public LaminaMarcoCliente() {
        String nick_usuario = JOptionPane.showInputDialog("Nick: ");
        JLabel n_nick = new JLabel("Nick: ");
        add(n_nick);
        nick = new JLabel();
        nick.setText(nick_usuario);
        add(nick);
        JLabel texto = new JLabel("Online: ");
        add(texto);
        ip = new JComboBox();
        add(ip);

        campochat = new JTextArea(12,20);
        add(campochat);
        campo1 = new JTextField(20);
        add(campo1);
        miboton = new JButton("Enviar");
        EnviaTexto mievento = new EnviaTexto();
        miboton.addActionListener(mievento);
        add(miboton);
        Thread mihilo = new Thread(this);
        mihilo.start();
    }

    @Override
    public void run() {
        ServerSocket servidor_cliente = null;
        try {
            servidor_cliente = new ServerSocket(9090);
            Socket cliente;
            PaqueteEnvio paqueteRecibido;
            while (true){
                cliente = servidor_cliente.accept();
                ObjectInputStream flujoentrada = new ObjectInputStream(cliente.getInputStream());
                paqueteRecibido = (PaqueteEnvio) flujoentrada.readObject();

                if (!paqueteRecibido.getMensaje().equals(" online")){
                    campochat.append("\n" + paqueteRecibido.getNick() + ": " + paqueteRecibido.getMensaje());
                }else {
                    ArrayList<String> IpsMenu;
                    IpsMenu = paqueteRecibido.getIps();
                    ip.removeAllItems();
                    for (String z : IpsMenu) {
                        ip.addItem(z);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class EnviaTexto implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            campochat.append("\n" + campo1.getText());
            try {
                Socket misocket = new Socket("192.168.0.101",9999);
                PaqueteEnvio datos = new PaqueteEnvio();
                datos.setNick(nick.getText());
                datos.setIp(ip.getSelectedItem().toString());
                datos.setMensaje(campo1.getText());
                campo1.setText("");

                ObjectOutputStream paquete_datos = new ObjectOutputStream(misocket.getOutputStream());
                paquete_datos.writeObject(datos);
                misocket.close();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
    private JTextField campo1;
    private JComboBox ip;
    private JLabel nick;
    private JTextArea campochat;
    private JButton miboton;
}

class PaqueteEnvio implements Serializable {
    private String nick, ip , mensaje;
    private ArrayList <String> Ips;

    public ArrayList<String> getIps() {
        return Ips;
    }

    public void setIps(ArrayList<String> ips) {
        Ips = ips;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}