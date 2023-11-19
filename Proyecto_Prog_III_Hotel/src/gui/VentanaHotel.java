package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import domain.Datos;
import domain.Habitacion;
import domain.Mesa;

public class VentanaHotel extends JFrame{
	private Logger logger = java.util.logging.Logger.getLogger("Logger");
	
	private static final long serialVersionUID = 1L;
	protected JButton botonReserva, botonTerminarReserva, botonCerrar, botonSeleccionar;
	protected JPanel pBotones, pArbol;
	protected Datos datos;
//	protected JDatePicker datePicker;
	protected boolean estado = false;
	//Componentes para la JTable
	private JTable tablaComedor, tablaA, tablaB, tablaC;
	private JScrollPane scrollComedor, scrollA, scrollB, scrollC;
	//Componentes para el arbol
	private DefaultTreeModel modeloArbol;							
	private JTree arbol;
	
	public VentanaHotel(Datos datos) {
		this.datos=datos;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(900,800);
		setTitle("Hotel");
		
		//Creo los botones
		botonReserva = new JButton ("Reserva");
		botonTerminarReserva = new JButton ("Acabar reserva");
		botonCerrar = new JButton ("Cerrar");
		botonSeleccionar = new JButton("Seleccionar");
		pBotones = new JPanel();
		pArbol = new JPanel();
		logger.info("Se han creado los botones");
		
		//Creación de la tabla A
		String [] titulos = {"Estado ocupación","nº Planta","nº Habitacion"};
		
		class MiModeloA extends AbstractTableModel{

		
			private static final long serialVersionUID = 1L;
			@Override
			public int getRowCount() {
				if(datos.getMapaHabitaciones().get(0) == null) {
					return 0;
				}else {
					return datos.getMapaHabitaciones().get(0).size();
				}
			}
				
			@Override
			public int getColumnCount() {
				return titulos.length;
			}
			@Override
			public String getColumnName(int column) {
				return titulos[column];
			}
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				Habitacion h = datos.getMapaHabitaciones().get(0).get(rowIndex);
				switch(columnIndex) {
					case 0: return String.valueOf(h.isOcupado()); 
					case 1: return String.valueOf(h.getPlanta()); 
					case 2: return String.valueOf(h.getNumero()); 
					default: return null;
				}
			}
			public void actualizarEstado(int rowIndex, boolean ocupado) {
			    Habitacion h = datos.getMapaHabitaciones().get(0).get(rowIndex);
			    h.setOcupado(ocupado);

			    fireTableCellUpdated(rowIndex, 0);
			}
		}
		logger.info("Se ha creado el modelo de la tabla A");
		tablaA = new JTable(new MiModeloA());
		tablaA.setRowHeight(50);
		tablaA.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		scrollA = new JScrollPane(tablaA);
		
		//Creación de la tabla B
		class MiModeloB extends AbstractTableModel{

			
			private static final long serialVersionUID = 1L;
			@Override
			public int getRowCount() {
				if(datos.getMapaHabitaciones().get(1) == null) {
					return 0;
				}else {
					return datos.getMapaHabitaciones().get(1).size();
				}
			}
				
			@Override
			public int getColumnCount() {
				return titulos.length;
			}
			@Override
			public String getColumnName(int column) {
				return titulos[column];
			}
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				Habitacion h = datos.getMapaHabitaciones().get(1).get(rowIndex);
				switch(columnIndex) {
					case 0: return String.valueOf(h.isOcupado()); 
					case 1: return String.valueOf(h.getPlanta()); 
					case 2: return String.valueOf(h.getNumero()); 
					default: return null;
				}
			}
		}
		logger.info("Se ha creado el modelo de la tabla B");
		tablaB = new JTable(new MiModeloB());
		tablaB.setRowHeight(50);
		tablaB.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		scrollB = new JScrollPane(tablaB);
		
		//Creación de la tabla C
		class MiModeloC extends AbstractTableModel{

			
			private static final long serialVersionUID = 1L;
			@Override
			public int getRowCount() {
				if(datos.getMapaHabitaciones().get(2) == null) {
					return 0;
				}else {
					return datos.getMapaHabitaciones().get(2).size();
				}
			}
				
			@Override
			public int getColumnCount() {
				return titulos.length;
			}
			@Override
			public String getColumnName(int column) {
				return titulos[column];
			}
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				Habitacion h = datos.getMapaHabitaciones().get(2).get(rowIndex);
				switch(columnIndex) {
					case 0: return String.valueOf(h.isOcupado()); 
					case 1: return String.valueOf(h.getPlanta()); 
					case 2: return String.valueOf(h.getNumero()); 
					default: return null;
				}
			}
		}
		logger.info("Se ha creado el modelo de la tabla C");
		tablaC = new JTable(new MiModeloC());
		tablaC.setRowHeight(50);
		tablaC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		scrollC = new JScrollPane(tablaC);
		
		//Creación de la tabla Comedor
		String [] titulos1 = {"nº Mesas","Estado"};
		class MiModeloComedor extends AbstractTableModel{

			
			private static final long serialVersionUID = 1L;
			@Override
			public int getRowCount() {
				if(datos.getListaComedor() == null) {
					return 0;
				}else {
					return datos.getListaComedor().size();
				}
			}
				
			@Override
			public int getColumnCount() {
				return titulos1.length;
			}
			@Override
			public String getColumnName(int column) {
				return titulos1[column];
			}
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				Mesa c = datos.getListaComedor().get(rowIndex);
				switch(columnIndex) {
					case 0: return c.getNumero(); 
					case 1: return c.isOcupado(); 
					default: return null;
				}
			}
		}
		logger.info("Se ha creado el modelo de la tabla del comedor");
		tablaComedor = new JTable(new MiModeloComedor());
		tablaComedor.setRowHeight(50);
		tablaComedor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		scrollComedor = new JScrollPane(tablaComedor);
		
		//Creacion del arbol
		DefaultMutableTreeNode hotel = new DefaultMutableTreeNode("Hotel");		
		DefaultMutableTreeNode elemA = new DefaultMutableTreeNode("Comedor");		
		DefaultMutableTreeNode elemB = new DefaultMutableTreeNode("Planta A");		
		DefaultMutableTreeNode elemC = new DefaultMutableTreeNode("Planta B");		
		DefaultMutableTreeNode elemD = new DefaultMutableTreeNode("Planta C");		
		
		modeloArbol = new DefaultTreeModel(hotel);					
		modeloArbol.insertNodeInto(elemA, hotel, 0);					
		modeloArbol.insertNodeInto(elemB, hotel, 1);					
		modeloArbol.insertNodeInto(elemC, hotel, 2);					
		modeloArbol.insertNodeInto(elemD, hotel, 3);					
		
		arbol = new JTree(modeloArbol);
		pArbol.add(arbol);
		logger.info("Se ha creado el arbol");
		
		getContentPane().add(pBotones, BorderLayout.EAST);
		getContentPane().add(pArbol, BorderLayout.WEST);
		
		pBotones.add(botonReserva);
		pBotones.add(botonTerminarReserva);
		pBotones.add(botonCerrar);
		pBotones.add(botonSeleccionar);
		
		//ActionListeners de los botones
		botonCerrar.addActionListener((e) -> {
			dispose();
			logger.info("Se cierra la ventana del hotel");
		});
//		botonReserva.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				tablaA.setEnabled(true);
//				tablaB.setEnabled(true);
//				tablaC.setEnabled(true);
//				tablaComedor.setEnabled(true);
//				botonReserva.setEnabled(false);
//			}
//		});
		botonReserva.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        MiModeloA modeloA = (MiModeloA) tablaA.getModel();
		        modeloA.actualizarEstado(1, true);
		        tablaA.repaint();
		    }
		});
//		botonTerminarReserva.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				for (PlazaHotel string : estado.getListaHabitacionesHotel()) {
//					System.out.println(string);
//				}
//				datos.guardarDatos();
//				dispose();
//			}
//		});
		botonSeleccionar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//Creacion de los render de cada tabla
		tablaA.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
		    
			private static final long serialVersionUID = 1L;

			@Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		        MiModeloA modelo = (MiModeloA) table.getModel();
		        boolean ocupado = Boolean.parseBoolean(modelo.getValueAt(row, 0).toString());

		        if (ocupado) {
		            c.setBackground(Color.RED);
		        } else {
		            c.setBackground(Color.GREEN);
		        }
		        
		        return c;
		    }
		});
		
		
		//Funcionamiento del arbol
		arbol.addTreeSelectionListener(new TreeSelectionListener() {
		    @Override
		    public void valueChanged(TreeSelectionEvent e) {
		        DefaultMutableTreeNode nodoSeleccionado = (DefaultMutableTreeNode) arbol.getLastSelectedPathComponent();

		        if (nodoSeleccionado != null) {
		            String nodo = nodoSeleccionado.toString();
		            getContentPane().removeAll();

		            if (nodo.equals("Comedor")) {
		                getContentPane().add(scrollComedor, BorderLayout.CENTER);
		            } else if (nodo.equals("Planta A")) {
		                getContentPane().add(scrollA, BorderLayout.CENTER);
		            } else if (nodo.equals("Planta B")) {
		                getContentPane().add(scrollB, BorderLayout.CENTER);
		            } else if (nodo.equals("Planta C")) {
		                getContentPane().add(scrollC, BorderLayout.CENTER);
		            }

		            revalidate();
		            repaint();
		            pack();
		        }
		    }
		});
		
		pack();
		setVisible(true);
	}	
	public static void main(String[] args) {
		System.out.println("hola mundo!");
		Datos datos = new Datos();
		new VentanaHotel(datos);
		datos.inicializarDatos();
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new VentanaHotel(datos);
				
			}
		});
		
	}
	
}
