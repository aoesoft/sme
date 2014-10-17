package aoesoft.sme.order.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import aoesoft.sme.order.po.Contact;

/**
 * 联系人.
 *
 */
public class ContactAdd {
	private Text txtName;
	private Text txtTel;
	private Text txtEmail;
	private Text txtQQ;
	private Text txtCName;
	private Text txtCAddress;
	private Text txtCTel;
	private Button btnOk;
	private Button btnCancel;
	private Button btnTitleIco;
	private Composite container;
	
	public ContactAdd(Composite parent){
		this.container = parent;
	}
	
	public void draw(){
		if(container == null)
			return;
		
		container.setBounds(10, 10, 477, 313);
		
		Group group = new Group(container, SWT.NONE);
		group.setText("公司信息");
		group.setBounds(10, 175, 457, 128);
		
		Label label_3 = new Label(group, SWT.NONE);
		label_3.setText("公司名称");
		label_3.setBounds(40, 30, 61, 17);
		
		txtCName = new Text(group, SWT.BORDER);
		txtCName.setBounds(107, 27, 184, 23);
		
		Label label_4 = new Label(group, SWT.NONE);
		label_4.setText("公司地址");
		label_4.setBounds(40, 57, 61, 17);
		
		txtCAddress = new Text(group, SWT.BORDER);
		txtCAddress.setBounds(107, 53, 184, 23);
		
		Label label_5 = new Label(group, SWT.NONE);
		label_5.setText("公司电话");
		label_5.setBounds(40, 86, 61, 17);
		
		txtCTel = new Text(group, SWT.BORDER);
		txtCTel.setBounds(107, 82, 184, 23);
		
		Button btnNewButton = new Button(group, SWT.NONE);
		btnNewButton.setBounds(307, 24, 129, 27);
		btnNewButton.setText("...选择公司");
		
		Label label = new Label(container, SWT.NONE);
		label.setAlignment(SWT.RIGHT);
		label.setBounds(18, 8, 40, 17);
		label.setText("姓名");
		
		txtName = new Text(container, SWT.BORDER);
		txtName.setBounds(72, 6, 176, 23);
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setAlignment(SWT.RIGHT);
		label_1.setBounds(18, 36, 40, 17);
		label_1.setText("手机号");
		
		txtTel = new Text(container, SWT.BORDER);
		txtTel.setBounds(72, 35, 176, 23);
		
		txtEmail = new Text(container, SWT.BORDER);
		txtEmail.setBounds(72, 67, 176, 23);
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setText("邮箱");
		label_2.setAlignment(SWT.RIGHT);
		label_2.setBounds(18, 68, 40, 17);
		
		Label lblQq = new Label(container, SWT.NONE);
		lblQq.setAlignment(SWT.RIGHT);
		lblQq.setBounds(18, 103, 40, 17);
		lblQq.setText("QQ");
		
		txtQQ = new Text(container, SWT.BORDER);
		txtQQ.setBounds(72, 101, 176, 23);
		
		btnTitleIco = new Button(container, SWT.NONE);
		btnTitleIco.setText("头像");
		btnTitleIco.setBounds(273, 10, 173, 112);
		
		btnOk = new Button(container, SWT.NONE);
		btnOk.setBounds(178, 365, 80, 27);
		btnOk.setText("确定");
		
		btnCancel = new Button(container, SWT.NONE);
		btnCancel.setBounds(286, 365, 80, 27);
		btnCancel.setText("取消");
		
		registeListener();
	}

	private void registeListener() {
		btnTitleIco.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(container.getShell());
				dialog.setText("选择头像图片");
				dialog.setFilterExtensions(new String[]{"*.jpg", "*.jpeg", "*.png", "*.gif", "*.bmp"});
				String ico = dialog.open();
				btnTitleIco.setData("path", ico);
				if(ico != null){
					btnTitleIco.setImage(new Image(null, ico));
				}
			}
		});
		
		
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Contact c = new Contact();
				c.setName(txtName.getText());
				c.setMail(txtEmail.getText());
				c.setQq(txtQQ.getText());
				c.setTel(txtTel.getText());
				c.setIco(btnTitleIco.getData("path").toString());
				System.out.println(c);
			}
		});
		
		
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				container.dispose();
			}
		});
	}
}
