package dao;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import model.Book;
import model.BookDTO;
import model.User;

@Repository
public class BookDAOIpm implements BookDAO {

	/*******************************************************/

	@Value("${upload.file.extensions}")
	private String validExtensions;

	// get extension of image

	public String getFileExtension(String fileName) {
		int dotIndex = fileName.lastIndexOf(".");
		if (dotIndex < 0) {
			return null;
		}
		return fileName.substring(dotIndex + 1);
	}

	// check valid extension of image (valid image extension: jpg, jpeg, png)

	public boolean isValidExtension(String fileName) {
		String fileExtension = getFileExtension(fileName);
		if (fileExtension == null) {
			return false;
		}
		fileExtension = fileExtension.toLowerCase();
		for (String validExtension : validExtensions.split(",")) {
			if (fileExtension.equals(validExtension)) {
				return true;
			}
		}
		return false;
	}

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	/*******************************************************/
	@Transactional
	@Override
	public void addBook(MultipartFile multipartFile, Book book, String uploadDirectory) {
		try {
			// book.setImageName(multipartFile.getOriginalFilename());
			String imageName = book.getUserId().getUserId() + book.getBookName() + "."
					+ getFileExtension(multipartFile.getOriginalFilename());
			imageName = imageName.replaceAll("\\s", "");
			Path path = Paths.get(uploadDirectory, imageName);
			Files.copy(multipartFile.getInputStream(), path);
			// book.setImage(multipartFile.getBytes());
			Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
			session.getTransaction().begin();
			book.setImageName(imageName);
			session.save(book);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*******************************************************/

	@Override
	public List<BookDTO> listBook() {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
		Root<Book> root = criteriaQuery.from(Book.class);
		criteriaQuery.select(root);
		Query<Book> query = session.createQuery(criteriaQuery);
		List<Book> listBook = query.getResultList();
		List<BookDTO> listBookDTO = new ArrayList<BookDTO>();
		ModelMapper modelMapper = new ModelMapper();
		for (Book book : listBook) {
			BookDTO bookDTO = new BookDTO();
			modelMapper.map(book, bookDTO);
			bookDTO.setUsername(book.getUserId().getUsername());
			bookDTO.setAddress(book.getUserId().getAddress());
			listBookDTO.add(bookDTO);
		}
		return listBookDTO;
	}

	/*******************************************************/

	@Override
	public void deleteBook(int id) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		session.getTransaction().begin();
		Book book = session.byId(Book.class).load(id);
		session.delete(book);
		session.getTransaction().commit();
		session.close();
	}

	/*******************************************************/

	@Override
	public void updateBook(int id, Book book) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		session.getTransaction().begin();
		Book bookUpdate = session.byId(Book.class).load(id);
		if (book.getBookName() != null) {
			bookUpdate.setBookName(book.getBookName());
		}
		if (book.getBookAuthor() != null) {
			bookUpdate.setBookAuthor(book.getBookAuthor());
		}
		if (book.getBookDescription() != null) {
			bookUpdate.setBookDescription(book.getBookDescription());
		}
		session.getTransaction().commit();
	}

	/*******************************************************/

	@Override
	public Book getBookId(int id) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		Book book = session.get(Book.class, id);
		return book;
	}

	@Override
	public List<Book> getBookByUser(User user) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
		Root<Book> root = criteriaQuery.from(Book.class);
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("userId"), user.getUserId()));
		Query<Book> query = session.createQuery(criteriaQuery);
		List<Book> bookByUser = query.getResultList();
		return bookByUser;
	}

	/*******************************************************/

}
