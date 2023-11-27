package swingDelays;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class KButton extends JButton implements ActionListener {
	private static final long serialVersionUID = 1L;
	ActionListener userActionListener;
	long previousTime;
	long eventDelay;
	int eventCount;
	int fullCount;
	
	@Override
	public void addActionListener (ActionListener aActionListener) {
		long tTime;
		long tDelay;
		
		tDelay = 500;
		tTime = System.currentTimeMillis ();
		super.addActionListener (this);
		setActionListener (aActionListener);
		setPreviousTime (tTime);
		setEventDelay (tDelay);
		setEventCount (0);
	}
	
	public void setEventDelay (long aEventDelay) {
		eventDelay = aEventDelay;
	}

	private void setPreviousTime (long aPreviousTime) {
		previousTime = aPreviousTime;
	}
	
	private void setActionListener (ActionListener aUserActionListener) {
		userActionListener = aUserActionListener;
	}
	
	public void setEventCount (int aEventCount) {
		eventCount = aEventCount;
		fullCount = aEventCount;
	}
	
	public int getFullCount () {
		return fullCount;
	}
	
	public int getEventCount () {
		return eventCount;
	}
	
	@Override
	public void removeActionListener (ActionListener aActionListener) {
		if (aActionListener == userActionListener) {
			userActionListener = null;
		}
	}

	@Override
	public void actionPerformed (ActionEvent aActionEvent) {
		long tTime;
		
		if (userActionListener != null) {
			tTime = System.currentTimeMillis ();
			fullCount++;
			if ((tTime - previousTime) > eventDelay) {
				eventCount++;
				userActionListener.actionPerformed (aActionEvent);
				setPreviousTime (tTime);
			}
		}
		
	}
}
