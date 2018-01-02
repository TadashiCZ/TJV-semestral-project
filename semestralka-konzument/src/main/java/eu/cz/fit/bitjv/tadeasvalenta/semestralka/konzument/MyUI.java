package eu.cz.fit.bitjv.tadeasvalenta.semestralka.konzument;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import eu.cz.fit.bitjv.tadeasvalenta.semestralka.*;
import eu.cz.fit.bitjv.tadeasvalenta.semestralka.konzument.dto.Flat;
import eu.cz.fit.bitjv.tadeasvalenta.semestralka.konzument.dto.Occupier;
import eu.cz.fit.bitjv.tadeasvalenta.semestralka.konzument.dto.OccupierBox;
import eu.cz.fit.bitjv.tadeasvalenta.semestralka.rest.FlatClient;
import eu.cz.fit.bitjv.tadeasvalenta.semestralka.rest.OccupierClient;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
    private Label occupier = new Label();
    private TextField occupiersCount = new TextField("Flat Count");
    
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
         final TextField name = new TextField();
        name.setCaption("Input numer of drivers:");
        Button button = new Button("Send");
        button.addClickListener(e -> {
           
        });
        
        
        FlatClient fs = new FlatClient();
        OccupierClient os = new OccupierClient();
   
        
        try {
            System.out.println(fs.find_JSON(Flat.class, "1"));
        } catch (Exception e) {
            System.out.println("Nothing found");
        }
        
        try {
            System.out.println(os.countREST()); 
        } catch (Exception e) {
            System.out.println("Nothing found");
        }
        
        try {
            occupier.setValue(fs.find_JSON(Occupier.class, "1").getName());}
        catch (Exception e){
            System.out.println("Nothing found to set");
        }
        occupiersCount.setValue(os.countREST());
        Grid<Occupier> grid = new Grid<>();
        grid.setItems(os.findAllOccupiers_JSON(OccupierBox.class).getOccupiers());
        
        grid.addColumn(Occupier::getName).setCaption("Name");
        grid.addColumn(Occupier::getPhone).setCaption("Phone");
      //    layout.addComponents(name, button);
      //    layout.addComponent(occupier);
      //    layout.addComponent(occupiersCount);
        layout.addComponent(grid);
        setContent(layout);
        
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
