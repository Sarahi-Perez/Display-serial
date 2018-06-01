/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arduino;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import jssc.SerialPortException;

/**
 *
 * @author Sarah Lee
 */
public class Window extends JFrame{
    
    PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
    
    private JPanel board, head; // panel que contiene los botones
    private JButton btnEnviar;
    private JButton btnLimpiar;
    private JButton btnDateTime;
    private JButton btnTemperatura;
    
    private JTextField txtMensaje; //Se declara privado, puesto que solo lo utilizaremos en esta clase
    private JLabel lbCaracteres;
    private int caracteres;
    
    //Es un contructor, es decir, un metodo llamado igual que la clase, es como darle propiedades a la clase
    public Window()
    {
        //Son las propiedades de la ventana o Frame
        super("Acciones para la pantalla LCD"); // El titulo que se mostrara al ejecutar
        
        board = new JPanel(); // establece el panel
        board.setLayout( new GridLayout( 2, 3, 5, 5 ) ); // 2 por 3; espacios de 5, es decir como se acomodaran los botones, se acomodaran de 2 botones en 3 columnas
        //Se instancian los botones y se les asigna el texto de cada boton
        btnEnviar = new JButton("Enviar Texto"); 
        btnLimpiar = new JButton("Limpiar Texto");
        btnDateTime = new JButton("Mostrar Fecha/Hora");
        btnTemperatura = new JButton("Mostrar Temperatura");
        
        //Se le da accion al boton, es decir, lo que debe realizar al darse clic al boton ENVIAR TEXTO
        btnEnviar.addActionListener(
            new ActionListener() // clase interna anonima
            {  
                // procesa evento del boton ENVIAR TEXTO 
                public void actionPerformed( ActionEvent evento )
                {
                    try {
                        //Con este metodo le enviamos a Arduino el contenido del TextField
                        enviarDatos(txtMensaje.getText());
                    } catch (SerialPortException ex) {
                        Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //Se limpia el contenido del TextField
                    txtMensaje.setText("");
                    //Se llama al metodo de letras
                    letras();
                } // fin del metodo actionPerformed
            } // fin de la clase interna anonima
        ); // fin de la llamada a addActionListener
        
        //Se le da accion al boton, es decir, lo que debe realizar al darse clic al boton LIMPIAR TEXTO
        btnLimpiar.addActionListener(
            new ActionListener() // clase interna anonima
            {  
                // procesa evento del boton ENVIAR TEXTO 
                public void actionPerformed( ActionEvent evento )
                {
                    //Se limpia el contenido del TextField y se actualiza la cantidad de caracteres
                    txtMensaje.setText("");
                    letras();
                } // fin del metodo actionPerformed
            } // fin de la clase interna anonima
        ); // fin de la llamada a addActionListener
        
        //Se le da accion al boton, es decir, lo que debe realizar al darse clic al boton MOSTRAR FECHA/HORA
        btnDateTime.addActionListener(
            new ActionListener() // clase interna anonima
            {  
                // procesa evento del boton ENVIAR TEXTO 
                public void actionPerformed( ActionEvent evento )
                {
                    try {
                        //enviarDatos("~");
                        ino.sendData("1");
                    } catch (SerialPortException ex) {
                        Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ArduinoException ex) {
                        Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } // fin del metodo actionPerformed
            } // fin de la clase interna anonima
        ); // fin de la llamada a addActionListener
        
        //Se le da accion al boton, es decir, lo que debe realizar al darse clic al boton MOSTRAR TEMPERATURA
        btnTemperatura.addActionListener(
            new ActionListener() // clase interna anonima
            {  
                // procesa evento del boton ENVIAR TEXTO 
                public void actionPerformed( ActionEvent evento )
                {
                    try {
                       // enviarDatos("`");
                       ino.sendData("0");
                    } catch (SerialPortException ex) {
                        Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ArduinoException ex) {
                        Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } // fin del metodo actionPerformed
            } // fin de la clase interna anonima
        ); // fin de la llamada a addActionListener
        
        //Lo que habra en el encabezado de la ventana
        head = new JPanel();
        txtMensaje = new JTextField(30); //El numero 30 se le asina, para darle un tamaño, el largo.
        lbCaracteres = new JLabel();
        
        txtMensaje.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    //Esto permite que cuando teclea ENTER mientras esta escribiendo en un TextField, se ejecute determinado comando.
                    enviarDatos(txtMensaje.getText());
                } catch (SerialPortException ex) {
                    Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                }
                txtMensaje.setText("");
            }
        });
        
         txtMensaje.addKeyListener( new KeyListener()
        {
            /*
            El evento KeyReleased se ejecuta cada vez que una tecla pasa de estar presionada a cuando el usuario deja de presionarla.
            Al escribir presionamos las teclas de nuestra computadora, por lo que este evento se ejecutará cada vez que introduzcamos un caracter.
            */
             @Override
            public void keyReleased(KeyEvent ke) {
               letras();
            }
            //NOTA: estos otros dos eventos no se usan en este proyecto, solo se ponen porque asi te lo pide java y se comenta lo que haya
            //adentro para que no afecte a la funcionalidad
            @Override
            public void keyTyped(KeyEvent ke) {
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
         
        //Se agregan al panel para que se puedan visualizar
        board.add(btnEnviar);
        board.add(btnLimpiar);
        board.add(btnDateTime);
        board.add(btnTemperatura);
        
        head.add(txtMensaje);
        head.add(lbCaracteres);
        
        add(head);
        add(board, BorderLayout.SOUTH); //Se da una direccion y el tipo de direccion para un acomodo de los elementos
        
        // Realiza la coneccion
        conexion();
        //Llama el metodo de Letras
        letras();
    }
    
     public void letras()
    {
        caracteres = 32 - txtMensaje.getText().length();//Indica la cantidad de caracteres disponibles.
        //En el LCD solo se permite imprimir 32 caracteres.
        
        if(caracteres <= 0) // Si la cantidad de caracteres se ha agotado...
        {
           lbCaracteres.setText("Caracteres disponibles: 0"); // Se imprime que la cantidad de caracteres disponibles es 0
           String cadena = ""; //Se declara la variable que guardara el mensaje a enviar
           cadena = txtMensaje.getText(); //Se asigna el texto del TextField a la variable
           cadena = cadena.substring(0,32); //Se evita que por alguna razon la variable contenga mas de 32 caracteres,
           //utilizando un substring que crea un string a partir de uno mayor.
           txtMensaje.setText(cadena); //Se regresa la cadena con 32 caracteres al TextField 
        }
        else
        {
            //Si la cantidad de caracteres disponibles es mayor a 0 solamente se imprimira la cantidad de caracteres disponibles
            lbCaracteres.setText("Caracteres disponibles: " + (caracteres));
        }
        
    }
    
    // Realiza la coneccion en el COM
    public void conexion() { 
        try {
            ino.arduinoTX("COM12", 9600 );
        } catch (ArduinoException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   // METODO PARA ENVIAR DATOS 
    public void enviarDatos(String Dato) throws SerialPortException { 
        try {
            // CONEXION ENVIA UN DATO
            ino.sendData(Dato);
        } catch (ArduinoException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SerialPortException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Metodo principal de ejecucion, es importante que este este metodo para poder ejecutar la aplicacion
    public static void main(String[] args)
    {
        Window mens = new Window(); // Se instancia a si misma para que se ejecute.
        mens.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        mens.setSize( 350,150 ); // establece el tama�o del marco
        mens.setVisible( true ); // muestra el marco
    }
}
