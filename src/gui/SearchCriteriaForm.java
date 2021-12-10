package gui;

import Database.PropertyDatabaseController;
import Database.RegisteredRenter;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class SearchCriteriaForm implements ActionListener {

  JFrame frame = new JFrame();
  // all components on title panel
  private static JPanel titlePanel;
  private static JLabel titleLabel;

  // house type
  private static JPanel htPanel;
  private static JLabel htLabel;
  private String[] types = {"Apartment", "Bungalow", "Condo", "Duplex",
                            "Townhouse"};
  private static JCheckBox apartmentCheckBox;
  private static JCheckBox duplexCheckBox;
  private static JCheckBox townhouseCheckBox;
  private static JCheckBox bungalowCheckBox;
  private static JCheckBox condoCheckBox;

  // all components on bath panel
  private static JPanel bathPanel;
  private static JLabel bathLabel;
  private static JComboBox minBathComboBox;
  private static JComboBox maxBathComboBox;

  // all components on bed panel
  private static JPanel bedPanel;
  private static JLabel bedLabel;
  private static JComboBox minBedComboBox;
  private static JComboBox maxBedComboBox;

  // all components on furnish panel
  private static JPanel furnishPanel;
  private static JLabel furnishLabel;
  private static JCheckBox furnishedCheckBox;
  private static JCheckBox unfurnishedCheckBox;
  private String[] furnish = {"Furnished", "Unfurnished"};

  // all components on quadrant panel
  private static JPanel quadrantPanel;
  private static JLabel quadrantLabel;
  private static JCheckBox neCheckBox;
  private static JCheckBox nwCheckBox;
  private static JCheckBox seCheckBox;
  private static JCheckBox swCheckBox;
  private String[] quadrants = {"NE", "NW", "SE", "SW"};

  // all components on price panel
  private static JPanel pricePanel;
  private static JLabel priceLabel;
  private static JComboBox minPriceComboBox;
  private static JComboBox maxPriceComboBox;
  private String[] minPriceRange = {"MIN",     "200.00",  "400.00",  "600.00",
                                    "800.00",  "1000.00", "1400.00", "1800.00",
                                    "2200.00", "2600.00"};
  private String[] maxPriceRange = {"200.00",  "800.00",  "1400.00",
                                    "2000.00", "2600.00", "2600.00+"};

  // SERACH BUTTON FOR REGULAR RENTER
  private static JButton searchButton;

  // SEARCH BUTTON FOR REGISTERED RENTER
  private static JButton searchButton2;

  // comboBox for min and max bath/bed
  private String[] min = {"MIN", "1", "2", "3", "4",  "5",
                          "6",   "7", "8", "9", "10", "11"};
  private String[] max = {"1", "2", "3", "4",  "5",  "6",
                          "7", "8", "9", "10", "11+"};

  private static int renterID;

  SearchCriteriaForm() {

    // ********* TITLE *********
    titlePanel = new JPanel();           // create title panel
    titlePanel.setBounds(0, 0, 700, 50); // setting bounds for panel
    titleLabel = new JLabel(
        "Please Fill in Search Criteria"); // creating the label for the title
    titleLabel.setFont(
        new Font("Helvetica Neue", Font.BOLD, 20)); // setting font for title
    titlePanel.add(titleLabel); // add the label to the panel

    // ******** HOUSE TYPE *********
    htPanel = new JPanel(); //
    htPanel.setBounds(0, 50, 700, 50);
    htLabel = new JLabel("House Type: ");
    htLabel.setFont(new Font("Courier", Font.PLAIN, 20));
    htPanel.add(htLabel);

    // check box for house type( num of boxs for num of types)
    // making checkbox for apartment
    apartmentCheckBox = new JCheckBox();    // creating checkbox
    apartmentCheckBox.setText("Apartment"); // name of checkbox
    apartmentCheckBox.addActionListener(
        this); // give checkbox ability to make an action
    htPanel.add(
        apartmentCheckBox); // add the checkbox to the panel for housetypes

    // same steps for all checkboxes
    // making checkbox for bungalow
    bungalowCheckBox = new JCheckBox();
    bungalowCheckBox.setText("Bungalow");
    bungalowCheckBox.addActionListener(this);
    htPanel.add(bungalowCheckBox);

    // making checkbox for condo
    condoCheckBox = new JCheckBox();
    condoCheckBox.setText("Condo");
    condoCheckBox.addActionListener(this);
    htPanel.add(condoCheckBox);

    // making checkbox for duplex
    duplexCheckBox = new JCheckBox();
    duplexCheckBox.setText("Duplex");
    duplexCheckBox.addActionListener(this);
    htPanel.add(duplexCheckBox);

    // making checkbox for townhouse
    townhouseCheckBox = new JCheckBox();
    townhouseCheckBox.setText("Townhouse");
    townhouseCheckBox.addActionListener(this);
    htPanel.add(townhouseCheckBox);
    htPanel.add(Box.createHorizontalStrut(30)); // add a gap on the panel

    // ******** BATHROOM **********

    bathPanel = new JPanel();
    // bathPanel.setBackground(Color.magenta);
    bathPanel.setBounds(0, 100, 700, 50);
    bathLabel = new JLabel("Number of Bathrooms: ");
    bathLabel.setFont(new Font("Courier", Font.PLAIN, 20));
    bathPanel.add(bathLabel);

    // comboBox for min for
    minBathComboBox = new JComboBox(min);
    bathPanel.add(minBathComboBox);
    bathPanel.add(Box.createHorizontalStrut(20)); // add a gap on the panel

    // comboBox for max
    maxBathComboBox = new JComboBox(max);
    maxBathComboBox.setSelectedIndex(10);
    minBathComboBox.addActionListener(this);
    bathPanel.add(maxBathComboBox);
    bathPanel.add(Box.createHorizontalStrut(190)); // add a gap on the panel

    // ******** BEDROOM **********
    // panel for bed
    bedPanel = new JPanel();
    bedPanel.setBounds(0, 150, 700, 50);
    // label for bed
    bedLabel = new JLabel("Number of Bedrooms: ");
    bedLabel.setFont(new Font("Courier", Font.PLAIN, 20));
    bedPanel.add(bedLabel);

    // bed comboBox for min for
    minBedComboBox = new JComboBox(min);
    minBedComboBox.addActionListener(this);
    bedPanel.add(minBedComboBox);
    bedPanel.add(Box.createHorizontalStrut(20)); // add a gap on the panel

    // bed comboBox for max
    maxBedComboBox = new JComboBox(max);
    maxBedComboBox.setSelectedIndex(10);
    maxBedComboBox.addActionListener(this);
    bedPanel.add(maxBedComboBox);
    bedPanel.add(Box.createHorizontalStrut(200)); // add a gap on the panel

    // ******** FURNISH **********
    furnishPanel = new JPanel();
    furnishPanel.setBounds(0, 200, 700, 50);
    // label for furnish
    furnishLabel = new JLabel("Furnish State: ");
    furnishLabel.setFont(new Font("Courier", Font.PLAIN, 20));
    furnishPanel.add(furnishLabel);

    // creating furnished checkbox
    furnishedCheckBox = new JCheckBox();
    furnishedCheckBox.setText("Furnished");
    furnishPanel.add(furnishedCheckBox);
    furnishedCheckBox.addActionListener(this);

    furnishPanel.add(Box.createHorizontalStrut(30)); // add a gap on the panel

    // creating unfurnished checkbox
    unfurnishedCheckBox = new JCheckBox();
    unfurnishedCheckBox.setText("Unfurnished");
    furnishPanel.add(unfurnishedCheckBox);
    unfurnishedCheckBox.addActionListener(this);

    furnishPanel.add(Box.createHorizontalStrut(190)); // add a gap on the panel

    // ******** QUADRANT **********
    quadrantPanel = new JPanel();
    quadrantPanel.setBounds(0, 250, 700, 50);
    quadrantLabel = new JLabel("City Quadrant: ");
    quadrantLabel.setFont(new Font("Courier", Font.PLAIN, 20));
    quadrantPanel.add(quadrantLabel);

    // checkbox for ne
    neCheckBox = new JCheckBox();
    neCheckBox.setText("NE");
    quadrantPanel.add(neCheckBox);
    neCheckBox.addActionListener(this);
    quadrantPanel.add(Box.createHorizontalStrut(10)); // add a gap on the panel

    // checkbox for nw
    nwCheckBox = new JCheckBox();
    nwCheckBox.setText("NW");
    quadrantPanel.add(nwCheckBox);
    nwCheckBox.addActionListener(this);
    quadrantPanel.add(Box.createHorizontalStrut(10)); // add a gap on the panel

    // checkbox for se
    seCheckBox = new JCheckBox();
    seCheckBox.setText("SE");
    quadrantPanel.add(seCheckBox);
    seCheckBox.addActionListener(this);
    quadrantPanel.add(Box.createHorizontalStrut(10)); // add a gap on the panel

    // checkbox for sw
    swCheckBox = new JCheckBox();
    swCheckBox.setText("SW");
    quadrantPanel.add(swCheckBox);
    swCheckBox.addActionListener(this);
    quadrantPanel.add(Box.createHorizontalStrut(170)); // add a gap on the panel

    // ******** PRICE **********
    pricePanel = new JPanel();
    pricePanel.setBounds(0, 300, 700, 50);
    priceLabel = new JLabel("Rent Price per Month($CAD): ");
    priceLabel.setFont(new Font("Courier", Font.PLAIN, 20));
    pricePanel.add(priceLabel);

    // minimum combobox for price
    minPriceComboBox =
        new JComboBox(minPriceRange); // takes in minpricerange array
    minPriceComboBox.addActionListener(this);
    pricePanel.add(minPriceComboBox);
    pricePanel.add(Box.createHorizontalStrut(20)); // add a gap on the panel

    // max combobox for price
    maxPriceComboBox =
        new JComboBox(maxPriceRange); // takes in maxpricerange array
    maxPriceComboBox.setSelectedIndex(5);
    maxPriceComboBox.addActionListener(this);
    pricePanel.add(maxPriceComboBox);
    pricePanel.add(Box.createHorizontalStrut(20)); // add a gap on the panel

    // ********* SUBMIT BUTTON ***********

    // button for search
    searchButton = new JButton("Search");
    searchButton.setFont(new Font("Dialog", Font.BOLD, 30));
    searchButton.setBounds(350, 400, 150, 50);
    searchButton.setFocusable(false);
    searchButton.addActionListener(this);
    frame.add(searchButton);

    // frame for search criteria form
    frame.add(titlePanel);
    frame.add(htPanel);
    frame.add(bathPanel);
    frame.add(bedPanel);
    frame.add(furnishPanel);
    frame.add(quadrantPanel);
    frame.add(pricePanel);
    frame.setDefaultCloseOperation(
        JFrame.DISPOSE_ON_CLOSE); // exiting window will close window
    frame.setSize(700, 600);      // setting size of window
    frame.setLayout(null);
    frame.setTitle("Rental Property Management System");

    frame.setVisible(true);
  }

  SearchCriteriaForm(int id) {
    renterID = id;

    // ********* TITLE *********
    titlePanel = new JPanel();           // create title panel
    titlePanel.setBounds(0, 0, 700, 50); // setting bounds for panel
    titleLabel = new JLabel(
        "Please Fill in Search Criteria"); // creating the label for the title
    titleLabel.setFont(
        new Font("Helvetica Neue", Font.BOLD, 20)); // setting font for title
    titlePanel.add(titleLabel); // add the label to the panel

    // ******** HOUSE TYPE *********
    htPanel = new JPanel(); //
    htPanel.setBounds(0, 50, 700, 50);
    htLabel = new JLabel("House Type: ");
    htLabel.setFont(new Font("Courier", Font.PLAIN, 20));
    htPanel.add(htLabel);

    // check box for house type( num of boxs for num of types)
    // making checkbox for apartment
    apartmentCheckBox = new JCheckBox();    // creating checkbox
    apartmentCheckBox.setText("Apartment"); // name of checkbox
    apartmentCheckBox.addActionListener(
        this); // give checkbox ability to make an action
    htPanel.add(
        apartmentCheckBox); // add the checkbox to the panel for housetypes

    // same steps for all checkboxes
    // making checkbox for bungalow
    bungalowCheckBox = new JCheckBox();
    bungalowCheckBox.setText("Bungalow");
    bungalowCheckBox.addActionListener(this);
    htPanel.add(bungalowCheckBox);

    // making checkbox for condo
    condoCheckBox = new JCheckBox();
    condoCheckBox.setText("Condo");
    condoCheckBox.addActionListener(this);
    htPanel.add(condoCheckBox);

    // making checkbox for duplex
    duplexCheckBox = new JCheckBox();
    duplexCheckBox.setText("Duplex");
    duplexCheckBox.addActionListener(this);
    htPanel.add(duplexCheckBox);

    // making checkbox for townhouse
    townhouseCheckBox = new JCheckBox();
    townhouseCheckBox.setText("Townhouse");
    townhouseCheckBox.addActionListener(this);
    htPanel.add(townhouseCheckBox);
    htPanel.add(Box.createHorizontalStrut(30)); // add a gap on the panel

    // ******** BATHROOM **********

    bathPanel = new JPanel();
    // bathPanel.setBackground(Color.magenta);
    bathPanel.setBounds(0, 100, 700, 50);
    bathLabel = new JLabel("Number of Bathrooms: ");
    bathLabel.setFont(new Font("Courier", Font.PLAIN, 20));
    bathPanel.add(bathLabel);

    // comboBox for min for
    minBathComboBox = new JComboBox(min);
    bathPanel.add(minBathComboBox);
    bathPanel.add(Box.createHorizontalStrut(20)); // add a gap on the panel

    // comboBox for max
    maxBathComboBox = new JComboBox(max);
    maxBathComboBox.setSelectedIndex(10);
    minBathComboBox.addActionListener(this);
    bathPanel.add(maxBathComboBox);
    bathPanel.add(Box.createHorizontalStrut(190)); // add a gap on the panel

    // ******** BEDROOM **********
    // panel for bed
    bedPanel = new JPanel();
    bedPanel.setBounds(0, 150, 700, 50);
    // label for bed
    bedLabel = new JLabel("Number of Bedrooms: ");
    bedLabel.setFont(new Font("Courier", Font.PLAIN, 20));
    bedPanel.add(bedLabel);

    // bed comboBox for min for
    minBedComboBox = new JComboBox(min);
    minBedComboBox.addActionListener(this);
    bedPanel.add(minBedComboBox);
    bedPanel.add(Box.createHorizontalStrut(20)); // add a gap on the panel

    // bed comboBox for max
    maxBedComboBox = new JComboBox(max);
    maxBedComboBox.setSelectedIndex(10);
    maxBedComboBox.addActionListener(this);
    bedPanel.add(maxBedComboBox);
    bedPanel.add(Box.createHorizontalStrut(200)); // add a gap on the panel

    // ******** FURNISH **********
    furnishPanel = new JPanel();
    furnishPanel.setBounds(0, 200, 700, 50);
    // label for furnish
    furnishLabel = new JLabel("Furnish State: ");
    furnishLabel.setFont(new Font("Courier", Font.PLAIN, 20));
    furnishPanel.add(furnishLabel);

    // creating furnished checkbox
    furnishedCheckBox = new JCheckBox();
    furnishedCheckBox.setText("Furnished");
    furnishPanel.add(furnishedCheckBox);
    furnishedCheckBox.addActionListener(this);

    furnishPanel.add(Box.createHorizontalStrut(30)); // add a gap on the panel

    // creating unfurnished checkbox
    unfurnishedCheckBox = new JCheckBox();
    unfurnishedCheckBox.setText("Unfurnished");
    furnishPanel.add(unfurnishedCheckBox);
    unfurnishedCheckBox.addActionListener(this);

    furnishPanel.add(Box.createHorizontalStrut(190)); // add a gap on the panel

    // ******** QUADRANT **********
    quadrantPanel = new JPanel();
    quadrantPanel.setBounds(0, 250, 700, 50);
    quadrantLabel = new JLabel("City Quadrant: ");
    quadrantLabel.setFont(new Font("Courier", Font.PLAIN, 20));
    quadrantPanel.add(quadrantLabel);

    // checkbox for ne
    neCheckBox = new JCheckBox();
    neCheckBox.setText("NE");
    quadrantPanel.add(neCheckBox);
    neCheckBox.addActionListener(this);
    quadrantPanel.add(Box.createHorizontalStrut(10)); // add a gap on the panel

    // checkbox for nw
    nwCheckBox = new JCheckBox();
    nwCheckBox.setText("NW");
    quadrantPanel.add(nwCheckBox);
    nwCheckBox.addActionListener(this);
    quadrantPanel.add(Box.createHorizontalStrut(10)); // add a gap on the panel

    // checkbox for se
    seCheckBox = new JCheckBox();
    seCheckBox.setText("SE");
    quadrantPanel.add(seCheckBox);
    seCheckBox.addActionListener(this);
    quadrantPanel.add(Box.createHorizontalStrut(10)); // add a gap on the panel

    // checkbox for sw
    swCheckBox = new JCheckBox();
    swCheckBox.setText("SW");
    quadrantPanel.add(swCheckBox);
    swCheckBox.addActionListener(this);
    quadrantPanel.add(Box.createHorizontalStrut(170)); // add a gap on the panel

    // ******** PRICE **********
    pricePanel = new JPanel();
    pricePanel.setBounds(0, 300, 700, 50);
    priceLabel = new JLabel("Rent Price per Month($CAD): ");
    priceLabel.setFont(new Font("Courier", Font.PLAIN, 20));
    pricePanel.add(priceLabel);

    // minimum combobox for price
    minPriceComboBox =
        new JComboBox(minPriceRange); // takes in minpricerange array
    minPriceComboBox.addActionListener(this);
    pricePanel.add(minPriceComboBox);
    pricePanel.add(Box.createHorizontalStrut(20)); // add a gap on the panel

    // max combobox for price
    maxPriceComboBox =
        new JComboBox(maxPriceRange); // takes in maxpricerange array
    maxPriceComboBox.setSelectedIndex(5);
    maxPriceComboBox.addActionListener(this);
    pricePanel.add(maxPriceComboBox);
    pricePanel.add(Box.createHorizontalStrut(20)); // add a gap on the panel

    // ********* SUBMIT BUTTON ***********
    // button for search
    searchButton2 = new JButton("Search");
    searchButton2.setFont(new Font("Dialog", Font.BOLD, 30));
    searchButton2.setBounds(350, 400, 150, 50);
    searchButton2.setFocusable(false);
    searchButton2.addActionListener(this);
    frame.add(searchButton2);

    // PREVIOUS SEARCH BUTTON GOES HERE

    // frame for search criteria form
    frame.add(titlePanel);
    frame.add(htPanel);
    frame.add(bathPanel);
    frame.add(bedPanel);
    frame.add(furnishPanel);
    frame.add(quadrantPanel);
    frame.add(pricePanel);
    frame.setDefaultCloseOperation(
        JFrame.EXIT_ON_CLOSE); // exiting window will close window
    frame.setSize(700, 600);   // setting size of window
    frame.setLayout(null);
    frame.setTitle("Rental Property Management System");

    frame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // min and max array for local
    // -1 represents 11+
    int[] localmin = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    int[] localmax = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -1};

    double[] localMinRange = {0,       200.00,  400.00,  600.00,  800.00,
                              1000.00, 1400.00, 1800.00, 2200.00, 2600.00};
    double[] localMaxRange = {200.00,  800.00,  1400.00,
                              2000.00, 2600.00, -1}; // -1 reprensents 2600.00+

    // getting index of selected combobox
    int minBathIndex = minBathComboBox.getSelectedIndex();
    int maxBathIndex = maxBathComboBox.getSelectedIndex();
    int minBedIndex = minBedComboBox.getSelectedIndex();
    int maxBedIndex = maxBedComboBox.getSelectedIndex();
    int minPriceIndex = minPriceComboBox.getSelectedIndex();
    int maxPriceIndex = maxPriceComboBox.getSelectedIndex();

    // using index that user picked and coordinate with local min and max to
    // pass through to display property
    int minBath = localmin[minBathIndex];
    int maxBath = localmax[maxBathIndex];
    int minBed = localmin[minBedIndex];
    int maxBed = localmax[maxBedIndex];
    double minPrice = localMinRange[minPriceIndex];
    double maxPrice = localMaxRange[maxPriceIndex];
    // boolean array to store which checkboxes were selected
    boolean[] htBool = {apartmentCheckBox.isSelected(),
                        bungalowCheckBox.isSelected(),
                        condoCheckBox.isSelected(), duplexCheckBox.isSelected(),
                        townhouseCheckBox.isSelected()};
    boolean[] furBool = {furnishedCheckBox.isSelected(),
                         unfurnishedCheckBox.isSelected()};
    boolean[] quadBool = {neCheckBox.isSelected(), nwCheckBox.isSelected(),
                          seCheckBox.isSelected(), swCheckBox.isSelected()};

    if (e.getSource() == searchButton) {
      // method to get selected ht and put into string array
      String[] possible = new String[htBool.length];
      int counter = 0; // will keep track of how big array is suppose to be
      // loop that goes through housetype bool array
      for (int i = 0; i < htBool.length; i++) {

        // if element is true (selected)
        if (htBool[i]) {
          possible[counter] =
              types[i]; // set possible to the house type that was selected
          counter++;
        }
      }
      // make a string[] that is the actual house type selected
      String[] htSelected =
          new String[counter]; // length will be how many times bool showed true
      for (int i = 0; i < htSelected.length; i++) {
        htSelected[i] = possible[i]; // selected will take from the possible
      }

      // clear array for furniture
      possible = new String[furBool.length];
      counter = 0;
      // loop that goes through furniture bool array
      for (int i = 0; i < furBool.length; i++) {
        // if element is true (selected)
        if (furBool[i]) {
          possible[counter] = furnish[i]; // set possible to the furniture state
                                          // that was selected
          counter++;
        }
      }
      // make a string[] that is the actual furniture state selected
      String[] furSelected =
          new String[counter]; // length will be how many times bool showed true
      for (int i = 0; i < furSelected.length; i++) {
        furSelected[i] = possible[i]; // selected will take from the possible
      }

      // clear array for quadrant
      possible = new String[quadBool.length];
      counter = 0;
      // loop that goes through quadrant bool array
      for (int i = 0; i < quadBool.length; i++) {
        // if element is true (selected)
        if (quadBool[i]) {
          possible[counter] =
              quadrants[i]; // set possible to the furniture state
          // that was selected

          counter++;
        }
      }
      // make a string[] that is the actual quadrants selected
      String[] quadSelected = new String[counter];
      for (int i = 0; i < quadSelected.length; i++) {
        quadSelected[i] = possible[i]; // selected will take from the possible
      }

      PropertyDatabaseController propertyDB = new PropertyDatabaseController();

      PropertiesDisplayForm list =
          new PropertiesDisplayForm(propertyDB.performSearch(
              htSelected, minBath, maxBath, minBed, maxBed, furSelected,
              quadSelected, minPrice, maxPrice));
    }

    else if (e.getSource() == searchButton2) {
      // method to get selected ht and put into string array
      String[] possible = new String[htBool.length];
      int counter = 0; // will keep track of how big array is suppose to be
      // loop that goes through housetype bool array
      for (int i = 0; i < htBool.length; i++) {

        // if element is true (selected)
        if (htBool[i]) {
          possible[counter] =
              types[i]; // set possible to the house type that was selected
          counter++;
        }
      }
      // make a string[] that is the actual house type selected
      String[] htSelected =
          new String[counter]; // length will be how many times bool showed true
      for (int i = 0; i < htSelected.length; i++) {
        htSelected[i] = possible[i]; // selected will take from the possible
      }

      // clear array for furniture
      possible = new String[furBool.length];
      counter = 0;
      // loop that goes through furniture bool array
      for (int i = 0; i < furBool.length; i++) {
        // if element is true (selected)
        if (furBool[i]) {
          possible[counter] = furnish[i]; // set possible to the furniture state
                                          // that was selected
          counter++;
        }
      }
      // make a string[] that is the actual furniture state selected
      String[] furSelected =
          new String[counter]; // length will be how many times bool showed true
      for (int i = 0; i < furSelected.length; i++) {
        furSelected[i] = possible[i]; // selected will take from the possible
      }

      // clear array for quadrant
      possible = new String[quadBool.length];
      counter = 0;
      // loop that goes through quadrant bool array
      for (int i = 0; i < quadBool.length; i++) {
        // if element is true (selected)
        if (quadBool[i]) {
          possible[counter] =
              quadrants[i]; // set possible to the furniture state
          // that was selected

          counter++;
        }
      }
      // make a string[] that is the actual quadrants selected
      String[] quadSelected = new String[counter];
      for (int i = 0; i < quadSelected.length; i++) {
        quadSelected[i] = possible[i]; // selected will take from the possible
      }

      RegisteredRenter renterSearch;
      renterSearch = new RegisteredRenter(renterID);
      renterSearch.saveCriteria(htSelected, minBath, maxBath, minBed, maxBed,
                                furSelected, quadSelected, minPrice, maxPrice);
      PropertiesDisplayForm list =
          new PropertiesDisplayForm(renterSearch.performSearch());
    }
  }
}
