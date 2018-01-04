package eu.cz.fit.bitjv.tadeasvalenta.semestralka.konzument;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
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
import java.util.ArrayList;
import java.util.EnumSet;
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
    private Grid<Flat> flatGrid = new Grid<>();
    private Grid<Occupier> occupierGrid = new Grid<>();        
    private Grid<Plug> plugGrid = new Grid<>();
    
    
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        HorizontalLayout formBox = new HorizontalLayout();
        
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
        
       
       // initData(os, fs, ps);
        
        occupierGrid = initOccupierGrid(os.findAllOccupiers_JSON(OccupierBox.class).getOccupiers());
        flatGrid = initFlatGrid(fs.findAllFlats_JSON(FlatBox.class).getFlats());
        plugGrid = initPlugGrid(ps.findAllPlugs_JSON(PlugBox.class).getPlugs());
               
        
        FormLayout flatForm = new FormLayout();
       
        TextField ff1 = new TextField("ID");
        //ff1.setEnabled(false);
        TextField ff2 = new TextField("Area");
        TextField ff3 = new TextField("Rent");
        TextField ff4 = new TextField("Door number");
        Button fb1 = new Button("Edit");
        Button fb2 = new Button("Delete");
        Button fb3 = new Button("Create");
        fb1.addClickListener(e -> {
            Flat flat = new Flat();
            flat.setId(Long.parseLong(ff1.getValue()));
            flat.setArea(Long.parseLong(ff2.getValue()));
            flat.setRent(Long.parseLong(ff3.getValue()));
            flat.setDoorNumber(Long.parseLong(ff4.getValue()));
            fs.edit_JSON(flat, ff1.getValue());
            FlatBox tmp = fs.findAllFlats_JSON(FlatBox.class);
            flatGrid.setItems(tmp.getFlats());
            flatForm.setVisible(false);
        });
        fb2.addClickListener(e -> {
            fs.remove(ff1.getValue());
            FlatBox tmp = fs.findAllFlats_JSON(FlatBox.class);
            if (!tmp.getFlats().isEmpty()) flatGrid.setHeightByRows(tmp.getFlats().size());
            flatGrid.setItems(tmp.getFlats());
            flatForm.setVisible(false);
         });
        fb3.addClickListener(e -> {
            Flat flat = new Flat();
            flat.setId(Long.parseLong(ff1.getValue()));
            flat.setArea(Long.parseLong(ff2.getValue()));
            flat.setRent(Long.parseLong(ff3.getValue()));
            flat.setDoorNumber(Long.parseLong(ff4.getValue()));
            fs.create_JSON(flat);
            FlatBox tmp = fs.findAllFlats_JSON(FlatBox.class);
            flatGrid.setHeightByRows(tmp.getFlats().size());
            flatGrid.setItems(tmp.getFlats());
            flatForm.setVisible(false);
        });
        flatForm.addComponents(ff1, ff2, ff3, ff4, new HorizontalLayout(fb1, fb2, fb3));
                
        
        FormLayout occupierForm = new FormLayout();
        
        TextField of1 = new TextField("ID");
        //of1.setEnabled(false);
        TextField of2 = new TextField("Name");
        TextField of3 = new TextField("E-mail");
        TextField of4 = new TextField("Phone");
        Button ob1 = new Button("Edit");
        Button ob2 = new Button("Delete");
        Button ob3 = new Button("Create");
        ob1.addClickListener(e -> {
            Occupier occupier = new Occupier();
            occupier.setId(Long.parseLong(of1.getValue()));
            occupier.setName(of2.getValue());
            occupier.setEmail(of3.getValue());
            occupier.setPhone(of4.getValue());
            os.edit_JSON(occupier, of1.getValue());
            OccupierBox tmp = os.findAllOccupiers_JSON(OccupierBox.class);
            occupierGrid.setItems(tmp.getOccupiers());
            occupierForm.setVisible(false);
        });
        ob2.addClickListener(e -> {
            os.remove(of1.getValue());
            OccupierBox tmp = os.findAllOccupiers_JSON(OccupierBox.class);
            if (!tmp.getOccupiers().isEmpty()) occupierGrid.setHeightByRows(tmp.getOccupiers().size());
            occupierGrid.setItems(tmp.getOccupiers());
            occupierForm.setVisible(false);
        });
        ob3.addClickListener(e -> {
            Occupier occupier = new Occupier();
            occupier.setId(Long.parseLong(of1.getValue()));
            occupier.setName(of2.getValue());
            occupier.setEmail(of3.getValue());
            occupier.setPhone(of4.getValue());
            os.create_JSON(occupier);
            OccupierBox tmp = os.findAllOccupiers_JSON(OccupierBox.class);
            occupierGrid.setHeightByRows(tmp.getOccupiers().size());
            occupierGrid.setItems(tmp.getOccupiers());
            occupierForm.setVisible(false);
        });
        occupierForm.addComponents(of1,of2,of3,of4, new HorizontalLayout(ob1,ob2,ob3));
        
        List<PlugType> plugTypes = new ArrayList<>(EnumSet.allOf(PlugType.class));  
        FormLayout plugForm = new FormLayout();
        
        TextField pf1 = new TextField("ID");
        //pf1.setEnabled(false);
        TextField pf2 = new TextField("Provider");
        TextField pf3 = new TextField("Payment");
        //TextField pf4 = new TextField("Plug type");
        ComboBox pf4 = new ComboBox<>("Select plug type");
        pf4.setEmptySelectionAllowed(false);
        pf4.setItems(plugTypes);
        Button pb1 = new Button("Edit");
        Button pb2 = new Button("Delete");
        Button pb3 = new Button("Create");
        pb1.addClickListener(e -> {
            Plug plug = new Plug();
            plug.setId(Long.parseLong(pf1.getValue()));
            plug.setProvider(pf2.getValue());
            plug.setPayment(Long.parseLong(pf3.getValue()));
            plug.setType((PlugType) pf4.getValue());
            ps.edit_JSON(plug, pf1.getValue());
            PlugBox tmp = ps.findAllPlugs_JSON(PlugBox.class);
            plugGrid.setItems(tmp.getPlugs());
            plugForm.setVisible(false);
        });
        pb2.addClickListener(e -> {
            ps.remove(pf1.getValue());
            PlugBox tmp = ps.findAllPlugs_JSON(PlugBox.class);
            if (!tmp.getPlugs().isEmpty()) plugGrid.setHeightByRows(tmp.getPlugs().size());
            plugGrid.setItems(tmp.getPlugs());
            plugForm.setVisible(false);
        });
        pb3.addClickListener(e -> {
            Plug plug = new Plug();
            plug.setId(Long.parseLong(pf1.getValue()));
            plug.setProvider(pf2.getValue());
            plug.setPayment(Long.parseLong(pf3.getValue()));
            plug.setType((PlugType) pf4.getValue());
            ps.create_JSON(plug);
            PlugBox tmp = ps.findAllPlugs_JSON(PlugBox.class);
            plugGrid.setItems(tmp.getPlugs());
            plugGrid.setHeightByRows(tmp.getPlugs().size());
            plugForm.setVisible(false);
        });
        
        
        
        plugForm.addComponents(pf1, pf2, pf3, pf4, new HorizontalLayout(pb1, pb2, pb3));
        
        formBox.addComponent(flatForm);
        formBox.addComponent(occupierForm);
        formBox.addComponent(plugForm);
        layout.addComponent(flatGrid);
        layout.addComponent(occupierGrid);
        layout.addComponent(plugGrid);
        layout.addComponent(formBox);
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
