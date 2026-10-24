package swingTweaks;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import geUtilities.GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * This "Collapsible" JPanel extension makes all of the contents of the JPanel invisible
 * Which then does shrink the size of the Panel itself down to zero width/height
 * It is not visible. The problem is since it has no size, there is no place to click on
 * to return the panel to the original size. 
 * Unless a different object (button most likely) is shown to restore the panel, is made visible
 * when the panel contents are invisible, this is not very useful.
 */
public class KCollapsiblePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private TitledBorder border;
	private String title = "Title";
	private JLabel panelJLabel;
	private boolean collapsable;
	private Component invisibleStrut;
	 
    public KCollapsiblePanel () {
        border = BorderFactory.createTitledBorder (title);
        setBorder (border);
        BorderLayout borderLayout = new BorderLayout ();
        setLayout (borderLayout);
        addMouseListener (mouseListener);
        setCollapsable (true);
        invisibleStrut = Box.createHorizontalStrut (300);
        invisibleStrut.setVisible (false);
        super.add (invisibleStrut);
        panelJLabel = new JLabel (title);
        super.add (panelJLabel);
    }
 
    MouseListener mouseListener = new MouseAdapter () {
        @Override
        public void mouseClicked (MouseEvent eMouseEvent) {
            toggleVisibility ();
            System.out.println ("Mouse Clicked on Collapsable Panel");
        }
    };
 
    ComponentListener contentComponentListener = new ComponentAdapter () {
        @Override
        public void componentShown (ComponentEvent eComponentEvent) {
            updateBorderTitle ();
        }
        @Override
        public void componentHidden (ComponentEvent eComponentEvent) {
            updateBorderTitle ();
        }
    };
 
    public void setCollapsable (boolean aCollapsable) {
    	collapsable = aCollapsable;
    }
    
    public boolean isCollapsable () {
    	return collapsable;
    }
    
    public String getTitle () {
        return title;
    }
 
    public void setTitle (String aTitle) {
        firePropertyChange ("title", title, title = aTitle);
        panelJLabel.setText (title);
    }
 
    @Override
    public Component add (Component aComponent) {
    	Component tComponent;
    	
       aComponent.addComponentListener (contentComponentListener);
       tComponent = super.add (aComponent);
       updateBorderTitle ();
        
       return tComponent;
    }
 
    @Override
    public Component add (String aName, Component aComponent) {
    	Component tComponent;
    	
        aComponent.addComponentListener (contentComponentListener);
        tComponent = super.add (aName, aComponent);
        updateBorderTitle ();
        
        return tComponent;
    }
 
    @Override
    public Component add (Component aComponent, int aIndex) {
       	Component tComponent;

        aComponent.addComponentListener (contentComponentListener);
        tComponent = super.add (aComponent, aIndex);
        updateBorderTitle ();
        
        return tComponent;
    }
 
    @Override
    public void add (Component aComponent, Object aConstraints) {
        aComponent.addComponentListener (contentComponentListener);
        super.add (aComponent, aConstraints);
        updateBorderTitle ();
    }
 
    @Override
    public void add (Component aComponent, Object aConstraints, int aIndex) {
        aComponent.addComponentListener (contentComponentListener);
        super.add (aComponent, aConstraints, aIndex);
        updateBorderTitle ();
    }
 
    @Override
    public void remove (int aIndex) {
      	Component tComponent;

        tComponent = getComponent (aIndex);
        tComponent.removeComponentListener (contentComponentListener);
        super.remove (aIndex);
    }
 
    @Override
    public void remove (Component aComponent) {
        aComponent.removeComponentListener (contentComponentListener);
        super.remove (aComponent);
    }
 
    @Override
    public void removeAll () {
        for (Component tComponent : getComponents ()) {
        	tComponent.removeComponentListener (contentComponentListener);
        }
        super.removeAll ();
    }
 
    protected void toggleVisibility () {
        toggleVisibility (hasInvisibleComponent ());
    }
 
	protected void toggleVisibility (boolean visible) {
    		if (isCollapsable ()) {
	    		for (Component c : getComponents ()) {
	        		if ((c == invisibleStrut) || (c == this.panelJLabel)) {
	                c.setVisible (visible);
	            } else {
	                c.setVisible (!visible);
	            }
	        }
	        updateBorderTitle ();
    		}
    }
 
    protected void updateBorderTitle () {
        String arrow = GUI.EMPTY_STRING;
        
        if (isCollapsable ()) {
	        if (getComponentCount () > 0) {
	            arrow = (hasInvisibleComponent ()?"▽":"△");
	        }
	        border.setTitle (title + " " + arrow);
	        repaint ();
        }
    }
 
    protected final boolean hasInvisibleComponent () {
        for (Component tComponent : getComponents ()) {
            if (!tComponent.isVisible ()) {
                return true;
            }
        }
        
        return false;
    }
}