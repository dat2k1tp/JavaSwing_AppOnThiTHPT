
package DAO;

import java.sql.Connection;
import java.util.List;

public interface EntityDAOimp<E,K> {
    public void insert(E entity,Connection con);
    public void update(E entity,Connection con);
    public void delete(E entity,Connection con);
    public List<E>selectAll(Connection con);
    public E selectByID(K key,Connection con);
    
}
