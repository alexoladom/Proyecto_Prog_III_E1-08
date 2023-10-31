package Ventanas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Clases.Datos;
import Clases.Trabajador;

public class VentanaInicioTrabajador extends JFrame{
	protected JButton botonAtras, botonRegistro, botonIniSesion;
	protected JLabel lblNombre, lblContra, lblTrabajador;
	protected JPanel pBotones, pCentro, pArriba;
	protected JTextField textoNombre;
	protected JPasswordField textoContra;
	protected Datos datos;

	public VentanaInicioTrabajador(Datos datos) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		this.datos= datos;
		botonAtras = new JButton("ATRAS");
		botonIniSesion = new JButton("INICIO DE SESION");
		ImageIcon imTrabajador = new ImageIcon("src\\Imagenes\\Trabajadores.jpeg");

		lblNombre = new JLabel("Introduce tu DNI: ");
		lblContra = new JLabel("Introduce tu contraseña: ");
		lblTrabajador = new JLabel(new ImageIcon("src\\Imagenes\\Trabajadores.jpeg"));

		pBotones = new JPanel();
		pCentro = new JPanel();
		pArriba = new JPanel();

		textoContra = new JPasswordField(20);
		textoNombre = new JTextField(20);

		pBotones.add(botonIniSesion);
		pArriba.add(botonAtras);
		pCentro.add(lblNombre);
		pCentro.add(textoNombre);
		pCentro.add(lblContra);
		pCentro.add(textoContra);
		pCentro.add(lblTrabajador);

		getContentPane().add(pBotones, BorderLayout.SOUTH);
		getContentPane().add(pArriba, BorderLayout.NORTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);

		botonAtras.addActionListener((e) -> {
			dispose();
			new VentanaSeleccion(datos);
		});
		
		botonIniSesion.addActionListener((e) -> {
			String dni = textoNombre.getText();
			if (datos.getMapaTrabajadoresPorDNI().containsKey(dni)) {
				if(datos.comprobarContraseñaTrabajador(dni, textoContra.getText())) {
					JOptionPane.showMessageDialog(null, "Bienvenido!!");
				}else {
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Primero tienes que registrarte");
			}
		}); 
		setVisible(true);
	}

}