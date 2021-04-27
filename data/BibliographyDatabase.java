package data;
import java.util.ArrayList;

public class BibliographyDatabase {
  private boolean inProgress;
  private ArrayList<Journal> journals;
  
  public BibliographyDatabase(ArrayList<Journal> newJournals) {
    journals = newJournals;
    System.out.println(newJournals.size());
    inProgress = false;
  }
  
  public void startDatabase() {
    inProgress = true;
  }

  public void stopDatabase() {
    inProgress = false;
  }

  public boolean isFinished() {
    return !inProgress;
  }

  public ArrayList<Article> getArticlesFromYear(int year) {
    ArrayList<Article> filteredArticles = new ArrayList<Article>();
    for (Journal j : journals) {
      filteredArticles.addAll(j.getArticlesFromYear(year));
    }
    return filteredArticles;
  }

  public ArrayList<Issue> getAvailableIssues() {
    ArrayList<Issue> availableIssues = new ArrayList<Issue>();
    for (Journal j : journals) {
      availableIssues.addAll(j.getAvailableIssues());
    }
    return availableIssues;
  }

  public void addIssueToJournal(int newYear, int newVolNum, int newIssueNum, Journal journalAddTo) {
    Issue newIssue = new Issue(newYear, newVolNum, newIssueNum, journalAddTo);
    journalAddTo.addIssue(newIssue);
  }

  public ArrayList<Article> getAvailableArticles() {
    ArrayList<Article> availableArticles = new ArrayList<Article>();
    for (Journal j : journals) {
      availableArticles.addAll(j.getAvailableArticles());
    }
    return availableArticles;
  }

  public void addArticleToIssue(String newArticleTitle, String newArticleAuthor, Issue issueAddTo) {
    Article newArticle = new Article(newArticleTitle, newArticleAuthor);
    issueAddTo.addArticle(newArticle, issueAddTo);
    newArticle.setIssue(issueAddTo);
  }

  public void addJournal(Journal newJournal) {
    journals.add(newJournal);
  }

  public void addJournals(ArrayList<Journal> newJournals) {
    journals.addAll(newJournals);
  }
}
