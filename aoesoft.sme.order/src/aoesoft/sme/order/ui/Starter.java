package aoesoft.sme.order.ui;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Starter {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		
		
		
		shell.open();
		while(!shell.isDisposed())
			if(!display.readAndDispatch())display.sleep();
		shell.dispose();
	}
}
