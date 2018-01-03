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
import com.vaadin.ui.components.grid.HeaderRow;
import eu.cz.fit.bitjv.tadeasvalenta.semestralka.*;
import eu.cz.fit.bitjv.tadeasvalenta.semestralka.konzument.dto.Flat;
import eu.cz.fit.bitjv.tadeasvalenta.semestralka.konzument.dto.FlatBox;
import eu.cz.fit.bitjv.tadeasvalenta.semestralka.konzument.dto.Occupier;
import eu.cz.fit.bitjv.tadeasvalenta.semestralka.konzument.dto.OccupierBox;
import eu.cz.fit.bitjv.tadeasvalenta.semestralka.konzument.dto.Plug;
import eu.cz.fit.bitjv.tadeasvalenta.semestralka.konzument.dto.PlugBox;
import eu.cz.fit.bitjv.tadeasvalenta.semestralka.konzument.dto.PlugType;
import eu.cz.fit.bitjv.tadeasvalenta.semestralka.rest.FlatClient;
import eu.cz.fit.bitjv.tadeasvalenta.semestralka.rest.OccupierClient;
import eu.cz.fit.bitjv.tadeasvalenta.semestralka.rest.PlugClient;
import java.util.List;

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
        PlugClient ps = new PlugClient();
        
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
        
       
        initData(os, fs, ps);
        
        Grid<Occupier> occupierGrid = new Grid<>();
        occupierGrid = initOccupierGrid(os.findAllOccupiers_JSON(OccupierBox.class).getOccupiers());
        
        Grid<Flat> flatGrid = new Grid<>();
        flatGrid = initFlatGrid(fs.findAllFlats_JSON(FlatBox.class).getFlats());
        
        Grid<Plug> plugGrid = new Grid<>();
        plugGrid = initPlugGrid(ps.findAllPlugs_JSON(PlugBox.class).getPlugs());
        
        
        layout.addComponent(flatGrid);
        layout.addComponent(occupierGrid);
        layout.addComponent(plugGrid);
        setContent(layout);
       
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
    
    public Grid<Occupier> initOccupierGrid(List<Occupier> occupiers){
        Grid<Occupier> grid = new Grid<>();
        grid.setSizeFull();
        grid.setHeightByRows(occupiers.size());
        grid.setItems(occupiers);
        grid.addColumn(Occupier::getId).setCaption("ID");
        grid.addColumn(Occupier::getName).setCaption("Name");
        grid.addColumn(Occupier::getEmail).setCaption("Email");
        grid.addColumn(Occupier::getPhone).setCaption("Phone");
        return grid;
    }
    
    public Grid<Flat> initFlatGrid(List<Flat> flats){
        Grid<Flat> grid = new Grid<>();    
        grid.setSizeFull();
        grid.setHeightByRows(flats.size());
        grid.setItems(flats);
        grid.addColumn(Flat::getId).setCaption("ID");
        grid.addColumn(Flat::getArea).setCaption("Area");
        grid.addColumn(Flat::getDoorNumber).setCaption("Door number");
        grid.addColumn(Flat::getRent).setCaption("Rent");
        return grid;
    }
    
    public Grid<Plug> initPlugGrid(List<Plug> plugs){
        Grid<Plug> grid = new Grid<>();    
        grid.setSizeFull();
        grid.setHeightByRows(plugs.size());
        grid.setItems(plugs);
        grid.addColumn(Plug::getId).setCaption("ID");
        grid.addColumn(Plug::getProvider).setCaption("Provider");
        grid.addColumn(Plug::getPayment).setCaption("Payment");
        grid.addColumn(Plug::getType).setCaption("Type of plug");
        return grid;
    }
    
    public void initData(OccupierClient oc, FlatClient fc, PlugClient pc){
        //delete all data
         OccupierBox ob = new OccupierBox();
        ob = oc.findAllOccupiers_JSON(OccupierBox.class);
        for (Occupier occup : ob.getOccupiers()){
            oc.remove( occup.getId().toString());
        }
        
        FlatBox fb = new FlatBox();
        fb = fc.findAllFlats_JSON(FlatBox.class);
        for (Flat flat : fb.getFlats()){
            fc.remove(flat.getId().toString());
        }
        
        PlugBox pb = new PlugBox();
        pb = pc.findAllPlugs_JSON(PlugBox.class);
        for (Plug plug : pb.getPlugs()){
            pc.remove(plug.getId().toString());
        }
        
        //input new data
        Occupier occup = new Occupier();
        occup.setName("Jan");
        occup.setEmail("jan@mail.cz");
        occup.setPhone("111222333");
        oc.create_JSON(occup);
        occup.setName("Tomáš");
        occup.setEmail("tomas@mail.cz");
        occup.setPhone("123123123");
        oc.create_JSON(occup);
        
        Flat flat = new Flat();
        flat.setArea(10l);
        flat.setDoorNumber(10l);
        flat.setRent(1000l);
        fc.create_JSON(flat);
        flat.setArea(20l);
        flat.setDoorNumber(20l);
        flat.setRent(2000l);
        fc.create_JSON(flat);
        
        Plug plug = new Plug();
        plug.setPayment(100l);
        plug.setProvider("UPC");
        plug.setType(PlugType.Internet);
        pc.create_JSON(plug);
        
        plug.setPayment(200l);
        plug.setProvider("ČEZ");
        plug.setType(PlugType.Electricity);
        pc.create_JSON(plug);
    }
}
