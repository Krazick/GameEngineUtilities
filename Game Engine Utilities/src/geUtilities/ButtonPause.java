package geUtilities;

import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import geUtilities.xml.XMLFrame;
import swingTweaks.KButton;

public class ButtonPause {	
	public ButtonPause (XMLFrame aFrame, String aActionInfo) {
        aFrame.toFront ();
        preparePause (aFrame, aActionInfo);
	}
	
    public void preparePause (JFrame aFrame, String aActionInfo) {
    	JLabel tJLabel1;
    	JLabel tJLabel2;
        JDialog tJDialog;
        KButton tCloseButton;
        
        tJDialog = new JDialog (aFrame, "PAUSE", true);
        
        tJDialog.setSize (270, 100);
        tJDialog.setLayout (new FlowLayout ());
        tJLabel1 = new JLabel (aActionInfo);
        tJLabel2 = new JLabel ("Hit Continue when ready");
        tCloseButton = new KButton ("Continue");
        // Attach an action listener to handle what happens *after* the click
        tCloseButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed (ActionEvent aActionEvent) {
            	System.out.println ("Continuing operations");
            	tJDialog.setVisible (false);
            }
        });

        tJDialog.add (tJLabel1);
        tJDialog.add (tJLabel2);
        tJDialog.add (tCloseButton);
        tJDialog.setVisible (true);
        tJDialog.setModalityType (Dialog.ModalityType.APPLICATION_MODAL);
    }
}
