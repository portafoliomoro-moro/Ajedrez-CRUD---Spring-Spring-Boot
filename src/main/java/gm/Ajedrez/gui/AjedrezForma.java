package gm.Ajedrez.gui;

import gm.Ajedrez.servicio.IDeportistaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class AjedrezForma extends JFrame{
    private JPanel panelPrincipal;
    IDeportistaServicio deportistaServicio;

    @Autowired
    public AjedrezForma(IDeportistaServicio deportistaServicio){
        this.deportistaServicio = deportistaServicio;
        listarForma();
    }

    private void listarForma(){
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,700);
        setLocationRelativeTo(null);
    }
}
