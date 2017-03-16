/*To DO List:
 * Fix the possibility of repeating planes
 * Possibly figure out how to add cool animations eg. cards flipping like in solitaire or something
 * Dynamically size the image as well?
 * Add planechase die roll
 * Add actions associated if planechase symbol is rolled?
 * figure out layout
 */
package planechaseapp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

/**
 *
 * @author Alex
 */
public class PlaneChaseApp {
    public static void main(String[] args) {
        //calls class to make the window and display it.
        window myWindow = new window();
        myWindow.setVisible(true);
    }
}


class window extends JFrame{
    
    private JLabel currentPlane;
    private JLabel currentDieSide;
    
    window(){
        setTitle("Alpha: 0.0.7");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width-1300)/2;
        int y = (screenSize.height-850)/2;
        setBounds(x,y,1300,850);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //layout stuff 
        setLayout(null);
        
        //<editor-fold desc="Starting Images" defaultstate="collapsed">
        //starting plane to be displayed
        JLabel plane = new JLabel(new ImageIcon(getClass().getResource("Back.jpg")));
        plane.setBounds(0,0,1032,720);
        add(plane);
        //stores the last displayed plane
        currentPlane = plane;
        
        //starting die side to be displayed
        JLabel dieSide = new JLabel(new ImageIcon(getClass().getResource("Blank_Roll.jpg")));
        dieSide.setBounds(1130,360,70,70);
        add(dieSide);
        //stores last displayed die side
        currentDieSide = dieSide;
        //</editor-fold>
        
        //<editor-fold desc="Array of Planes" defaultstate="collapsed">
         //array of planes
        final ImageIcon planes[] = new ImageIcon[]{
            new ImageIcon(getClass().getResource("Academy_at_Tolaria_West.jpg")),
            new ImageIcon(getClass().getResource("Agyrem.jpg")),
            new ImageIcon(getClass().getResource("Akoum.jpg")),
            new ImageIcon(getClass().getResource("Aretopolis.jpg")),
            new ImageIcon(getClass().getResource("Astral_Arena.jpg")),
            new ImageIcon(getClass().getResource("Bant.jpg")),
            new ImageIcon(getClass().getResource("Bloodhill_Bastion.jpg")),
            new ImageIcon(getClass().getResource("Celestine_Reef.jpg")),
            new ImageIcon(getClass().getResource("Chaotic_Æther.jpg")),//Phenomenon
            new ImageIcon(getClass().getResource("Cliffside_Market.jpg")),
            new ImageIcon(getClass().getResource("Edge_of_Malacol.jpg")),
            new ImageIcon(getClass().getResource("Eloren_Wilds.jpg")),
            new ImageIcon(getClass().getResource("Feeding_Grounds.jpg")),
            new ImageIcon(getClass().getResource("Fields_of_Summer.jpg")),
            new ImageIcon(getClass().getResource("Furnace_Layer.jpg")),
            new ImageIcon(getClass().getResource("Gavony.jpg")),
            new ImageIcon(getClass().getResource("Glen_Elendra.jpg")),
            new ImageIcon(getClass().getResource("Glimmervoid_Basin.jpg")),
            new ImageIcon(getClass().getResource("Goldmeadow.jpg")),
            new ImageIcon(getClass().getResource("Grand_Ossuary.jpg")),
            new ImageIcon(getClass().getResource("Grixis.jpg")),
            new ImageIcon(getClass().getResource("Grove_of_the_Dreampods.jpg")),
            new ImageIcon(getClass().getResource("Hedron_Fields_of_Agadeem.jpg")),
            new ImageIcon(getClass().getResource("Horizon_Boughs.jpg")),
            new ImageIcon(getClass().getResource("Immersturm.jpg")),
            new ImageIcon(getClass().getResource("Interplanar_Tunnel.jpg")),//Phenomenon
            new ImageIcon(getClass().getResource("Isle_of_Vesuva.jpg")),
            new ImageIcon(getClass().getResource("Izzet_Steam_Maze.jpg")),
            new ImageIcon(getClass().getResource("Jund.jpg")),
            new ImageIcon(getClass().getResource("Kessig.jpg")),
            new ImageIcon(getClass().getResource("Kharasha_Foothills.jpg")),
            new ImageIcon(getClass().getResource("Kilnspire_District.jpg")),
            new ImageIcon(getClass().getResource("Krosa.jpg")),
            new ImageIcon(getClass().getResource("Lair_of_the_Ashen_Idol.jpg")),
            new ImageIcon(getClass().getResource("Lethe_Lake.jpg")),
            new ImageIcon(getClass().getResource("Llanowar.jpg")),
            new ImageIcon(getClass().getResource("Minamo.jpg")),
            new ImageIcon(getClass().getResource("Mirrored_Depths.jpg")),
            new ImageIcon(getClass().getResource("Morphic_Tide.jpg")),//Phenomenon
            new ImageIcon(getClass().getResource("Mount_Keralia.jpg")),
            new ImageIcon(getClass().getResource("Murasa.jpg")),
            new ImageIcon(getClass().getResource("Mutual_Epiphany.jpg")),//Phenomenon
            new ImageIcon(getClass().getResource("Naar_Isle.jpg")),
            new ImageIcon(getClass().getResource("Naya.jpg")),
            new ImageIcon(getClass().getResource("Nephalia.jpg")),
            new ImageIcon(getClass().getResource("Norn's_Dominion.jpg")),
            new ImageIcon(getClass().getResource("Onakke_Catacomb.jpg")),
            new ImageIcon(getClass().getResource("Orochi_Colony.jpg")),
            new ImageIcon(getClass().getResource("Orzhova.jpg")),
            new ImageIcon(getClass().getResource("Otaria.jpg")),
            new ImageIcon(getClass().getResource("Panopticon.jpg")),
            new ImageIcon(getClass().getResource("Planewide_Disaster.jpg")),//Phenomenon
            new ImageIcon(getClass().getResource("Pools_of_Becoming.jpg")),
            new ImageIcon(getClass().getResource("Prahv.jpg")),
            new ImageIcon(getClass().getResource("Quicksilver_Sea.jpg")),
            new ImageIcon(getClass().getResource("Raven's_Run.jpg")),
            new ImageIcon(getClass().getResource("Reality_Shaping.jpg")),//Phenomenon
            new ImageIcon(getClass().getResource("Sanctum_of_Serra.jpg")),
            new ImageIcon(getClass().getResource("Sea_of_Sand.jpg")),
            new ImageIcon(getClass().getResource("Selesnya_Loft_Gardens.jpg")),
            new ImageIcon(getClass().getResource("Shiv.jpg")),
            new ImageIcon(getClass().getResource("Skybreen.jpg")),
            new ImageIcon(getClass().getResource("Sokenzan.jpg")),
            new ImageIcon(getClass().getResource("Spatial_Merging.jpg")),//Phenomenon
            new ImageIcon(getClass().getResource("Stairs_to_Infinity.jpg")),
            new ImageIcon(getClass().getResource("Stensia.jpg")),
            new ImageIcon(getClass().getResource("Stronghold_Furnace.jpg")),
            new ImageIcon(getClass().getResource("Takenuma.jpg")),
            new ImageIcon(getClass().getResource("Talon_Gates.jpg")),
            new ImageIcon(getClass().getResource("Tazeem.jpg")),
            new ImageIcon(getClass().getResource("Tember_City.jpg")),
            new ImageIcon(getClass().getResource("The_Æther_Flues.jpg")),
            new ImageIcon(getClass().getResource("The_Dark_Barony.jpg")),
            new ImageIcon(getClass().getResource("The_Eon_Fog.jpg")),
            new ImageIcon(getClass().getResource("The_Fourth_Sphere.jpg")),
            new ImageIcon(getClass().getResource("The_Great_Forest.jpg")),
            new ImageIcon(getClass().getResource("The_Hippodrome.jpg")),
            new ImageIcon(getClass().getResource("The_Maelstrom.jpg")),
            new ImageIcon(getClass().getResource("The_Zephyr_Maze.jpg")),
            new ImageIcon(getClass().getResource("Time_Distortion.jpg")),//Phenomenon
            new ImageIcon(getClass().getResource("Trail_of_the_Mage_Rings.jpg")),
            new ImageIcon(getClass().getResource("Truga_Jungle.jpg")),
            new ImageIcon(getClass().getResource("Turri_Island.jpg")),
            new ImageIcon(getClass().getResource("Undercity_Reaches.jpg")),
            new ImageIcon(getClass().getResource("Velis_Vel.jpg")),
            new ImageIcon(getClass().getResource("Windriddle_Palaces.jpg"))
        };
        //</editor-fold>
        
        //<editor-fold desc="Array of Die Sides" defaultstate="collapsed">
        //Array of die sides images
        final ImageIcon die[] = new ImageIcon[]{
            new ImageIcon(getClass().getResource("Chaos_Roll.jpg")),
            new ImageIcon(getClass().getResource("Plane_Roll.jpg")),
            new ImageIcon(getClass().getResource("Blank_Roll.jpg"))
        };
        //</editor-fold>
        
        //<editor-fold desc="Next Button" defaultstate="collapsed">
        //Next Button
        JButton nextButton = new JButton("Next");
        nextButton.setBounds(475,750,70,30);
        add(nextButton);
        nextButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                remove(currentPlane);
                currentPlane = planeGenerator(planes);
                add(currentPlane);
                revalidate();
                repaint();
            }
        });
        //</editor-fold>
        
        //<editor-fold desc="Roll Button" defaultstate="collapsed">
        //Roll Button
        JButton rollButton = new JButton("Roll");
        rollButton.setBounds(1130,500,70,30);
        add(rollButton);
        rollButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                remove(currentDieSide);
                currentDieSide = dieRoll(die);
                //used to compare if the rolled die is the planewalk symbol
                JLabel planeDieSide = new JLabel(die[1]);
                //causes the plane to change if the die roll is the image in die[1];
                if(currentDieSide.getIcon() == planeDieSide.getIcon()){
                    remove(currentPlane);
                    currentPlane = planeGenerator(planes);
                    add(currentPlane);
                    revalidate();
                    repaint();
                }
                add(currentDieSide);
                revalidate();
                repaint();
            }
        });
        //</editor-fold>
    }
    
    JLabel dieRoll(ImageIcon images[]) {
        int number;
        JLabel image;
        
        //Randomly assign a number
        Random generator = new Random();
        number = generator.nextInt(5);
        
        //logic to prevent the number generated being outside of the array
        if(number > 2){
            number = 2;
        }
        
        //Pick from the array the image to return
        image = new JLabel(images[number]);
        image.setBounds(1130,360,70,70);
        return image;
    }
    
    JLabel planeGenerator(ImageIcon images[]){
        int number;
        JLabel image;
        
        //Randomly assisgn a number
        Random generator = new Random();
        number = generator.nextInt(images.length);
        
        //Pick from the array the image to return
        image = new JLabel(images[number]);
        image.setBounds(0,0,1032,720);
        return image;
    }
}
