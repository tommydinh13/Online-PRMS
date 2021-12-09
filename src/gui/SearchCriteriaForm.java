package gui;

import Database.PropertyDatabaseController;
import Database.RegisteredRenter;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;


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
  private String[] minPriceRange = {"MIN",   "200.00", "400.00",
                                    "600.00", "800.00", "1000.00", "1400.00", "1800.00", "2200.00", "2600.00"};
  private String[] maxPriceRange = {"200.00", "800.00", "1400.00", "2000.00",
                                    "2600.00", "2600.00+"};

  // SERACH BUTTON FOR REGULAR RENTER
  private static JButton searchButton;

  // SEARCH BUTTON FOR REGISTERED RENTER
  private static JButton searchButton2;

  // comboBox for min and max bath/bed
  private String[] min = {"MIN","1" ,"2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
  private String[] max = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11+"};

  private static int renterID;

  SearchCriteriaForm() {

    // ********* TITLE *********
    titlePanel = new JPanel();  // create title panel
    titlePanel.setBounds(0, 0, 700, 50);  // setting bounds for panel
    titleLabel = new JLabel("Please Fill in Search Criteria");  // creating the label for the title
    titleLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 20));  // setting font for title
    titlePanel.add(titleLabel); // add the label to the panel

    // ******** HOUSE TYPE *********
    htPanel = new JPanel(); //
    htPanel.setBounds(0, 50, 700, 50);
    htLabel = new JLabel("House Type: ");
    htLabel.setFont(new Font("Courier", Font.PLAIN, 20));
    htPanel.add(htLabel);

    // check box for house type( num of boxs for num of types)
    // making checkbox for apartment
    apartmentCheckBox = new JCheckBox();  // creating checkbox
    apartmentCheckBox.setText("Apartment"); // name of checkbox
    apartmentCheckBox.addActionListener(this); // give checkbox ability to make an action
    htPanel.add(apartmentCheckBox); // add the checkbox to the panel for housetypes

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
    htPanel.add(Box.createHorizontalStrut(30));

    // ******** BATHROOM **********

    bathPanel = new JPanel();
    // bathPanel.setBackground(Color.magenta);
    bathPanel.setBounds(0, 100, 700, 50);
    bathLabel = new JLabel("Number of Bathrooms: ");
    bathLabel.setFont(new Font("Courier", Font.PLAIN, 20));
    bathPanel.add(bathLabel);

    // comboBox for min for
    minBathComboBox = new JComboBox(min);
    // minBathComboBox.setRenderer(new MyComboBoxRenderer("MIN"));
    // minBathComboBox.setSelectedIndex(-1);
    // minBathComboBox.setPrototypeDisplayValue("MIN");
    // minBathComboBox.addActionListener(this);
    bathPanel.add(minBathComboBox);
    bathPanel.add(Box.createHorizontalStrut(20));

    // comboBox for max
    maxBathComboBox = new JComboBox(max);
    // maxBathComboBox.setRenderer(new MyComboBoxRenderer("MAX"));
    maxBathComboBox.setSelectedIndex(10);
    // maxBathComboBox.setPrototypeDisplayValue("MAX");
    minBathComboBox.addActionListener(this);
    bathPanel.add(maxBathComboBox);
    bathPanel.add(Box.createHorizontalStrut(190));

    // ******** BEDROOM **********

    bedPanel = new JPanel();
    // bedPanel.setBackground(Color.red);
    bedPanel.setBounds(0, 150, 700, 50);
    bedLabel = new JLabel("Number of Bedrooms: ");
    bedLabel.setFont(new Font("Courier", Font.PLAIN, 20));
    bedPanel.add(bedLabel);

    // bed comboBox for min for
    minBedComboBox = new JComboBox(min);
    // minBedComboBox.setRenderer(new MyComboBoxRenderer("MIN"));
    // minBedComboBox.setSelectedIndex(-1);
    // minBedComboBox.setPrototypeDisplayValue("MIN");
    minBedComboBox.addActionListener(this);
    bedPanel.add(minBedComboBox);
    bedPanel.add(Box.createHorizontalStrut(20));

    // bed comboBox for max
    maxBedComboBox = new JComboBox(max);
    // maxBedComboBox.setRenderer(new MyComboBoxRenderer("MAX"));
    maxBedComboBox.setSelectedIndex(10);
    // maxBedComboBox.setPrototypeDisplayValue("MAX");
    maxBedComboBox.addActionListener(this);
    bedPanel.add(maxBedComboBox);
    bedPanel.add(Box.createHorizontalStrut(200));

    // ******** FURNISH **********
    furnishPanel = new JPanel();
    // furnishPanel.setBackground(Color.green);
    furnishPanel.setBounds(0, 200, 700, 50);
    furnishLabel = new JLabel("Furnish State: ");
    furnishLabel.setFont(new Font("Courier", Font.PLAIN, 20));
    furnishPanel.add(furnishLabel);

    //		furnishedComboBox = new JComboBox(furnish);
    //		furnishPanel.add(furnishedComboBox);

    furnishedCheckBox = new JCheckBox();
    furnishedCheckBox.setText("Furnished");
    furnishPanel.add(furnishedCheckBox);
    furnishedCheckBox.addActionListener(this);

    furnishPanel.add(Box.createHorizontalStrut(30));

    unfurnishedCheckBox = new JCheckBox();
    unfurnishedCheckBox.setText("Unfurnished");
    furnishPanel.add(unfurnishedCheckBox);
    unfurnishedCheckBox.addActionListener(this);

    furnishPanel.add(Box.createHorizontalStrut(190));

    // ******** QUADRANT **********
    quadrantPanel = new JPanel();
    // quadrantPanel.setBackground(Color.orange);
    quadrantPanel.setBounds(0, 250, 700, 50);
    quadrantLabel = new JLabel("City Quadrant: ");
    quadrantLabel.setFont(new Font("Courier", Font.PLAIN, 20));
    quadrantPanel.add(quadrantLabel);

    //		quadrantComboBox = new JComboBox(quadrants);
    //		quadrantPanel.add(quadrantComboBox);
    //		quadrantPanel.add(Box.createHorizontalStrut(100));

    neCheckBox = new JCheckBox();
    neCheckBox.setText("NE");
    quadrantPanel.add(neCheckBox);
    neCheckBox.addActionListener(this);
    quadrantPanel.add(Box.createHorizontalStrut(10));

    nwCheckBox = new JCheckBox();
    nwCheckBox.setText("NW");
    quadrantPanel.add(nwCheckBox);
    nwCheckBox.addActionListener(this);
    quadrantPanel.add(Box.createHorizontalStrut(10));

    seCheckBox = new JCheckBox();
    seCheckBox.setText("SE");
    quadrantPanel.add(seCheckBox);
    seCheckBox.addActionListener(this);
    quadrantPanel.add(Box.createHorizontalStrut(10));

    swCheckBox = new JCheckBox();
    swCheckBox.setText("SW");
    quadrantPanel.add(swCheckBox);
    swCheckBox.addActionListener(this);
    quadrantPanel.add(Box.createHorizontalStrut(170));

    // ******** PRICE **********
    pricePanel = new JPanel();
    // pricePanel.setBackground(Color.red);
    pricePanel.setBounds(0, 300, 700, 50);
    priceLabel = new JLabel("Rent Price per Month($CAD): ");
    priceLabel.setFont(new Font("Courier", Font.PLAIN, 20));
    pricePanel.add(priceLabel);

    minPriceComboBox = new JComboBox(minPriceRange);
    // minPriceComboBox.setRenderer(new MyComboBoxRenderer("MIN"));
    // minPriceComboBox.setSelectedIndex(-1);
    minPriceComboBox.addActionListener(this);
    pricePanel.add(minPriceComboBox);
    pricePanel.add(Box.createHorizontalStrut(20));

    maxPriceComboBox = new JComboBox(maxPriceRange);
    // maxPriceComboBox.setRenderer(new MyComboBoxRenderer("MAX"));
    maxPriceComboBox.setSelectedIndex(5);
    maxPriceComboBox.addActionListener(this);
    pricePanel.add(maxPriceComboBox);
    pricePanel.add(Box.createHorizontalStrut(20));

    // ********* SUBMIT BUTTON ***********

    searchButton = new JButton("Search");
    searchButton.setFont(new Font("Dialog", Font.BOLD, 30));
    searchButton.setBounds(350, 400, 150, 50);
    searchButton.setFocusable(false);
    searchButton.addActionListener(this);
    frame.add(searchButton);
    // login page button

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

  SearchCriteriaForm(int id) {
    renterID = id;
    // ********* TITLE *********
    titlePanel = new JPanel();
    // titlePanel.setBackground(Color.green);
    titlePanel.setBounds(0, 0, 700, 50);
    titleLabel = new JLabel("Please Fill in Search Criteria");
    titleLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
    titlePanel.add(titleLabel);

    // ******** HOUSE TYPE *********
    htPanel = new JPanel();
    // htPanel.setBackground(Color.blue);
    htPanel.setBounds(0, 50, 700, 50);
    htLabel = new JLabel("House Type: ");
    htLabel.setFont(new Font("Courier", Font.PLAIN, 20));
    htPanel.add(htLabel);

    // check box for house type( num of boxs for num of types)
    // making checkbox for apartment
    apartmentCheckBox = new JCheckBox();
    apartmentCheckBox.setText("Apartment");
    apartmentCheckBox.addActionListener(this);
    htPanel.add(apartmentCheckBox);

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
    htPanel.add(Box.createHorizontalStrut(30));

    // ******** BATHROOM **********

    bathPanel = new JPanel();
    // bathPanel.setBackground(Color.magenta);
    bathPanel.setBounds(0, 100, 700, 50);
    bathLabel = new JLabel("Number of Bathrooms: ");
    bathLabel.setFont(new Font("Courier", Font.PLAIN, 20));
    bathPanel.add(bathLabel);

    // comboBox for min for
    minBathComboBox = new JComboBox(min);
    // minBathComboBox.setRenderer(new MyComboBoxRenderer("MIN"));
    // minBathComboBox.setSelectedIndex(-1);
    // minBathComboBox.setPrototypeDisplayValue("MIN");
    minBathComboBox.addActionListener(this);
    bathPanel.add(minBathComboBox);
    bathPanel.add(Box.createHorizontalStrut(20));

    // comboBox for max
    maxBathComboBox = new JComboBox(max);
    // maxBathComboBox.setRenderer(new MyComboBoxRenderer("MAX"));
    maxBathComboBox.setSelectedIndex(10);
    // maxBathComboBox.setPrototypeDisplayValue("MAX");
    minBathComboBox.addActionListener(this);
    bathPanel.add(maxBathComboBox);
    bathPanel.add(Box.createHorizontalStrut(190));

    // ******** BEDROOM **********

    bedPanel = new JPanel();
    // bedPanel.setBackground(Color.red);
    bedPanel.setBounds(0, 150, 700, 50);
    bedLabel = new JLabel("Number of Bedrooms: ");
    bedLabel.setFont(new Font("Courier", Font.PLAIN, 20));
    bedPanel.add(bedLabel);

    // bed comboBox for min for
    minBedComboBox = new JComboBox(min);
    // minBedComboBox.setRenderer(new MyComboBoxRenderer("MIN"));
    // minBedComboBox.setSelectedIndex(-1);
    // minBedComboBox.setPrototypeDisplayValue("MIN");
    minBedComboBox.addActionListener(this);
    bedPanel.add(minBedComboBox);
    bedPanel.add(Box.createHorizontalStrut(20));

    // bed comboBox for max
    maxBedComboBox = new JComboBox(max);
    // maxBedComboBox.setRenderer(new MyComboBoxRenderer("MAX"));
    maxBedComboBox.setSelectedIndex(10);
    // maxBedComboBox.setPrototypeDisplayValue("MAX");
    maxBedComboBox.addActionListener(this);
    bedPanel.add(maxBedComboBox);
    bedPanel.add(Box.createHorizontalStrut(200));

    // ******** FURNISH **********
    furnishPanel = new JPanel();
    // furnishPanel.setBackground(Color.green);
    furnishPanel.setBounds(0, 200, 700, 50);
    furnishLabel = new JLabel("Furnish State: ");
    furnishLabel.setFont(new Font("Courier", Font.PLAIN, 20));
    furnishPanel.add(furnishLabel);

    //		furnishedComboBox = new JComboBox(furnish);
    //		furnishPanel.add(furnishedComboBox);

    furnishedCheckBox = new JCheckBox();
    furnishedCheckBox.setText("Furnished");
    furnishPanel.add(furnishedCheckBox);
    furnishedCheckBox.addActionListener(this);

    furnishPanel.add(Box.createHorizontalStrut(30));

    unfurnishedCheckBox = new JCheckBox();
    unfurnishedCheckBox.setText("Unfurnished");
    furnishPanel.add(unfurnishedCheckBox);
    unfurnishedCheckBox.addActionListener(this);

    furnishPanel.add(Box.createHorizontalStrut(190));

    // ******** QUADRANT **********
    quadrantPanel = new JPanel();
    // quadrantPanel.setBackground(Color.orange);
    quadrantPanel.setBounds(0, 250, 700, 50);
    quadrantLabel = new JLabel("City Quadrant: ");
    quadrantLabel.setFont(new Font("Courier", Font.PLAIN, 20));
    quadrantPanel.add(quadrantLabel);

    //		quadrantComboBox = new JComboBox(quadrants);
    //		quadrantPanel.add(quadrantComboBox);
    //		quadrantPanel.add(Box.createHorizontalStrut(100));

    neCheckBox = new JCheckBox();
    neCheckBox.setText("NE");
    quadrantPanel.add(neCheckBox);
    neCheckBox.addActionListener(this);
    quadrantPanel.add(Box.createHorizontalStrut(10));

    nwCheckBox = new JCheckBox();
    nwCheckBox.setText("NW");
    quadrantPanel.add(nwCheckBox);
    nwCheckBox.addActionListener(this);
    quadrantPanel.add(Box.createHorizontalStrut(10));

    seCheckBox = new JCheckBox();
    seCheckBox.setText("SE");
    quadrantPanel.add(seCheckBox);
    seCheckBox.addActionListener(this);
    quadrantPanel.add(Box.createHorizontalStrut(10));

    swCheckBox = new JCheckBox();
    swCheckBox.setText("SW");
    quadrantPanel.add(swCheckBox);
    swCheckBox.addActionListener(this);
    quadrantPanel.add(Box.createHorizontalStrut(170));

    // ******** PRICE **********
    pricePanel = new JPanel();
    // pricePanel.setBackground(Color.red);
    pricePanel.setBounds(0, 300, 700, 50);
    priceLabel = new JLabel("Rent Price per Month($CAD): ");
    priceLabel.setFont(new Font("Courier", Font.PLAIN, 20));
    pricePanel.add(priceLabel);

    minPriceComboBox = new JComboBox(minPriceRange);
    // minPriceComboBox.setRenderer(new MyComboBoxRenderer("MIN"));
    // minPriceComboBox.setSelectedIndex(-1);
    minPriceComboBox.addActionListener(this);
    pricePanel.add(minPriceComboBox);
    pricePanel.add(Box.createHorizontalStrut(20));

    maxPriceComboBox = new JComboBox(maxPriceRange);
    // maxPriceComboBox.setRenderer(new MyComboBoxRenderer("MAX"));
    maxPriceComboBox.setSelectedIndex(5);
    maxPriceComboBox.addActionListener(this);
    pricePanel.add(maxPriceComboBox);
    pricePanel.add(Box.createHorizontalStrut(20));

    // ********* SUBMIT BUTTON ***********

    searchButton2 = new JButton("Search");
    searchButton2.setFont(new Font("Dialog", Font.BOLD, 30));
    searchButton2.setBounds(350, 400, 150, 50);
    searchButton2.setFocusable(false);
    searchButton2.addActionListener(this);
    frame.add(searchButton2);
    // login page button

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
    // two cases where it is a registered renter or regular
    // if registered, should pass in its id so registered can be notified
    // regular does not need to pass in anything

    // CheckBox.isSelected() to get bool value of selected

       int[] localmin = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
   int[] localmax = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -1};

      double[] localMinRange = {0,   200.00, 400.00,
                                    600.00, 800.00, 1000.00, 1400.00, 1800.00, 2200.00,2600.00};
  double[] localMaxRange = {200.00, 800.00, 1400.00, 2000.00,
                                    2600.00, -1};

  int minBathIndex =minBathComboBox.getSelectedIndex();
  int maxBathIndex =maxBathComboBox.getSelectedIndex();
  int minBedIndex =minBedComboBox.getSelectedIndex();
  int maxBedIndex =maxBedComboBox.getSelectedIndex();
  int minPriceIndex =minPriceComboBox.getSelectedIndex();
  int maxPriceIndex =maxPriceComboBox.getSelectedIndex();

    int minBath = localmin[minBathIndex];
    int maxBath = localmax[maxBathIndex];
    int minBed = localmin[minBedIndex];
    int maxBed = localmax[maxBedIndex];
    double minPrice = localMinRange[minPriceIndex];
    double maxPrice = localMaxRange[maxPriceIndex];


    // int minBath =
    //     Integer.parseInt(minBathComboBox.getSelectedItem().toString());
    // int maxBath =
    //     Integer.parseInt(maxBathComboBox.getSelectedItem().toString());
    // int minBed = Integer.parseInt(minBedComboBox.getSelectedItem().toString());
    // int maxBed = Integer.parseInt(maxBedComboBox.getSelectedItem().toString());
    // double minPrice =
    //     Double.parseDouble(minPriceComboBox.getSelectedItem().toString());
    // double maxPrice =
    //     Double.parseDouble(maxPriceComboBox.getSelectedItem().toString());
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
      int counter = 0;
      for (int i = 0; i < htBool.length; i++) {

        if (htBool[i]) {
          possible[counter] = types[i];
          counter++;
        }
      }
      String[] htSelected = new String[counter];
      for (int i = 0; i < htSelected.length; i++) {
        htSelected[i] = possible[i];
      }

      // clear array for furniture
      possible = new String[furBool.length];
      counter = 0;
      for (int i = 0; i < furBool.length; i++) {
        if (furBool[i]) {
          possible[counter] = furnish[i];
          counter++;
        }
      }

      String[] furSelected = new String[counter];
      for (int i = 0; i < furSelected.length; i++) {
        furSelected[i] = possible[i];
      }

      // clear array for quadrant
      possible = new String[quadBool.length];
      counter = 0;
      for (int i = 0; i < quadBool.length; i++) {
        if (quadBool[i]) {
          possible[counter] = quadrants[i];
          ;
          counter++;
        }
      }
      String[] quadSelected = new String[counter];
      for (int i = 0; i < quadSelected.length; i++) {
        quadSelected[i] = possible[i];
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
      int counter = 0;
      for (int i = 0; i < htBool.length; i++) {

        if (htBool[i]) {
          possible[counter] = types[i];
          counter++;
        }
      }
      String[] htSelected = new String[counter];
      for (int i = 0; i < htSelected.length; i++) {
        htSelected[i] = possible[i];
      }

      // clear array for furniture
      possible = new String[furBool.length];
      counter = 0;
      for (int i = 0; i < furBool.length; i++) {
        if (furBool[i]) {
          possible[counter] = furnish[i];
          counter++;
        }
      }

      String[] furSelected = new String[counter];
      for (int i = 0; i < furSelected.length; i++) {
        furSelected[i] = possible[i];
      }

      // clear array for quadrant
      possible = new String[quadBool.length];
      counter = 0;
      for (int i = 0; i < quadBool.length; i++) {
        if (quadBool[i]) {
          possible[counter] = quadrants[i];
          ;
          counter++;
        }
      }
      String[] quadSelected = new String[counter];
      for (int i = 0; i < quadSelected.length; i++) {
        quadSelected[i] = possible[i];
      }

      RegisteredRenter renterSearch;
      try {
        renterSearch = new RegisteredRenter(renterID);
        renterSearch.saveCriteria(htSelected, minBath, maxBath, minBed, maxBed,
                                  furSelected, quadSelected, minPrice,
                                  maxPrice);
        PropertiesDisplayForm list =
            new PropertiesDisplayForm(renterSearch.performSearch());
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
    }
  }

  class MyComboBoxRenderer extends JLabel implements ListCellRenderer {
    private String _title;

    public MyComboBoxRenderer(String title) { _title = title; }

    @Override
    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected,
                                                  boolean hasFocus) {
      if (index == -1 && value == null)
        setText(_title);
      else
        setText(value.toString());
      return this;
    }
  }
}
