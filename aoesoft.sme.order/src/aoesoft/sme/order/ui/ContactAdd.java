package aoesoft.sme.order.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * ��ϵ������.
 *
 */
public class ContactAdd {
	private Composite container;
	
	public ContactAdd(Composite parent){
		this.container = parent;
	}
	
	public void draw(){
		if(container == null)
			return;
		
		container.setLayout(new GridLayout(4, false));
		
		Label lblName = new Label(container, SWT.NONE);
		lblName.setText("����");
		
		Text txtName = new Text(container, SWT.NONE);
	}
}
