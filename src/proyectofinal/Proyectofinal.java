package proyectofinal;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.JOptionPane.showMessageDialog;
import proyectofinal.Modificaciones_UI.BackgroundMenuBar;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Proyectofinal extends JFrame implements ActionListener, ItemListener {
    // Base de Datos Mock;
    Cliente[] clientes = new Cliente[10];
    Tratamiento[] tratamientos = new Tratamiento[5];
    Caja caja_principal = new Caja();
    
    // Menu Items
    private BackgroundMenuBar mb;
    private JMenu clientesMenu, tratamientosMenu, cajaMenu, opcionesMenu;
    private JMenuItem editarClientes, insertarClientes;
    private JMenuItem editarTratamientos, insertarTratamientos;
    private JMenuItem cerrarCaja, nuevaFactura;
    private JMenuItem salirOpciones;
    
    // Pantalla Inicial
    private JLabel textoBienvenida, textoSubtitulo;
    
    // Pantalla Añadir Cliente
    private JLabel textoAnadirCliente, textoNombreCliente, textoCedulaCliente;
    private JTextField nombreCliente, cedulaCliente;
    private JButton guardarCliente;
    
    // Pantalla Editar Cliente;
    private JLabel textoEditarCliente, textoNombreClienteEditar, textoCedulaClienteEditar, textoListaCliente;
    private JTextField nombreClienteEditar, cedulaClienteEditar;
    private JButton editarCliente, eliminarCliente;
    private JComboBox listaClientes;
    
    // Pantalla Añadir Tratamiento
    private JLabel textoAnadirTratamiento, textoDescripcionTratamiento, textoPrecioTratamiento;
    private JTextField descripcionTratamiento, precioTratamiento;
    private JButton guardarTratamiento;
    
    // Pantalla Editar Tratamiento;
    private JLabel textoAnadirTratamientoEditar, textoDescripcionTratamientoEditar, textoPrecioTratamientoEditar, textoListaTratamiento;
    private JTextField descripcionTratamientoEditar, precioTratamientoEditar;
    private JButton editarTratamiento, eliminarTratamiento;
    private JComboBox listaTratamientos;
    
    // Pantalla Crear Factura
    private JLabel textoCrearFactura, seleccionCliente, clienteSeleccionado, seleccionTratamiento, tratamientosSeleccionados, totalTratamientos, seleccionCaja;
    private JComboBox listaClientesFactura, listaTratamientosFactura1, listaTratamientosFactura2, listaTratamientosFactura3, cajaParaFacturar;
    private JButton crearFactura;
    
    // Pantalla Cerrar Caja
    private JLabel textoCerrarCaja;
    private JTextArea cierreDeCaja;
    private JScrollPane scrollCierreDeCaja;
    
    // Constructor e Inicialización de Elementos.
    public Proyectofinal(){
        // Datos de Inicio
        clientes[0] = new Cliente("André López", "20202020");
        clientes[1] = new Cliente("Jorge Bastos", "20202320");
        clientes[2] = new Cliente("Jafeth Perez", "30202020");
        tratamientos[0] = new Tratamiento("Tratamiento de Nervio", 320000.0);
        tratamientos[1] = new Tratamiento("Calza", 35000.0);
        Tratamiento[] tratamiento_ejemplo = new Tratamiento[2];
        tratamiento_ejemplo[0] = tratamientos[0];
        tratamiento_ejemplo[1] = tratamientos[1];
        caja_principal.caja[0][0] = new Factura("Fecha Ejemplo", clientes[0], tratamiento_ejemplo);
        
        // Limpieza de Interfaz y Configuración Inicial
        setLayout(null);
        setTitle("Patitos Fronterizos - Clínica Dental");
        getContentPane().setBackground(new Color(255, 255, 255));
        
        // Barra de Menús - Utilizando un Componente Personalizado de JMenuBar.
        mb = new BackgroundMenuBar();
        mb.setColor(new Color(200, 200, 200));
        setJMenuBar(mb);
        
        // Menu de Clientes y Acciones
        clientesMenu = new JMenu("Clientes");
        mb.add(clientesMenu);
        editarClientes = new JMenuItem("Editar Clientes");
        editarClientes.addActionListener(this);
        clientesMenu.add(editarClientes);
        insertarClientes = new JMenuItem("Insertar Cliente");
        insertarClientes.addActionListener(this);
        clientesMenu.add(insertarClientes);
        
        // Menu de Tratamientos y Acciones
        tratamientosMenu = new JMenu("Tratamientos");
        mb.add(tratamientosMenu);
        editarTratamientos = new JMenuItem("Editar Tratamientos");
        editarTratamientos.addActionListener(this);
        tratamientosMenu.add(editarTratamientos);
        insertarTratamientos = new JMenuItem("Insertar Tratamiento");
        insertarTratamientos.addActionListener(this);
        tratamientosMenu.add(insertarTratamientos);
        
        cajaMenu = new JMenu("Facturación");
        mb.add(cajaMenu);
        nuevaFactura = new JMenuItem("Nueva Factura");
        cerrarCaja = new JMenuItem("Cerrar Caja");
        cerrarCaja.addActionListener(this);
        nuevaFactura.addActionListener(this);
        cajaMenu.add(cerrarCaja);
        cajaMenu.add(nuevaFactura);
        
        opcionesMenu = new JMenu("Opciones");
        mb.add(opcionesMenu);
        salirOpciones = new JMenuItem("Salir");
        salirOpciones.addActionListener(this);
        opcionesMenu.add(salirOpciones);
            
        this.crearPantallaInicial();
        this.crearPantallaAnadirCliente();
        this.crearPantallaEditarCliente();
        this.crearPantallaAnadirTratamiento();
        this.crearPantallaEditarTratamiento();
        this.crearPantallaFactura();
        this.crearPantallaCerrarCaja();
        
        this.limpiarPantalla();
        this.mostrarPantallaInicio();
    }
    
    private void crearPantallaInicial(){
        textoBienvenida = new JLabel("¡Bienvenido(a) al Sistema de Clínica Dental!");
        textoSubtitulo = new JLabel("Selecciona una opción del menú.");
        
        textoBienvenida.setFont(new Font("Segoe UI", Font.BOLD, 32));
        textoBienvenida.setForeground(Color.blue);
        textoSubtitulo.setFont(new Font("Segoe UI", Font.ITALIC, 24));
        
        
        textoBienvenida.setBounds(200, 270, 650, 30);
        textoSubtitulo.setBounds(350, 320, 650, 30);
        
        add(textoBienvenida);
        add(textoSubtitulo);
    } 
    private void crearPantallaAnadirCliente(){
        textoAnadirCliente = new JLabel("Añadir Cliente");
        textoNombreCliente = new JLabel("Nombre: ");
        textoCedulaCliente = new JLabel("Cédula: ");
        nombreCliente = new JTextField(); 
        cedulaCliente = new JTextField();
        guardarCliente = new JButton("Guardar");
        
        
        textoAnadirCliente.setFont(new Font("Segoe UI", Font.BOLD, 24));
        textoAnadirCliente.setForeground(Color.blue);
        
        
        
        textoAnadirCliente.setBounds(50, 30, 650, 30);
        textoNombreCliente.setBounds(50, 70, 250, 30);
        nombreCliente.setBounds(50, 100, 250, 30);
        textoCedulaCliente.setBounds(50, 130, 250, 30);
        cedulaCliente.setBounds(50, 160, 250, 30);
        guardarCliente.setBounds(50, 230, 100, 40);
        
        guardarCliente.addActionListener(this);
        
        add(textoAnadirCliente);
        add(textoNombreCliente);
        add(nombreCliente);
        add(textoCedulaCliente);
        add(cedulaCliente);
        add(guardarCliente);
        
    }
    private void crearPantallaEditarCliente(){
        textoEditarCliente = new JLabel("Editar Clientes");
        textoNombreClienteEditar = new JLabel("Nombre: ");
        textoCedulaClienteEditar = new JLabel("Cédula: ");
        nombreClienteEditar = new JTextField(); 
        cedulaClienteEditar = new JTextField();
        editarCliente = new JButton("Editar");
        eliminarCliente = new JButton("Eliminar");
        textoListaCliente = new JLabel("Seleccione la ID del cliente");
        listaClientes = new JComboBox();
        
        for(int i=0;i<clientes.length;i++){
            listaClientes.addItem(i);
        }
        
        textoEditarCliente.setFont(new Font("Segoe UI", Font.BOLD, 24));
        textoEditarCliente.setForeground(Color.blue);
        
        
        
        textoEditarCliente.setBounds(50, 30, 650, 30);
        textoNombreClienteEditar.setBounds(50, 70, 250, 30);
        nombreClienteEditar.setBounds(50, 100, 250, 30);
        textoCedulaClienteEditar.setBounds(50, 130, 250, 30);
        cedulaClienteEditar.setBounds(50, 160, 250, 30);
        editarCliente.setBounds(50, 230, 100, 40);
        eliminarCliente.setBounds(175, 230, 100, 40);
        textoListaCliente.setBounds(450, 70, 250, 30);
        listaClientes.setBounds(450, 100, 250, 30);
        
        listaClientes.addItemListener(this);
        editarCliente.addActionListener(this);
        eliminarCliente.addActionListener(this);
        
        add(textoEditarCliente);
        add(textoNombreClienteEditar);
        add(nombreClienteEditar);
        add(textoCedulaClienteEditar);
        add(cedulaClienteEditar);
        add(editarCliente);
        add(eliminarCliente);
        add(textoListaCliente);
        add(listaClientes);
        
        listaClientes.setSelectedIndex(0);
    }
    private void crearPantallaAnadirTratamiento(){
        textoAnadirTratamiento = new JLabel("Añadir Tratamiento");
        textoDescripcionTratamiento = new JLabel("Descripción del Tratamiento: ");
        textoPrecioTratamiento = new JLabel("Costo del Tratamiento (CRC): ");
        descripcionTratamiento= new JTextField(); 
        precioTratamiento = new JTextField();
        guardarTratamiento = new JButton("Guardar");
        
        
        textoAnadirTratamiento.setFont(new Font("Segoe UI", Font.BOLD, 24));
        textoAnadirTratamiento.setForeground(Color.blue);
        
        
        
        textoAnadirTratamiento.setBounds(50, 30, 650, 30);
        textoDescripcionTratamiento.setBounds(50, 70, 250, 30);
        descripcionTratamiento.setBounds(50, 100, 250, 30);
        textoPrecioTratamiento.setBounds(50, 130, 250, 30);
        precioTratamiento.setBounds(50, 160, 250, 30);
        guardarTratamiento.setBounds(50, 230, 100, 40);
        
        guardarTratamiento.addActionListener(this);
        
        add(textoAnadirTratamiento);
        add(textoDescripcionTratamiento);
        add(descripcionTratamiento);
        add(textoPrecioTratamiento);
        add(precioTratamiento);
        add(guardarTratamiento);
        
    }
    private void crearPantallaEditarTratamiento(){
        textoAnadirTratamientoEditar = new JLabel("Editar Tratamientos");
        textoDescripcionTratamientoEditar  = new JLabel("Descripción del Tratamiento: ");
        textoPrecioTratamientoEditar  = new JLabel("Costo del Tratamiento (CRC): ");
        descripcionTratamientoEditar = new JTextField(); 
        precioTratamientoEditar  = new JTextField();
        editarTratamiento = new JButton("Editar");
        eliminarTratamiento= new JButton("Eliminar");
        textoListaTratamiento = new JLabel("Seleccione la ID del cliente");
        listaTratamientos = new JComboBox();
        
        for(int i=0;i<tratamientos.length;i++){
            listaTratamientos.addItem(i);
        }
        
        textoAnadirTratamientoEditar.setFont(new Font("Segoe UI", Font.BOLD, 24));
        textoAnadirTratamientoEditar.setForeground(Color.blue);
        
        
        
        textoAnadirTratamientoEditar.setBounds(50, 30, 650, 30);
        textoDescripcionTratamientoEditar.setBounds(50, 70, 250, 30);
        descripcionTratamientoEditar.setBounds(50, 100, 250, 30);
        textoPrecioTratamientoEditar.setBounds(50, 130, 250, 30);
        precioTratamientoEditar.setBounds(50, 160, 250, 30);
        editarTratamiento.setBounds(50, 230, 100, 40);
        eliminarTratamiento.setBounds(175, 230, 100, 40);
        textoListaTratamiento.setBounds(450, 70, 250, 30);
        listaTratamientos.setBounds(450, 100, 250, 30);
        
        listaTratamientos.addItemListener(this);
        editarTratamiento.addActionListener(this);
        eliminarTratamiento.addActionListener(this);
        
        add(textoAnadirTratamientoEditar);
        add(textoDescripcionTratamientoEditar);
        add(descripcionTratamientoEditar);
        add(textoPrecioTratamientoEditar);
        add(precioTratamientoEditar);
        add(editarTratamiento);
        add(eliminarTratamiento);
        add(textoListaTratamiento);
        add(listaTratamientos);
        
        listaClientes.setSelectedIndex(0);
    } 
    private void crearPantallaFactura(){
        textoCrearFactura = new JLabel("Crear Factura");
        seleccionCliente = new JLabel("Seleccione el cliente:");
        seleccionTratamiento = new JLabel("Seleccione los tratamientos: ");
        tratamientosSeleccionados = new JLabel("Selección: ");
        totalTratamientos = new JLabel("Total: 0");
        listaClientesFactura = new JComboBox();  
        for(int i = 0;i<clientes.length;i++){
            listaClientesFactura.addItem(i);
        }
        listaClientesFactura.addItemListener(this);
        clienteSeleccionado = new JLabel("Seleccionado: ");
        
        listaTratamientosFactura1 = new JComboBox();

        listaTratamientosFactura2 = new JComboBox();
        listaTratamientosFactura3 = new JComboBox();
        for(int i = 0;i<tratamientos.length;i++){
             listaTratamientosFactura1.addItem(i);
             listaTratamientosFactura2.addItem(i);
             listaTratamientosFactura3.addItem(i);
         }
        listaTratamientosFactura1.addItemListener(this);
        listaTratamientosFactura2.addItemListener(this);
        listaTratamientosFactura3.addItemListener(this);
        seleccionCaja = new JLabel("Seleccione caja para facturación: ");
        cajaParaFacturar = new JComboBox();
        cajaParaFacturar.addItem(0);
        cajaParaFacturar.addItem(1);
        cajaParaFacturar.addItem(2);
        cajaParaFacturar.addItemListener(this);
        crearFactura = new JButton("Crear Factura");
        crearFactura.addActionListener(this);
        
        textoCrearFactura.setFont(new Font("Segoe UI", Font.BOLD, 24));
        textoCrearFactura.setForeground(Color.blue);
        
        textoCrearFactura.setBounds(50, 30, 650, 30);
        seleccionCliente.setBounds(50, 70, 650, 30);
        listaClientesFactura.setBounds(50, 100, 200, 30);
        clienteSeleccionado.setBounds(50, 130, 650, 30);
        seleccionTratamiento.setBounds(50, 170, 650, 30);
        listaTratamientosFactura1.setBounds(50, 200, 200, 30);
        listaTratamientosFactura2.setBounds(275, 200, 200, 30);
        listaTratamientosFactura3.setBounds(500, 200, 200, 30);
        tratamientosSeleccionados.setBounds(50, 230, 700, 30);
        totalTratamientos.setBounds(50, 245, 650, 30);
        seleccionCaja.setBounds(50, 270, 650, 30);
        cajaParaFacturar.setBounds(50, 300, 200, 30);
        crearFactura.setBounds(50, 350, 150, 30);
        
        add(textoCrearFactura);
        add(seleccionCliente);
        add(listaClientesFactura);
        add(clienteSeleccionado);
        add(seleccionTratamiento);
        add(listaTratamientosFactura1);
        add(listaTratamientosFactura2);
        add(listaTratamientosFactura3);
        add(tratamientosSeleccionados);
        add(totalTratamientos);
        add(seleccionCaja);
        add(cajaParaFacturar);
        add(crearFactura);
        
    }
    private void crearPantallaCerrarCaja(){
        textoCerrarCaja = new JLabel("Cierre de Caja");
        cierreDeCaja = new JTextArea();
        scrollCierreDeCaja = new JScrollPane(cierreDeCaja);
        
        textoCerrarCaja.setFont(new Font("Segoe UI", Font.BOLD, 24));
        textoCerrarCaja.setForeground(Color.blue);
        cierreDeCaja.setBackground(Color.LIGHT_GRAY);
        
        textoCerrarCaja.setBounds(50, 30, 650, 30);;
        scrollCierreDeCaja.setBounds(50, 70, 800, 500);
          
        add(textoCerrarCaja);
        add(scrollCierreDeCaja);
    }
    
    
    // Métodos para poner visible lo que se necesita.
    private void mostrarPantallaInicio(){
        textoBienvenida.setVisible(true);
        textoSubtitulo.setVisible(true);
    }
    private void mostrarPantallaAnadirCliente(){
        textoAnadirCliente.setVisible(true);
        textoNombreCliente.setVisible(true);
        nombreCliente.setVisible(true);
        textoCedulaCliente.setVisible(true);
        cedulaCliente.setVisible(true);
        guardarCliente.setVisible(true);
    }
    private void mostrarPantallaEditarCliente(){
        textoEditarCliente.setVisible(true);
        textoNombreClienteEditar.setVisible(true);
        nombreClienteEditar.setVisible(true);
        textoCedulaClienteEditar.setVisible(true);
        cedulaClienteEditar.setVisible(true);
        editarCliente.setVisible(true);
        eliminarCliente.setVisible(true);
        textoListaCliente.setVisible(true);
        listaClientes.setVisible(true);
        nombreClienteEditar.setText("");
        cedulaClienteEditar.setText("");
    }
    private void mostrarPantallaAnadirTratamiento(){
        textoAnadirTratamiento.setVisible(true);
        textoDescripcionTratamiento.setVisible(true);
        descripcionTratamiento.setVisible(true);
        textoPrecioTratamiento.setVisible(true);
        precioTratamiento.setVisible(true);
        guardarTratamiento.setVisible(true);
    }
    private void mostrarPantallaEditarTratamiento(){
        textoAnadirTratamientoEditar.setVisible(true);
        textoDescripcionTratamientoEditar.setVisible(true);
        descripcionTratamientoEditar.setVisible(true);
        textoPrecioTratamientoEditar.setVisible(true);
        precioTratamientoEditar.setVisible(true);
        editarTratamiento.setVisible(true);
        eliminarTratamiento.setVisible(true);
        textoListaTratamiento.setVisible(true);
        listaTratamientos.setVisible(true);
        descripcionTratamientoEditar.setText("");
        precioTratamientoEditar.setText("");
    }
    private void mostrarPantallaCrearFactura(){
        textoCrearFactura.setVisible(true);
        seleccionCliente.setVisible(true);
        listaClientesFactura.setVisible(true);
        clienteSeleccionado.setVisible(true);
        seleccionTratamiento.setVisible(true);
        listaTratamientosFactura1.setVisible(true);;
        listaTratamientosFactura2.setVisible(true);
        listaTratamientosFactura3.setVisible(true);
        tratamientosSeleccionados.setVisible(true);
        totalTratamientos.setVisible(true);
        seleccionCaja.setVisible(true);
        cajaParaFacturar.setVisible(true);
        crearFactura.setVisible(true);
    }
    private void mostrarPantallaCerrarCaja(){
        textoCerrarCaja.setVisible(true);
        scrollCierreDeCaja.setVisible(true);
        String texto_caja = "";
        for(int i = 0; i < caja_principal.caja.length; i++){
            texto_caja += "===========================\n";
            texto_caja += "Caja #"+i+"\n";
            
            for(int j = 0; j < caja_principal.caja[i].length; j++){
                if(caja_principal.caja[i][j]!=null){
                    texto_caja += "@@@@@@@@@@@@@@@@@@@@@@@@@@@\n";
                    texto_caja += "Factura #"+j+"\n";
                    texto_caja += "Fecha: "+ caja_principal.caja[i][j].fecha+"\n";
                    texto_caja += "Cedula Cliente: "+ caja_principal.caja[i][j].cliente.cedula+"\n";
                    texto_caja += "Nombre Cliente: "+ caja_principal.caja[i][j].cliente.nombre+"\n";
                    texto_caja += "-----------------\n";
                    texto_caja += "Tratamientos\n";
                    for(int k = 0; k < caja_principal.caja[i][j].tratamientos.length; k++){
                        if(caja_principal.caja[i][j].tratamientos[k] != null){
                            texto_caja += "- "+caja_principal.caja[i][j].tratamientos[k].descripcion +" : "+String.valueOf(caja_principal.caja[i][j].tratamientos[k].costoBase)+"\n";
                        }
                    }
                    texto_caja += "Total de Compra: "+caja_principal.caja[i][j].calcularPrecio()+"\n";
                    texto_caja += "----------------\n";
                    texto_caja += "@@@@@@@@@@@@@@@@@@@@@@@@@@@\n";
                }
            }
            
        }
        cierreDeCaja.setText(texto_caja);
        caja_principal.cerrar_caja1();
        caja_principal.cerrar_caja2();
        caja_principal.cerrar_caja3();
    }
    
    // Método que elimina todo lo visible.
    private void limpiarPantalla(){
        // Pantalla de Bienvenida
        textoBienvenida.setVisible(false);
        textoSubtitulo.setVisible(false);
        // Pantalla de Añadir Cliente
        textoAnadirCliente.setVisible(false);
        textoNombreCliente.setVisible(false);
        nombreCliente.setVisible(false);
        textoCedulaCliente.setVisible(false);
        cedulaCliente.setVisible(false);
        guardarCliente.setVisible(false);
        // Pantalla de Editar Cliente
        textoEditarCliente.setVisible(false);
        textoNombreClienteEditar.setVisible(false);
        nombreClienteEditar.setVisible(false);
        textoCedulaClienteEditar.setVisible(false);
        cedulaClienteEditar.setVisible(false);
        editarCliente.setVisible(false);
        eliminarCliente.setVisible(false);
        textoListaCliente.setVisible(false);
        listaClientes.setVisible(false);
        // Pantalla de Añadir Tratamiento
        textoAnadirTratamiento.setVisible(false);
        textoDescripcionTratamiento.setVisible(false);
        descripcionTratamiento.setVisible(false);
        textoPrecioTratamiento.setVisible(false);
        precioTratamiento.setVisible(false);
        guardarTratamiento.setVisible(false);
        // Pantalla de Editar Tratamiento
        textoAnadirTratamientoEditar.setVisible(false);
        textoDescripcionTratamientoEditar.setVisible(false);
        descripcionTratamientoEditar.setVisible(false);
        textoPrecioTratamientoEditar.setVisible(false);
        precioTratamientoEditar.setVisible(false);
        editarTratamiento.setVisible(false);
        eliminarTratamiento.setVisible(false);
        textoListaTratamiento.setVisible(false);
        listaTratamientos.setVisible(false);
        // Pantalla de Crear Factura;
        textoCrearFactura.setVisible(false);
        seleccionCliente.setVisible(false);
        listaClientesFactura.setVisible(false);
        clienteSeleccionado.setVisible(false);
        seleccionTratamiento.setVisible(false);
        listaTratamientosFactura1.setVisible(false);;
        listaTratamientosFactura2.setVisible(false);
        listaTratamientosFactura3.setVisible(false);
        tratamientosSeleccionados.setVisible(false);
        totalTratamientos.setVisible(false);
        seleccionCaja.setVisible(false);
        cajaParaFacturar.setVisible(false);
        crearFactura.setVisible(false);
        // Cerrar Caja
        textoCerrarCaja.setVisible(false);
        scrollCierreDeCaja.setVisible(false);
    }
      
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == salirOpciones){
            System.exit(0);
        }
        if(e.getSource()==cerrarCaja){
            this.limpiarPantalla();
            this.mostrarPantallaCerrarCaja();
        }
        if(e.getSource() == insertarClientes){
            this.limpiarPantalla();
            this.mostrarPantallaAnadirCliente();
        }
        if(e.getSource() == editarClientes){
            this.limpiarPantalla();
            this.mostrarPantallaEditarCliente();
        }
        if(e.getSource()==nuevaFactura){
            this.limpiarPantalla();
            this.mostrarPantallaCrearFactura();
        }
        if(e.getSource() == guardarCliente){
            if(!nombreCliente.getText().isEmpty() || !cedulaCliente.getText().isEmpty()){
                for(int i=0; i< clientes.length ; i++){
                    if(clientes[i]==null){
                        clientes[i] = new Cliente(nombreCliente.getText(), cedulaCliente.getText());
                        showMessageDialog(null, "Guardado Correctamente.");
                        this.limpiarPantalla();
                        this.mostrarPantallaEditarCliente();
                        return;
                    }
                }
            }else{
                showMessageDialog(null, "Porfavor llene todos los espacios.");
                return;
            }
            showMessageDialog(null, "No hay espacio para más clientes.");
        }
        if(e.getSource()==editarCliente){
            this.limpiarPantalla();
            this.mostrarPantallaEditarCliente();
            if(!nombreClienteEditar.getText().isEmpty() || !cedulaClienteEditar.getText().isEmpty()){
                clientes[(int)listaClientes.getSelectedItem()].ModificarCliente(nombreClienteEditar.getText(), cedulaClienteEditar.getText());
                showMessageDialog(null, "Editado Correctamente.");
            }else{
                showMessageDialog(null, "Porfavor llene todos los espacios.");
                return;
            }
        }
        if(e.getSource()==eliminarCliente){
            clientes[(int)listaClientes.getSelectedItem()] = null;
            this.mostrarPantallaEditarCliente();
        }
        if(e.getSource() == insertarTratamientos){
            this.limpiarPantalla();
            this.mostrarPantallaAnadirTratamiento();
        }
        if(e.getSource() == editarTratamientos){
            this.limpiarPantalla();
            this.mostrarPantallaEditarTratamiento();
        }
        if(e.getSource() == guardarTratamiento){
            if(!descripcionTratamiento.getText().isEmpty() || !descripcionTratamiento.getText().isEmpty()){
                for(int i=0; i< clientes.length ; i++){
                    if(tratamientos[i]==null){
                        tratamientos[i] = new Tratamiento(descripcionTratamiento.getText(), Double.valueOf(precioTratamiento.getText()));
                        showMessageDialog(null, "Guardado Correctamente.");
                        this.limpiarPantalla();
                        this.mostrarPantallaEditarTratamiento();
                        return;
                    }
                }
            }else{
                showMessageDialog(null, "Porfavor llene todos los espacios.");
                return;
            }
            showMessageDialog(null, "No hay espacio para más tratamientos. Límite: 5");
        }
        if(e.getSource()==eliminarTratamiento){
            tratamientos[(int)listaTratamientos.getSelectedItem()] = null;
            this.mostrarPantallaEditarTratamiento();
        }
        if(e.getSource()==editarTratamiento){
            this.limpiarPantalla();
            this.mostrarPantallaEditarTratamiento();
            if(!descripcionTratamientoEditar.getText().isEmpty() || !precioTratamientoEditar.getText().isEmpty()){
                tratamientos[(int)listaTratamientos.getSelectedItem()].ModificarTratamiento(descripcionTratamientoEditar.getText(), Double.valueOf(precioTratamientoEditar.getText()));
                showMessageDialog(null, "Editado Correctamente.");
            }else{
                showMessageDialog(null, "Porfavor llene todos los espacios.");
                return;
            }
        }
        if(e.getSource()==crearFactura){
            Tratamiento[] tratamientosEnFactura = new Tratamiento[3];
            Cliente clienteEnFactura;
            tratamientosEnFactura[0] = tratamientos[(int)listaTratamientosFactura1.getSelectedItem()];
            tratamientosEnFactura[1] = tratamientos[(int)listaTratamientosFactura2.getSelectedItem()];
            tratamientosEnFactura[2] = tratamientos[(int)listaTratamientosFactura3.getSelectedItem()];
            Date fecha = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss");
            String fecha_texto = sdf.format(fecha);
            Cliente clienteParaFacturar = clientes[(int)listaClientesFactura.getSelectedItem()];
            
            for(int i = 0; i< caja_principal.caja[(int)cajaParaFacturar.getSelectedItem()].length;i++){
                if(caja_principal.caja[(int)cajaParaFacturar.getSelectedItem()][i] == null){
                    caja_principal.caja[(int)cajaParaFacturar.getSelectedItem()][i] = new Factura(fecha_texto, clienteParaFacturar, tratamientosEnFactura);
                    showMessageDialog(null, "Factura creada correctamente");
                    this.limpiarPantalla();
                    this.mostrarPantallaInicio();
                    return;
                }
            }
            showMessageDialog(null, "Ya la caja está llena, porfavor, haga cierre de caja.");
        }
    }
    @Override
    public void itemStateChanged(ItemEvent e){
        if(e.getSource() == listaClientes){
            if(clientes[(int)listaClientes.getSelectedItem()] != null){
                nombreClienteEditar.setText(clientes[(int)listaClientes.getSelectedItem()].nombre);
                cedulaClienteEditar.setText(clientes[(int)listaClientes.getSelectedItem()].cedula);
                editarCliente.setEnabled(true);
                eliminarCliente.setEnabled(true);
            }else{
                nombreClienteEditar.setText("");
                cedulaClienteEditar.setText("");
                editarCliente.setEnabled(false);
                eliminarCliente.setEnabled(false);
                return;
            }
        }
        if(e.getSource() == listaTratamientos){
            if(tratamientos[(int)listaTratamientos.getSelectedItem()] != null){
                descripcionTratamientoEditar.setText(tratamientos[(int)listaTratamientos.getSelectedItem()].descripcion);
                precioTratamientoEditar.setText(String.valueOf(tratamientos[(int)listaTratamientos.getSelectedItem()].costoBase));
                editarTratamiento.setEnabled(true);
                eliminarTratamiento.setEnabled(true);
            }else{
                descripcionTratamientoEditar.setText("");
                precioTratamientoEditar.setText("");
                editarTratamiento.setEnabled(false);
                eliminarTratamiento.setEnabled(false);
                return;
            }
        }
        if(e.getSource()==listaClientesFactura){
            if(clientes[(int)listaClientesFactura.getSelectedItem()] != null){
                clienteSeleccionado.setText("Seleccionado: "+clientes[(int)listaClientesFactura.getSelectedItem()].nombre);
            }
        }
        if(e.getSource()==listaTratamientosFactura1 || e.getSource()==listaTratamientosFactura2 || e.getSource()==listaTratamientosFactura3){
            String cadena_tratamientos = "";
            Double precio_tratamientos = 0.0;
            if(tratamientos[(int)listaTratamientosFactura1.getSelectedItem()] != null){
                cadena_tratamientos += tratamientos[(int)listaTratamientosFactura1.getSelectedItem()].descripcion+" + ";
                precio_tratamientos += tratamientos[(int)listaTratamientosFactura1.getSelectedItem()].costoBase;
            }
            if(tratamientos[(int)listaTratamientosFactura2.getSelectedItem()] != null){
                cadena_tratamientos += tratamientos[(int)listaTratamientosFactura2.getSelectedItem()].descripcion+" + ";
                precio_tratamientos += tratamientos[(int)listaTratamientosFactura2.getSelectedItem()].costoBase;
            }
            if(tratamientos[(int)listaTratamientosFactura3.getSelectedItem()] != null){
                cadena_tratamientos += tratamientos[(int)listaTratamientosFactura3.getSelectedItem()].descripcion+" + ";
                precio_tratamientos += tratamientos[(int)listaTratamientosFactura3.getSelectedItem()].costoBase;
            }  
            tratamientosSeleccionados.setText("Selección: "+cadena_tratamientos);
            totalTratamientos.setText("Total: "+String.valueOf(precio_tratamientos));
        }
    }
    
    public static void main(String[] args) {
        Proyectofinal proyecto = new Proyectofinal();
        proyecto.setBounds(10,20,1080,720);
        proyecto.setResizable(false);
        proyecto.setVisible(true);
    }
}