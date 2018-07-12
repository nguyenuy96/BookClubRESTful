package services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import model.Book;
import model.BookDTO;
import model.User;

public interface BookService {
	void addBook(MultipartFile multipartFile, Book book, String uploadDirectory);
	void updateBook(int id, Book book);
	void deleteBook(int id);
	List<BookDTO> listBook();
	Book getBookId(int id);
	List<Book> getBookByUser(User user);
}
