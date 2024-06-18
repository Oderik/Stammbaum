package de.oderik.genealogy.objects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DataContainer implements Serializable {
	private static final long serialVersionUID = -5114205648385591361L;

	private PersonListModel persons;

	public DataContainer() {
		super();
		persons = new PersonListModel();
	}

	public PersonListModel getPersons() {
		return persons;
	}

	public boolean saveToFile(String directory, String fileName) {
		File file = new File(directory + fileName);
		file.delete();
		try {
			file.createNewFile();
			FileOutputStream fileOut;
			fileOut = new FileOutputStream(file);
			ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(this);
			objOut.flush();
			objOut.close();
			fileOut.close();
			return true;
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
	}

	public static DataContainer loadFromFile(String directory, String fileName) throws IOException {
		DataContainer dataContainer;
		File file = new File(directory + fileName);
		try {
			FileInputStream fileIn;
			fileIn = new FileInputStream(file);
			ObjectInputStream objIn = new ObjectInputStream(fileIn);
			dataContainer = ((DataContainer) objIn.readObject());
			objIn.close();
			fileIn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return dataContainer;
	}
}