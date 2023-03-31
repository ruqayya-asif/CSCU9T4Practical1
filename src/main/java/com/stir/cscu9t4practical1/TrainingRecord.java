// An implementation of a Training Record as an ArrayList
package com.stir.cscu9t4practical1;




import java.util.*;


public class TrainingRecord {
    private List<Entry> tr;
    
    public TrainingRecord() {
        tr = new ArrayList<Entry>();
    } //constructor
    
    // add a record to the list
	public void addEntry(Entry e) {
		boolean isUnique = true;
		ListIterator<Entry> iter = tr.listIterator();
		while (iter.hasNext()) {
			Entry current = iter.next();
			//check if the record has never been added before
			if (current.getName().equals(e.getName()) && current.getDay() == e.getDay()
					&& current.getMonth() == e.getMonth() && current.getYear() == e.getYear()) {
				isUnique = false;
				return;
			}
		}
		if (isUnique) {
			tr.add(e);
		}

	} // addClass

	// look up the entry of a given day and month
	public String lookupEntry(int d, int m, int y) {
		ListIterator<Entry> iter = tr.listIterator();
		String result = "No entries found";
		while (iter.hasNext()) {
			Entry current = iter.next();
			if (current.getDay() == d && current.getMonth() == m && current.getYear() == y)
				result = current.getEntry();
		}
		return result;
	} // lookupEntry

	//look up all the entries of a given day, month and year
	public String lookupEntries(int d, int m, int y) {
		ListIterator<Entry> iter = tr.listIterator();
		String result = "Sorry couldn't find anything for this date";
		StringBuffer sb = new StringBuffer();
		while (iter.hasNext()) {
			Entry current = iter.next();
			if (current.getDay() == d && current.getMonth() == m && current.getYear() == y) {
				sb.append(current.getEntry());
				result = sb.toString();
			}
		}
		return result;
	} // lookupEntry

	//remove entry of a given name, day, month and year
	public String removeEntry(String n, int d, int m, int y) {
		ListIterator<Entry> iter = tr.listIterator();
		String result = "No such entry found";
		while (iter.hasNext()) {
			Entry current = iter.next();
			if (current.getName() == n && current.getDay() == d && current.getMonth() == m && current.getYear() == y) {
				iter.remove();
				result = "Entry removed";
			}
		}
		return result;
	} //removeEntry
   
   // Count the number of entries
   public int getNumberOfEntries(){
       return tr.size();
   }
   // Clear all entries
   public void clearAllEntries(){
       tr.clear();
   }
   
} // TrainingRecord
