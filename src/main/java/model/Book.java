package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
//import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "book")
public class Book {

	/**
	 * 
	 */

	/*******************************************************/

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookId", nullable = false, unique = true)
	private int bookId;

	/*******************************************************/

	@Column(name = "bookName", nullable = false, length = 200)
	private String bookName;

	/*******************************************************/

	@Column(name = "bookAuthor")
	private String bookAuthor;

	/*******************************************************/

	@Column(name = "bookDescription", length = 1000)
	private String bookDescription;

	/*******************************************************/
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false)
	private User userId;

	/*******************************************************/
	@Column(name = "datePost")
	private String datePost = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());

	/*******************************************************/

	@Column(name = "imageName")
	private String imageName;

	/*******************************************************/
//	@Lob
//	@Column(name = "image")
//	private byte[] image;

	/*******************************************************/

	public Book() {
	}
	
	public Book(int bookid) {
		this.bookId = bookid;
	}

	public Book(int bookid, String bookName, String bookAuthor, String bookDescription, User userId, String datePost,
			String imageName/*, byte[] image*/) {
		this.bookId = bookid;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookDescription = bookDescription;
		this.userId = userId;
		this.datePost = datePost;
		this.imageName = imageName;
//		this.image = image;
	}

	/*******************************************************/

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	/*******************************************************/

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	/*******************************************************/

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	/*******************************************************/

	public String getBookDescription() {
		return bookDescription;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}

	/*******************************************************/

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	/*******************************************************/

	public String getDatePost() {
		return datePost;
	}

	public void setDatePost(String datePost) {
		this.datePost = datePost;
	}

	/*******************************************************/

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	/*******************************************************/

//	public byte[] getImage() {
//		return image;
//	}
//
//	public void setImage(byte[] image) {
//		this.image = image;
//	}

	/*******************************************************/
	
}
