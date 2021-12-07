package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class SearchCriteriaForm implements ActionListener {

  JFrame frame = new JFrame();
  // title
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

  // bath panel
  private static JPanel bathPanel;
  private static JLabel bathLabel;
  private static JComboBox minBathComboBox;
  private static JComboBox maxBathComboBox;

  // bed panel
  private static JPanel bedPanel;
  private static JLabel bedLabel;
  private static JComboBox minBedComboBox;
  private static JComboBox maxBedComboBox;

  // furnish panel
  private static JPanel furnishPanel;
  private static JLabel furnishLabel;
  private static JCheckBox furnishedCheckBox;
  private static JCheckBox unfurnishedCheckBox;

  // quadrant panel
  private static JPanel quadrantPanel;
  private static JLabel quadrantLabel;
  private static JCheckBox neCheckBox;
  private static JCheckBox nwCheckBox;
  private static JCheckBox seCheckBox;
  private static JCheckBox swCheckBox;

  // price panel
  private static JPanel pricePanel;
  private static JLabel priceLabel;
  private static JTextField priceText;

  // submit button
  private static JButton searchButton;

  // comboBox for min and max bath/bed
  private String[] min = {"Min", "1+", "2+", "3+", "4+", "5+", "6+"};
  private String[] max = {"Max", "1", "2", "3", "4", "5", "6"};

  SearchCriteriaForm() {

    // ********* TITLE *********
    titlePanel = new JPanel();
    //		titlePanel.setBackground(Color.green);
    titlePanel.setBounds(0, 0, 700, 50);
    titleLabel = new JLabel("Please Fill in Search Criteria");
    titleLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
    titlePanel.add(titleLabel);

    // ******** HOUSE TYPE *********
    htPanel = new JPanel();
    //		htPanel.setBackground(Color.blue);
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
    //		bathPanel.setBackground(Color.magenta);
    bathPanel.setBounds(0, 100, 700, 50);
    bathLabel = new JLabel("Number of Bathrooms: ");
    bathLabel.setFont(new Font("Courier", Font.PLAIN, 20));
    bathPanel.add(bathLabel);

    // comboBox for min for
    minBathComboBox = new JComboBox(min);
    minBathComboBox.addActionListener(this);
    bathPanel.add(minBathComboBox);
    bathPanel.add(Box.createHorizontalStrut(20));

    // comboBox for max
    maxBathComboBox = new JComboBox(max);
    minBathComboBox.addActionListener(this);
    bathPanel.add(maxBathComboBox);
    bathPanel.add(Box.createHorizontalStrut(190));

    // ******** BEDROOM **********

    bedPanel = new JPanel();
    //		bedPanel.setBackground(Color.red);
    bedPanel.setBounds(0, 150, 700, 50);
    bedLabel = new JLabel("Number of Bedrooms: ");
    bedLabel.setFont(new Font("Courier", Font.PLAIN, 20));
    bedPanel.add(bedLabel);

    // comboBox for min for
    minBedComboBox = new JComboBox(min);
    minBedComboBox.addActionListener(this);
    bedPanel.add(minBedComboBox);
    bedPanel.add(Box.createHorizontalStrut(20));

    // comboBox for max
    maxBedComboBox = new JComboBox(max);
    maxBedComboBox.addActionListener(this);
    bedPanel.add(maxBedComboBox);
    bedPanel.add(Box.createHorizontalStrut(200));

    // ******** FURNISH **********
    furnishPanel = new JPanel();
    //		furnishPanel.setBackground(Color.green);
    furnishPanel.setBounds(0, 200, 700, 50);
    furnishLabel = new JLabel("Furnish State: ");
    furnishLabel.setFont(new Font("Courier", Font.PLAIN, 20));
    furnishPanel.add(furnishLabel);

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
    //		quadrantPanel.setBackground(Color.orange);
    quadrantPanel.setBounds(0, 250, 700, 50);
    quadrantLabel = new JLabel("City Quadrant: ");
    quadrantLabel.setFont(new Font("Courier", Font.PLAIN, 20));
    quadrantPanel.add(quadrantLabel);

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
    //		pricePanel.setBackground(Color.red);
    pricePanel.setBounds(0, 300, 700, 50);
    priceLabel = new JLabel("Rent Price per Month: ");
    priceLabel.setFont(new Font("Courier", Font.PLAIN, 20));
    pricePanel.add(priceLabel);

    priceText = new JTextField(10);
    pricePanel.add(priceText);
    priceText.addActionListener(this);
    pricePanel.add(Box.createHorizontalStrut(200));

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

  @Override
  public void actionPerformed(ActionEvent e) {
    // two cases where it is a registered renter or regular
    // if registered, should pass in its id so registered can be notified
    // regular does not need to pass in anything

    // CheckBox.isSelected() to get bool value of selected
    boolean[] htBool = {apartmentCheckBox.isSelected(),
                        bungalowCheckBox.isSelected(),
                        condoCheckBox.isSelected(), duplexCheckBox.isSelected(),
                        townhouseCheckBox.isSelected()};
    String[] possible = new String[5];

    if (e.getSource() == searchButton) {
      // method to get selected ht and put into string array
      int counter = 0;
      for (int i = 0; i < htBool.length; i++) {

        if (htBool[i]) {
          possible[counter] = types[i];
          counter++;
        }
      }

      String[] selected = new String[counter];
      for (int i = 0; i < selected.length; i++) {
        selected[i] = possible[i];
        System.out.println(selected[i]);
      }
    }
  }
}
