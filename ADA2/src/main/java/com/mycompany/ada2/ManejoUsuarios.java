/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ada2;


import com.csvreader.CsvWriter;
import java.awt.HeadlessException;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Personal
 */
public class ManejoUsuarios {
    private final LinkedList<Usuarios> listaUsuarios = new LinkedList<>();
    Usuarios datos = new Usuarios();
    static int users=0;
   void readFile(String Arc) {
        int count=0;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr  = new FileReader(Arc);
            br = new BufferedReader(fr);
            String linea;
            while ((linea=br.readLine())!=null){

                String arreglo [] = linea.split(",");
               
                if(arreglo.length==2){
                    Usuarios a  = new Usuarios();
                    a.setUsuario(arreglo[0]);
                    a.setContra(arreglo[1]);
                    listaUsuarios.add(a);

                }
                count++;
            }
        } catch (IOException e) {
            //TODO: handle exception
            
        }
        finally{
            users=count;
            try {
                if(fr!=null){
                    fr.close();
                    br.close();
                }
            } catch (IOException e) {
                //TODO: handle exception
                
            }
        }
    }

    public void guardarDatos(String Arc, String encript, String usuario) {
    
        FileWriter fichero = null;
        PrintWriter pw= null;

      
        try {
            fichero = new FileWriter(Arc,true);
            pw  = new PrintWriter(fichero);
                String linea = usuario+","+encript;
                pw.append(linea+"\n");
                
                JOptionPane.showMessageDialog(null, "Usuario y contraseña guardados corectamente");
                fichero.close();
                    pw.close();
        } catch (HeadlessException | IOException e) {
            //TODO: handle exception
            
        }
    }

    boolean Comprobacion(String usuario, String contra, String Arc) {
    
               Scanner fichero = null;
        PrintWriter pw= null;

      
        try {
            fichero = new Scanner(new File(Arc));
            while(fichero.hasNext()){
            String[] cadena=fichero.nextLine().split(",");
             if(usuario.equals(cadena[0])&&contra.equals(datos.Desencriptar(cadena[1]))){
             return true;
             }
            //pw  = new PrintWriter(fichero);
               
            }
        } catch (FileNotFoundException e) {
                   //TODO: handle exception
                   
        }
        finally{
            try {
                if(fichero!=null){
                    fichero.close();
                    pw.close();
                }
            } catch (Exception e) {
                //TODO: handle exception
                
            }
        }
        return false;
    }
    
    
    
}
