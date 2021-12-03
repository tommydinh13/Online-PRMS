package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SearchCriteriaForm {

	JFrame frame = new JFrame();
	private static JLabel title;
	private static JLabel type;
	private static JPanel titlePanel;
	private static JPanel typePanel;
	private static JPanel bathPanel;
	private static JPanel bedPanel;
	private static JPanel furnishPanel;
	private static JPanel quadrantPanel;

	// check box for different house types
	private static JCheckBox apartmentCheckBox;
	private static JCheckBox duplexCheckBox;
	private static JCheckBox townhouseCheckBox;
	private static JCheckBox bungalowCheckBox;
	private static JCheckBox condoCheckBox;

	// comboBox for min and max bath/bed
	private static JComboBox minComboBox;
	private static JComboBox maxComboBox;

	SearchCriteriaForm() {
		typePanel = new JPanel();
		typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.LINE_AXIS));

		titlePanel = new JPanel();
		title = new JLabel("Please Fill in Search Criteria");
		titlePanel.setBackground(Color.CYAN);
		titlePanel.add(title);

		type = new JLabel("House Type: ");
		type.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
		typePanel.add(type);

		// text field for num of bathrooms
		// combo box for furnish (furnished or non furnished)
		// check box for quadrant (4 boxes)

		// check box for house type( num of boxs for num of types)
		// making checkbox for apartment
		apartmentCheckBox = new JCheckBox();
		apartmentCheckBox.setText("Apartment");
		typePanel.add(apartmentCheckBox);

		// making checkbox for bungalow
		bungalowCheckBox = new JCheckBox();
		bungalowCheckBox.setText("Bungalow");
		typePanel.add(bungalowCheckBox);

		// making checkbox for condo
		condoCheckBox = new JCheckBox();
		condoCheckBox.setText("Condo");
		typePanel.add(condoCheckBox);

		// making checkbox for duplex
		duplexCheckBox = new JCheckBox();
		duplexCheckBox.setText("Duplex");
		typePanel.add(duplexCheckBox);

		// making checkbox for townhouse
		townhouseCheckBox = new JCheckBox();
		townhouseCheckBox.setText("Townhouse");
		typePanel.add(duplexCheckBox);

		// BEDROOM

		bedPanel = new JPanel();
		bedPanel.setLayout(new BoxLayout(bedPanel, BoxLayout.LINE_AXIS));
		// comboBox for min for
		String[] min = { "Min", "1+", "2+", "3+", "4+", "5+", "6+" };
		minComboBox = new JComboBox(min);
//		minComboBox.setBounds(160, 150, 165, 25);
		bedPanel.add(minComboBox);

//		// comboBox for max
		String[] max = { "Max", "1", "2", "3", "4", "5", "6" };
		maxComboBox = new JComboBox(max);
//		maxComboBox.setBounds(160, 150, 165, 25);
		bedPanel.add(maxComboBox);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exiting window will close window
		frame.setSize(500, 500); // setting size of window
		frame.setLayout(new BoxLayout(frame, BoxLayout.LINE_AXIS)); // no layout
		frame.setTitle("Rental Property Management System");
		frame.add(titlePanel);
		frame.add(typePanel);
		frame.add(bedPanel);

		frame.setVisible(true);

	}

}
