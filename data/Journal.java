package data;
import java.util.ArrayList;

public class Journal {
  private String title;
  private ArrayList<Issue> issues;

  public Journal(String newTitle) {
    title = newTitle;
    issues = new ArrayList<Issue>();
  }

  public void addIssue(Issue newIssue) {
    issues.add(newIssue);
  }

  public String toString() {
    return title;
  }

  public ArrayList<Issue> getAvailableIssues() {
    return issues;
  }
  
  public ArrayList<Article> getAvailableArticles() {
    ArrayList<Article> availableArticles = new ArrayList<Article>();
    for (Issue i : issues) {
      availableArticles.addAll(i.getArticles());
    }
    return availableArticles;
  }

  public ArrayList<Article> getArticlesFromYear(int year) {
    ArrayList<Article> filteredArticles = new ArrayList<Article>();
    for (Issue i : issues) {
      if (i.getYear() == year) {
        filteredArticles.addAll(i.getArticles());
      }
    }
    return filteredArticles;
  }

  public static ArrayList<Journal> getSampleJournals() {
    Article art1 = new Article("title1", "author1");
    Article art2 = new Article("title2", "author2");

    Article art3 = new Article("title3", "author3");
    Article art4 = new Article("title4", "author4");

    Article art5 = new Article("title5", "author5");
    Article art6 = new Article("title6", "author6");

    Article art7 = new Article("title7", "author7");
    Article art8 = new Article("title8", "author8");

    Journal journal1 = new Journal("journal1");

    Issue iss1 = new Issue(2000, 1, 1, journal1);
    Issue iss2 = new Issue(2010, 2, 1, journal1);

    iss1.addArticle(art1, iss1);
    iss1.addArticle(art2, iss1);

    iss2.addArticle(art3, iss2);
    iss2.addArticle(art4, iss2);

    journal1.addIssue(iss1);
    journal1.addIssue(iss2);

    Journal journal2 = new Journal("journal2");

    Issue iss3 = new Issue(2015, 3, 1, journal2);
    Issue iss4 = new Issue(2020, 4, 1, journal2);

    iss3.addArticle(art5, iss3);
    iss3.addArticle(art6, iss3);

    iss4.addArticle(art7, iss4);
    iss4.addArticle(art8, iss4);

    journal2.addIssue(iss3);
    journal2.addIssue(iss4);

    ArrayList<Journal> journals = new ArrayList<Journal>(); 
    journals.add(journal1);
    journals.add(journal2);

    return journals;
  }
}
