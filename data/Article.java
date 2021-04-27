package data;

public class Article {
  private String title;
  private String author;
  
  private Issue issue;

  public Article(String newTitle, String newAuthor) {
    title = newTitle;
    author = newAuthor;
    issue = null;
  }

  public Article(String newTitle, String newAuthor, Issue issueRef) {
    title = newTitle;
    author = newAuthor;
    issue = issueRef;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }
  
  public void setIssue(Issue issueRef) {
    issue = issueRef;
  }

  public String toString() {
    return author + ". " + title + ".";
  }

  public String printIssueShort() {
    if (issue != null) {
      return issue.toStringShort();
    } else {
      return "";
    }
  }

  public String printIssueLong() {
    return issue.toStringLong();
  }
}
