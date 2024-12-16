package visualso.component;

import javax.swing.DefaultButtonModel;

public class FixedStateButtonModel extends DefaultButtonModel  {

	@Override
    public boolean isPressed() {
        return false;
    }
}
