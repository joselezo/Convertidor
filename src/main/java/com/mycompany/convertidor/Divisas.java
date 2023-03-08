
package com.mycompany.convertidor;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import org.json.JSONObject;


public class Divisas extends JFrame implements ActionListener{
	//Listas despleganbles
	String[] monedas = {"USD","EUR","GBP","JPY","KRW"};
        String moneda1, moneda2;
	int cantidadConvertir;
	JComboBox primeraSeleccionDeMoneda = new JComboBox(monedas);
        JComboBox segundaSeleccionDeMoneda = new JComboBox(monedas);
        JTextField cantidad = new JTextField();
        JTextField resultadoConversion = new JTextField();
        JButton botonConvertir = new JButton("Convertir");
  	 
	public Divisas() {
                setSize(500, 700);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		iniciarComponentes();
                setVisible(true);
		
	}
	
	private void iniciarComponentes() {
                
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(222,247,255));
		this.getContentPane().add(panel);
		panel.setLayout(null);

		primeraSeleccionDeMoneda.setBounds(270,200, 200, 30);
		primeraSeleccionDeMoneda.addActionListener(this);
                //----------------------------------------------
                primeraSeleccionDeMoneda.setBackground(new Color(222,247,255));
                primeraSeleccionDeMoneda.setFont(new Font("Arial", Font.BOLD, 14));
                primeraSeleccionDeMoneda.setBorder(BorderFactory.createLineBorder(new Color(12,183,242)));
                primeraSeleccionDeMoneda.setForeground(new Color(97,109,113));
		panel.add(primeraSeleccionDeMoneda);
                
                segundaSeleccionDeMoneda.setBounds(270, 300, 200, 30);
		segundaSeleccionDeMoneda.addActionListener(this);
                segundaSeleccionDeMoneda.setBackground(new Color(222,247,255));
                segundaSeleccionDeMoneda.setFont(new Font("Arial", Font.BOLD, 14));
                segundaSeleccionDeMoneda.setBorder(BorderFactory.createLineBorder(new Color(12,183,242)));
                segundaSeleccionDeMoneda.setForeground(new Color(97,109,113));
		panel.add(segundaSeleccionDeMoneda);
		
                JLabel introduceCantidad = new JLabel("Introduce la cantidad: ");
                introduceCantidad.setBounds(20,175,200,30);
                introduceCantidad.setFont(new Font("Arial", Font.ITALIC, 15));
                introduceCantidad.setForeground(new Color(97,109,113));
                panel.add(introduceCantidad);
                
                cantidad.setBounds(20,200,200,30);
                cantidad.setBackground(new Color(222,247,255));
                cantidad.setFont(new Font("Arial", Font.BOLD, 20));
                cantidad.setBorder(BorderFactory.createLineBorder(new Color(12,183,242)));
                cantidad.setForeground(new Color(97,109,113));
                panel.add(cantidad);
                
                resultadoConversion.setBounds(60, 430, 350, 40);
                resultadoConversion.setFont(new Font("Arial", Font.BOLD, 23));
                resultadoConversion.setEditable(false); 
                resultadoConversion.setBackground(new Color(255,255,255));
                resultadoConversion.setFont(new Font("Arial", Font.BOLD, 20));
                resultadoConversion.setBorder(BorderFactory.createLineBorder(new Color(12,183,242)));
                resultadoConversion.setForeground(new Color(97,109,113));
		panel.add(resultadoConversion);
                
                botonConvertir.setBounds(20,300,200,50);
                botonConvertir.addActionListener(this);
                botonConvertir.setBackground(new Color(83,212,255));
                botonConvertir.setFont(new Font("Arial", Font.BOLD, 14));
               
                botonConvertir.setForeground(new Color(97,109,113));
                panel.add(botonConvertir);
                
                JLabel de = new JLabel("Convertir de:");
                de.setBounds(270,175,200,30);
                de.setFont(new Font("Arial", Font.ITALIC, 15));
                de.setForeground(new Color(97,109,113));
                panel.add(de);
                
                JLabel a = new JLabel("Convertir a:");
                a.setBounds(270,280,200,20);
                a.setFont(new Font("Arial", Font.ITALIC, 15));
                a.setForeground(new Color(97,109,113));
                panel.add(a);

                JLabel titulo = new JLabel("DIVISAS");
                titulo.setBounds(80, 30, 320, 70);
                titulo.setFont(new Font("Arial", Font.BOLD, 40));
                titulo.setForeground(new Color(83,212,255));
                titulo.setHorizontalAlignment(SwingConstants.CENTER);
                // Crear un borde
                Border border = BorderFactory.createLineBorder(Color.WHITE, 3);
                titulo.setBorder(border);
                panel.add(titulo);
                //ICONO
                Toolkit mipantalla = Toolkit.getDefaultToolkit();
		Image miIcono = mipantalla.getImage("src/imagenes/divisas.png");
		setIconImage(miIcono);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
                        if(e.getSource() == botonConvertir) {
                        if (cantidad.getText().trim().isEmpty()) {
                           JOptionPane.showMessageDialog(null, "Esta vacio");
                        } else {
                            try {
                            cantidadConvertir = Integer.parseInt(cantidad.getText().trim());
                            moneda1=(String) primeraSeleccionDeMoneda.getSelectedItem();
                            moneda2=(String) segundaSeleccionDeMoneda.getSelectedItem();
                            Double valor = api(moneda1,moneda2,moneda2) * cantidadConvertir;
                            resultadoConversion.setText(valor.toString()+" " + segundaSeleccionDeMoneda.getSelectedItem());
                        }catch(NumberFormatException a) { 
                            JOptionPane.showMessageDialog(null, "El contenido no es un número válido");
                        }
                        }		
		}
		
	}
        
        
        public double api(String base,String conversion,String moneda){
             double tasaConversion = 0.0;
        try{
	URL url = new URL("https://api.getgeoapi.com/v2/currency/convert?api_key=0d077c95d94c82cee05bc58f0a87b15c8f036746&from="+base+"&to="+conversion+"&amount=10&format=json");
	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	conn.setRequestMethod("GET");
	conn.connect();

	int responseCode = conn.getResponseCode();
	if(responseCode != 200){
		throw new RuntimeException("Ocurrio un error" + responseCode);
	
	}else {
		StringBuilder informationString = new StringBuilder();
		Scanner scanner = new Scanner(url.openStream());
		while(scanner.hasNext()){
			informationString.append(scanner.nextLine());
   		}
		scanner.close();              
                JSONObject miObjeto = new JSONObject(informationString.toString());               
                JSONObject accederRates = miObjeto.getJSONObject("rates");
                JSONObject accederRub = accederRates.getJSONObject(moneda);
                resultadoConversion.setText(accederRub.get("rate").toString());
                tasaConversion = accederRub.getDouble("rate");
         
		}
  }catch(Exception e){
	e.printStackTrace();
 }
     return tasaConversion;  
}

}