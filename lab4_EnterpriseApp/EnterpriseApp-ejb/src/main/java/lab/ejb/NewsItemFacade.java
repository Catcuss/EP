package lab.ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

@Stateless
public class NewsItemFacade implements NewsItemFacadeLocal {

    @PersistenceContext
    private EntityManager em;


    public List<NewsItem> getAllNewsItems() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<NewsItem> cq = cb.createQuery(NewsItem.class);
        Root<NewsItem> rootEntry = cq.from(NewsItem.class);
        CriteriaQuery<NewsItem> ct = cq.select(rootEntry);
        TypedQuery<NewsItem> allNewsItemsQuery = em.createQuery(ct);
        return allNewsItemsQuery.getResultList();
    }
}