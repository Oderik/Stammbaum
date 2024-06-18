package de.oderik.genealogy.gui.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.oderik.genealogy.GlobalConfig;
import de.oderik.genealogy.gui.CertainCheckBox;
import de.oderik.genealogy.objects.Person;
import de.oderik.genealogy.objects.PersonFilter;
import de.oderik.genealogy.objects.UncertainData;

public class PersonPanel extends JPanel {
	private static final long serialVersionUID = 1857246778435826307L;
	
	private JTextField	name_TextField;
	private JLabel		name_Label;
	private CertainCheckBox	name_CheckBox;
	private JSpinner	dob_Spinner;
	private JLabel		dob_Label;
	private JCheckBox	born_CheckBox;
	private CertainCheckBox	dob_CheckBox;
	private JSpinner	dod_Spinner;
	private JLabel		dod_Label;
	private JCheckBox	died_CheckBox;
	private CertainCheckBox	dod_CheckBox;
	private JLabel		gender_Label;
	private JComboBox	gender_ComboBox;
	private CertainCheckBox gender_CheckBox;
	private SpinnerDateModel dob_SpinnerDateModel;
	private SpinnerDateModel dod_SpinnerDateModel;
	private JLabel		father_Label;
	private JComboBox	father_ComboBox;
	private CertainCheckBox	father_CheckBox;
	private JLabel		mother_Label;
	private JComboBox	mother_ComboBox;
	private CertainCheckBox mother_CheckBox;

	private Person person;

	public PersonPanel(Person person) {
		super();
		setName("Person");
		initGUI();
		setPerson(person);
	}
	
	public void setPerson(Person person) {
		if (person == null) {
			throw new NullPointerException();
		}
		if (this.person != person) {
			this.person = person;
			updateGUI();
		}
	}
	
	private void updateGUI() {
		name_TextField.setText(person.getName().val());
		name_CheckBox.setSelected(person.getName().isCertain());
		
		gender_ComboBox.setSelectedIndex(person.isMale().val().booleanValue()?0:1);		
		gender_CheckBox.setSelected(person.isMale().isCertain());
		
		if (person.getDateOfBirth().val() == null) {
			born_CheckBox.getModel().setSelected(false);
			dob_Spinner.setEnabled(false);
			dob_Spinner.getModel().setValue(new Date());
		} else {
			dob_SpinnerDateModel.setValue(person.getDateOfBirth().val());
			born_CheckBox.getModel().setSelected(true);
		}
		dob_CheckBox.setSelected(person.getDateOfBirth().isCertain());

		if (person.getDateOfDeath().val() == null) {
			died_CheckBox.getModel().setSelected(false);
			dod_Spinner.setEnabled(false);
			dod_Spinner.getModel().setValue(new Date());
		} else {
			dod_SpinnerDateModel.setValue(person.getDateOfDeath().val());
			died_CheckBox.getModel().setSelected(true);
		}
		dod_CheckBox.setSelected(person.getDateOfDeath().isCertain());
		
		Vector<Person> potentialFathers = GlobalConfig.dataContainer.getPersons().getFilteredPersons(new PersonFilter() {
			public boolean checkPerson(Person candidate) {
				if (person == candidate) {
					return false;
				}
				return candidate.isMale().val();
			}
		});
		potentialFathers.insertElementAt(null, 0);
		father_ComboBox.setModel(new DefaultComboBoxModel(potentialFathers));
		father_ComboBox.setSelectedItem(person.getFather().val());
		father_CheckBox.setSelected(person.getFather().isCertain());
		
		Vector<Person> potentialMothers = GlobalConfig.dataContainer.getPersons().getFilteredPersons(new PersonFilter() {
			public boolean checkPerson(Person candidate) {
				if (person == candidate) {
					return false;
				}
				return !candidate.isMale().val();
			}
		});
		potentialMothers.insertElementAt(null, 0);
		mother_ComboBox.setModel(new DefaultComboBoxModel(potentialMothers));
		mother_ComboBox.setSelectedItem(person.getMother().val());
		mother_CheckBox.setSelected(person.getMother().isCertain());
	}

	private void initGUI() {
		// Create instances
		name_Label = new JLabel("Name:", JLabel.TRAILING);
		name_TextField = new JTextField();
		name_Label.setLabelFor(name_TextField);
		name_CheckBox = new CertainCheckBox();
		
		gender_Label = new JLabel("Geschlecht:", JLabel.TRAILING);
		gender_ComboBox = new JComboBox(new String[] {"männlich", "weiblich"});
		gender_CheckBox = new CertainCheckBox();
		
		dob_Label = new JLabel("Geburtstag:", JLabel.TRAILING);
		born_CheckBox = new JCheckBox();
		dob_SpinnerDateModel = new SpinnerDateModel();
		dob_Spinner = new JSpinner(dob_SpinnerDateModel);
		dob_Spinner.setEditor(new JSpinner.DateEditor(dob_Spinner, "dd.MM.yyyy"));
		((JSpinner.DateEditor) dob_Spinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.TRAILING);
		dob_Label.setLabelFor(dob_Spinner);
		born_CheckBox.setToolTipText("Ist diese Person bereits geboren?");
		dob_CheckBox = new CertainCheckBox();
		
		dod_Label = new JLabel("Todestag:", JLabel.TRAILING);
		dod_SpinnerDateModel = new SpinnerDateModel();
		dod_Spinner = new JSpinner(dod_SpinnerDateModel);
		dod_Spinner.setEditor(new JSpinner.DateEditor(dod_Spinner, "dd.MM.yyyy"));
		((JSpinner.DateEditor) dod_Spinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.TRAILING);
		dod_Label.setLabelFor(dod_Spinner);
		died_CheckBox = new JCheckBox();
		died_CheckBox.setToolTipText("Ist diese Person bereits gestorben?");
		dod_CheckBox = new CertainCheckBox();
		
		father_Label = new JLabel("Vater:", JLabel.TRAILING);
		father_ComboBox = new JComboBox();
		father_ComboBox.setEditable(false);
		father_Label.setLabelFor(father_ComboBox);
		father_CheckBox = new CertainCheckBox();
		
		mother_Label = new JLabel("Mutter:", JLabel.TRAILING);
		mother_ComboBox = new JComboBox();
		mother_ComboBox.setEditable(false);
		mother_Label.setLabelFor(mother_ComboBox);
		mother_CheckBox = new CertainCheckBox();

		// Define dependencies
		born_CheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (born_CheckBox.getModel().isSelected()) {
					dob_Spinner.setEnabled(true);
				} else {
					dob_Spinner.setEnabled(false);
					died_CheckBox.getModel().setSelected(false);
					dod_Spinner.setEnabled(false);
				}
			}
		});
		
		dob_Spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (dob_SpinnerDateModel.getDate().after(dod_SpinnerDateModel.getDate())) {
					dod_SpinnerDateModel.setValue(dob_SpinnerDateModel.getDate());
				}
			}
		});
		
		died_CheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (died_CheckBox.getModel().isSelected()) {
					born_CheckBox.getModel().setSelected(true);
					dob_Spinner.setEnabled(true);
					dod_Spinner.setEnabled(true);
				} else {
					dod_Spinner.setEnabled(false);
				}
			}
		});
		
		dod_Spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (dod_SpinnerDateModel.getDate().before(dob_SpinnerDateModel.getDate())) {
					dob_SpinnerDateModel.setValue(dod_SpinnerDateModel.getDate());
				}
			}
		});
		
		// Place everything into the panel
		setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		
		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		add(name_Label, gridBagConstraints);
		
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.gridx++;
		add(name_TextField, gridBagConstraints);

		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.gridx +=2;
		add(name_CheckBox, gridBagConstraints);
		
		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy++;
		add(gender_Label, gridBagConstraints);
		
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.gridx++;
		add(gender_ComboBox, gridBagConstraints);

		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.gridx +=2;
		add(gender_CheckBox, gridBagConstraints);

		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy++;
		add(dob_Label, gridBagConstraints);
		
		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.gridx++;
		add(born_CheckBox, gridBagConstraints);
		
		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.gridx++;
		add(dob_Spinner, gridBagConstraints);

		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.gridx++;
		add(dob_CheckBox, gridBagConstraints);
		
		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy++;
		add(dod_Label, gridBagConstraints);
		
		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.gridx++;
		add(died_CheckBox, gridBagConstraints);
		
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.gridx++;
		add(dod_Spinner, gridBagConstraints);
		
		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.gridx++;
		add(dod_CheckBox, gridBagConstraints);

		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy++;
		add(father_Label, gridBagConstraints);
		
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.gridx++;
		add(father_ComboBox, gridBagConstraints);
		
		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.gridx+=2;
		add(father_CheckBox, gridBagConstraints);
		
		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy++;
		add(mother_Label, gridBagConstraints);
		
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.gridx++;
		add(mother_ComboBox, gridBagConstraints);
		
		gridBagConstraints.weightx = 0.0;
		gridBagConstraints.gridwidth = 1;
	gridBagConstraints.gridx+=2;
		add(mother_CheckBox, gridBagConstraints);
		
	}
	
	public void updatePerson() {
		UncertainData<String> name = new UncertainData<String>();
		if (name_TextField.getText().length() > 0) {
			name.set(name_TextField.getText());
		}
		name.setCertain(name_CheckBox.isSelected());
		
		UncertainData<Boolean> isMale = new UncertainData<Boolean>();
		isMale.set(new Boolean(gender_ComboBox.getSelectedIndex() == 0));
		isMale.setCertain(gender_CheckBox.isSelected());
		
		UncertainData<Date> dob = new UncertainData<Date>();
		if (born_CheckBox.isSelected()) {
			dob.set(dob_SpinnerDateModel.getDate());
		}
		dob.setCertain(dob_CheckBox.isSelected());
		
		UncertainData<Date> dod = new UncertainData<Date>();
		if (died_CheckBox.isSelected()) {
			dod.set(dod_SpinnerDateModel.getDate());
		}
		dod.setCertain(dod_CheckBox.isSelected());
		
		UncertainData<Person> father = new UncertainData<Person>();
		father.set((Person) father_ComboBox.getSelectedItem());
		father.setCertain(father_CheckBox.isSelected() && (father.val() != null));
		
		UncertainData<Person> mother = new UncertainData<Person>();
		mother.set((Person) mother_ComboBox.getSelectedItem());
		mother.setCertain(mother_CheckBox.isSelected() && (mother.val() != null));
		
		person.setName(name);
		person.setMale(isMale);
		person.setDateOfBirth(dob);
		person.setDateOfDeath(dod);
		person.setFather(father);
		person.setMother(mother);
	}
	
	public Person getPerson() {
		return this.person;
	}
	
	public void resetGUI() {
		updateGUI();
	}
}