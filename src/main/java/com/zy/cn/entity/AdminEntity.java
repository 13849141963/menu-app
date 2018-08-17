package com.zy.cn.entity;
import java.io.Serializable;
/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-08-10 23:20:23
 */
public class AdminEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	private Integer id;
	/**
	 *
	 */
	private String name;
	/**
	 *
	 */
	private String password;
	/**
	 *
	 */
	private String salt;
	/**
	 *
	 */
	private String email;
	/**
	 *
	 */
	private String mobile;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}
	/**
	 * 获取：
	 */
	public String getSalt() {
		return salt;
	}
	/**
	 * 设置：
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：
	 */
	public String getMobile() {
		return mobile;
	}
}
