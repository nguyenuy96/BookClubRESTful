package dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import model.Book;
import model.BookDTO;
import model.User;
public interface BookDAO {
	void addBook(MultipartFile multipartFile , Book book, String uploadDirectory );
	List<BookDTO> listBook();
	void deleteBook(int id);
	void updateBook(int id, Book book);
	Book getBookId(int id);
	List<Book> getBookByUser(User user);
}
