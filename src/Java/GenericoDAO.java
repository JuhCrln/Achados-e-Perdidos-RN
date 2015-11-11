package Java;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;

public class GenericoDAO<PK, T> implements InterfaceDAO<PK, T> {

	private EntityManager entityManager;

	public GenericoDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void adiciona(T entity) {
		entityManager.persist(entity);
	}

	public void substitui(T entity) {
		entityManager.merge(entity);
	}

	public void remove(T entity) {
		entityManager.remove(entity);
	}

	@SuppressWarnings("unchecked")
	public List<T> busca() {
		return entityManager.createQuery(("FROM " + getTypeClass().getName())).getResultList();
	}

	private Class<?> getTypeClass() {
		Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
		return clazz;
	}

	@Override
	public List<T> busca(String field, String keyword) {
		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search
				.getFullTextEntityManager(entityManager);

		// create native Lucene query unsing the query DSL
		// alternatively you can write the Lucene query using the Lucene query
		// parser
		// or the Lucene programmatic API. The Hibernate Search DSL is
		// recommended though
		QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(getTypeClass()).get();
		org.apache.lucene.search.Query luceneQuery = qb.keyword().onFields(field)
				.matching(keyword).createQuery();

		// wrap Lucene query in a javax.persistence.Query
		javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, getTypeClass());

		// execute search
		List<T> resultList = jpaQuery.getResultList();
		return resultList;
	}
}
