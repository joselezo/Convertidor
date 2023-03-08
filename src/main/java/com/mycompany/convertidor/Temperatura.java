
package com.mycompany.convertidor;

/**
 *
 * @author Ozel
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

class Temperatura extends JFrame implements ActionListener {
    JTextField introduceCantidad = new JTextField();
    JTextField muestraCantidad = new JTextField();
    JButton celsiusFahrenheit = new JButton("Celsius a Fahrenheit");
    JButton fahrenheitCelsius = new JButton("Fahrenheit a Celsius");


    public Temperatura() {
    setSize(500, 700);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    iniciarComponentes();
    setVisible(true);
    }
    
    private void iniciarComponentes(){
    JPanel panel = new JPanel();
    panel.setBackground(new Color(222,247,255));
    this.getContentPane().add(panel);
    panel.setLayout(null);
    
    
    introduceCantidad.setBounds(130,220,200,30);
    introduceCantidad.setBackground(new Color(222,247,255));
    introduceCantidad.setFont(new Font("Arial", Font.BOLD, 20));
    introduceCantidad.setBorder(BorderFactory.createLineBorder(new Color(12,183,242)));
    introduceCantidad.setForeground(new Color(97,109,113));
    panel.add(introduceCantidad);
    
    muestraCantidad.setBounds(130,400,200,50);
    muestraCantidad.setFont(new Font("Arial", Font.BOLD, 23));
    muestraCantidad.setEditable(false); 
    muestraCantidad.setBackground(new Color(255,255,255));
    muestraCantidad.setFont(new Font("Arial", Font.BOLD, 20));
    muestraCantidad.setBorder(BorderFactory.createLineBorder(new Color(12,183,242)));
    muestraCantidad.setForeground(new Color(97,109,113));
    panel.add(muestraCantidad);
    
    celsiusFahrenheit.setBounds(130,270,200,40);
    celsiusFahrenheit.setBackground(new Color(83,212,255));
    celsiusFahrenheit.setFont(new Font("Arial", Font.BOLD, 14));          
    celsiusFahrenheit.setForeground(new Color(97,109,113));
    celsiusFahrenheit.addActionListener(this);
    panel.add(celsiusFahrenheit);
    
    fahrenheitCelsius.setBounds(130,330,200,40);
    fahrenheitCelsius.setBackground(new Color(83,212,255));
    fahrenheitCelsius.setFont(new Font("Arial", Font.BOLD, 14));          
    fahrenheitCelsius.setForeground(new Color(97,109,113));
    fahrenheitCelsius.addActionListener(this);
    panel.add(fahrenheitCelsius);
    
    JLabel introduceCantidadEtiqueta = new JLabel("Introduce la cantidad: ");
    introduceCantidadEtiqueta.setBounds(130,195,200,30);
    introduceCantidadEtiqueta.setFont(new Font("Arial", Font.ITALIC, 15));
    introduceCantidadEtiqueta.setForeground(new Color(97,109,113));
    panel.add(introduceCantidadEtiqueta);
    
    JLabel titulo = new JLabel("TEMPERATURA");
    titulo.setBounds(80, 30, 320, 70);
    titulo.setFont(new Font("Arial", Font.BOLD, 40));
    titulo.setForeground(new Color(83,212,255));
    titulo.setHorizontalAlignment(SwingConstants.CENTER);

    Border border = BorderFactory.createLineBorder(Color.WHITE, 3);
    titulo.setBorder(border);
    panel.add(titulo);
    
     //ICONO
    Toolkit mipantalla = Toolkit.getDefaultToolkit();
    Image miIcono = mipantalla.getImage("src/imagenes/temperatura.png");
    setIconImage(miIcono);
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == celsiusFahrenheit) {
			
                        if (introduceCantidad.getText().trim().isEmpty()) {  
                            // El JTextField está vacío
                           
                          JOptionPane.showMessageDialog(null, "Esta vacio");
                        } else {
                            try {
                            Double cantidadConvertir = Double.parseDouble(introduceCantidad.getText().trim());
                            System.out.println("El número es: " + cantidadConvertir);
                            Double fahrenheit = (cantidadConvertir*1.8)+32;
                            DecimalFormat df = new DecimalFormat("#.###"); // Definir el formato con dos decimales
                            df.setRoundingMode(RoundingMode.DOWN); // Establecer el modo de redondeo (en este caso, hacia abajo)

                            double numeroRedondeado = Double.parseDouble(df.format(fahrenheit));
                           muestraCantidad.setText(Double.toString(numeroRedondeado));
                            
                        }catch(NumberFormatException a) {
                            
                            JOptionPane.showMessageDialog(null, "El contenido no es un número válido");
                        }
                        }
                       
			
		}else if(e.getSource() == fahrenheitCelsius){
                     
                       if (introduceCantidad.getText().trim().isEmpty()) {  
                           JOptionPane.showMessageDialog(null, "Esta vacio");
                        } else {
                            try {
                            Double cantidadConvertir = Double.valueOf(introduceCantidad.getText().trim());
                            Double centigrados = (cantidadConvertir-32)*(0.5556);
                            DecimalFormat df = new DecimalFormat("#.###"); // Definir el formato con dos decimales
                            df.setRoundingMode(RoundingMode.DOWN); // Establecer el modo de redondeo (en este caso, hacia abajo
                            double numeroRedondeado = Double.parseDouble(df.format(centigrados));
                            muestraCantidad.setText(Double.toString(numeroRedondeado));   
                        }catch(NumberFormatException a) {
                            JOptionPane.showMessageDialog(null, "El contenido no es un número válido");
                        }
                        }
                }
    }
}
