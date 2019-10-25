/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uytube;

import Logica.DataType.DtImagenUsuario;
import Logica.Fabrica;
import Logica.Interfaces.IPersistenciaDeImagenes;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Juan
 */
@WebServlet(name = "ImagenUsuario", urlPatterns = {"/usuario-imagen"})
public class ImagenUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Funciones.Funciones.showLog(request, response);
        try {
            IPersistenciaDeImagenes pi = Fabrica.getInstancia().getIPersistenciaDeImagenes();
            String id = request.getParameter("id");
            if (id == null)  id = "";
            
            byte[] byteArr;
            
            DtImagenUsuario iu = pi.find(id);
            byteArr = iu.getImagen();
            
            
            //byte[] byteArr = ImagePersistController.pathToByteArray("C:\\cnf.jpg");
            
            response.setContentType("image/" + iu.getExtension());
            OutputStream sos = response.getOutputStream();
            sos.write(byteArr);
            sos.flush();
            sos.close();
            
        } catch (Exception e) {
            Funciones.Funciones.showLog(e);
            RequestDispatcher rd; //objeto para despachar
            request.setAttribute("mensajeError", e.getMessage());
            rd = request.getRequestDispatcher("/404.jsp");
            rd.forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
