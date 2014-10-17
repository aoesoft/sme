package aoesoft.sme.order.po;

import aoesoft.sme.util.annotation.Column;
import aoesoft.sme.util.annotation.Key;
import aoesoft.sme.util.annotation.Table;
import aoesoft.sme.util.dao.AbsDao;

@Table("tf_company")
public class Company extends AbsDao<Company>{
	@Key
	@Column(value="TID")
	private int id;
	private String name;
	private String address;
	private String tel;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
