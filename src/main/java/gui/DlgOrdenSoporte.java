package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Cliente;
import model.OrdenSoporte;
import model.Tecnico;
import util.JPAUtil;

public class DlgOrdenSoporte extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblNroOrdenSoporte;
	private JLabel lblDetalleIncidencia;
	private JLabel lblTécnico;
	private JLabel lblCliente;
	private JLabel lblMonto;
	private JLabel lblFechaRegistro;
	private JTextField txtNroOrdenSoporte;
	private JTextField txtDetalleIncidencia;
	private JComboBox<Object> cboTecnicos;
	private JComboBox<Object> cboClientes;
	private JTextField txtMonto;
	private JTextField txtFechaRegistro;
	private JButton btnBuscar;
	private JButton btnOK;
	private JButton btnOpciones;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JScrollPane scrollPane;

	// Tipo de operaci�n a procesar: Adicionar, Consultar, Modificar o Eliminar
	private int tipoOperacion;

	// Constantes para los tipos de operaciones
	public final static int ADICIONAR = 0;
	public final static int CONSULTAR = 1;
	public final static int MODIFICAR = 2;
	public final static int ELIMINAR = 3;
	private JButton btnListar;
	private JTextArea txtSalida;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DlgOrdenSoporte dialog = new DlgOrdenSoporte();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public DlgOrdenSoporte() {
		setResizable(false);
		setTitle("Mantenimiento | Orden Soporte");
		setBounds(100, 100, 810, 604);
		getContentPane().setLayout(null);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				JPAUtil.close();
			}
		});

		lblNroOrdenSoporte = new JLabel("Nro Orden Soporte :");
		lblNroOrdenSoporte.setBounds(10, 10, 149, 23);
		getContentPane().add(lblNroOrdenSoporte);

		lblDetalleIncidencia = new JLabel("Detalle de la incidencia :");
		lblDetalleIncidencia.setBounds(10, 35, 149, 23);
		getContentPane().add(lblDetalleIncidencia);

		lblTécnico = new JLabel("Técnico :");
		lblTécnico.setBounds(10, 62, 149, 23);
		getContentPane().add(lblTécnico);

		lblMonto = new JLabel("Monto :");
		lblMonto.setBounds(11, 116, 149, 23);
		getContentPane().add(lblMonto);

		txtNroOrdenSoporte = new JTextField();
		txtNroOrdenSoporte.setBounds(174, 10, 86, 23);
		getContentPane().add(txtNroOrdenSoporte);
		txtNroOrdenSoporte.setEditable(false);
		txtNroOrdenSoporte.setColumns(10);

		txtDetalleIncidencia = new JTextField();
		txtDetalleIncidencia.setBounds(174, 35, 251, 23);
		getContentPane().add(txtDetalleIncidencia);
		txtDetalleIncidencia.setColumns(10);

		cboTecnicos = new JComboBox<Object>();
		cboTecnicos.setBounds(174, 60, 251, 26);
		getContentPane().add(cboTecnicos);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setEnabled(false);
		btnBuscar.setBounds(324, 10, 101, 23);
		getContentPane().add(btnBuscar);

		btnOK = new JButton("OK");
		btnOK.addActionListener(this);
		btnOK.setBounds(325, 140, 100, 23);
		getContentPane().add(btnOK);

		btnOpciones = new JButton("Opciones");
		btnOpciones.addActionListener(this);
		btnOpciones.setBounds(555, 10, 100, 75);
		getContentPane().add(btnOpciones);

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(this);
		btnAdicionar.setBounds(664, 10, 120, 23);
		getContentPane().add(btnAdicionar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.setBounds(664, 36, 120, 23);
		getContentPane().add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(664, 62, 120, 23);
		getContentPane().add(btnEliminar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 174, 775, 343);
		getContentPane().add(scrollPane);

		txtSalida = new JTextArea();
		txtSalida.setFont(new Font("Monospaced", Font.PLAIN, 13));
		scrollPane.setViewportView(txtSalida);

		btnListar = new JButton("Listar");
		btnListar.addActionListener(this);
		btnListar.setBounds(345, 525, 115, 29);
		getContentPane().add(btnListar);

		lblFechaRegistro = new JLabel("Fecha registro :");
		lblFechaRegistro.setBounds(11, 143, 149, 20);
		getContentPane().add(lblFechaRegistro);

		txtFechaRegistro = new JTextField();
		txtFechaRegistro.setEditable(false);
		txtFechaRegistro.setBounds(175, 140, 146, 26);
		getContentPane().add(txtFechaRegistro);
		txtFechaRegistro.setColumns(10);

		txtMonto = new JTextField();
		txtMonto.setEditable(false);
		txtMonto.setColumns(10);
		txtMonto.setBounds(175, 116, 86, 23);
		getContentPane().add(txtMonto);

		lblCliente = new JLabel("Cliente :");
		lblCliente.setBounds(10, 90, 149, 23);
		getContentPane().add(lblCliente);

		cboClientes = new JComboBox<Object>();
		cboClientes.setEnabled(false);
		cboClientes.setBounds(174, 88, 251, 26);
		getContentPane().add(cboClientes);

		habilitarEntradas(false);
		habilitarBotones(true);
		cargarTecnico();
		cargarCliente();
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnListar) {
			actionPerformedBtnListar(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(arg0);
		}
		if (arg0.getSource() == btnModificar) {
			actionPerformedBtnModificar(arg0);
		}
		if (arg0.getSource() == btnAdicionar) {
			actionPerformedBtnAdicionar(arg0);
		}
		if (arg0.getSource() == btnOpciones) {
			actionPerformedBtnOpciones(arg0);
		}
		if (arg0.getSource() == btnOK) {
			actionPerformedBtnOK(arg0);
		}
		if (arg0.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(arg0);
		}
	}

	protected void actionPerformedBtnBuscar(ActionEvent arg0) {
		consultar();
	}

	protected void actionPerformedBtnOK(ActionEvent arg0) {
		switch (tipoOperacion) {
		case ADICIONAR:
			adicionar();
			break;
		case MODIFICAR:
			modificar();
			break;
		case ELIMINAR:
			eliminar();
		}
	}

	protected void actionPerformedBtnOpciones(ActionEvent arg0) {
		limpiar();
	}

	protected void actionPerformedBtnListar(ActionEvent arg0) {

		listar();
	}

	protected void actionPerformedBtnAdicionar(ActionEvent arg0) {
		tipoOperacion = ADICIONAR;
		habilitarEntradas(true);
		habilitarBotones(false);
		txtDetalleIncidencia.requestFocus();
	}

	protected void actionPerformedBtnModificar(ActionEvent arg0) {
		tipoOperacion = MODIFICAR;
		txtNroOrdenSoporte.setEditable(true);
		habilitarBotones(false);
		txtNroOrdenSoporte.requestFocus();
	}

	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		tipoOperacion = ELIMINAR;
		txtNroOrdenSoporte.setEditable(true);
		habilitarBotones(false);
		txtNroOrdenSoporte.requestFocus();
	}

	void cargarCliente() {
		EntityManager manager = JPAUtil.getEntityManager();
		String jpql = "Select c from Cliente c";

		try {
           List<Cliente> lstClientes = manager.createQuery(jpql, Cliente.class).getResultList();
           
          for (Cliente cliente : lstClientes) {
			cboClientes.addItem(cliente);
		}
		} finally {
			manager.close();
		}
	}
	
	void cargarTecnico() {
		EntityManager manager = JPAUtil.getEntityManager();
		String jpql = "Select t from Tecnico t";

		try {
           List<Tecnico> lstTecnicos = manager.createQuery(jpql, Tecnico.class).getResultList();
           
          for (Tecnico tecnico : lstTecnicos) {
			cboTecnicos.addItem(tecnico);
		}
		} finally {
			manager.close();
		}

	}

	void listar() {
		EntityManager manager = JPAUtil.getEntityManager();
		String jpql = "select o from OrdenSoporte o";

		try {
			List<OrdenSoporte> lstOrdenes = manager.createQuery(jpql, OrdenSoporte.class).getResultList();

			for (OrdenSoporte ordenSoporte : lstOrdenes) {
				Tecnico tecnico = ordenSoporte.getTecnico();
				Cliente cliente = ordenSoporte.getCliente();

				imprimir("Nro de orden......:" + ordenSoporte.getNroOrden());
				imprimir("Fecha de Registro.:" + ordenSoporte.getFechaRegistro());
				imprimir("Tecnico...........:" + tecnico.getNombre() + " Especialista en "
						+ tecnico.getEspecialidadDescripcion());
				imprimir("Cliente...........:" + cliente.getIdCliente() + "-" + cliente.getRazonSocial());
				imprimir("Monto.............:" + ordenSoporte.getMonto());
				imprimir("Detalle Incidencia:" + ordenSoporte.getDetalleIncidencia());
				imprimir("------------------------------------------------\n");
			}

		} finally {

			manager.close();
		}
	}

	void adicionar() {
		String detalleIncidencia = txtDetalleIncidencia.getText();
		Tecnico tecnico = (Tecnico) cboTecnicos.getSelectedItem();
		Cliente cliente = (Cliente) cboClientes.getSelectedItem();
		Double monto = Double.parseDouble(txtMonto.getText());
		
		EntityManager manager = JPAUtil.getEntityManager();
		
		try {
			OrdenSoporte ordenSoporte = new OrdenSoporte(null, null, tecnico, cliente, monto, detalleIncidencia);
			
			manager.getTransaction().begin();
			manager.persist(ordenSoporte);
			manager.getTransaction().commit();
			
			mensajeInfo("Orden de soporte registrada");
			limpiar();
			
		} catch (Exception e) {
			mensajeError("Hubo un error en la transacción");
			e.printStackTrace();
		} finally {
			manager.close();
		}
	}

	void consultar() {
     Integer nroOrden = Integer.parseInt(txtNroOrdenSoporte.getText());
     EntityManager manager = JPAUtil.getEntityManager();
     
     try {
		OrdenSoporte OrdenSoporte = manager.find(OrdenSoporte.class, nroOrden);
		
		if(OrdenSoporte == null) {
			mensajeAdvertencia("Orden de soporte no encontrada");
			return;
		}
		txtDetalleIncidencia.setText(OrdenSoporte.getDetalleIncidencia());
		cboTecnicos.setSelectedItem(OrdenSoporte.getTecnico());
		cboClientes.setSelectedItem(OrdenSoporte.getCliente());
		txtMonto.setText(OrdenSoporte.getMonto()+ "");
		txtFechaRegistro.setText(OrdenSoporte.getFechaRegistro()+ "");
		
	} finally {
     manager.close();	
     }
	}

	void modificar() {
		Integer nroOrden = Integer.parseInt(txtNroOrdenSoporte.getText());
		String detalleIncidencia = txtDetalleIncidencia.getText();
		Tecnico tecnico = (Tecnico) cboTecnicos.getSelectedItem();
		Cliente cliente = (Cliente) cboClientes.getSelectedItem();
		Double monto = Double.parseDouble(txtMonto.getText());
		//LocalDate fechaRegistro = LocalDate.parse(detalleIncidencia);
		EntityManager manager = JPAUtil.getEntityManager();
		
		try {
			OrdenSoporte ordenSoporte = new OrdenSoporte( nroOrden, null, tecnico, cliente, monto, detalleIncidencia);
			
			manager.getTransaction().begin();
			manager.persist(ordenSoporte);
			manager.getTransaction().commit();
			
			mensajeInfo("Orden de soporte actualizada");
			limpiar();
			
		} catch (Exception e) {
			mensajeError("Hubo un error en la transacción");
			e.printStackTrace();
		} finally {
			manager.close();
		}
	}

	void eliminar() {

	}

	// M�todos tipo void (con par�metros)
	void habilitarEntradas(boolean sino) {
		txtDetalleIncidencia.setEditable(sino);
		cboTecnicos.setEnabled(sino);
		cboClientes.setEnabled(sino);
		txtMonto.setEditable(sino);
	}

	void habilitarBotones(boolean sino) {
		if (tipoOperacion == ADICIONAR)
			btnOK.setEnabled(!sino);
		else {
			btnBuscar.setEnabled(!sino);
			btnOK.setEnabled(false);
		}
		btnAdicionar.setEnabled(sino);
		btnModificar.setEnabled(sino);
		btnEliminar.setEnabled(sino);
		btnOpciones.setEnabled(!sino);
	}

	void habilitarOk() {
		if (tipoOperacion == MODIFICAR) {
			habilitarEntradas(true);
			txtNroOrdenSoporte.setEditable(false);
			btnBuscar.setEnabled(false);
			btnOK.setEnabled(true);
			txtDetalleIncidencia.requestFocus();
		}
		if (tipoOperacion == ELIMINAR) {
			txtNroOrdenSoporte.setEditable(false);
			btnBuscar.setEnabled(false);
			btnOK.setEnabled(true);
		}
	}

	void mensajeInfo(String msj) {
		mensaje(msj, "INFO", JOptionPane.INFORMATION_MESSAGE);
	}

	void mensajeAdvertencia(String msj) {
		mensaje(msj, "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
	}

	void mensajeError(String msj) {
		mensaje(msj, "ERROR", JOptionPane.ERROR_MESSAGE);
	}

	void mensaje(String msj, String titulo, int tipo) {
		JOptionPane.showMessageDialog(this, msj, titulo, tipo);
	}

	void imprimir(String texto) {
		txtSalida.append(texto + "\n");
	}

	void imprimir() {
		imprimir("");
	}

	void limpiar() {
		txtNroOrdenSoporte.setText("");
		txtDetalleIncidencia.setText("");
		if (cboTecnicos.getItemCount() > 0)
			cboTecnicos.setSelectedIndex(0);
		if (cboClientes.getItemCount() > 0)
			cboClientes.setSelectedIndex(0);
		txtMonto.setText("");
		txtFechaRegistro.setText("");
		txtNroOrdenSoporte.setEditable(false);
		habilitarEntradas(false);
		habilitarBotones(true);
	}
}