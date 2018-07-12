package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import dao.BookDAO;
import model.Book;
import model.BookDTO;
import model.User;

@Service
@Transactional(propagation = Propagation.SUPPORTS ,readOnly=true)
public class BookServiceIpm implements BookService{
	
	@Autowired
	private BookDAO bookDAO;

	/*******************************************************/
	
	@Transactional
	@Override
	public void addBook(MultipartFile multipartFile, Book book, String uploadDirectory) {
		bookDAO.addBook(multipartFile, book, uploadDirectory);
	}
	
	/*******************************************************/
	
	@Transactional
	@Override
	public void updateBook(int id, Book book) {
		bookDAO.updateBook(id, book);
	}

	/*******************************************************/
	
	@Transactional
	@Override
	public void deleteBook(int id) {
		bookDAO.deleteBook(id);
	}

	/*******************************************************/
	
	@Override
	public List<BookDTO> listBook() {
		List<BookDTO> listBookDTO = bookDAO.listBook();
		return listBookDTO;
	}

	/*******************************************************/
	
	@Override
	public Book getBookId(int id) {
		Book book = bookDAO.getBookId(id);
		return book;
	}

	/*******************************************************/
	
	@Override
	public List<Book> getBookByUser(User user) {
		return bookDAO.getBookByUser(user);
	}
	
	/*******************************************************/
	


}
