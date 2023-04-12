/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entities.Remorquage;
import Interface.IRemorquageService;
import MyConnection.MyConnection;
import Services.RemorquageService;
import static java.lang.Boolean.parseBoolean;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author TECHN
 */
public class RemorquageController implements Initializable {

    @FXML
    private TableView<Remorquage> tableRemorquage;
    @FXML
    private TableColumn<Remorquage, Integer> id;
    @FXML
    private TableColumn<Remorquage, String> nom;
    @FXML
    private TableColumn<Remorquage, String> prenom;
    @FXML
    private TableColumn<Remorquage, String> email;
    @FXML
    private TableColumn<Remorquage, String> numtel;
    @FXML
    private TextArea idremorquage;
    @FXML
    private TextArea nomremorquage;
    @FXML
    private TextArea prenomremorquage;
    @FXML
    private TextArea emailremorquage;
    @FXML
    private TextArea numtelremorquage;
    @FXML
    private ComboBox<Integer> idservice;
    @FXML
    private Button ajouterRom;
    @FXML
    private Button supprimerRom;
    @FXML
    private Button modifierRom;
     
    
    Connection mc;
    PreparedStatement ste;
    ObservableList<Remorquage>remList;
    @FXML
    private TableColumn<Remorquage, Integer > ids;
    
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      try {
            String req="select id from service";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(req);
            ResultSet rs=pst.executeQuery();
            ObservableList<Integer> id = null;
            List<Integer> list = new ArrayList<>();
            while(rs.next()){
                
                list.add(rs.getInt("id"));
                
            }
            id = FXCollections
                    .observableArrayList(list);
            idservice.setItems(id);
        } catch (SQLException ex) {
            Logger.getLogger(RemorquageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        afficherRemorquages();
    }    

    
    
    
    
     void afficherRemorquages(){
            mc=MyConnection.getInstance().getCnx();
            remList = FXCollections.observableArrayList();
      
      try {
            String requete = "SELECT * FROM remorquage r ";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete); 
            while(rs.next()){
                Remorquage r = new Remorquage();
                
                r.setIdremorquage(rs.getInt("idremorquage"));
                r.setIds(rs.getInt("ids"));
                r.setName(rs.getString("name"));
                r.setPrenom(rs.getString("prenom"));
                r.setEmail(rs.getString("email"));
                r.setNumtel(rs.getInt("numtel"));
                
                remList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
  id.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getIdremorquage()));
  ids.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getIds()));
  nom.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
  prenom.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrenom()));
  email.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
  numtel.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getNumtel()));
 
   tableRemorquage.setItems(remList);
  
  refresh();
    
  }
  
  
    
    public void refresh(){
            remList.clear();
            mc=MyConnection.getInstance().getCnx();
            remList = FXCollections.observableArrayList();
      
      try {
            String requete = "SELECT * FROM remorquage r ";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete); 
            while(rs.next()){
                Remorquage r = new Remorquage();
                
                r.setIdremorquage(rs.getInt("idremorquage"));
                r.setIds(rs.getInt("ids"));
                r.setName(rs.getString("name"));
                r.setPrenom(rs.getString("prenom"));
                r.setEmail(rs.getString("email"));
                r.setNumtel(rs.getInt("numtel"));
                remList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        tableRemorquage.setItems(remList);
       
  }
    
   
    
    
    
    
    
    @FXML
    private void selected(MouseEvent event) {
        
         Remorquage clicked = tableRemorquage.getSelectionModel().getSelectedItem();
         
         idremorquage.setText(String.valueOf(clicked.getIdremorquage()));
         idservice.setValue(clicked.getIds());
        nomremorquage.setText(String.valueOf(clicked.getName()));
        prenomremorquage.setText(String.valueOf(clicked.getPrenom()));
        emailremorquage.setText(String.valueOf(clicked.getEmail()));
        numtelremorquage.setText(String.valueOf(clicked.getNumtel()));

        
    }

    @FXML
    private void addRom(MouseEvent event) {

        String idser = idservice.getSelectionModel().getSelectedItem().toString();
        String nom =nomremorquage.getText();
        String prenom =prenomremorquage.getText();
        String email =emailremorquage.getText();
        String numtel =numtelremorquage.getText();
        
       Remorquage r= new Remorquage(1,Integer.parseInt(idser),nom,prenom,email,Integer.parseInt(numtel));
      
        IRemorquageService rs= new RemorquageService();
        rs.ajouterRemorquage(r);
       
        refresh();
        
     
        nomremorquage.setText(null);
        prenomremorquage.setText(null);
        emailremorquage.setText(null);
        numtelremorquage.setText(null); 
        
        
    }

    @FXML
    private void deleteRom(MouseEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
       alert.setHeaderText("Warning");
       alert.setContentText("Es-tu sûre de supprimer!"); 
        
        
        String idmorq  = idremorquage.getText();
        String idser = idservice.getSelectionModel().getSelectedItem().toString();
        String nom =nomremorquage.getText();
        String prenom =prenomremorquage.getText();
        String email =emailremorquage.getText();
        String numtel =numtelremorquage.getText();
        
        
         Optional<ButtonType>result =  alert.showAndWait();
        if(result.get() == ButtonType.OK)
        {
        
        Remorquage r= new Remorquage(Integer.parseInt(idmorq),Integer.parseInt(idser),nom,prenom,email,Integer.parseInt(numtel));
        IRemorquageService rs= new RemorquageService();
        rs.supprimerRemorquage(r);
       
        refresh();
        
        
        
        nomremorquage.setText(null);
        prenomremorquage.setText(null);
        emailremorquage.setText(null);
        numtelremorquage.setText(null); 
       }
        
        
        
        
        
    }

    @FXML
    private void updateRom(MouseEvent event) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
       alert.setHeaderText("Warning");
       alert.setContentText("Es-tu sûre de modifier!"); 
        
        
        String idmorq  = idremorquage.getText();
        String idser = idservice.getSelectionModel().getSelectedItem().toString();
        String nom =nomremorquage.getText();
        String prenom =prenomremorquage.getText();
        String email =emailremorquage.getText();
        String numtel =numtelremorquage.getText();
        
        
         Optional<ButtonType>result =  alert.showAndWait();
        if(result.get() == ButtonType.OK)
        {
        
        Remorquage r= new Remorquage(Integer.parseInt(idmorq),Integer.parseInt(idser),nom,prenom,email,Integer.parseInt(numtel));
        IRemorquageService rs= new RemorquageService();
        rs.modifierRemorquage(r);
       
        refresh();
        
        
        
        nomremorquage.setText(null);
        prenomremorquage.setText(null);
        emailremorquage.setText(null);
        numtelremorquage.setText(null); 
       }
        
        
        
        
        
        
        
    }
    
}
