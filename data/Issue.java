package data;
import java.util.ArrayList;

public class Issue {
  private int year;
  private int volNum;
  private int issueNum;

  private ArrayList<Article> articles;
  private Journal journal; 

  public Issue(int newYear, int newVolNum, int newIssueNum, Journal journalRef) {
    year = newYear;
    volNum = newVolNum;
    issueNum = newIssueNum;
    journal = journalRef;
    articles = new ArrayList<Article>();
  }

  public void addArticle(Article newArticle, Issue toIssue) {
    articles.add(newArticle);
    newArticle.setIssue(toIssue);
  }

  public ArrayList<Article> getArticles() {
    return articles;
  }

  public int getYear() {
    return year;
  }

  public String toStringShort() {
    return journal.toString() + ", " + volNum + "(" + issueNum + "), " + year + ".";
  }

  public String toStringLong() {
    return journal.toString() + ", Volume " + volNum + ", Issue " + issueNum; 
  }
}
