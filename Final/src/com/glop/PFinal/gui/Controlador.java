package com.glop.PFinal.gui;

import com.glop.PFinal.base.Reserva;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

public class Controlador implements ActionListener, WindowListener {

    private Vista vista;
    private Modelo modelo;

    public Controlador(Vista vista, Modelo modelo) {
        this.modelo = modelo;
        this.vista = vista;

        try {
            modelo.cargarDatosFichero();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        anadirActionListeners(this);
        anadirWindowListeners(this);

        refrescarListaReservas();
    }

    public void anadirActionListeners(ActionListener listener){
        vista.btnAceptar.addActionListener(listener);
        vista.btnCancelar.addActionListener(listener);
        vista.rbtnCongreso.addActionListener(listener);
        vista.rbtnBanquete.addActionListener(listener);
        vista.rbtnJornada.addActionListener(listener);
        vista.checkHabitaciones.addActionListener(listener);
    }

    private void anadirWindowListeners(WindowListener listener){
        vista.frame.addWindowListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String comando = e.getActionCommand();

        switch (comando){
            case "Aceptar":
                modelo.altaReserva(vista.txtNombre.getText(), vista.txtTlfno.getText(), vista.txtDireccion.getText(),
                        vista.dpFecha.getDate(),obtieneRadioBtn(), (Integer) vista.txtNumPersonas.getValue(),
                        String.valueOf(vista.cbTipoCocina.getSelectedItem()), (Integer) vista.spinNumJornadas.getValue(),
                        vista.checkHabitaciones.isSelected(), Integer.parseInt(vista.txtNumHabitaciones.getText()),
                        String.valueOf(vista.cbTipoHabitacion.getSelectedItem()));
                refrescarListaReservas();

                JOptionPane.showMessageDialog(null, "¡Reserva realizada con éxito!\n"+modelo.getListaReservas());
                break;

            case "Cancelar":
                vista.dlm.clear();
                break;
            case "Congreso":
                activaPanel2();
                vista.sm.setValue(1);
                vista.sm.setMaximum(50);
                break;
            case "Banquete":
                desactivaPanel2();
                vista.sm.setMaximum(100);
                break;
            case "Jornada":
                desactivaPanel2();
                vista.sm.setValue(1);
                vista.sm.setMaximum(50);
                break;
            case "Habitaciones":
                activDesactivPanel3();
                break;
        }
    }

    private void activDesactivPanel3() {
        if (vista.checkHabitaciones.isSelected()){
            vista.nHabitaciones.setEnabled(true);
            vista.txtNumHabitaciones.setEnabled(true);
            vista.tipoHabitacion.setEnabled(true);
            vista.cbTipoHabitacion.setEnabled(true);
        }else{
            vista.nHabitaciones.setEnabled(false);
            vista.txtNumHabitaciones.setEnabled(false);
            vista.tipoHabitacion.setEnabled(false);
            vista.cbTipoHabitacion.setEnabled(false);
        }
    }


    private void refrescarListaReservas() {
        vista.dlm.clear();
         for(Reserva reserva : modelo.getListaReservas()){
            vista.dlm.addElement(reserva);
        }
    }

    private String obtieneRadioBtn(){
        String rBtnValor = null;
        if (vista.rbtnJornada.isSelected()){
            rBtnValor= "Jornada";
        }else if (vista.rbtnBanquete.isSelected()){
            rBtnValor= "Banquete";
        }else {
            rBtnValor= "Congreso";
        }
        return rBtnValor;
    }


    private void activaPanel2(){
            if (vista.rbtnCongreso.isSelected()){
                vista.spinNumJornadas.setEnabled(true);
                vista.checkHabitaciones.setEnabled(true);
                vista.nJornadasLbl.setEnabled(true);
                //vista.sm.setValue(50);
                //vista.sm.setMaximum(50);
            }
    }

    private void desactivaPanel2(){
        if (!vista.rbtnCongreso.isSelected()){
            vista.spinNumJornadas.setEnabled(false);
            vista.checkHabitaciones.setEnabled(false);
            vista.nJornadasLbl.setEnabled(false);
            vista.nHabitaciones.setEnabled(false);
            vista.txtNumHabitaciones.setEnabled(false);
            vista.tipoHabitacion.setEnabled(false);
            vista.cbTipoHabitacion.setEnabled(false);
            vista.checkHabitaciones.setSelected(false);
        }
    }
    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            modelo.guardarDatosFichero();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}