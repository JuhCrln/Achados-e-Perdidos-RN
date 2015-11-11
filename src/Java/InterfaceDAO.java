package Java;

import java.util.List;

public interface InterfaceDAO<PK, T> {
	public void adiciona(T entity);
    public void substitui(T entity);
    public void remove(T entity);
    public List<T> busca();
    public List<T> busca(String field, String keyword);
}
