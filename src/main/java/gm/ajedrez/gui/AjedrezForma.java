package gm.ajedrez.gui;

import gm.ajedrez.modelo.Deportista;
import gm.ajedrez.servicio.IDeportistaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class AjedrezForma extends JFrame{
    private JPanel panelPrincipal;
    private JTable deportistaTabla;
    private JTextField nombreTexto;
    private JTextField apellidoTexto;
    private JTextField edadTexto;
    private JButton agregarButton;
    private JButton eliminarButton;
    private JButton limpiarButton;
    IDeportistaServicio deportistaServicio;
    private DefaultTableModel tablaModelDeportista;

    @Autowired
    public AjedrezForma(IDeportistaServicio deportistaServicio){
        this.deportistaServicio = deportistaServicio;
        listarForma();
        agregarButton.addActionListener(e -> agregarDeportista());
    }

    private void listarForma(){
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,700);
        setLocationRelativeTo(null);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.tablaModelDeportista = new DefaultTableModel(0,4){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        String[] cabecero = {"Id", "Nombre", "Apellido", "Edad"};
        this.tablaModelDeportista.setColumnIdentifiers(cabecero);
        this.deportistaTabla = new JTable(tablaModelDeportista);
        this.deportistaTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listarDeportistas();
    }

    private void listarDeportistas(){
        this.tablaModelDeportista.setRowCount(0);
        var deportistas = this.deportistaServicio.listarDeportista();
        deportistas.forEach(deportista -> {
            Object[] renglonDeportista = {
                    deportista.getId(),
                    deportista.getNombre(),
                    deportista.getApellido(),
                    deportista.getEdad()
            };
            this.tablaModelDeportista.addRow(renglonDeportista);
        });
    }

    private void agregarDeportista(){
        if(nombreTexto.getText().equals("")){
            mostrarMensaje("Proporcione un nombre");
            nombreTexto.requestFocusInWindow();
            return;
        }
        if(edadTexto.getText().equals("")){
            mostrarMensaje("Proporcione una edad");
            edadTexto.requestFocusInWindow();
            return;
        }
        var nombre = nombreTexto.getText();
        var apellido = apellidoTexto.getText();
        var edad = Integer.parseInt(edadTexto.getText());
        var deportista = new Deportista();
        deportista.setNombre(nombre);
        deportista.setApellido(apellido);
        deportista.setEdad(edad);
        this.deportistaServicio.guardarDeportista(deportista);
        limpiarFormulario();
        listarDeportistas();
    }
    private void limpiarFormulario(){
        nombreTexto.setText("");
        apellidoTexto.setText("");
        edadTexto.setText("");
    }

    private void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
