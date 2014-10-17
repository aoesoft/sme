package aoesoft.sme.order.po;

import aoesoft.sme.util.annotation.Column;
import aoesoft.sme.util.annotation.Key;
import aoesoft.sme.util.annotation.Table;
import aoesoft.sme.util.dao.AbsDao;
import aoesoft.sme.util.dao.DaoManager;
import aoesoft.sme.util.dao.MySqlDialect;
import aoesoft.sme.util.dao.OracleDialect;

@Table("tf_contact")
public class Contact extends AbsDao{
	@Key
	@Column(type="int", value="ID")
	private int id;
	@Column("NAME")
	@Key
	private String name;
	private String nameStarter;
	private String namePingyin;

	@Column("PHONE") private String tel;
	@Column("MAIL") private String mail;
	private String qq;
	private String ico;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameStarter() {
		return nameStarter;
	}

	public String getNamePingyin() {
		return namePingyin;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getIco() {
		return ico;
	}

	public void setIco(String ico) {
		this.ico = ico;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Contact [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", nameStarter=");
		builder.append(nameStarter);
		builder.append(", namePingyin=");
		builder.append(namePingyin);
		builder.append(", tel=");
		builder.append(tel);
		builder.append(", mail=");
		builder.append(mail);
		builder.append(", qq=");
		builder.append(qq);
		builder.append(", ico=");
		builder.append(ico);
		builder.append("]");
		return builder.toString();
	}

	
	//-----------------------------------------
	public static void main(String[] args) {
		DaoManager.setDialect(new MySqlDialect());
		Contact c = new Contact();
		c.id = 123;
		c.name = "tangxc";
		c.mail = "2785@qq.com";
		c.add();
		
		c.selectAll();
		c.delete();
		c.deleteAll();
		
		Contact c2 = new Contact();
		c2.id = 555;
		c.batchDelete(c, c2);
		c.update();
		c.selectPage(0, 5);
		
		Company com = new Company();
		com.setId(777);
		com.add();
		com.delete();
		com.selectAll();
		com.selectPage(10, 10);
	}
}
