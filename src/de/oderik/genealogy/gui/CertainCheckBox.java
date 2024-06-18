package de.oderik.genealogy.gui;

import javax.swing.JCheckBox;

import de.oderik.genealogy.objects.UncertainData;

public class CertainCheckBox extends JCheckBox {
	private static final long serialVersionUID = -2553620735153373540L;

	public CertainCheckBox() {
		super();
		setToolTipText("Sind die gemachten Angaben mit Sicherheit richtig?");
	}
	
	public CertainCheckBox(boolean checked) {
		this();
		setSelected(checked);
	}
	
	public CertainCheckBox(UncertainData uncertainData) {
		this(uncertainData.isCertain());
	}
	
	public void stampUncertainData(UncertainData uncertainData) {
		uncertainData.setCertain(isSelected());
	}
}
