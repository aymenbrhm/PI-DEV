/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raouf;

import Entities.Remorquage;
import Entities.Service;
import Interface.IRemorquageService;
import Interface.IServiceService;
import MyConnection.MyConnection;
import Services.RemorquageService;
import Services.ServiceService;

/**
 *
 * @author TECHN
 */
public class Raouf {

     public static void main(String[] args) {
        MyConnection mc = MyConnection.getInstance();
        System.out.println(mc.hashCode());
        
        
        Service s = new Service(3,"nnnnn","nnn");
        Remorquage r = new Remorquage(1,3,"kiki","ki","kii",453);
        
        
        
        IServiceService ss = new ServiceService();
        //ss.ajouterService(s);
        //ss.supprimerService(s);
        //ss.modifierService(s);
        //ss.afficherServices();
        
        
        IRemorquageService rs = new RemorquageService();
       
        //rs.ajouterRemorquage(r);
        //rs.afficherRemorquages();
        //rs.modifierRemorquage(r);
        //rs.supprimerRemorquage(r);
        
        
        
        
        
        
        
     }}
