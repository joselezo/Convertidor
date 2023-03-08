

package com.mycompany.convertidor;

/**
 *
 * @author Ozel
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.border.Border;

public class Convertidor extends JFrame implements ActionListener {
    String[] Opciones = {"CONVERTIDOR DE DIVISAS","CONVERTIDOR DE TEMPERATURA"};
    JComboBox listaOpciones = new JComboBox(Opciones);
   

    public Convertidor() {
        this.getContentPane().setBackground(new Color(222,247,255));
        setSize(500, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setLayout(null);
        //JCOMBOBOX
        JLabel titulo = new JLabel("CONVERTIDOR");
        titulo.setBounds(80, 130, 320, 70);
        titulo.setFont(new Font("Arial", Font.BOLD, 40));
        titulo.setForeground(new Color(83,212,255));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        // Crear un borde 
        Border border = BorderFactory.createLineBorder(Color.WHITE, 3);
        titulo.setBorder(border);
        add(titulo);
      
        listaOpciones.setBackground(new Color(188,238,255));
        listaOpciones.setFont(new Font("Arial", Font.BOLD, 14));
        listaOpciones.setBorder(BorderFactory.createLineBorder(new Color(12,183,242)));
        listaOpciones.setForeground(new Color(97,109,113));
        listaOpciones.setBounds(90, 250, 300, 50);
        listaOpciones.addActionListener(this);
        add(listaOpciones);
        
        //ICONO
        Toolkit mipantalla = Toolkit.getDefaultToolkit();
	Image miIcono = mipantalla.getImage("src/imagenes/convertidor.png");
	setIconImage(miIcono);
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == listaOpciones) {
            if (listaOpciones.getSelectedItem() == "CONVERTIDOR DE DIVISAS") {
               // Crear una nueva ventana y mostrarla
               Divisas divisa = new Divisas();
               divisa.setTitle("Divisas");
               divisa.setVisible(true);
           } else if (listaOpciones.getSelectedItem() == "CONVERTIDOR DE TEMPERATURA") {
               Temperatura temperatura = new Temperatura();
               temperatura.setTitle("Temperatura");
               temperatura.setVisible(true);
        }
        }
    }
    
    public static void main(String[] args) {
        Convertidor convertidor  = new Convertidor();
        convertidor.setVisible(true);
        
        convertidor.setTitle("Convertidor");
        convertidor.setResizable(false);
        
    }

}


