package lab.ejb;


import jakarta.ejb.Local;

import java.util.List;

@Local
public interface NewsItemFacadeLocal {
    public List<NewsItem> getAllNewsItems();
}