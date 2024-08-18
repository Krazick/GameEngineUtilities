package checksum;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class CellColorRenderer extends JLabel implements TableCellRenderer {
	private static final long serialVersionUID = 1L;
    public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer ();
	boolean isBordered;
	int firstChecksumColumn;
	
	public CellColorRenderer (int aFirstChecksumColumn) {
		this (false, aFirstChecksumColumn);
	}
	
	public CellColorRenderer (boolean aIsBordered, int aFirstChecksumColumn) {
        isBordered = aIsBordered;
        setOpaque (true); //MUST do this for background to show up.
        setFirstChecksumColumn (aFirstChecksumColumn);
    }
 
	public void setFirstChecksumColumn (int aFirstChecksumColumn) {
		firstChecksumColumn = aFirstChecksumColumn;
	}
	
    @Override
    public Component getTableCellRendererComponent (JTable aTable, Object aValue,
            boolean aIsSelected, boolean aHasFocus, int aRow, int aColumn) {
    		int tFirstChecksumIndex;
    		int tLastChecksumIndex;
    		int tColumnIndex;
    		String tChecksumValue;
    		String tFirstChecksumValue;
    		Component tCellRenderer;
    		TableModel tTableModel;
    		boolean tIdenticalChecksums;
    		
       	tIdenticalChecksums = true;
       	tFirstChecksumValue = null;
		tFirstChecksumIndex = firstChecksumColumn;
		tCellRenderer = DEFAULT_RENDERER.getTableCellRendererComponent (aTable, aValue, aIsSelected, aHasFocus, 
							aRow, aColumn);
  		if (aColumn >= tFirstChecksumIndex) {
	    		tTableModel = aTable.getModel ();
	    		tLastChecksumIndex = tTableModel.getColumnCount () - 1;
	    		while ((tFirstChecksumValue == null) && (tFirstChecksumIndex < tLastChecksumIndex)) {
	    			tFirstChecksumValue = (String) tTableModel.getValueAt (aRow, tFirstChecksumIndex++);
	    		}
	
	    		if (tFirstChecksumValue != null) {
	    			tFirstChecksumIndex = firstChecksumColumn;
		     	for (tColumnIndex = tFirstChecksumIndex; tColumnIndex <= tLastChecksumIndex; tColumnIndex++) {
		     		tChecksumValue = (String) tTableModel.getValueAt (aRow, tColumnIndex);
		     		if (! tFirstChecksumValue.equals (tChecksumValue)) {
		     			tIdenticalChecksums = false;
		     		}
		    		}
	    		}
       	}
   		if (tIdenticalChecksums) {
  			tCellRenderer.setBackground (Color.WHITE);
  		} else {
 			tCellRenderer.setBackground (Color.YELLOW);
  		}
	 
        return tCellRenderer;
    }
}
