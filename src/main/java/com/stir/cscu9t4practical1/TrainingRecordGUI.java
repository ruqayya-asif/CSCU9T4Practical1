// GUI and main program for the Training Record
package com.stir.cscu9t4practical1;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Number;

public class TrainingRecordGUI extends JFrame implements ActionListener {

	private JTextField name = new JTextField(30);
	private JTextField day = new JTextField(2);
	private JTextField month = new JTextField(2);
	private JTextField year = new JTextField(4);
	private JTextField hours = new JTextField(2);
	private JTextField mins = new JTextField(2);
	private JTextField secs = new JTextField(2);
	private JTextField dist = new JTextField(4);
	private JTextField entyp = new JTextField(4);
	private JTextField rept = new JTextField(2);
	private JTextField ry = new JTextField(2);
	private JTextField wh = new JTextField(4);
	private JTextField terr = new JTextField(4);
	private JTextField temp = new JTextField(4);

	private JLabel labn = new JLabel(" Name:");
	private JLabel labd = new JLabel(" Day:");
	private JLabel labm = new JLabel(" Month:");
	private JLabel laby = new JLabel(" Year:");
	private JLabel labh = new JLabel(" Hours:");
	private JLabel labmm = new JLabel(" Mins:");
	private JLabel labs = new JLabel(" Secs:");
	private JLabel labdist = new JLabel(" Distance (km):");
	private JLabel labentyp = new JLabel(" Entry Type (Swim/Sprint/Cycle):");
	private JLabel labrept = new JLabel(" Repetitions (if sprint):");
	private JLabel labry = new JLabel(" Recovery (if sprint):");
	private JLabel labwh = new JLabel(" Where (if swim):");
	private JLabel labterr = new JLabel(" Terrain (if cycle):");
	private JLabel labtemp = new JLabel(" Tempo (if cycle):");
	private JButton addR = new JButton("Add");
	private JButton lookUpByDate = new JButton("Look Up");
	private JButton findAllByDate = new JButton("Find All");
	private JButton removeR = new JButton("Remove");

	private TrainingRecord myAthletes = new TrainingRecord();

	private JTextArea outputArea = new JTextArea(5, 50);

	public static void main(String[] args) {
		TrainingRecordGUI applic = new TrainingRecordGUI();
	} // main

	// set up the GUI
	public TrainingRecordGUI() {
		super("Training Record");
		setLayout(new FlowLayout());
		add(labn);
		add(name);
		name.setEditable(true);
		add(labd);
		add(day);
		day.setEditable(true);
		add(labm);
		add(month);
		month.setEditable(true);
		add(laby);
		add(year);
		year.setEditable(true);
		add(labh);
		add(hours);
		hours.setEditable(true);
		add(labmm);
		add(mins);
		mins.setEditable(true);
		add(labs);
		add(secs);
		secs.setEditable(true);
		add(labdist);
		add(dist);
		dist.setEditable(true);
		add(labentyp);
		add(entyp);
		entyp.setEditable(true);
		add(labrept);
		add(rept);
		rept.setEditable(true);
		add(labry);
		add(ry);
		ry.setEditable(true);
		add(labwh);
		add(wh);
		wh.setEditable(true);
		add(labterr);
		add(terr);
		terr.setEditable(true);
		add(labtemp);
		add(temp);
		temp.setEditable(true);
		add(addR);
		addR.addActionListener(this);
		add(lookUpByDate);
		lookUpByDate.addActionListener(this);
		add(findAllByDate);
		findAllByDate.addActionListener(this);
		add(removeR);
		removeR.addActionListener(this);
		add(outputArea);
		outputArea.setEditable(false);
		setSize(720, 200);
		setVisible(true);
		blankDisplay();

		// To save typing in new entries while testing, uncomment
		// the following lines (or add your own test cases)

	} // constructor

	// listen for and respond to GUI events
	public void actionPerformed(ActionEvent event) {
		String message = "";
		if (event.getSource() == addR) {
			//take the entry type input and pass it as parameter to addEntry method
			String type = entyp.getText();
			message = addEntry(type);
		}
		if (event.getSource() == lookUpByDate) {
			message = lookupEntry();
		}
		if (event.getSource() == findAllByDate) {
			message = lookupEntries();
		}
		if (event.getSource() == removeR) {
			message = removeEntry();
		}
		outputArea.setText(message);
		blankDisplay();
	} // actionPerformed

	/*
	 * mehtod that takes in input through text fields
	 * adds entry for specified training session
	 * */
	public String addEntry(String what) {
		String message = "Record added\n";
		System.out.println("Adding " + what + " entry to the records");
		String n = name.getText();
		if (n.isEmpty()) {
			message = "Must enter name\n";
		}
		try {
			int m = Integer.parseInt(month.getText());
			int d = Integer.parseInt(day.getText());
			int y = Integer.parseInt(year.getText());
			float km = java.lang.Float.parseFloat(dist.getText());
			int h = Integer.parseInt(hours.getText());
			int mm = Integer.parseInt(mins.getText());
			int s = Integer.parseInt(secs.getText());

			if (what.equalsIgnoreCase("sprint")) {
				if (rept.getText().isEmpty() || ry.getText().isEmpty()) {
					message = "Must enter repetitions and recovery\n";
				} else {
					int rpt = Integer.parseInt(rept.getText());
					int rcy = Integer.parseInt(ry.getText());
					Entry e = new SprintEntry(n, d, m, y, h, mm, s, km, rpt, rcy);
					myAthletes.addEntry(e);
				}
			}

			if (what.equalsIgnoreCase("swim")) {
				String where = wh.getText();
				if (where.isEmpty()) {
					message = "Must enter where \n";
				} else {
					Entry e = new SwimEntry(n, d, m, y, h, mm, s, km, where);
					myAthletes.addEntry(e);
				}
			}

			if (what.equalsIgnoreCase("cycle")) {
				String ter = terr.getText();
				String tem = temp.getText();
				if (ter.isEmpty() || tem.isEmpty()) {
					message = "Must enter terrain and tempo\n";
				} else {
					Entry e = new CycleEntry(n, d, m, y, h, mm, s, km, ter, tem);
					myAthletes.addEntry(e);
				}
			}

		} catch (NumberFormatException e) {
			message = "Enter only integer value\n";
		}

		return message;
	}

	/*
	 * mehtod that takes in input through text field
	 * looks up entry for given day, month, year
	 * */
	public String lookupEntry() {
		int m = Integer.parseInt(month.getText());
		int d = Integer.parseInt(day.getText());
		int y = Integer.parseInt(year.getText());
		outputArea.setText("looking up record ...");
		String message = myAthletes.lookupEntry(d, m, y);
		return message;
	}

	/*
	 * mehtod that takes in input through text field
	 * looks up all entries for given day, month, year
	 * */
	public String lookupEntries() {
		int m = Integer.parseInt(month.getText());
		int d = Integer.parseInt(day.getText());
		int y = Integer.parseInt(year.getText());
		outputArea.setText("looking up record ...");
		String message = myAthletes.lookupEntries(d, m, y);
		return message;
	}

	/*
	 * mehtod that takes in input through text field
	 * removes any entry for given day, month, year
	 * */
	public String removeEntry() {
		String n = name.getText();
		int m = Integer.parseInt(month.getText());
		int d = Integer.parseInt(day.getText());
		int y = Integer.parseInt(year.getText());
		outputArea.setText("looking up record ...");
		String message = myAthletes.removeEntry(n, d, m, y);
		return message;
	}

	public void blankDisplay() {
		name.setText("");
		day.setText("");
		month.setText("");
		year.setText("");
		hours.setText("");
		mins.setText("");
		secs.setText("");
		dist.setText("");

	}// blankDisplay
		// Fills the input fields on the display for testing purposes only

	public void fillDisplay(Entry ent) {
		name.setText(ent.getName());
		day.setText(String.valueOf(ent.getDay()));
		month.setText(String.valueOf(ent.getMonth()));
		year.setText(String.valueOf(ent.getYear()));
		hours.setText(String.valueOf(ent.getHour()));
		mins.setText(String.valueOf(ent.getMin()));
		secs.setText(String.valueOf(ent.getSec()));
		dist.setText(String.valueOf(ent.getDistance()));
	}

} // TrainingRecordGUI

