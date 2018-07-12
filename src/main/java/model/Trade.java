package model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tradebook")
public class Trade implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*******************************************************/
	
	@Id
	@Column(name = "bookFrom")
	private int bookFrom;

	/*******************************************************/
	
	@Id
	@Column(name = "bookTo")
	private int bookTo;

	/*******************************************************/

	public Trade() {
		
	}
	
	public Trade(int bookFrom) {
		this.bookFrom = bookFrom;
	}
	
	public Trade(int bookFrom, int bookTo) {
		this.bookFrom = bookFrom;
		this.bookTo = bookTo;
	}
	
	/*******************************************************/
	
	public Integer getBookFrom() {
		return bookFrom;
	}

	public void setBookFrom(int bookFrom) {
		this.bookFrom = bookFrom;
	}

	/*******************************************************/
	
	public Integer getBookTo() {
		return bookTo;
	}

	public void setBookTo(int bookTo) {
		this.bookTo = bookTo;
	}

	/*******************************************************/

}
