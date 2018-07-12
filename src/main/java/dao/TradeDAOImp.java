package dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Book;
import model.Trade;
import model.User;

@Repository
public class TradeDAOImp implements TradeDAO {
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public void tradeBook(Trade trade) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		session.getTransaction().begin();
		Book bookFrom = session.get(Book.class, trade.getBookFrom());
		Book bookTo = session.get(Book.class, trade.getBookTo());
		User ownerFrom = bookFrom.getUserId();
		User ownerTo = bookTo.getUserId();
		bookFrom.setUserId(ownerTo);
		bookTo.setUserId(ownerFrom);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void addTrade(Trade trade) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		session.getTransaction().begin();
		session.save(trade);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<Trade> getAllTrade() {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Trade> criteriaQuery = criteriaBuilder.createQuery(Trade.class);
		Root<Trade> root = criteriaQuery.from(Trade.class);
		criteriaQuery.select(root).where(criteriaBuilder.and(criteriaBuilder.equal(root.get("bookFrom"), 1), criteriaBuilder.equal(root.get("bookTo"), 1)));
		Query<Trade> query = session.createQuery(criteriaQuery);
		List<Trade> listTrade = query.getResultList();
		return listTrade;
	}

	@Override
	public List<Trade> getWaitingTrade() {
		return null;
	}

	@Override
	public List<Trade> getHistoryTrade() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Trade getSingleTrade(Trade trade) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Trade> criteriaQuery = criteriaBuilder.createQuery(Trade.class);
		Root<Trade> root = criteriaQuery.from(Trade.class);
		criteriaQuery.select(root).where(criteriaBuilder.and(criteriaBuilder.equal(root.get("bookFrom"), trade.getBookFrom()), criteriaBuilder.equal(root.get("bookTo"), trade.getBookTo())));
		Query<Trade> query = session.createQuery(criteriaQuery);
		List<Trade> tradeFound = query.getResultList();
		Trade singleTrade = ((tradeFound == null) || (tradeFound.isEmpty())) ? null : tradeFound.get(0);
		return singleTrade;
	}
	

}
