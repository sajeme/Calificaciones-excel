/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ada2;

/**
 *
 * @author Andrés Mena Salazar y Julián Chan Palomo
 */
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.awt.Graphics2D;
import java.util.StringTokenizer;
public class ManejoFichero {
    //
        
        //
    private LinkedList<ListaAlumnos> listaAlumnos = new LinkedList<>();
    private LinkedList <Calificaciones> calificaciones = new LinkedList<>();
    private int navegador =0;
    private int importarConfirmado = 0;
    private int cantidadColumnas;
  
    public void exportarArchivo(File archivo){
      
        FileWriter fichero = null;
        PrintWriter pw= null;

      
        try {
            fichero = new FileWriter(archivo);
            pw  = new PrintWriter(fichero);
            for(Calificaciones u:  calificaciones){
                String linea = u.getMatricula()+","+u.getNombreAsignatura()+","+u.getCalificacion();
                pw.println(linea);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //TODO: handle exception
        }
        finally{
            try {
                if(fichero!=null){
                    fichero.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                //TODO: handle exception
            }
        }
       
    }


    public void importarArchivos(File archivo){
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr  = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            while ((linea=br.readLine())!=null){

                String arreglo [] = linea.split(",");
                cantidadColumnas=arreglo.length;
               
                if(arreglo.length==4){
                    ListaAlumnos a  = new ListaAlumnos();
                    a.setMatricula(arreglo[0]);
                    a.setPrimerApellido(arreglo[1]);
                    a.setSegundoApellido(arreglo[2]);
                    a.setNombres(arreglo[3]);
                    listaAlumnos.add(a);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //TODO: handle exception
        }
        finally{
            try {
                if(fr!=null){
                    fr.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                //TODO: handle exception
            }
        }

    }
    

    public void guardarAlumno( javax.swing.JTextField matriculajTextField1,javax.swing.JTextField MateriajTextField1,javax.swing.JTextField CalificacionjTextField2){
        
        Calificaciones nuevaCalificaciones = new Calificaciones();
        nuevaCalificaciones.setMatricula(matriculajTextField1.getText());
        nuevaCalificaciones.setNombreAsignatura(MateriajTextField1.getText());
        nuevaCalificaciones.setCalificacion(Integer.parseInt(CalificacionjTextField2.getText()));
        /*
        nuevoAlumno.setMatricula(matriculajTextField1.getText());
        nuevoAlumno.setPrimerApellido(MateriajTextField1.getText());
        nuevoAlumno.setSegundoApellido(cali.getText());
        nuevoAlumno.setNombres(nombresjTextField1.getText());
         listaAlumnos.add(nuevoAlumno);
    
        */
        calificaciones.add(nuevaCalificaciones);
       

    }
   public void inicio(javax.swing.JTextField matriculajTextField1, javax.swing.JTextField apellidoPjTextField1, javax.swing.JTextField apellidoMjTextField1, javax.swing.JTextField nombresjTextField){
       
       matriculajTextField1.setText(listaAlumnos.get(navegador).getMatricula());
       
       apellidoPjTextField1.setText(listaAlumnos.get(navegador).getPrimerApellido());
      
       apellidoMjTextField1.setText(listaAlumnos.get(navegador).getSegundoApellido());
       
       nombresjTextField.setText(listaAlumnos.get(navegador).getNombres());
       

       
   }

    public void atras(){
        navegador=navegador-1;
        if(navegador==-1){
            navegador=0;
        }

        

    }

    public void delante(){
        navegador=navegador+1;
       if(navegador>listaAlumnos.size()){
            navegador=listaAlumnos.size();
        }

      

    }

    public void setImportarConfirmado(){
        importarConfirmado=1;
    }


    public int getImportarConfirmado() {
        return importarConfirmado;
    }


    public int getCantidadColumnas() {
        return cantidadColumnas;
    }


    public int getNavegador() {
        return navegador;
    }

    public void borrarAlumnos(int index){
        listaAlumnos.remove(index);
        setNavegador();
    }


    public void setNavegador() {
        navegador=listaAlumnos.size();
        
    }


  

    public boolean confirmarCaptura(){
        return listaAlumnos.isEmpty();
    }


    
/*
   public void bloquearTexto(javax.swing.JTextField MateriajTextField1, javax.swing.JTextField CalificacionjTextField2){
    MateriajTextField1.setEnabled(false);
    CalificacionjTextField2.setEnabled(false);
   }

   public void desbloquearTexto(javax.swing.JTextField MateriajTextField1, javax.swing.JTextField CalificacionjTextField2){
    MateriajTextField1.setEnabled(true);
    CalificacionjTextField2.setEnabled(true);
   }

   */

    public void exportarArchivopdf(File archivo){
        try
        {
            PdfWriter writer = new PdfWriter(archivo);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf,PageSize.A4);
            document.setMargins(30, 30, 30, 30);
            PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
            PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
            
            Table table=new Table(new float[]{2,4,4});
            table.setWidth(400);
            StringTokenizer tokenizer= new StringTokenizer("Matricula,Materia,Calificacion",",");
            while(tokenizer.hasMoreElements()){
               table.addHeaderCell(new Cell().add(new Paragraph(tokenizer.nextToken()).setFont(bold)));
            }
            for(Calificaciones u:  calificaciones){
                String linea = u.getMatricula()+","+u.getNombreAsignatura()+","+u.getCalificacion();
                StringTokenizer token= new StringTokenizer(linea,",");
                while(token.hasMoreElements()){
               table.addCell(new Cell().add(new Paragraph(token.nextToken()).setFont(font)));
                }
            }
            document.add(table);
            document.close();
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
    }
    

    
}
